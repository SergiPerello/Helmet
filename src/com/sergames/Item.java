package com.sergames;

import javax.swing.*;
import java.awt.*;

public abstract class Item {
    public Game game;
    ImageIcon imageIcon;
    private int x, y;
    private int fallingSpeed;
    private int width = 80;
    private int height = 80;
    private boolean collisionPlayer;

    public Item(Game game, int xPosition, int fallingSpeed, String img) {
        this.game = game;
        this.x = xPosition;
        this.fallingSpeed = fallingSpeed;
        this.imageIcon = new ImageIcon(getClass().getResource(img));
    }

    public boolean isCollisionPlayer() {
        return collisionPlayer;
    }

    public int getY() {
        return y;
    }

    public void move() {
        if (x >= 0 && x < game.getWidth() - width) y = y + fallingSpeed;
        collisionPlayer();
        if (collisionPlayer && !game.player.isImmune()) action();

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
