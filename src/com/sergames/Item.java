package com.sergames;

import javax.swing.*;
import java.awt.*;

public abstract class Item {
    public Game game;
    ImageIcon imageIcon;
    private int x, y;
    private int ya = 1;
    private int width = 80;
    private int height = 80;
    private boolean collisionPlayer;

    public Item(Game game, int xPosition, String img) {
        this.game = game;
        this.x = xPosition;
        this.imageIcon = new ImageIcon(getClass().getResource(img));
    }

    public boolean isCollisionPlayer() {
        return collisionPlayer;
    }

    public int getY() {
        return y;
    }

    public void move() {
        if (x >= 0 && x < game.getWidth() - width) y = y + ya;
        collisionPlayer();
        if (collisionPlayer) action();

    }

    public void paint(Graphics2D g) {
        g.drawImage(imageIcon.getImage(), x, y, width, height, null);
    }

    private void collisionPlayer() {
        collisionPlayer = game.player.getBounds().intersects(getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    abstract void action();
}
