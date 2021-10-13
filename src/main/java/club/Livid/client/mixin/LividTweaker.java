package club.Livid.client.mixin;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* Tweaker Used to Start Mixin Bootstrap - called in Launch Arguments */
public class LividTweaker implements ITweaker {

    // List of Launch Arguments for getLaunchArguments[]
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

        MixinBootstrap.init();
        System.out.println("initialized mixin");

        MixinEnvironment env = MixinEnvironment.getDefaultEnvironment();
        Mixins.addConfiguration("mixins.livid.json");

        if (env.getObfuscationContext() == null) {
            env.setObfuscationContext("notch");
        }

        env.setSide(MixinEnvironment.Side.CLIENT);

        try {
            ITweaker tweaker = (ITweaker) Class.forName("club.Livid.client.mixin.OptiFineTweaker", true, classLoader).newInstance();
            tweaker.acceptOptions(launchArguments, gameDir, assetsDir, profile);
            tweaker.injectIntoClassLoader(classLoader);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("no optifine :(");
        }

        //Launch.blackboard.put("TweakClasses", "club.Livid.client.mixin.OptifineTweaker");
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