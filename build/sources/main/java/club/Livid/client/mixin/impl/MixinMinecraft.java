package club.Livid.client.mixin.impl;

import club.Livid.client.Livid;
import club.Livid.client.ui.LividMainMenu;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft  {

    @Inject(method = "createDisplay", at = @At("RETURN"))
    public void createDisplay(CallbackInfo ci) {
        Display.setTitle("Loading Livid Client...");
    }

    @Inject(method = "startGame", at = @At("TAIL"))
    public void startGame(CallbackInfo ci) {
        Display.setTitle("Livid Client | 1.8.9 | Build " + Livid.instance.getCLIENT_VERSION());
        Livid.instance.init();
        Minecraft.getMinecraft().displayGuiScreen(new LividMainMenu());
    }


}
