package club.Livid.client.value;

import club.Livid.client.Livid;
import club.Livid.client.module.Module;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class ValueManager {

    private ArrayList<Value> valueRegistry;

    public ValueManager() {
        valueRegistry = new ArrayList<>();
        for (Module m : Livid.instance.getModuleManager().getModuleRegistry()) {
            ArrayList<Field> fields = new ArrayList<>();
            for (Field f : getAllFields(fields, m.getClass())) {
                Livid.instance.log(f.getType().getName());
                if (f.getType().getName().contains("Value")) {
                    try {
                        if(!f.isAccessible()){
                            f.setAccessible(true);
                        }
                        valueRegistry.add((Value) f.get(m));
                        Livid.instance.log("value added " + f.get(m));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static ArrayList<Field> getAllFields(ArrayList<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }

}
