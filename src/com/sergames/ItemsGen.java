package com.sergames;

import java.util.Random;

public class ItemsGen {
    int[] positions = {110, 210, 310, 410, 510, 610};
    private Main game;

    public ItemsGen(Main game) {
        this.game = game;
    }

    private ItemType randomItem() {
        int pick = new Random().nextInt(ItemType.values().length);
        return ItemType.values()[pick];
    }

    int getRandom(int[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    void addItem() {
        ItemType itemType = randomItem();
        switch (itemType) {
            case HAMMER:
                game.items.add(new Item_Hammer(game, getRandom(positions), "/img/fallingObject-hammer.png"));
                break;
            case SCREWDRIVER:
                game.items.add(new Item_Screwdriver(game, getRandom(positions), "/img/fallingObject-screwdriver.png"));
                break;
            case WRENCH:
                game.items.add(new Item_Wrench(game, getRandom(positions), "/img/fallingObject-wrench.png"));
                break;
        }
    }

    void deleteCollisionItems() {
        game.items.removeIf(Item::isCollisionPlayer);
    }

    enum ItemType {HAMMER, SCREWDRIVER, WRENCH}
}
