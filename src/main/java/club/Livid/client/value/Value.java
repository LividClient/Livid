package club.Livid.client.value;

import club.Livid.client.module.Module;

public class Value<T> {

    private T value;
    private String name;
    private Module parent;

    public Value(String name, Module parent, T value){
        this.name = name;
        this.parent = parent;
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Module getParent() {
        return parent;
    }

    public void setParent(Module parent) {
        this.parent = parent;
    }
}
