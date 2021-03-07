package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.PlaguedGame;
import com.mygdx.game.screens.PlayScreen;

public abstract class GameCharacter extends Sprite {

    protected enum State {
        STANDING, RUNNING;
    }

    protected State currentState, previousState;
    public Body b2body;
    public World world;
    public TextureRegion characterStand;

    private Animation<TextureRegion> characterRun;

    private float stateTimer;
    private boolean runningRight;

    public GameCharacter(World world, PlayScreen screen, String name, int width, int height) {
        super(screen.getAtlas().findRegion(name));
        this.world = world;
        currentState = previousState = State.STANDING;
        stateTimer = 0f;
        runningRight = true;

        Array<TextureRegion> frames = new Array<>();
        for (int i = 1; i < 8; i++) {
            frames.add(new TextureRegion(getTexture(), (i * width) + 1, 1, width, height));
            System.out.println(i * width);
        }
        characterRun = new Animation<TextureRegion>(.1f, frames);
        frames.clear();

        defineCharacter();

        characterStand = new TextureRegion(getTexture(),getRegionX(), getRegionY(), width, height);
        setBounds(0, 0, width / PlaguedGame.PPM, height /PlaguedGame.PPM);
        setRegion(characterStand);

        b2body.setLinearDamping(7);
    }

    public void defineCharacter() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / PlaguedGame.PPM, 32 / PlaguedGame.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(6 / PlaguedGame.PPM);
        fdef.shape = shape;
        b2body.createFixture(fdef);
    }

    public void update(float dt) {
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        setRegion(getFrame(dt));
    }

    public TextureRegion getFrame(float dt) {
        currentState = getState();
        TextureRegion region;

        switch (currentState) {
            case RUNNING:
                region = characterRun.getKeyFrame(stateTimer, true);
                break;
            case STANDING:
            default:
                region = characterStand;
                break;
        }

        if ((b2body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
            region.flip(true, false);
            runningRight = false;
        } else if ((b2body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer + dt : 0;
        previousState = currentState;

        return region;
    }

    public State getState() {
        if (b2body.getLinearVelocity().x != 0 || b2body.getLinearVelocity().y != 0) {
            return State.RUNNING;
        } else {
            return State.STANDING;
        }
    }

}