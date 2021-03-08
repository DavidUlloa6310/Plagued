package com.mygdx.game.sprites;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.screens.PlayScreen;

public class Gunner extends Hero {
    private PlayScreen screen;

    public Gunner(PlayScreen screen) {
        super(screen, "gunner", 29, 31);
        this.screen = screen;
    }

    @Override
    public void passive() {

    }

    @Override
    public void primary() {
        getBullets().add(new Bullet(b2body.getPosition().x, b2body.getPosition().y,43, 26, isRunningRight(), screen));
        System.out.println(b2body.getPosition().x);
        System.out.println(b2body.getPosition().y);
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
