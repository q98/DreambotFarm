package org.dreambot.utilities.globals;

public class Global {
    private static Global instance;
    private Settings settings;
    private ClientVariables clientVariables;
    private ServerVariables serverVariables;

    private Global() {
        settings = new Settings();
        clientVariables = new ClientVariables();
        serverVariables = new ServerVariables();
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
    public ClientVariables getClientVariables() {
        return clientVariables;
    }
    public ServerVariables getServerVariables() {
        return serverVariables;
    }
}
