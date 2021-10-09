package club.Livid.client.ui.clickUI;

import club.Livid.client.Livid;
import club.Livid.client.ui.AnimatedGuiScreen;
import club.Livid.client.utilities.FontRenderer.FontUtil;
import club.Livid.client.utilities.render.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;
import java.io.IOException;

public class ClickUI extends AnimatedGuiScreen {

    public ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
    public int LEFT = 150;
    public int RIGHT = sr.getScaledWidth() - LEFT;
    public int TOP = 100;
    public int BOTTOM = sr.getScaledHeight() - TOP;

    @Override
    public void customDrawScreen(int mouseX, int mouseY, float partialTicks) {
        sr = new ScaledResolution(Minecraft.getMinecraft());
        Color converter = new Color(0x363636);
        Color textColor = new Color(0x73A0FF);
        Color color = new Color(converter.getRed(), converter.getGreen(), converter.getBlue(), (int)Math.min(255, 255 * animationA));
        RenderUtil.drawShadedRoundedRect(LEFT, TOP, RIGHT, BOTTOM, 7, 10, color.getRGB());
        Gui.drawRect(LEFT + 5, TOP + 5, RIGHT - 5, BOTTOM - 5, color.getRGB());
        FontUtil.large.drawString(Livid.instance.CLIENT_NAME, LEFT + 10, TOP + 10, textColor.getRGB());
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
    }

}
