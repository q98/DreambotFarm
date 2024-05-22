package org.dreambot;

public class Settings {
    private String foodName;
    private boolean lootBones;
    private boolean usePrayer;
    private String enemyName;
    private int someValue;
    private boolean isRunning;
    private boolean isMule;

    // Getters and setters for all fields

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public boolean isLootBones() {
        return lootBones;
    }

    public void setLootBones(boolean lootBones) {
        this.lootBones = lootBones;
    }

    public boolean isUsePrayer() {
        return usePrayer;
    }

    public void setUsePrayer(boolean usePrayer) {
        this.usePrayer = usePrayer;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public void setEnemyName(String enemyName) {
        this.enemyName = enemyName;
    }

    public int getSomeValue() {
        return someValue;
    }

    public void setSomeValue(int someValue) {
        this.someValue = someValue;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public boolean isMule() {
        return isMule;
    }

    public void setMule(boolean mule) {
        isMule = mule;
    }
}
