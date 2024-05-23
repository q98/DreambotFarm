package org.dreambot.utilities;

public class Global {
    private static Global instance;
    private Settings settings;
    private Variables variables;

    private Global() {
        settings = new Settings();
        variables = new Variables();
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
    public Variables getVariables() {
        return variables;
    }
}
