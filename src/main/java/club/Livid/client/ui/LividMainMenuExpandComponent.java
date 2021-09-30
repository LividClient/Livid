package club.Livid.client.ui;

import club.Livid.client.utilities.FontRenderer.CFont;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static org.lwjgl.opengl.ARBInternalformatQuery2.GL_TEXTURE_2D_MULTISAMPLE;
import static org.lwjgl.opengl.ARBTextureMultisample.glTexImage2DMultisample;
import static org.lwjgl.opengl.GL11.*;

public class LividMainMenuExpandComponent extends LividUIComponent {

    private boolean expanded;
    int size;

    /**
     * things
     */

    float sizeDifference;

    public LividMainMenuExpandComponent(int id, int x, int y, LividUIComponent parentComponent, int size, LividUIComponent... children) {
        super(id, x, y, parentComponent, children);
        this.size = size;
    }

    @Override
    public void action() {
        super.action();
    }

    @Override
    public boolean isHovered(int mouseX, int mouseY) {
        return getDistance(mouseX, mouseY) < size;
    }

    public int getDistance(int mouseX, int mouseY) {
        int distX = mouseX - getX();
        int distY = mouseY - getY();
        distX = Math.abs(distX);
        distY = Math.abs(distY);
        int e = (int) Math.sqrt(distX * distX + distY * distY);
        return e;
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        GL11.glPushMatrix();
        ResourceLocation r = new ResourceLocation("Livid/main.png");
        mc.getTextureManager().bindTexture(r);
        sizeDifference += ((isHovered(mouseX, mouseY) ? 4 : 0) - sizeDifference) / 4;
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        GL11.glEnable(GL11.GL_BLEND);
        Gui.drawModalRectWithCustomSizedTexture(getX() - size - (int)sizeDifference * 2, getY() - size - (int)sizeDifference * 2, 0, 0, (int) (size + sizeDifference * 2) * 2, (int) (size + sizeDifference * 2) * 2, (size + sizeDifference * 2) * 2, (size + sizeDifference * 2) * 2);
        GL11.glPopMatrix();
    }
}
