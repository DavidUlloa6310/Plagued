package com.mygdx.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.sprites.Bullet;
import com.mygdx.game.sprites.DefaultZombie;
import com.mygdx.game.sprites.Zombie;

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        if ((fixtureA.getUserData() instanceof Bullet || fixtureB.getUserData() instanceof Bullet) && (fixtureA.getUserData() instanceof DefaultZombie || fixtureB.getUserData() instanceof DefaultZombie)) {
            Fixture bullet = fixtureA.getUserData() instanceof Bullet ? fixtureA : fixtureB;
            Fixture zombie = fixtureA.getUserData() instanceof DefaultZombie ? fixtureA : fixtureB;

            ((Bullet) bullet.getUserData()).remove = true;
            ((DefaultZombie) zombie.getUserData()).remove  = true;

        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
