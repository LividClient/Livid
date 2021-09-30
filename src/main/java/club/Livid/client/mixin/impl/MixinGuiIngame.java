package club.Livid.client.mixin.impl;

import club.Livid.client.event.impl.Render2DEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ChatComponentText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.security.auth.callback.Callback;

@Mixin(GuiIngame.class)
public class MixinGuiIngame {

    @Inject(method = "renderGameOverlay", at = @At("TAIL"))
    public void renderGameOverlay(float partialTicks, CallbackInfo ci) {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        new Render2DEvent(sr.getScaledWidth(), sr.getScaledHeight()).call();
    }

}
