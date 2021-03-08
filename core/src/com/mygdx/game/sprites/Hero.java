package com.mygdx.game.sprites;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.screens.PlayScreen;

public abstract class Hero extends GameCharacter{

    private Array<Bullet> bullets;

    public Hero(PlayScreen screen, String name, int width, int height) {
        super(screen, name, width, height);
        bullets = new Array<Bullet>();
    }

    public Array<Bullet> getBullets() {
        return bullets;
    }

    public abstract void passive();
    public abstract void primary();
    public abstract void shift();
    public abstract void special();
    public abstract void ultimate();
}
