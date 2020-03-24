package com.sergames;

import static com.sergames.Const.HEART_HEAL_AMOUNT;

public class Item_Shield extends Item {
    public Item_Shield(Game game, int xPosition, String img) {
        super(game, xPosition, img);
    }

    @Override
    void action() {
        super.game.player.setImmune();

    }
}