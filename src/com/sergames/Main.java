package com.sergames;

import javax.swing.*;

import static com.sergames.Const.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame(APP_TITLE);
        frame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Game game = new Game();
        frame.add(game);
        frame.setVisible(true);
        while (true) {
            game.move();
            game.repaint();
            Thread.sleep(10);
        }
    }
}
