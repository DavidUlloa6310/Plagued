package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.screens.PlayScreen;

public class Gunner extends Hero {

    public Gunner(PlayScreen screen) {
        super(screen, "gunner", 29, 31);
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
