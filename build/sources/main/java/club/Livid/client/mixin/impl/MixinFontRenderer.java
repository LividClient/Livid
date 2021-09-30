package club.Livid.client.mixin.impl;

import club.Livid.client.utilities.FontRenderer.FontUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.Inject;

@Mixin(FontRenderer.class)
public class MixinFontRenderer {

    /**
     * @author Submaryne
     */
    @Overwrite
    public int drawString(String text, float x, float y, int color, boolean dropShadow) {
        FontUtil.normal.drawString(text, x, y, color);
        return 1;
    }

    /**
     * @author Submaryne
     */
    @Overwrite
    public int getStringWidth(String text) {
        if (FontUtil.normal == null) {
            if (text == null) {
                return 0;
            } else {
                int i = 0;
                boolean flag = false;

                for (int j = 0; j < text.length(); ++j) {
                    char c0 = text.charAt(j);
                    int k = this.getCharWidth(c0);

                    if (k < 0 && j < text.length() - 1) {
                        ++j;
                        c0 = text.charAt(j);

                        if (c0 != 108 && c0 != 76) {
                            if (c0 == 114 || c0 == 82) {
                                flag = false;
                            }
                        } else {
                            flag = true;
                        }

                        k = 0;
                    }

                    i += k;

                    if (flag && k > 0) {
                        ++i;
                    }
                }

                return i;
            }
        }
        return (int) FontUtil.normal.getStringWidth(text);
    }

    /**
     * @author Submaryne
     */
    @Overwrite
    public int getCharWidth(char character) {
        if (FontUtil.normal == null) {
            return 0;
        }
        return (int) FontUtil.normal.getCharWidthFloat(character);
    }

}
