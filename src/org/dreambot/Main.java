package org.dreambot;

import org.dreambot.api.Client;
import org.dreambot.utilities.*;
import org.dreambot.utilities.muling.MuleClient;
import org.dreambot.utilities.muling.MuleServer;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.utilities.Logger;
import org.dreambot.behaviour.combat.CombatBranch;
import org.dreambot.behaviour.combat.leaves.CombatLeaf;
import org.dreambot.behaviour.fallback.FallbackLeaf;
import org.dreambot.behaviour.timeout.TimeoutLeaf;
import org.dreambot.framework.Tree;
import org.dreambot.paint.CustomPaint;
import org.dreambot.paint.PaintInfo;
import org.dreambot.utilities.globals.Global;
import org.dreambot.utilities.globals.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

@ScriptManifest(author = "q98", name = "Automated bot farm with muling.", version = 0.01, category = Category.MONEYMAKING)
public class Main extends AbstractScript implements PaintInfo {

    // Instantiate the tree to hold our branches and leaves
    private MuleServer server;
    private MuleClient client;
    private final Tree tree = new Tree();
    Settings settings = Global.getInstance().getSettings();

    // Define the button dimensions and position
    private final Rectangle muleButtonRect = new Rectangle(10, 350, 100, 30);

    // Instantiate the paint object. This can be customized to your liking.
    private final CustomPaint CUSTOM_PAINT = new CustomPaint(this,
            CustomPaint.PaintLocations.BOTTOM_LEFT_PLAY_SCREEN, new Color[]{new Color(255, 251, 255)},
            "Trebuchet MS",
            new Color[]{new Color(50, 50, 50, 175)},
            new Color[]{new Color(28, 28, 29)},
            1, false, 5, 3, 0);
    private final RenderingHints aa = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // Our onStart that supports arguments
    @Override
    public void onStart(String... args) {
        instantiateTree();
        addMouseListener();
    }

    // Our onStart for when no arguments have been passed to the script
    @Override
    public void onStart() {
        SwingUtilities.invokeLater(GUI::showGUI);
        addMouseListener();
    }

    @Override
    public void onExit() {
        Timing.tickTimeout = 0;
        Timing.sleepLength = 0;
        MuleServer.getInstance().stop();
        try {
            MuleClient.getInstance().stopConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStop() {
        MuleServer.getInstance().stop();
        try {
            MuleClient.getInstance().stopConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Add all of the branches and leaves to the tree
    private void instantiateTree() {
        tree.addBranches(
                new TimeoutLeaf(),
                // Place your own branches and leaves below this
                new CombatBranch().addLeafs(new CombatLeaf()),
                // Place your own branches and leaves above this
                new FallbackLeaf()
        );
    }

    // Infinite loop. Returns the current leaf and executes it
    @Override
    public int onLoop() {
        if(settings.shouldInitialize()) {
            instantiateTree();
            settings.setShouldInitialize(false);
        }
        return this.tree.onLoop();
    }

    // Our paint info
    // Add new lines to the paint here
    @Override
    public String[] getPaintInfo() {
        return new String[]{
                getManifest().name() + " V" + getManifest().version(),
                "Current Branch: " + API.currentBranch,
                "Current Leaf: " + API.currentLeaf,
                "Tick Timeout: " + Timing.tickTimeout,
                "Sleep Delay: " + Timing.sleepLength + "ms"
        };
    }

    // Show the GUI to get user input


    // Add the mouse listener for detecting button clicks
    private void addMouseListener() {
        Client.getInstance().getCanvas().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (muleButtonRect.contains(e.getPoint())) {
                    settings.setShouldMule(!settings.ShouldMule());
                    Logger.log("Mule button pressed, shouldMule set to: " + settings.ShouldMule());
                }
            }
        });
    }

    // onPaint (you probably don't need to touch this)
    @Override
    public void onPaint(Graphics2D graphics2D) {
        graphics2D.setRenderingHints(aa);
        CUSTOM_PAINT.paint(graphics2D);

        // Draw the mule button
        graphics2D.setColor(settings.ShouldMule() ? Color.GREEN : Color.RED);
        graphics2D.fill(muleButtonRect);
        graphics2D.setColor(Color.BLACK);
        graphics2D.drawString("Mule: " + (settings.ShouldMule() ? "On" : "Off"), 15, 370);
    }
    @Override
    public void handleMessage(String message) {
        Logger.log("Handled message: " + message);
        // Add custom handling logic here
    }
}
