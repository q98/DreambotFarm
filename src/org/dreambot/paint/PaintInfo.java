package org.dreambot.paint;

public interface PaintInfo {
    void onStop();

    String[] getPaintInfo();

    void handleMessage(String message);
}
