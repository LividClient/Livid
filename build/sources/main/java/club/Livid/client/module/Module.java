package club.Livid.client.module;

import club.Livid.client.event.EventManager;
import net.minecraft.client.Minecraft;

public class Module {

    protected Minecraft mc = Minecraft.getMinecraft();
    private String name;
    private boolean enabled;
    private String documentation;
    private String[] aliases;

    public Module(String name, String description, String... aliases) {
        this.name = name;
        this.documentation = description;
        this.aliases = new String[32];
        for (int i = 0; i < aliases.length - 1; i++) {
            String string = aliases[i];
            this.aliases[i] = string;
        }
    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public void toggle() {
        this.setEnabled(!isEnabled());
        if (this.isEnabled()) {
            EventManager.register(this);
            onEnable();
        } else {
            EventManager.unregister(this);
            onDisable();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) {
            EventManager.register(this);
        } else {
            EventManager.unregister(this);
        }
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public String[] getAliases() {
        return aliases;
    }

    public void setAliases(String[] aliases) {
        this.aliases = aliases;
    }
}

