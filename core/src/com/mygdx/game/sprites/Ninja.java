package com.mygdx.game.sprites;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.screens.PlayScreen;

public class Ninja extends Hero {
    /*
    Ninja Hero.
     */
    public Ninja(PlayScreen screen, int x, int y) {
        super(screen, "ninja", 25, 30, x, y);
    }

    @Override
    public void passive() {

    }

    @Override
    public void primary() {
        getBullets().add(new Bullet("shuriken", b2body.getPosition().x, b2body.getPosition().y,43, 26, isRunningRight(), getArrowDirection(), getScreen()));
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
