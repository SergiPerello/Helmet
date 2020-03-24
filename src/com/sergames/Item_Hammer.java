package com.sergames;

import static com.sergames.Const.HEAVY_ATTACK;

public class Item_Hammer extends Item {
    public Item_Hammer(Game game, int xPosition, String img) {
        super(game, xPosition, img);
    }

    @Override
    void action() {
        super.game.player.injure(HEAVY_ATTACK);
        super.game.player.setInitialPosition();
    }
}
