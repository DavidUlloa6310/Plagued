package com.mygdx.game.sprites;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.screens.PlayScreen;

public abstract class Hero extends GameCharacter{

    private Array<Bullet> bullets;
    private Array<Bullet> bulletsToRemove;

    private PlayScreen screen;

    public Hero(PlayScreen screen, String name, int width, int height, int x, int y) {
        super(screen, name, width, height, x, y);
        bullets = new Array<Bullet>();
        bulletsToRemove = new Array<Bullet>();
        this.screen = screen;
    }

    public Array<Bullet> getBullets() {
        return bullets;
    }
    public Array<Bullet> getBulletsToRemove() { return bulletsToRemove; }
    public PlayScreen getScreen() { return screen; }

    public abstract void passive();
    public abstract void primary();
    public abstract void shift();
    public abstract void special();
    public abstract void ultimate();
}
