package com.mygdx.game.sprites;

import com.badlogic.gdx.math.Vector2;

public abstract class Character {
    private Vector2 position;
    private Animation animation;

    public abstract void move();

}
