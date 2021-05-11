package com.mygdx.game.sprites;

import com.mygdx.game.screens.PlayScreen;

public abstract class Zombie extends GameCharacter {
    /*
    Zombie class, which inherits from GameCharacter
     */

    public boolean remove = false;

    public Zombie(PlayScreen screen, String name, int width, int height, int x, int y) {
        super(screen, name, width, height, x, y);
    }

    public abstract void move(float x, float y);
    public abstract void update(float dt, Hero character);
}
