package com.mygdx.game.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.sprites.Bullet;
import com.mygdx.game.sprites.Zombie;

public class WorldContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
//        Fixture fixtureA = contact.getFixtureA();
//        Fixture fixtureB = contact.getFixtureB();
//
//        if (fixtureA == null || fixtureB == null)
//            return;
//        if ((fixtureA.getUserData() instanceof Bullet || fixtureB.getUserData() instanceof Bullet) && (fixtureA.getUserData().equals("zombie") || fixtureB.getUserData().equals("zombie"))) {
//            Fixture bullet = fixtureA.getUserData() instanceof Bullet ? fixtureA : fixtureB;
//            Fixture zombie = fixtureA.getUserData() instanceof Zombie ? fixtureA : fixtureB;
//
//            ((Bullet) bullet.getUserData()).remove = true;
//            ((Zombie) zombie.getUserData()).remove  = true;
//
//        }
        Gdx.app.log("Begin Contact", "");
    }

    @Override
    public void endContact(Contact contact) {
        Gdx.app.log("End Contact", "");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
