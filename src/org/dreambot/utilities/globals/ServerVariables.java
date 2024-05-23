package org.dreambot.utilities.globals;

public class ServerVariables {
    private boolean MuleHere = false;

    public void setMuleHere(boolean muleHere) {
        MuleHere = muleHere;
    }

    public boolean isMuleHere() { return MuleHere; }
}