package club.Livid.client.ui;

import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LividUIComponent {

    Minecraft mc = Minecraft.getMinecraft();
    private int id;
    private int x;
    private int y;
    private LividUIComponent parentComponent;
    public List<LividUIComponent> children;

    public LividUIComponent(int x, int y, int id) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public LividUIComponent(int x, int y, int id, LividUIComponent parentComponent) {
        this.id = id;
        this.parentComponent = parentComponent;
        this.x = x;
        this.y = y;
    }

    public LividUIComponent(int x, int y, int id, LividUIComponent parentComponent, LividUIComponent... children) {
        this.id = id;
        this.parentComponent = parentComponent;
        this.children = Arrays.asList(children);
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public List<LividUIComponent> getChildren() {
        return children;
    }

    public void setChildren(List<LividUIComponent> children) {
        this.children = children;
    }
}
