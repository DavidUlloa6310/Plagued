package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.PlaguedGame;

public class PlayScreen implements Screen {

    private PlaguedGame game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private Texture labFloorOne = new Texture("labFloorOne.png");
    private Texture gunnerModel = new Texture("gunnerModel.png");

    public PlayScreen(PlaguedGame game) {
        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(PlaguedGame.WIDTH, PlaguedGame.HEIGHT, gameCam);

        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(gameCam.combined);

        game.batch.begin();

        for (int x = 0; x < PlaguedGame.WIDTH / labFloorOne.getWidth(); x++) {
            for (int y = 0; y < PlaguedGame.HEIGHT / labFloorOne.getHeight(); y++) {
                game.batch.draw(labFloorOne, x * labFloorOne.getWidth(), y * labFloorOne.getHeight());
            }
        }

        game.batch.draw(gunnerModel, 0, 0);


        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
