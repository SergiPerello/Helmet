package com.sergames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JPanel {
    Player player = new Player(this);
    FinalDoor finalDoor = new FinalDoor(this);
    FallingObject fallingObject = new FallingObject(this);

    private static JLabel label = new JLabel("La porta esta tancada");

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
            Thread.sleep(4);
        }
    }

    private void move() {
        player.move();
        fallingObject.move();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        player.paint(g2d);
        finalDoor.paint(g2d);
        fallingObject.paint(g2d);
    }
    public void showText(boolean bool){
        label.setVisible(bool);
    }

}
