package club.Livid.client;

import club.Livid.client.event.EventManager;
import club.Livid.client.event.Subscribe;
import club.Livid.client.event.impl.KeyEvent;
import club.Livid.client.module.ModuleManager;
import club.Livid.client.ui.clickUI.ClickUI;
import club.Livid.client.utilities.FontRenderer.FontUtil;
import club.Livid.client.utilities.file.FileManager;
import club.Livid.client.value.ValueManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;

public enum Livid {

    instance;

    public String CLIENT_NAME = "Livid";
    public String CLIENT_VERSION = "1.0";
    public FileManager fileManager;
    public ModuleManager moduleManager;
    public ValueManager valueManager;
    public GuiScreen lastGui = null;
    public float lastGuiScale;

    public void init() {
        fileManager = new FileManager();
        moduleManager = new ModuleManager();
        valueManager = new ValueManager();
        FontUtil.bootstrap();
        EventManager.register(this);
        lastGuiScale = new ScaledResolution(Minecraft.getMinecraft()).getScaleFactor();
    }

    public String getCLIENT_NAME() {
        return CLIENT_NAME;
    }

    public String getCLIENT_VERSION() {
        return CLIENT_VERSION;
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public void log(String s) {
        System.out.println("[Livid LOGS]: " + s);
        if(Minecraft.getMinecraft().theWorld != null)
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(EnumChatFormatting.LIGHT_PURPLE + "[Livid] (!) -> " + s));
    }

    public ValueManager getValueManager() {
        return valueManager;
    }

    @Subscribe
    public void onKeyType(KeyEvent event) {
        if (event.getKey() == Keyboard.KEY_RSHIFT) {
            Minecraft.getMinecraft().displayGuiScreen(new ClickUI());
        }
    }

}
