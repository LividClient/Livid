package club.Livid.client.ui.components;

import club.Livid.client.utilities.render.RenderUtil;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

public class LividMainMenuExpandComponent extends LividUIComponent {

    public boolean expanded;
    float size;
    int offset = 0;
    public float offsetDifference = 0;
    public int defX;
    /**
     * things
     */

    float sizeDifference;

    public LividMainMenuExpandComponent(float x, float y, int id, LividUIComponent parentComponent, float size, LividUIComponent... children) {
        super(x, y, id, parentComponent, children);
        this.size = size;
        this.defX = (int) x;
    }

    @Override
    public void action() {
        offset = (offset == 0 ? 120 : 0);
        expanded = !expanded;
        if (expanded && !checkList()) {
            System.out.println("Expand: " + expanded);
            this.children.add(new LividRoundButton(getX() - 50, getY(), 1, this, new ResourceLocation("Livid/multiplayer.png"), 25, LividActions.MULTIPLAYERMENU));
            this.children.add(new LividRoundButton(getX() - 50 * 2, getY(), 2, this, new ResourceLocation("Livid/single.png"), 25, LividActions.SINGLEPLAYERMENU));
            this.children.add(new LividRoundButton(getX(), getY(), 3, this, new ResourceLocation("Livid/settings.png"), 25, LividActions.SETTINGS));
            this.children.add(new LividRoundButton(getX() + 50, getY(), 4, this, new ResourceLocation("Livid/language.png"), 25, LividActions.LANGAUGE));
            this.children.add(new LividRoundButton(getX() + 50 * 2, getY(), 5, this, new ResourceLocation("Livid/exit.png"), 25, LividActions.QUIT));
        }
        for (LividUIComponent c : children) {
            if (c instanceof LividRoundButton) {
                ((LividRoundButton) c).onMainClick();
            }
        }
        super.action();
    }

    public boolean checkList() {
        for (LividUIComponent c : children) {
            if (c != null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        for (LividUIComponent c : children) {
            if (c instanceof LividRoundButton) {
                c.mouseClicked(mouseX, mouseY, mouseButton);
            }
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public boolean isHovered(int mouseX, int mouseY) {
        return getDistance(mouseX, mouseY) < size;
    }

    public int getDistance(int mouseX, int mouseY) {
        int distX = (int) (mouseX - getX());
        int distY = (int) (mouseY - getY());
        distX = Math.abs(distX);
        distY = Math.abs(distY);
        int e = (int) Math.sqrt(distX * distX + distY * distY);
        return e;
    }

    public void updatePositions(int mouseX, int mouseY) {
        sizeDifference += ((isHovered(mouseX, mouseY) ? 4f : 0f) - sizeDifference) / 4f;
        offsetDifference += (offset - offsetDifference) / 4f;
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        ResourceLocation r = new ResourceLocation("livid/textures/main.png");
        setX(defX - offsetDifference);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.alphaFunc(516, 0.003921569F);
        mc.getTextureManager().bindTexture(r);
        RenderUtil.drawModalRectWithCustomSizedTextureF((getX() - size - sizeDifference), (getY() - size - sizeDifference), 0, 0, (size * 2f + sizeDifference * 2f), (size * 2f + sizeDifference * 2f), size * 2f + sizeDifference * 2f, size * 2f + sizeDifference * 2f);
    }
}
