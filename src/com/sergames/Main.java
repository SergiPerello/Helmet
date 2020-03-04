package com.sergames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.sergames.Const.timeBetweenItems;

public class Main extends JPanel {
    private static JLabel label = new JLabel("La porta esta tancada");
    Player player = new Player(this);
    FinalDoor finalDoor = new FinalDoor(this);
    ItemsGen itemsGen = new ItemsGen(this);
    ArrayList<FallingObject> fallingObjects = new ArrayList<>();

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
        Timer timer = new Timer("MyTimer");
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                itemsGen.addHammerItem();
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, timeBetweenItems);
    }

    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame("Helmet");
        Main game = new Main();
        frame.setSize(Const.WIDTH, Const.HEIGHT);
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
        fallingObjects.removeIf(fallingObject -> fallingObject.getY() > Const.HEIGHT);
        fallingObjects.forEach(FallingObject::move);
        itemsGen.deleteCollisionItems();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        player.paint(g2d);
        finalDoor.paint(g2d);
        fallingObjects.forEach((x) -> x.paint(g2d));
    }

    public void showText(boolean bool) {
        label.setVisible(bool);
    }

}
