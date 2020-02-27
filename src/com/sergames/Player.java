package com.sergames;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    private int xInitial = 0;
    private int yInitial = 50;
    private int x, y;
    private int xa = 0;
    private int width = 100;
    private int height = 180;
    private Main game;


    public Player(Main game) {
        this.game = game;
        setInitialPosition();
    }

    public void move() {
        game.showText(false);
        if (x + xa >= 0 && x + xa < game.getWidth() - width )
            x = x + xa;
        else if (x + xa >= game.getWidth() - width && !game.finalDoor.doorIsClose){
            x = x + xa;
        }
        if (collisionFinalDoor()) {
            if (game.finalDoor.doorIsClose) {
                game.showText(true);
            }
            else {
                game.showText(false);
                setInitialPosition();
            }
        }
        xa = 0;
    }

    public void paint(Graphics2D g) {
        y = game.getHeight() - height;
        g.fillRect(x, y, width, height);


    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            xa = -width;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            xa = width;
    }

    private boolean collisionFinalDoor() {
        return game.finalDoor.getBounds().intersects(getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public int getLeftX() {
        return x;
    }

    public void setInitialPosition() {
        x = xInitial;
        y = yInitial;
    }
}
