package com.sergames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.sergames.Const.*;

public class Main extends JPanel {
    Player player = new Player(this);
    FinalDoor finalDoor = new FinalDoor(this);
    ItemsGen itemsGen = new ItemsGen(this);
    ArrayList<Item> items = new ArrayList<>();

    public Main() {
        addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        setFocusable(true);
        Timer timer = new Timer("TimeBetweenItems");
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                itemsGen.addItem();
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, TIME_BETWEEN_ITEMS);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Helmet");
        Main game = new Main();
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.add(game);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        while (true) {
            game.move();
            game.repaint();
            Thread.sleep(6);
        }
    }

    private void move() {
        player.move();
        items.forEach(Item::move);
        itemsGen.deleteBottomItems();
        itemsGen.deleteCollisionItems();
    }

    public void gameOver() {
        JOptionPane.showMessageDialog(this, "Your score is: " + player.getScore(),
                "Game Over", JOptionPane.ERROR_MESSAGE);
        System.exit(ABORT);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new Font("Trebuchet MS", Font.BOLD, 60));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawString(String.valueOf(player.getScore()), SCREEN_WIDTH / 2, 50);
        showHealth(g2d);
        player.paint(g2d);
        finalDoor.paint(g2d);
        items.forEach((x) -> x.paint(g2d));
    }

    void showHealth(Graphics2D g2d) {
        int pos = 0;
        for (int i = 0; i < player.getHealth(); i++) {
            g2d.drawImage(new ImageIcon(getClass().getResource(IMG_HEART)).getImage(), pos += 50, 20, 50, 50, null);
        }
    }

}
