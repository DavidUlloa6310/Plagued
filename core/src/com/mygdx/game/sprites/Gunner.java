package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.screens.PlayScreen;

public class Gunner extends Hero {
    /*
    Gunner hero which inherits from Hero.
     */

    public Gunner(PlayScreen screen, int x, int y) {
        super(screen, "gunner", 29, 31, x, y);
    }

    @Override
    public void passive() {

    }

    @Override
    public void primary() {
        getBullets().add(new Bullet("gunnerBullet", b2body.getPosition().x, b2body.getPosition().y, 13, 6, isRunningRight(), getArrowDirection(), getScreen()));
    }

    @Override
    public void shift() {

    }

    @Override
    public void special() {

    }

    @Override
    public void ultimate() {

    }
}
