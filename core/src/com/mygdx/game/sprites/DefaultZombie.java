package com.mygdx.game.sprites;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.screens.PlayScreen;

public class DefaultZombie extends Zombie {
    public DefaultZombie(PlayScreen screen) {
        super(screen, "zdefault", 21, 32);
        b2body.setUserData(this);
        System.out.println(b2body.getUserData() instanceof DefaultZombie);
        System.out.println("Created Default Zombie");
    }

    public void update(float dt, Hero character) {
        super.update(dt);
        move(character.getX(), character.getY());
    }

    @Override
    public void move(float x, float y) {
        if (getX() < x)
            b2body.applyLinearImpulse(new Vector2(.075f, 0), b2body.getWorldCenter(), true);
        else
            b2body.applyLinearImpulse(new Vector2(-.075f, 0), b2body.getWorldCenter(), true);

        if (getY() < y)
            b2body.applyLinearImpulse(new Vector2(0, .075f), b2body.getWorldCenter(), true);
        else
            b2body.applyLinearImpulse(new Vector2(0, -.075f), b2body.getWorldCenter(), true);
    }
}
