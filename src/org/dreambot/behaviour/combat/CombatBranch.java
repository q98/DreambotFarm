package org.dreambot.behaviour.combat;

import org.dreambot.Global;
import org.dreambot.Settings;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.framework.Root;
import org.dreambot.utilities.Timing;

public class CombatBranch extends Root {
    Settings settings = Global.getInstance().getSettings();


    @Override
    public boolean isValid() {
        return (settings.isRunning() && !settings.ShouldMule());
    }
}