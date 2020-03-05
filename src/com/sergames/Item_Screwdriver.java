package com.sergames;

public class Item_Screwdriver extends FallingObject {
    public Item_Screwdriver(Main game, int xPosition, String img) {
        super(game, xPosition, img);
    }

    @Override
    void action() {
        super.game.player.confuse();
    }
}
