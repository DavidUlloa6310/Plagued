package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.PlaguedGame;
import com.mygdx.game.screens.PlayScreen;

public class Bullet extends Sprite {
    private final int SPEED = 1000;

    float x, y;

    public boolean remove = false;

    public Body b2body;
    public World world;

    private Animation<TextureRegion> bulletAnimation;
    private float stateTimer = 0f;
    private boolean facingRight;

    public Bullet(float x, float y, int width, int height, boolean facingRight, PlayScreen screen) {
        super(screen.getAtlas().findRegion("shuriken"));
        this.world = screen.getWorld();
        this.x = x;
        this.y = y;
        this.facingRight = facingRight;

        Array<TextureRegion> frames = new Array<>();
        for (int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(getTexture(), (i * width) + 1, getRegionY(), width, height));
        }
        bulletAnimation = new Animation<TextureRegion>(.1f, frames);
        frames.clear();

        defineBullet(x, y);
        setBounds(0, 0, width / PlaguedGame.PPM, height /PlaguedGame.PPM);

        if (facingRight) {
            b2body.setLinearVelocity(new Vector2(10f, 0));
        } else {
            b2body.setLinearVelocity(new Vector2(-10f, 0));
        }

    }

    public void defineBullet(float x, float y) {
        BodyDef bdef = new BodyDef();
        if (facingRight) {
            bdef.position.set(x + .1f, y);
        } else {
            bdef.position.set(x - .1f, y);
        }
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / PlaguedGame.PPM);
        fdef.shape = shape;
        b2body.createFixture(fdef);
    }

    public void update(float dt) {

        if (x > Gdx.graphics.getWidth()) {
            remove  = true;
        }

        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt) {
        TextureRegion region = bulletAnimation.getKeyFrame(stateTimer, true);
        if (!facingRight && !isFlipX()) {
            flip(true, false);
        } else if (facingRight && isFlipX()) {
            flip(true, false);
        }

        return region;
    }
}
