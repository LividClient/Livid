package club.Livid.client.utilities.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL13.GL_MULTISAMPLE;

public class RenderUtil {

    public static void drawModalRectWithCustomSizedTextureF(float x, float y, float u, float v, float width, float height, float textureWidth, float textureHeight) {
        float f = 1.0F / textureWidth;
        float f1 = 1.0F / textureHeight;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(x, (double) (y + height), 0.0F).tex((double) (u * f), (double) ((v + (float) height) * f1)).endVertex();
        worldrenderer.pos((x + width), (double) (y + height), 0.0F).tex((double) ((u + (float) width) * f), (double) ((v + (float) height) * f1)).endVertex();
        worldrenderer.pos((x + width), (double) y, 0.0F).tex((double) ((u + (float) width) * f), (double) (v * f1)).endVertex();
        worldrenderer.pos(x, (double) y, 0.0F).tex((double) (u * f), (double) (v * f1)).endVertex();
        tessellator.draw();
    }

}
