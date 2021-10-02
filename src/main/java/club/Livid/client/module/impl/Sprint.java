package club.Livid.client.module.impl;

import club.Livid.client.event.Subscribe;
import club.Livid.client.event.impl.UpdateEvent;
import club.Livid.client.module.Module;

public class Sprint extends Module {
    public Sprint() {
        super("Sprint", "Automatically Sprints for you", "Auto-Sprint", "Toggle-Sprint");
        this.toggle();
    }

    @Subscribe
    public void onUpdate(UpdateEvent event) {
        if (mc.thePlayer.onGround && mc.thePlayer.movementInput.moveForward > 0 && !mc.thePlayer.isCollidedHorizontally) {
            mc.thePlayer.setSprinting(true);
        }
    }

}
