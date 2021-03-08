package com.mygdx.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.PlaguedGame;
import com.mygdx.game.screens.PlayScreen;

public class Bullet {
    private final int SPEED = 1;
    private static Texture texture;

    float x, y;

    public boolean remove = false;

    public Bullet(float x, float y) {
        this.x = x;
        this.y = y;

        if (texture == null)
            texture = new Texture("bullet.png");
    }

    public void update(float dt) {
        x += SPEED * dt;
        if (x > Gdx.graphics.getWidth()) {
            remove  = true;
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x / PlaguedGame.PPM, y / PlaguedGame.PPM);
    }
}
