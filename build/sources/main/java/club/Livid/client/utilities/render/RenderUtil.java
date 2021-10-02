package club.Livid.client.utilities.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class RenderUtil {

    public static void drawModalRectWithCustomSizedTextureF(float x, float y, float u, float v, float width, float height, float textureWidth, float textureHeight) {
        float f = 1.0F / textureWidth;
        float f1 = 1.0F / textureHeight;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(x, y + height, 0.0F).tex(u * f, (v + height) * f1).endVertex();
        worldrenderer.pos((x + width), y + height, 0.0F).tex((u + width) * f, (v + height) * f1).endVertex();
        worldrenderer.pos((x + width), y, 0.0F).tex((u + width) * f, v * f1).endVertex();
        worldrenderer.pos(x, y, 0.0F).tex(u * f, v * f1).endVertex();
        tessellator.draw();
    }

}
