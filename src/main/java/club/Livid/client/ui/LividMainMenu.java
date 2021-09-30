package club.Livid.client.ui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

public class LividMainMenu extends GuiScreen {

    private final ResourceLocation background = new ResourceLocation("Livid/bg.png");

    private ArrayList<LividUIComponent> components = new ArrayList<>();

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution sr = new ScaledResolution(mc);
        mc.getTextureManager().bindTexture(background);
        Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, sr.getScaledWidth(), sr.getScaledHeight(), sr.getScaledWidth(), sr.getScaledHeight());
        for (LividUIComponent c : components) {
            GL11.glEnable(GL_BLEND);
            GL11.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            c.setX(sr.getScaledWidth() / 2);
            c.setY(sr.getScaledHeight() / 2);
            c.draw(mouseX, mouseY);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void onResize(Minecraft mcIn, int w, int h) {
        for (LividUIComponent c : components) {
            if(c instanceof LividMainMenuExpandComponent){
                ScaledResolution sr = new ScaledResolution(mc);
                c.setX(sr.getScaledWidth() / 2);
                c.setY(sr.getScaledHeight() / 2);
            }
        }
        super.onResize(mcIn, w, h);
    }


    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for (LividUIComponent c : components) {
            c.mouseClicked(mouseX, mouseY, mouseButton);
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
    }

    @Override
    public void initGui() {
        this.components.clear();
        ScaledResolution sr = new ScaledResolution(mc);
        this.components.add(new LividMainMenuExpandComponent(0, sr.getScaledWidth() / 2, sr.getScaledHeight() / 2, null, 30));
        super.initGui();
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
    }
}
