package club.Livid.client.mixin.impl;

import club.Livid.client.Livid;
import club.Livid.client.event.impl.Render3DEvent;
import club.Livid.client.ui.AnimatedGuiScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.EntityRenderer;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class MixinEntityRenderer {

    @Inject(method = "renderHand", at = @At("INVOKE"))
    public void renderHand(float partialTicks, int xOffset, CallbackInfo ci) {
        new Render3DEvent(partialTicks).call();
    }

    @Inject(method = "updateCameraAndRender", at = @At("TAIL"))
    public void updateCameraAndRender(float partialTicks, long nanoTime, CallbackInfo ci) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.currentScreen != null) {
            Livid.instance.lastGui = mc.currentScreen;
        }
        final ScaledResolution scaledresolution = new ScaledResolution(mc);
        int i1 = scaledresolution.getScaledWidth();
        int j1 = scaledresolution.getScaledHeight();
        final int k1 = Mouse.getX() * i1 / mc.displayWidth;
        final int l1 = j1 - Mouse.getY() * j1 / mc.displayHeight - 1;
        if (Livid.instance.lastGui != null && Livid.instance.lastGui instanceof AnimatedGuiScreen) {
            AnimatedGuiScreen animatedGui = (AnimatedGuiScreen) Livid.instance.lastGui;
            if (animatedGui.isEnding) {
                animatedGui.endingUpdates(k1, l1, partialTicks);
            }
        }
    }
}
