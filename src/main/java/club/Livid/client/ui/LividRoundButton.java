package club.Livid.client.ui;

import net.minecraft.util.ResourceLocation;

public class LividRoundButton extends LividUIComponent {

    private int size;
    private ResourceLocation resource;

    public LividRoundButton(int x, int y, int id, LividUIComponent parentComponent, ResourceLocation resource, int size) {
        super(x, y, id, parentComponent);
        this.resource = resource;
        this.size = size;
    }

    @Override
    public void action() {

        super.action();
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        super.draw(mouseX, mouseY);
    }
}
