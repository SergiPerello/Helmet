package com.sergames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.sergames.Const.TIME_BETWEEN_ITEMS;

public class Main extends JPanel {
    private static JLabel label = new JLabel("La porta esta tancada");
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
        frame.setSize(Const.SCREEN_WIDTH, Const.HEIGHT);
        game.add(label);
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
        JOptionPane.showMessageDialog(this, "Your score is: " /*getScore();*/,
                "Game Over", JOptionPane.ERROR_MESSAGE);
        System.exit(ABORT);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        player.paint(g2d);
        finalDoor.paint(g2d);
        items.forEach((x) -> x.paint(g2d));
    }

    public void showText(boolean bool) {
        label.setVisible(bool);
    }

}
