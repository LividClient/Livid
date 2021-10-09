package club.Livid.client.ui.mainMenu;

import club.Livid.client.ui.components.LividMainMenuExpandComponent;
import club.Livid.client.ui.components.LividUIComponent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.ArrayList;

public class LividMainMenu extends GuiScreen {


    private final ResourceLocation background = new ResourceLocation("Livid/bg.png");

    private ArrayList<LividUIComponent> components = new ArrayList<>();

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution sr = new ScaledResolution(mc);
        mc.getTextureManager().bindTexture(background);
        Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, sr.getScaledWidth(), sr.getScaledHeight(), sr.getScaledWidth(), sr.getScaledHeight());
        for (LividUIComponent c : components) {
            if (c instanceof LividMainMenuExpandComponent) {
                LividMainMenuExpandComponent comp = (LividMainMenuExpandComponent) c;
                comp.updatePositions(mouseX, mouseY);
                makeCropBox((int) (comp.defX - comp.offsetDifference), 0, sr.getScaledWidth(), sr.getScaledHeight());
                for (LividUIComponent child : comp.getChildren()) {
                    if(child != null) {
                        child.draw(mouseX, mouseY);
                    }
                }
                destroyCropBox();
            }
            c.draw(mouseX, mouseY);
        }
    }

    public void cropBox(float x, float y, float x2, float y2) {
        final ScaledResolution scale = new ScaledResolution(Minecraft.getMinecraft());
        int factor = scale.getScaleFactor();
        GL11.glScissor((int) (x * factor), (int) ((scale.getScaledHeight() - y2) * factor), (int) ((x2 - x) * factor), (int) ((y2 - y) * factor));
    }

    public void makeCropBox(int left, int top, int right, int bottom) {
        GlStateManager.pushMatrix();
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        cropBox(left, top, right, bottom);
    }

    public void destroyCropBox() {
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
        GlStateManager.popMatrix();
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
        super.initGui();
        this.components.clear();
        ScaledResolution sr = new ScaledResolution(mc);
        LividMainMenuExpandComponent expander = new LividMainMenuExpandComponent(sr.getScaledWidth() / 2f, sr.getScaledHeight() / 2f, 0,null, 35, (LividUIComponent) null);
        this.components.add(expander);
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
