package org.dreambot.behaviour.muling;

import org.dreambot.Global;
import org.dreambot.Settings;
import org.dreambot.framework.Root;

public class MulingBranch extends Root {
    Settings settings = Global.getInstance().getSettings();


    @Override
    public boolean isValid() {
        return (settings.ShouldMule());
    }
}