package com.sergames;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class FinalDoor {
    private int x = 0;
    private int y = 0;
    private int width = 100;
    private int height = 180;
    private Color color = Color.green;
    boolean doorIsClose = false;
    private Main game;
    private Timer timer = new Timer("MyTimer");
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            if (color == Color.green) {
                doorIsClose = true;
                color = Color.red;
            }
            else  {
                doorIsClose = false;
                color = Color.green;
            }
        }
    };

    public FinalDoor(Main game) {
        this.game = game;
        timer.scheduleAtFixedRate(timerTask, 0, 3000);
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
