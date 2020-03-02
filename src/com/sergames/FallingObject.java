package com.sergames;

import java.awt.*;

public class FallingObject {
    private int xInitial = Const.WIDTH/2;
    private int yInitial;
    private int x, y;
    private int ya = 1;
    private int width = 100;
    private int height = 100;
    private Color color;
    private Main game;

    public FallingObject(Main game) {
        this.game = game;
        this.color = Color.yellow;
    }

    public void move() {
        if (x >= 0 && x < game.getWidth() - width) {
            y = y + ya;
        }
    }

    public Color getColor() {
        return color;
    }

    public void paint(Graphics2D g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
