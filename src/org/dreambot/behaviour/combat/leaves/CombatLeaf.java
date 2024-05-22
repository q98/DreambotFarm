package org.dreambot.behaviour.combat.leaves;

import org.dreambot.Global;
import org.dreambot.Settings;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.framework.Leaf;
import org.dreambot.utilities.Interaction;
import org.dreambot.utilities.Timing;

import java.awt.*;
import java.util.List;

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
