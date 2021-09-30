package club.Livid.client.utilities.file;

import javax.net.ssl.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Objects;

public class FileManager {

    static {
        disableSslVerification();
    }

    private File lividDir;
    private File fontDir;
    private File configDir;
    private File config;

    public FileManager() {
        lividDir = new File("Livid");
        fontDir = new File(lividDir, "fonts");
        configDir = new File(lividDir, "config");
        downloadFonts();


        if (!(new File(configDir, "ModuleConfiguration.lividconfigurationfiletype").exists())) {
            createConfigFile();
            config = new File(configDir, "ModuleConfiguration.lividconfigurationfiletype");
        }
    }

    public void createConfigFile() {
        File file = new File(configDir, "ModuleConfiguration.lividconfigurationfiletype");
        file.mkdir();
    }

    public void downloadFonts() {
        //https://submaryne.ct8.pl/Livid/fonts/ProductSansLight.ttf
        try {
            copyURLToFile(new URL("https://submaryne.ct8.pl/Livid/fonts/ProductSansLight.ttf"), new File(fontDir, "ProductSansLight.ttf"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void copyURLToFile(URL url, File file) {
        try {
            InputStream input = url.openStream();
            if (file.exists()) {
                if (file.isDirectory())
                    throw new IOException("File '" + file + "' is a directory");

                if (!file.canWrite())
                    throw new IOException("File '" + file + "' cannot be written");
            } else {
                File parent = file.getParentFile();
                if ((parent != null) && (!parent.exists()) && (!parent.mkdirs())) {
                    throw new IOException("File '" + file + "' could not be created");
                }
            }
            FileOutputStream output = new FileOutputStream(file);
            byte[] buffer = new byte[4096];
            int n = 0;
            while (-1 != (n = input.read(buffer))) {
                output.write(buffer, 0, n);
            }
            input.close();
            output.close();
            System.out.println("File '" + file + "' downloaded successfully!");
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
    }

    public File getLividDir() {
        return lividDir;
    }

    public void setLividDir(File lividDir) {
        this.lividDir = lividDir;
    }

    public File getFontDir() {
        return fontDir;
    }

    public void setFontDir(File fontDir) {
        this.fontDir = fontDir;
    }

    public File getConfigDir() {
        return configDir;
    }

    public void setConfigDir(File configDir) {
        this.configDir = configDir;
    }

    public File getConfig() {
        return config;
    }

    public void setConfig(File config) {
        this.config = config;
    }


    private static void disableSslVerification() {
        try
        {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

}
