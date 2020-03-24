package com.sergames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import static com.sergames.Const.*;

public class Game extends JPanel {
    ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
    ScheduledFuture<?> scheduledFuture;
    Player player = new Player(this);
    FinalDoor finalDoor = new FinalDoor(this);
    ItemsGen itemsGen = new ItemsGen(this);
    ArrayList<Item> items = new ArrayList<>();
    boolean playing = false;
    Runnable addItems = () -> {
        itemsGen.addItem();
    };
    int timeBetweenItems = TIME_BETWEEN_ITEMS;
    private JButton btnPlay = new JButton(BTN_PLAY);
    private JLabel lblTopEntries;

    public Game() {
        lblTopEntries = new JLabel(DisplayTopEntries());
        btnPlay.setVerticalAlignment(SwingConstants.CENTER);
        btnPlay.addActionListener(actionEvent -> {
            playing = true;
            restartTimer();
        });
        add(btnPlay);
        add(lblTopEntries);

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

    private String DisplayTopEntries() {
        ArrayList<Entry> topEntries = JsonManager.read();
        String result = "<html><p style=\"text-align:center;font-size:15px;\">TOP 5</p><br/>";
        if (topEntries.size() <= 0) {
            result += "(Cap puntuació registrada)";
        } else {
            for (Entry e : topEntries) {
                result += e.getPlayer() + " ➜ " + e.getScore() + " points<br/>";
            }
        }
        result += "</html>";
        return result;
    }

    void restartTimer() {
        if (scheduledFuture != null) scheduledFuture.cancel(true);
        scheduledFuture = ses.scheduleAtFixedRate(addItems, 0, timeBetweenItems, TimeUnit.MILLISECONDS);
        System.out.println(timeBetweenItems);
        timeBetweenItems /= DIFFICULTY_RATE;
    }

    public void move() {
        if (playing) {
            player.move();
            items.forEach(Item::move);
            itemsGen.deleteBottomItems();
            itemsGen.deleteCollisionItems();
        }
    }

    public void gameOver() {
        String msg = "<html>Your score is: " + player.getScore() + "<br>Write your name:</html>";
        String playerName = JOptionPane.showInputDialog(this, msg, "Game Over", JOptionPane.ERROR_MESSAGE);
        JsonManager.write(new Entry(playerName, player.getScore()));
        System.exit(ABORT);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        if (playing) {
            g2d.setFont(new Font("Trebuchet MS", Font.BOLD, 60));
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawString(String.valueOf(player.getScore()), SCREEN_WIDTH / 1.2f, 70);
            showHealth(g2d);
            player.paint(g2d);
            finalDoor.paint(g2d);
            items.forEach((x) -> x.paint(g2d));
            lblTopEntries.setVisible(false);
            btnPlay.setVisible(false);
        } else {
            lblTopEntries.setVisible(true);
            btnPlay.setVisible(true);
        }
    }

    void showHealth(Graphics2D g2d) {
        int pos = 0;
        for (int i = 0; i < player.getHealth(); i++) {
            g2d.drawImage(new ImageIcon(getClass().getResource(IMG_HEART)).getImage(), pos += 50, 20, 50, 50, null);
        }
    }

    void difficulty() {
        if (player.getScore() != 0 && player.getScore() % POINTS_TO_INCREASE_DIFFICULTY == 0) {
            itemsGen.addDifficulty();
            restartTimer();
        }
    }

}
