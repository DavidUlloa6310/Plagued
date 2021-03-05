package com.mygdx.game.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.PlaguedGame;

public class PlayState extends State {

    private Texture labFloorOne = new Texture("labFloorOne.png");
    private Texture gunnerModel = new Texture("gunnerModel.png");

    public PlayState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, PlaguedGame.WIDTH / 2, PlaguedGame.HEIGHT / 2);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(float dt) {

    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        for (int x = 0; x < PlaguedGame.WIDTH / labFloorOne.getWidth(); x++) {
            for (int y = 0; y < PlaguedGame.HEIGHT / labFloorOne.getHeight(); y++) {
                sb.draw(labFloorOne, x * labFloorOne.getWidth(), y * labFloorOne.getHeight());
            }
        }

        sb.draw(gunnerModel, 0, 0);

        sb.end();
    }

    @Override
    public void dispose() {
        labFloorOne.dispose();
    }
}
