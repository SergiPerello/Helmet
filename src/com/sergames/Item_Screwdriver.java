package com.sergames;

import static com.sergames.Const.LIGHT_ATTACK;

public class Item_Screwdriver extends Item {
    public Item_Screwdriver(Game game, int xPosition, int fallingSpeed, String img) {
        super(game, xPosition, fallingSpeed, img);
    }

    @Override
    void action() {
        super.game.player.injure(LIGHT_ATTACK);
        super.game.player.confuse();

    }
}
