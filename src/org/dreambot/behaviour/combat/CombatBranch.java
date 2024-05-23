package org.dreambot.behaviour.combat;

import org.dreambot.utilities.Global;
import org.dreambot.utilities.Settings;
import org.dreambot.framework.Root;

public class CombatBranch extends Root {
    Settings settings = Global.getInstance().getSettings();


    @Override
    public boolean isValid() {
        return (settings.isRunning() && !settings.ShouldMule());
    }
}