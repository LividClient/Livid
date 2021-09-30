package club.Livid.client.mixin.impl;

import club.Livid.client.Livid;
import club.Livid.client.utilities.FontRenderer.FontUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiMainMenu.class)
public class MixinGuiMainMenu{

    @Inject(method = "drawScreen", at = @At("TAIL"))
    public void drawScreen(int mouseX, int mouseY, float partialTicks, CallbackInfo ci){
        FontUtil.normal.drawString("Client Made by Submaryne: Livid | 1.8.9 | " + Livid.instance.getCLIENT_VERSION(), 3, 3, 0xFFFFFFFF);
    }

}
