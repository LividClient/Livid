package club.Livid.client.ui.components;

import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.Arrays;

public class LividUIComponent {

    Minecraft mc = Minecraft.getMinecraft();
    private int id;
    private float x;
    private float y;
    private LividUIComponent parentComponent;
    public ArrayList<LividUIComponent> children;

    public LividUIComponent(float x, float y, int id) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public LividUIComponent(float x, float y, int id, LividUIComponent parentComponent) {
        this.id = id;
        this.parentComponent = parentComponent;
        this.x = x;
        this.y = y;
    }

    public LividUIComponent(float x, float y, int id, LividUIComponent parentComponent, LividUIComponent... children) {
        this.id = id;
        this.parentComponent = parentComponent;
        this.children = new ArrayList<LividUIComponent>(Arrays.asList(children));
        this.x = x;
        this.y = y;
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (isHovered(mouseX, mouseY)) {
            action();
        }
    }

    public void action() {

    }

    public boolean isHovered(int mouseX, int mouseY) {
        return false;
    }

    public void draw(int mouseX, int mouseY) {

    }

    public LividUIComponent getParentComponent() {
        return parentComponent;
    }

    public void setParentComponent(LividUIComponent parentComponent) {
        this.parentComponent = parentComponent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public ArrayList<LividUIComponent> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<LividUIComponent> children) {
        this.children = children;
    }
}
