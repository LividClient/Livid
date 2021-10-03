package club.Livid.client.event.impl;

import club.Livid.client.event.Event;

public class KeyEvent extends Event {

    int key;

    public KeyEvent(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
