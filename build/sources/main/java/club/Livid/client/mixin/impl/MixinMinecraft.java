package club.Livid.client.mixin.impl;

import club.Livid.client.Livid;
import club.Livid.client.ui.LividMainMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGameOver;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Inject(method = "createDisplay", at = @At("RETURN"))
    public void createDisplay(CallbackInfo ci) {
        Display.setTitle("Loading Livid Client...");
    }

    @Inject(method = "startGame", at = @At("TAIL"))
    public void startGame(CallbackInfo ci) {
        Display.setTitle("Livid Client | 1.8.9 | Build " + Livid.instance.getCLIENT_VERSION());
        Livid.instance.init();
        Minecraft.getMinecraft().displayGuiScreen(new LividMainMenu());
    }

    @Inject(method = "displayGuiScreen", at = @At("TAIL"))
    public void displayGuiScreen(GuiScreen guiScreenIn, CallbackInfo ci) {
        if (guiScreenIn instanceof GuiMainMenu) {
            guiScreenIn = new LividMainMenu();
            Minecraft mc = Minecraft.getMinecraft();

            if (mc.currentScreen != null) {
                mc.currentScreen.onGuiClosed();
            }

            if (guiScreenIn == null && mc.theWorld == null) {
                guiScreenIn = new GuiMainMenu();
            } else if (guiScreenIn == null && mc.thePlayer.getHealth() <= 0.0F) {
                guiScreenIn = new GuiGameOver();
            }

            if (guiScreenIn instanceof GuiMainMenu) {
                mc.gameSettings.showDebugProfilerChart = false;
                mc.ingameGUI.getChatGUI().clearChatMessages();
            }

            mc.currentScreen = (GuiScreen) guiScreenIn;

            if (guiScreenIn != null) {
                mc.setIngameNotInFocus();
                ScaledResolution scaledresolution = new ScaledResolution(mc);
                int i = scaledresolution.getScaledWidth();
                int j = scaledresolution.getScaledHeight();
                ((GuiScreen) guiScreenIn).setWorldAndResolution(mc, i, j);
                mc.skipRenderWorld = false;
            } else {
                mc.getSoundHandler().resumeSounds();
                mc.setIngameFocus();
            }
            if (mc.currentScreen != null) {
                mc.currentScreen.onGuiClosed();
            }

            if (guiScreenIn == null && mc.theWorld == null) {
                guiScreenIn = new GuiMainMenu();
            } else if (guiScreenIn == null && mc.thePlayer.getHealth() <= 0.0F) {
                guiScreenIn = new GuiGameOver();
            }

            if (guiScreenIn instanceof GuiMainMenu) {
                mc.gameSettings.showDebugProfilerChart = false;
                mc.ingameGUI.getChatGUI().clearChatMessages();
            }

            mc.currentScreen = (GuiScreen) guiScreenIn;

            if (guiScreenIn != null) {
                mc.setIngameNotInFocus();
                ScaledResolution scaledresolution = new ScaledResolution(mc);
                int i = scaledresolution.getScaledWidth();
                int j = scaledresolution.getScaledHeight();
                ((GuiScreen) guiScreenIn).setWorldAndResolution(mc, i, j);
                mc.skipRenderWorld = false;
            } else {
                mc.getSoundHandler().resumeSounds();
                mc.setIngameFocus();
            }
        }
    }


}
