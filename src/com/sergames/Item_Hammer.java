package com.sergames;

import java.awt.*;

import static com.sergames.Const.HEAVY_ATTACK;

public class Item_Hammer extends FallingObject {
    public Item_Hammer(Main game, int xPosition, String img) {
        super(game, xPosition, img);
    }

    @Override
    void action() {
        super.game.player.injure(HEAVY_ATTACK);
        super.game.player.setInitialPosition();
    }
}
