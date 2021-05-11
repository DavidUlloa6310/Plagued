package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.PlaguedGame;
import com.mygdx.game.screens.PlayScreen;

public class Bullet extends Sprite implements Disposable {
    /*
    Represents the bullets that can be fired from any GameCharacter.
     */
    private final int SPEED = 1000;

    float x, y;
    float worldWidth, worldHeight;

    public boolean remove = false;

    public Body b2body;
    public World world;

    private Animation<TextureRegion> bulletAnimation;
    private float stateTimer = 0f;
    private DIRECTION direction;
    boolean facingRight;

    public Bullet(String name, float x, float y, int width, int height, boolean facingRight, DIRECTION direction, PlayScreen screen) {
        super(screen.getAtlas().findRegion(name));
        this.world = screen.getWorld();
        this.x = x;
        this.y = y;
        this.direction = direction;

        this.worldWidth = screen.getWorldWidth();
        this.worldHeight = screen.getWorldHeight();

        Array<TextureRegion> frames = new Array<>();
        for (int i = 0; i < 4; i++) {
            frames.add(new TextureRegion(getTexture(), (i * width) + getRegionX(), getRegionY(), width, height));
        }
        bulletAnimation = new Animation<TextureRegion>(.5f, frames);

        defineBullet(x, y);
        setBounds(0, 0, width / PlaguedGame.PPM, height /PlaguedGame.PPM);
        setRegion(frames.get(0));

        switch (direction) {
            case UP:
                b2body.setLinearVelocity(new Vector2(0, 10f));
                break;
            case UP_RIGHT:
                b2body.setLinearVelocity(new Vector2(10f, 10f));
                break;
            case UP_LEFT:
                b2body.setLinearVelocity(new Vector2(-10f, 10f));
                break;
            case DOWN:
                b2body.setLinearVelocity(new Vector2(0, -10f));
                break;
            case DOWN_RIGHT:
                b2body.setLinearVelocity(new Vector2(10f, -10f));
                break;
            case DOWN_LEFT:
                b2body.setLinearVelocity(new Vector2(-10f, -10f));
                break;
            case LEFT:
                b2body.setLinearVelocity(new Vector2(-10f, 0));
                break;
            case RIGHT:
                b2body.setLinearVelocity(new Vector2(10f, 0));
                break;
        }

    }

    public void defineBullet(float x, float y) {
        BodyDef bdef = new BodyDef();
        switch (direction) {
            case RIGHT:
                bdef.position.set(x + .1f, y);
                break;
            case LEFT:
                bdef.position.set(x - .1f, y);
                break;
            case UP_RIGHT:
                bdef.position.set(x + .1f, y + .1f);
                break;
            case UP:
                bdef.position.set(x, y + .1f);
                break;
            case UP_LEFT:
                bdef.position.set(x - .1f, y + .1f);
                break;
            case DOWN:
                bdef.position.set(x, y - .1f);
                break;
            case DOWN_LEFT:
                bdef.position.set(x - .1f, y - .1f);
            case DOWN_RIGHT:
                bdef.position.set(x + .1f, y - .1f);
                break;
        }

        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / PlaguedGame.PPM);
        fdef.shape = shape;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData(this);
    }

    public void update(float dt) {

        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));

        //FIX REMOVE IF BIGGER THAN WORLD SIZE.
        if (b2body.getPosition().x < .15 || b2body.getPosition().y < .15 || b2body.getPosition().x > worldWidth - .15 || b2body.getPosition().y > worldHeight - .15) {
            remove = true;
        }
    }

    public TextureRegion getFrame(float dt) {
        TextureRegion region = bulletAnimation.getKeyFrame(stateTimer, true);

        if ((direction == DIRECTION.LEFT || direction == DIRECTION.UP_LEFT || direction == DIRECTION.DOWN_LEFT) && !region.isFlipX()) {
            region.flip(true, false);
        } else if ((direction == DIRECTION.RIGHT || direction == DIRECTION.UP_RIGHT || direction == DIRECTION.DOWN_RIGHT) && region.isFlipX()) {
            region.flip(true, false);
        }

        stateTimer += dt;

        return region;
    }

    public void dispose() {
        b2body.setLinearVelocity(new Vector2(0,0));
    }
}
