package com.sergames;

import static com.sergames.Const.HEART_HEAL_AMOUNT;

public class Item_Shield extends Item {
    public Item_Shield(Game game, int xPosition, int fallingSpeed, String img) {
        super(game, xPosition, fallingSpeed, img);
    }

    @Override
    void action() {
        super.game.player.setImmune();

    }
}