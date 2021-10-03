package club.Livid.client.mixin.impl;
import club.Livid.client.event.impl.KeyEvent;
import net.minecraft.client.settings.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyBinding.class)
public class MixinKeyBinding {

    @Inject(method = "onTick", at = @At("TAIL"))
    private static void onTick(int keyCode, CallbackInfo ci){
        new KeyEvent(keyCode).call();
    }

}
