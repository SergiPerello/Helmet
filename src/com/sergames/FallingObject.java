package com.sergames;

import javax.swing.*;
import java.awt.*;

public class FallingObject {
    ImageIcon imageIcon;
    private int x, y;
    private int ya = 1;
    private int width = 80;
    private int height = 80;
    private Color color;
    private Main game;
    private boolean collisionPlayer;

    public FallingObject(Main game, int xPosition, String img) {
        this.game = game;
        this.color = Color.yellow;
        this.x = xPosition;
        this.imageIcon = new ImageIcon(getClass().getResource(img));
    }

    public boolean isCollisionPlayer() {
        return collisionPlayer;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void move() {
        if (x >= 0 && x < game.getWidth() - width) y = y + ya;
        collisionPlayer();
    }

    public void paint(Graphics2D g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.drawImage(imageIcon.getImage(), x, y, width, height, null);
    }

    private void collisionPlayer() {
        collisionPlayer = game.player.getBounds().intersects(getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
