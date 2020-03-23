package com.sergames;

public class Entry {
    private String player;
    private int score;

    public Entry(String player, int score) {
        this.player = player;
        this.score = score;
    }

    public String getPlayer() {
        return player;
    }

    public int getScore() {
        return score;
    }
}
