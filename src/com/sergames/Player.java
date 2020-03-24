package com.sergames;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.sergames.Const.*;

public class Player {
    ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
    private int xInitial = 0;
    private int yInitial = 50;
    private int x, y;
    private int xa = 0;
    private int width = 100;
    private int height = 180;
    private int score = 0;
    private int health = PLAYER_HEALTH;
    private boolean confused = false;
    Runnable noConfuse = () -> confused = false;
    private boolean stunned = false;
    Runnable noStun = () -> stunned = false;
    private boolean immune = false;
    Runnable noImmune = () -> immune = false;
    private Game game;

    public Player(Game game) {
        this.game = game;
        setInitialPosition();
    }

    public boolean isImmune() {
        return immune;
    }

    public int getScore() {
        return score;
    }

    public void move() {
        if (x + xa >= 0 && x + xa < game.getWidth() - width) x = x + xa;
        else if (x + xa >= game.getWidth() - width && !game.finalDoor.doorIsClose) x = x + xa;
        if (collisionFinalDoor()) {
            if (!game.finalDoor.doorIsClose) {
                addScore(SCORE_FINAL_DOOR);
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
        if (confused) {
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) xa = -width;
            if (e.getKeyCode() == KeyEvent.VK_LEFT) xa = width;
        } else {
            if (!stunned) {
                if (e.getKeyCode() == KeyEvent.VK_LEFT) xa = -width;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) xa = width;
            }
        }
    }

    private boolean collisionFinalDoor() {
        return game.finalDoor.getBounds().intersects(getBounds());
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void setInitialPosition() {
        x = xInitial;
        y = yInitial;
    }

    public void injure(int dmg) {
        this.health -= dmg;
        if (health <= 0) game.gameOver(); //DEATH IS LIKE WIND, ALWAYS BY MY SIDE
    }

    public void confuse() {
        ses.schedule(noConfuse, PLAYER_CONFUSE_TIME, TimeUnit.SECONDS);
        confused = true;
    }

    public void stun() {
        ses.schedule(noStun, PLAYER_STUN_TIME, TimeUnit.SECONDS);
        stunned = true;
    }

    public void setImmune() {
        ses.schedule(noImmune, PLAYER_IMMUNE_TIME, TimeUnit.SECONDS);
        immune = true;
    }

    public void heal(int healAmount) {
        this.health += healAmount;
    }

    private void addScore(int points) {
        score += points;
    }

    public int getHealth() {
        return health;
    }
}
