package de.zokki.minesweeper.Utils;

public class GameManager {

    private static boolean isPlaying = false;

    public static boolean isPlaying() {
        return isPlaying;
    }

    public static void setPlaying(boolean isPlaying) {
        GameManager.isPlaying = isPlaying;
    }
}
