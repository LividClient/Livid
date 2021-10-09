package club.Livid.client.mixin.impl;

import club.Livid.client.utilities.FontRenderer.FontUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.awt.*;

@Mixin(GuiButton.class)
public class MixinGuiButton {

    @Shadow
    protected static final ResourceLocation buttonTextures = new ResourceLocation("textures/gui/widgets.png");
    @Shadow
    protected int width;
    @Shadow
    protected int height;
    @Shadow
    public int xPosition;
    @Shadow
    public int yPosition;
    @Shadow
    public String displayString;
    @Shadow
    public int id;
    @Shadow
    public boolean enabled;
    @Shadow
    public boolean visible;
    @Shadow
    protected boolean hovered;

    float alpha = 0;

    /**
     * @author Submaryne
     */
    @Overwrite
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        hovered = (mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height);
        alpha += ((hovered ? 255 : 0) - alpha) / (4 * (mc.theWorld == null ? 1 : 8));
        Gui.drawRect(xPosition, yPosition, xPosition + width, yPosition + height, 0xFFFFFFFF);
        Gui.drawRect(xPosition + 1, yPosition + 1, xPosition + width - 1, yPosition + height - 1, new Color((int) alpha, (int) alpha, (int) alpha).getRGB());
        FontUtil.normal.drawCenteredString(displayString, xPosition + width / 2, yPosition + height / 2 - FontUtil.normal.getHeight() / 2, new Color(255 - (int) alpha, 255 - (int) alpha, 255 - (int) alpha).getRGB());
    }


}
