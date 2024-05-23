package org.dreambot.behaviour.combat.leaves;

import org.dreambot.utilities.Global;
import org.dreambot.utilities.Settings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Timing;

public class CombatLeaf extends Leaf {
    Settings settings = Global.getInstance().getSettings();


    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public int onLoop() {
        //Logger.log(Color.GREEN, settings.isMule());

        return Timing.loopReturn();
    }
}
