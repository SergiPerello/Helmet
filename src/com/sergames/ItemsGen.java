package com.sergames;

import java.util.Random;

import static com.sergames.Const.*;

public class ItemsGen {
    private int[] positions = {110, 210, 310, 410, 510, 610};
    private int fallingSpeed = 1;
    private Game game;

    public ItemsGen(Game game) {
        this.game = game;
    }

    void addDifficulty() {
        fallingSpeed *= DIFFICULTY_RATE;
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
                game.items.add(new Item_Hammer(game, getRandom(positions), fallingSpeed, IMG_HAMMER));
                break;
            case HEART:
                game.items.add(new Item_Heart(game, getRandom(positions), fallingSpeed, IMG_HEART));
                break;
            case SCREWDRIVER:
                game.items.add(new Item_Screwdriver(game, getRandom(positions), fallingSpeed, IMG_SCREWDRIVER));
                break;
            case SHIELD:
                game.items.add(new Item_Shield(game, getRandom(positions), fallingSpeed, IMG_SHIELD));
                break;
            case WRENCH:
                game.items.add(new Item_Wrench(game, getRandom(positions), fallingSpeed, IMG_WRENCH));
                break;
        }
    }

    void deleteCollisionItems() {
        game.items.removeIf(Item::isCollisionPlayer);
    }

    void deleteBottomItems() {
        game.items.removeIf(item -> item.getY() > SCREEN_HEIGHT);
    }

    enum ItemType {HAMMER, HEART, SCREWDRIVER, SHIELD, WRENCH}
}
