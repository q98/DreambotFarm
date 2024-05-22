package org.dreambot;

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
import org.dreambot.utilities.API;
import org.dreambot.utilities.Timing;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

@ScriptManifest(author = "Bonfire", name = "DreamBot TBL", version = 1.00, category = Category.MAGIC)
public class Main extends AbstractScript implements PaintInfo {

    // Instantiate the tree to hold our branches and leaves
    private boolean isMule;
    private MuleServer server;
    private MuleClient client;
    private final Tree tree = new Tree();
    Settings settings = Global.getInstance().getSettings();


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
    }

    // Our onStart for when no arguments have been passed to the script
    @Override
    public void onStart() {
        SwingUtilities.invokeLater(this::showGUI);
        //TODO GUI should instantiate tree when start is clicked // Global.settings class?
        //instantiateTree();
    }

    @Override
    public void onExit() {
        Timing.tickTimeout = 0;
        Timing.sleepLength = 0;
            if (server != null) {
                server.stop();
            }
            if (client != null) {
                try {
                    client.stopConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            
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


    private void showGUI() {
        JFrame frame = new JFrame();
        frame.setTitle("Best fighter");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setPreferredSize(new Dimension(300, 200));
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel settingPanel = new JPanel();
        settingPanel.setLayout(new GridLayout(0, 2));

        JLabel foodNameLabel = new JLabel("Food name:");
        settingPanel.add(foodNameLabel);

        JTextField foodNameTextField = new JTextField();
        settingPanel.add(foodNameTextField);

        JCheckBox lootCheckBox = new JCheckBox("Loot bones");
        settingPanel.add(lootCheckBox);

        JCheckBox prayerCheckBox = new JCheckBox("Use prayer");
        settingPanel.add(prayerCheckBox);

        JLabel label = new JLabel("Enemy name:");
        settingPanel.add(label);

        JComboBox<String> enemyComboBox = new JComboBox<>(new String[]{"Chicken", "Cow", "Goblin"});
        settingPanel.add(enemyComboBox);

        label = new JLabel("Some value:");
        settingPanel.add(label);

        JComboBox<Integer> anotherComboBox = new JComboBox<>(new Integer[]{0, 6, 18});
        settingPanel.add(anotherComboBox);

        JCheckBox muleCheckBox = new JCheckBox("Is Mule");
        muleCheckBox.addActionListener(e -> isMule = muleCheckBox.isSelected());
        settingPanel.add(muleCheckBox);

        frame.getContentPane().add(settingPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton button = new JButton("Start script");
        button.addActionListener(e -> {
            Settings settings = Global.getInstance().getSettings();
            settings.setFoodName(foodNameTextField.getText());
            settings.setLootBones(lootCheckBox.isSelected());
            settings.setUsePrayer(prayerCheckBox.isSelected());
            settings.setEnemyName((String) enemyComboBox.getSelectedItem());
            settings.setSomeValue((int) anotherComboBox.getSelectedItem());
            settings.setRunning(true);
            settings.setMule(isMule);
            frame.dispose();

            if (settings.isRunning()) {
                instantiateTree();
                if (settings.isMule()) {
                    try {
                        MuleServer.getInstance().start(12345);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        MuleClient.getInstance().startConnection("127.0.0.1", 12345);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        buttonPanel.add(button);

        button = new JButton("Another button");
        button.addActionListener(e -> Logger.log("Another button clicked..."));
        buttonPanel.add(button);

        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
    }



    // onPaint (you probably don't need to touch this)
    @Override
    public void onPaint(Graphics2D graphics2D) {
        graphics2D.setRenderingHints(aa);
        CUSTOM_PAINT.paint(graphics2D);
    }
}
