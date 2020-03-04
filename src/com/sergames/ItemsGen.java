package com.sergames;

import java.util.Random;

public class ItemsGen {
    int[] positions = {110, 210, 310, 410, 510, 610};
    private Main game;

    public ItemsGen(Main game) {
        this.game = game;
    }

    int getRandom(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
    void addHammerItem() {
        game.fallingObjects.add(new Item_Hammer(game,getRandom(positions),"/img/fallingObject-hammer.png"));
    }

    void deleteCollisionItems() {
        game.fallingObjects.removeIf(fallingObject -> fallingObject.isCollisionPlayer());
    }
}
