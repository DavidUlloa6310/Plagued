package com.mygdx.game.sprites;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.mygdx.game.PlaguedGame;
import com.mygdx.game.screens.PlayScreen;

public class DefaultZombie extends Zombie {
    /*
    The Default Zombie
     */
    public DefaultZombie(PlayScreen screen, int x, int y) {
        super(screen, "zdefault", 21, 32, x, y);
    }

    public void update(float dt, Hero character) {
        super.update(dt);
        move(character.getX(), character.getY());
    }

    @Override
    public void defineCharacter(int x, int y) {
        BodyDef bdef = new BodyDef();
        bdef.position.set(x / PlaguedGame.PPM, y / PlaguedGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / PlaguedGame.PPM);
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
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
