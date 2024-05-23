package org.dreambot.behaviour.muling.leaves;

import org.dreambot.utilities.globals.Global;
import org.dreambot.utilities.globals.Settings;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Timing;

public class WaitForMule extends Leaf {
    Settings settings = Global.getInstance().getSettings();


    @Override
    public boolean isValid() {
        return !settings.isMuleHere();
    }

    @Override
    public int onLoop() {
        //TODO wait for mule to arrive.

        return Timing.loopReturn();
    }
}
