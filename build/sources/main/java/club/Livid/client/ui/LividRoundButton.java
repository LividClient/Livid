package club.Livid.client.ui;

import club.Livid.client.utilities.render.RenderUtil;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

public class LividRoundButton extends LividUIComponent {

    private float sizeDifference;
    private float leftOffset = 0f;
    private float leftOffsetSmooth = 0f;
    private int size;
    private ResourceLocation resource;
    private float defX;
    private LividActions actions;

    public LividRoundButton(float x, float y, int id, LividUIComponent parentComponent, ResourceLocation resource, int size, LividActions actions) {
        super(x, y, id, parentComponent);
        defX = x;
        this.actions = actions;
        this.resource = resource;
        this.size = size;
    }

    public void onMainClick() {
        if (getParentComponent() instanceof LividMainMenuExpandComponent) {
            LividMainMenuExpandComponent e = (LividMainMenuExpandComponent) getParentComponent();
            leftOffset = (e.expanded ? -50 : 150);
        }
    }

    @Override
    public void action() {
        if (getParentComponent() instanceof LividMainMenuExpandComponent) {
            LividMainMenuExpandComponent e = (LividMainMenuExpandComponent) getParentComponent();
            if (e.expanded) {
                switch (actions) {
                    case SINGLEPLAYERMENU:
                        mc.displayGuiScreen(new GuiSelectWorld(new LividMainMenu()));
                        break;
                    case MULTIPLAYERMENU:
                        mc.displayGuiScreen(new GuiMultiplayer(new LividMainMenu()));
                        break;
                    case SETTINGS:
                        mc.displayGuiScreen(new GuiOptions(new LividMainMenu(), mc.gameSettings));
                        break;
                    case LANGAUGE:
                        mc.displayGuiScreen(new GuiLanguage(new LividMainMenu(), mc.gameSettings, mc.getLanguageManager()));
                        break;
                    case QUIT:
                        mc.shutdown();
                        break;
                }
            }
        }
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

    @Override
    public void draw(int mouseX, int mouseY) {
        leftOffsetSmooth += (leftOffset - leftOffsetSmooth) / 4;
        setX(defX - leftOffsetSmooth);
        sizeDifference += ((isHovered(mouseX, mouseY) ? 4f : 0f) - sizeDifference) / 4f;
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.alphaFunc(516, 0.003921569F);
        mc.getTextureManager().bindTexture(resource);
        RenderUtil.drawModalRectWithCustomSizedTextureF((getX() - size - sizeDifference), (getY() - size - sizeDifference), 0, 0, (size * 2f + sizeDifference * 2f), (size * 2f + sizeDifference * 2f), size * 2f + sizeDifference * 2f, size * 2f + sizeDifference * 2f);
    }
}
