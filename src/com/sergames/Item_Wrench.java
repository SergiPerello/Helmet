package com.sergames;

import static com.sergames.Const.LIGHT_ATTACK;

public class Item_Wrench extends Item {
    public Item_Wrench(Main game, int xPosition, String img) {
        super(game, xPosition, img);
    }

    @Override
    void action() {
        super.game.player.injure(LIGHT_ATTACK);
        super.game.player.stun();

    }
}
