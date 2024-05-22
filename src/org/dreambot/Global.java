package org.dreambot;

public class Global {
    private static Global instance;
    private Settings settings;

    private Global() {
        settings = new Settings();
    }

    public static synchronized Global getInstance() {
        if (instance == null) {
            instance = new Global();
        }
        return instance;
    }

    public Settings getSettings() {
        return settings;
    }
}
