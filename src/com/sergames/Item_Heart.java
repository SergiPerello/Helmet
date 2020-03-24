package com.sergames;

import static com.sergames.Const.HEART_HEAL_AMOUNT;

public class Item_Heart extends Item {
    public Item_Heart(Game game, int xPosition, int fallingSpeed, String img) {
        super(game, xPosition, fallingSpeed, img);
    }

    @Override
    void action() {
        super.game.player.heal(HEART_HEAL_AMOUNT);
    }
}
