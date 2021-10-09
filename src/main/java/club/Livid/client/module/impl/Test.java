package club.Livid.client.module.impl;

import club.Livid.client.event.Subscribe;
import club.Livid.client.event.impl.Render2DEvent;
import club.Livid.client.module.Module;
import club.Livid.client.utilities.render.RenderUtil;

import java.awt.*;

public class Test extends Module {

    int ran = 0;

    public Test() {
        super("Test", "Test Module", "test", "Tester");
        this.toggle();
    }

    @Subscribe
    public void onRender(Render2DEvent event) {
        ran++;
        RenderUtil.drawCircularProgress(100, 100, 20, ran, ran + 360, 5f, new Color(255, 255, 255, 255));
    }

}
