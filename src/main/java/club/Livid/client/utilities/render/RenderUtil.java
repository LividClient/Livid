package club.Livid.client.utilities.render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

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

    public static void drawRoundedRemake(float left, float top, float right, float bottom, float radius, int points, int color) {
        float f3 = (float) (color >> 24 & 255) / 255.0F;
        float f = (float) (color >> 16 & 255) / 255.0F;
        float f1 = (float) (color >> 8 & 255) / 255.0F;
        float f2 = (float) (color & 255) / 255.0F;

        if (left < right) left = left + right - (right = left);
        if (top < bottom) top = top + bottom - (bottom = top);

        float[][] corners = {
                {right + radius, top - radius, 270},
                {left - radius, top - radius, 360},
                {left - radius, bottom + radius, 90},
                {right + radius, bottom + radius, 180}};

        GlStateManager.resetColor();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.alphaFunc(516, 0.003921569F);
        GlStateManager.color(f, f1, f2, f3);

        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer renderer = tessellator.getWorldRenderer();
        renderer.begin(GL11.GL_POLYGON, DefaultVertexFormats.POSITION);
        for (float[] c : corners) {
            for (int i = 0; i <= points; i++) {
                double anglerad = (Math.PI * (c[2] + i * 90.0F / points) / 180.0f);
                renderer.pos(c[0] + (Math.sin(anglerad) * radius), c[1] + (Math.cos(anglerad) * radius), 0).endVertex();
            }
        }

        tessellator.draw();
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
    }

    public static void drawShadedRoundedRect(float left, float top, float right, float bottom, float radius, int points, int color) {
        float f3 = (float) (color >> 24 & 255) / 255.0F;
        float f = (float) (color >> 16 & 255) / 255.0F;
        float f1 = (float) (color >> 8 & 255) / 255.0F;
        float f2 = (float) (color & 255) / 255.0F;

        if (left < right) {
            float i = left;
            left = right;
            right = i;
        }

        if (top < bottom) {
            float j = top;
            top = bottom;
            bottom = j;
        }

        float[][] corners = {
                {left - radius, top - radius},
                {left - radius, bottom + radius},
                {right + radius, bottom + radius},
                {right + radius, top - radius}};

        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer renderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.disableAlpha();
        GlStateManager.shadeModel(7425);

        renderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        renderer.pos((double) left - radius, (double) bottom + radius, 0.0D).color(f, f1, f2, f3).endVertex();
        renderer.pos((double) right + radius, (double) bottom + radius, 0.0D).color(f, f1, f2, f3).endVertex();
        renderer.pos((double) right + radius, (double) top - radius, 0.0D).color(f, f1, f2, f3).endVertex();
        renderer.pos((double) left - radius, (double) top - radius, 0.0D).color(f, f1, f2, f3).endVertex();
        tessellator.draw();

        renderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        renderer.pos((double) left - radius, (double) bottom, 0.0D).color(f, f1, f2, 0f).endVertex();
        renderer.pos((double) right + radius, (double) bottom, 0.0D).color(f, f1, f2, 0f).endVertex();
        renderer.pos((double) right + radius, (double) bottom + radius, 0.0D).color(f, f1, f2, f3).endVertex();
        renderer.pos((double) left - radius, (double) bottom + radius, 0.0D).color(f, f1, f2, f3).endVertex();
        tessellator.draw();

        renderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        renderer.pos((double) left - radius, (double) top - radius, 0.0D).color(f, f1, f2, f3).endVertex();
        renderer.pos((double) right + radius, (double) top - radius, 0.0D).color(f, f1, f2, f3).endVertex();
        renderer.pos((double) right + radius, (double) top, 0.0D).color(f, f1, f2, 0f).endVertex();
        renderer.pos((double) left - radius, (double) top, 0.0D).color(f, f1, f2, 0f).endVertex();
        tessellator.draw();


        //left right side
        renderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        renderer.pos((double) left, (double) bottom + radius, 0.0D).color(f, f1, f2, 0f).endVertex();
        renderer.pos((double) left - radius, (double) bottom + radius, 0.0D).color(f, f1, f2, f3).endVertex();
        renderer.pos((double) left - radius, (double) top - radius, 0.0D).color(f, f1, f2, f3).endVertex();
        renderer.pos((double) left, (double) top - radius, 0.0D).color(f, f1, f2, 0f).endVertex();
        tessellator.draw();

        renderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        renderer.pos((double) right + radius, (double) bottom + radius, 0.0D).color(f, f1, f2, f3).endVertex();
        renderer.pos((double) right, (double) bottom + radius, 0.0D).color(f, f1, f2, 0f).endVertex();
        renderer.pos((double) right, (double) top - radius, 0.0D).color(f, f1, f2, 0f).endVertex();
        renderer.pos((double) right + radius, (double) top - radius, 0.0D).color(f, f1, f2, f3).endVertex();
        tessellator.draw();

        float angle = 0f;
        for (float[] c : corners) {
            renderer.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION_COLOR);
            renderer.pos(c[0], c[1], 0).color(f, f1, f2, f3).endVertex();
            for (int i = 0; i <= points; i++) {
                double anglerad = (Math.PI * angle / 180.0f);
                double pointX = (Math.sin(anglerad) * radius);
                double pointY = (Math.cos(anglerad) * radius);
                renderer.pos(c[0] + pointX, c[1] + pointY, 0).color(f, f1, f2, 0f).endVertex();
                angle += 90f / points;
            }
            //renderer.pos(c[0], c[1], 0).endVertex();
            tessellator.draw();
            angle -= 90f / points;
        }
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawCircularProgress(float x, float y, float radius, int start, int end, float thicc, Color color) {
        GlStateManager.disableTexture2D();
        glEnable(GL_BLEND);
        GlStateManager.tryBlendFuncSeparate(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ZERO);
        glEnable(GL_LINE_SMOOTH);
        glLineWidth(2f);
        glBegin(GL_LINE_STRIP);
        //Dont use tesselator doesnt support float.
        for (int ie = 0; ie < thicc; ie++) {
            //to get rid of stroke uneveness
            for (float i = start; i <= end; i += (360 / 45f)) {
                glColor4f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, color.getAlpha() / 255f);
                glVertex2f((float) (x + (Math.cos(i * Math.PI / 180) * radius)), (float) (y + (Math.sin(i * Math.PI / 180) * radius)));
            }
            radius++;
        }
        glEnd();
        glDisable(GL_LINE_SMOOTH);
        GlStateManager.enableTexture2D();
    }

}
