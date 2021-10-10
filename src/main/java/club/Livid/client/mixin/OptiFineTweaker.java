package club.Livid.client.mixin;

import net.minecraft.launchwrapper.*;
import org.apache.logging.log4j.Level;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipFile;

public class OptiFineTweaker implements ITweaker {

    private static final Pattern optiPattern = Pattern.compile("x=[a-z0-9]{32}");

    private final List<String> launchArguments = new ArrayList<>();
    private File gameDir;
    private File assetsDir;
    private String profile;

    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        this.launchArguments.addAll(args);
        this.gameDir = gameDir;
        this.assetsDir = assetsDir;
        this.profile = profile;

        if (!args.contains("--version") && profile != null) {
            launchArguments.add("--version");
            launchArguments.add(profile);
        }

        if (!args.contains("--assetsDir") && assetsDir != null) {
            launchArguments.add("--assetsDir");
            launchArguments.add(assetsDir.getAbsolutePath());
        }

        if (!args.contains("--gameDir") && gameDir != null) {
            launchArguments.add("--gameDir");
            launchArguments.add(gameDir.getAbsolutePath());
        }
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        File optifineJar = new File(gameDir, "Livid/optifine.jar");

        if(!optifineJar.exists()) {
            try {
                new File(gameDir, "Livid").mkdirs();
                optifineJar.createNewFile();

                URL url = new URL("https://optifine.net/adloadx?f=OptiFine_1.8.9_HD_U_M5.jar");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux; rv:74.0) Gecko/20100101 Firefox/74.0");

                int status = con.getResponseCode();

                if(status == 200) {
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer content = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                    in.close();
                    con.disconnect();

                    Matcher m = optiPattern.matcher(content);

                    if(m.find()) {
                        String dlUrl = String.format("https://optifine.net/downloadx?f=OptiFine_1.8.9_HD_U_M5.jar&%s", m.group(0));

                        url = new URL(dlUrl);
                        con = (HttpURLConnection) url.openConnection();
                        con.setRequestMethod("GET");
                        con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux; rv:74.0) Gecko/20100101 Firefox/74.0");

                        status = con.getResponseCode();

                        if(status == 200) {
//                            BufferedReader in = new BufferedReader(
//                                    new InputStreamReader(con.getInputStream()));
//                            String inputLine;
//                            StringBuffer content = new StringBuffer();
//                            while ((inputLine = in.readLine()) != null) {
//                                content.append(inputLine);
//                            }
//                            in.close();
                            try (InputStream ins = con.getInputStream(); FileOutputStream out = new FileOutputStream(optifineJar))
                            {
                                byte[] b = new byte[1024];
                                int count;

                                while ((count = ins.read(b)) > 0)
                                {
                                    out.write(b, 0, count);
                                }
                            }

                            con.disconnect();
                        } else {
                            System.out.println("Unable to download OptiFine");
                            return;
                        }
                    } else {
                        System.out.println("Unable to download OptiFine");
                        return;
                    }
                } else {
                    System.out.println("Unable to download OptiFine");
                    return;
                }
            } catch(Exception e) {
                System.out.println("Unable to download OptiFine");
                e.printStackTrace();
                return;
            }
        }

        URLClassLoader loader = (URLClassLoader) classLoader;

        try {
            Method method = null;

            // hell yeah nested try-catch
            try {
                method = loader.getClass().getDeclaredMethod("addURL", URL.class);
            } catch(NoSuchMethodException e) {
                method = loader.getClass().getSuperclass().getDeclaredMethod("addURL", URL.class);
            }

            method.setAccessible(true);
            method.invoke(loader, optifineJar.toURI().toURL());
            System.out.println("added optifine to classpath");
        } catch (Throwable t) {
            System.out.println("no optifine :(");
            t.printStackTrace();

        }

        //classLoader.registerTransformer("optifine.OptiFineClassTransformer");

        try {
            ZipFile z = new ZipFile(optifineJar);

            IClassTransformer transformer = (IClassTransformer) classLoader.loadClass("optifine.OptiFineClassTransformer").newInstance();

            Field f = transformer.getClass().getDeclaredField("ofZipFile");
            f.setAccessible(true);
            f.set(transformer, z);

            Field f0 = transformer.getClass().getDeclaredField("patchMap");
            f0.setAccessible(true);

            Field f1 = transformer.getClass().getDeclaredField("patterns");
            f1.setAccessible(true);

            Class<?> cls = classLoader.loadClass("optifine.Patcher");
            Method m = cls.getDeclaredMethod("getConfigurationMap", ZipFile.class);
            m.setAccessible(true);

            Method m0 = cls.getDeclaredMethod("getConfigurationPatterns", Map.class);
            m0.setAccessible(true);

            f0.set(transformer, m.invoke(null, z));
            f1.set(transformer, m0.invoke(null, f0.get(transformer)));

            Field f111293 = classLoader.getClass().getDeclaredField("transformers");
            f111293.setAccessible(true);
            ArrayList<IClassTransformer> o = (ArrayList<IClassTransformer>) f111293.get(classLoader);
            o.add(transformer);
        } catch (Exception e) {
            LogWrapper.log(Level.ERROR, e, "A critical problem occurred registering the ASM transformer class %s", "optifine");
        }
    }

    @Override
    public String getLaunchTarget() {
        return "net.minecraft.client.main.Main";
    }

    @Override
    public String[] getLaunchArguments() {
        return launchArguments.toArray(new String[0]);
    }
}
