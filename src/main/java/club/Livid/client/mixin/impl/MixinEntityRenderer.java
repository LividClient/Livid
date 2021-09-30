package club.Livid.client.mixin.impl;

import club.Livid.client.event.impl.Render3DEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.util.ChatComponentText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class MixinEntityRenderer {

    @Inject(method = "renderHand", at = @At("INVOKE"))
    public void renderHand(float partialTicks, int xOffset, CallbackInfo ci){
        new Render3DEvent(partialTicks).call();
    }

}
