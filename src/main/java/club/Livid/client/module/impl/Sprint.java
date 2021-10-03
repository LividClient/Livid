package club.Livid.client.module.impl;

import club.Livid.client.event.Subscribe;
import club.Livid.client.event.impl.UpdateEvent;
import club.Livid.client.module.Module;
import club.Livid.client.value.Value;

public class Sprint extends Module {

    Value<Boolean> booleanValue = new Value<>("Boolean Value Test", this, true);
    Value<Double> doubleValue = new Value<>("Double Value Test", this, 1.0d);
    Value<Enum> enumValue = new Value<>("Boolean Value Test", this, Mode.Test);

    public static enum Mode {
        Test, Test1, Test2
    }

    public Sprint() {
        super("Sprint", "Automatically Sprints for you", "Auto-Sprint", "Toggle-Sprint");
        this.toggle();
    }

    @Subscribe
    public void onUpdate(UpdateEvent event) {
        if (mc.thePlayer.onGround && mc.thePlayer.movementInput.moveForward > 0 && !mc.thePlayer.isCollidedHorizontally) {
            mc.thePlayer.setSprinting(true);
            switch ((Sprint.Mode)enumValue.getValue()){
                case Test:
                    break;
            }

        }
    }

}
