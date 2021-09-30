package club.Livid.client.mixin.impl;

import club.Livid.client.event.impl.UpdateEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public class MixinEntityPlayerSP {

    @Inject(method = "onUpdate", at = @At("TAIL"))
    public void onUpdate(CallbackInfo ci) {
        new UpdateEvent().call();
    }

}
