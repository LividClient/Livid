package club.Livid.client.module;

import club.Livid.client.Livid;
import org.reflections.Reflections;

import java.util.ArrayList;

public class ModuleManager {

    private ArrayList<Module> moduleRegistry;

    public ModuleManager() {
        moduleRegistry = new ArrayList<>();
        new Reflections("club.Livid.client.module.impl").getSubTypesOf(Module.class).forEach(m -> {
            Module module;
            try {
                module = m.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            Livid.instance.log("Adding Module " + module);
            moduleRegistry.add(module);
            Livid.instance.log("Loading settings for Module " + module);
        });
    }

    public ArrayList<Module> getModuleRegistry() {
        return moduleRegistry;
    }

    public void register(Module m) {
        getModuleRegistry().add(m);
    }

    public Module getModule(Class clazz) {
        for (Module m : this.moduleRegistry) {
            if (m.getClass() == clazz) {
                return m;
            }
        }
        return null;
    }
}
