package org.dreambot.utilities;

public class Variables {
    public class Settings {
        private boolean isRunning;
        private boolean isMule;
        private boolean shouldInitialize;
        private boolean shouldMule;  // New field
        private boolean MuleHere = false;


        public boolean isRunning() {
            return isRunning;
        }
        public boolean shouldInitialize() {
            return shouldInitialize;
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

        public void setShouldInitialize(boolean ini) {
            shouldInitialize = ini;
        }

        public void setMuleHere(boolean muleHere) {
            MuleHere = muleHere;
        }

        public boolean isMuleHere() { return MuleHere; }

        public boolean ShouldMule() {
            return shouldMule;
        }

        public void setShouldMule(boolean shouldMule) {
            this.shouldMule = shouldMule;
        }
    }

}

