package club.Livid.client.module;

import javafx.scene.Parent;

public class Module {

    private String name;
    private boolean enabled;
    private String documentation;
    private String[] aliases;

    public Module(String name, String description, String... aliases) {
        this.name = name;
        this.documentation = description;
        this.aliases = new String[32];
        for (int i = 0; i < 31; i++) {
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
            onEnable();
        } else {
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

