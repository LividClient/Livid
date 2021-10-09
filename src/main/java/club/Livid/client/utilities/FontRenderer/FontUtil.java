package club.Livid.client.utilities.FontRenderer;

import club.Livid.client.Livid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("NonAtomicOperationOnVolatileField")
public class FontUtil {
    public static volatile int completed;
    public static MinecraftFontRenderer normal;
    public static MinecraftFontRenderer large;


    public static Font getFont(Map<String, Font> locationMap, String location, int size) {
        Font font = null;

        try {
            if (locationMap.containsKey(location)) {
                font = locationMap.get(location).deriveFont(Font.PLAIN, size);
            } else {
                InputStream is = new FileInputStream(new File(Livid.instance.getFileManager().getFontDir(), "ProductSansLight.ttf"));
                font = Font.createFont(0, is);
                locationMap.put(location, font);
                font = font.deriveFont(Font.PLAIN, size);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", Font.PLAIN, size);
        }

        return font;
    }

    public static void bootstrap() {
        Livid.instance.log("Fonts Reloaded");
        Map<String, Font> locationMap = new HashMap<>();
        Font normal_ = getFont(locationMap, "ProductSansLight.ttf", 20);
        Font large_ = getFont(locationMap, "ProductSansLight.ttf", 40);
        normal = new MinecraftFontRenderer(normal_, true, true);
        large = new MinecraftFontRenderer(large_, true, true);
    }
}