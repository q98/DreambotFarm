package org.dreambot.behaviour.combat;

import org.dreambot.utilities.globals.Global;
import org.dreambot.utilities.globals.Settings;
import org.dreambot.framework.Root;

public class CombatBranch extends Root {
    Settings settings = Global.getInstance().getSettings();


    @Override
    public boolean isValid() {
        return (settings.isRunning() && !settings.ShouldMule());
    }
}