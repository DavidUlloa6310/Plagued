package com.mygdx.game.sprites;

import com.mygdx.game.screens.PlayScreen;

public abstract class Zombie extends GameCharacter {

    public boolean remove = false;

    public Zombie(PlayScreen screen, String name, int width, int height) {
        super(screen, name, width, height);
        b2body.setUserData(this);
    }

    public abstract void move(float x, float y);
    public abstract void update(float dt, Hero character);
}
