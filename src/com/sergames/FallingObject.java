package com.sergames;

import java.awt.*;

public class FallingObject {
    private int x = 0;
    private int y = 0;
    private int width = 100;
    private int height = 180;
    private Color color = Color.green;
    private Main game;

    public FallingObject(Main game) {
        this.game = game;
    }

    public Color getColor() {
        return color;
    }

    public void paint(Graphics2D g) {
        x = game.getWidth() - width;
        y = game.getHeight() - height;
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
