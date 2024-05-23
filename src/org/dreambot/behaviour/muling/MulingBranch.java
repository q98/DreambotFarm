package org.dreambot.behaviour.muling;

import org.dreambot.utilities.globals.Global;
import org.dreambot.utilities.globals.Settings;
import org.dreambot.framework.Root;

public class MulingBranch extends Root {
    Settings settings = Global.getInstance().getSettings();


    @Override
    public boolean isValid() {
        return (settings.ShouldMule());
    }
}