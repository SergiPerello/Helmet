package com.sergames;

public class Entry {
    private String player;
    private String score;

    public Entry(String player, String score) {
        this.player = player;
        this.score = score;
    }

    public String getPlayer() {
        return player;
    }

    public String getScore() {
        return score;
    }
}
