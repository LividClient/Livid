package club.Livid.client.ui;

import club.Livid.client.Livid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;

public class AnimatedGuiScreen extends GuiScreen {

    public float animationA = 0f;
    public float animationI = 0f;
    public boolean isEnding = false;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.pushMatrix();
        ScaledResolution sr = new ScaledResolution(mc);
        int guiScale = sr.getScaleFactor();
        float scale = 2.0f / guiScale;
        animationA += (1f - animationA) / 16;
        animationI += (1f - animationI) / 16;
        GlStateManager.enableAlpha();
        GlStateManager.color(1.0f, 1.0f, 1.0f, animationA);
        GlStateManager.scale(animationI, animationI, 1.0f);
        GlStateManager.scale(scale, scale, scale);
        customDrawScreen(mouseX, mouseY, partialTicks);
        GlStateManager.popMatrix();
    }


    @Override
    public void initGui() {
        animationA = 0f;
        animationI = 0f;
        super.initGui();
    }

    public void customDrawScreen(int mouseX, int mouseY, float partialTicks) {

    }

    public void endingUpdates(int mouseX, int mouseY, float partialTicks) {
        GlStateManager.pushMatrix();
        GlStateManager.enableAlpha();
        GlStateManager.scale(animationI, animationI, 1.0f);
        animationA += (0f - animationA) / 16;
        animationI += (0f - animationI) / 16;
        customDrawScreen(mouseX, mouseY, partialTicks);
        if (animationI < 0.01f) {
            isEnding = false;
            mc.displayGuiScreen(null);
        }
        GlStateManager.popMatrix();
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
    }
}
