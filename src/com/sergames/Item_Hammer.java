package com.sergames;

import javax.swing.*;
import java.awt.*;

public class Item_Hammer extends FallingObject{
    public Item_Hammer(Main game, int xPosition, String img) {
        super(game, xPosition, img);
    }

    @Override
    public void paint(Graphics2D g) {
        super.paint(g);
        g.drawImage(super.imageIcon.getImage(), super.getX(), super.getY(), super.getWidth(), super.getHeight(), null);
    }
}
