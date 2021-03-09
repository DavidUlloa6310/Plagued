package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.PlaguedGame;
import com.mygdx.game.sprites.*;

public class PlayScreen implements Screen {

    private Hero player;

    private PlaguedGame game;
    private TextureAtlas atlas;

    private OrthographicCamera gameCam;
    private Viewport gamePort;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    private Texture gunnerModel;
    private Array<Bullet> bulletToRemove;

    public PlayScreen(PlaguedGame game) {
        atlas = new TextureAtlas("plagued.atlas");

        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(PlaguedGame.WIDTH / PlaguedGame.PPM, PlaguedGame.HEIGHT / PlaguedGame.PPM, gameCam);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1 / PlaguedGame.PPM);

        gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        world = new World(new Vector2(0, 0), true);
        b2dr = new Box2DDebugRenderer();

        player = new Gunner(this);

    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public void handleInput(float dt) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.F))
            player.primary();
        if (Gdx.input.isKeyPressed(Input.Keys.W))
            player.b2body.applyLinearImpulse(new Vector2(0, .25f), player.b2body.getWorldCenter(), true);
        if (Gdx.input.isKeyPressed(Input.Keys.A))
            player.b2body.applyLinearImpulse(new Vector2(-.25f, 0), player.b2body.getWorldCenter(), true);
        if (Gdx.input.isKeyPressed(Input.Keys.D))
            player.b2body.applyLinearImpulse(new Vector2(.25f, 0), player.b2body.getWorldCenter(), true);
        if (Gdx.input.isKeyPressed(Input.Keys.S))
            player.b2body.applyLinearImpulse(new Vector2(0, -.25f), player.b2body.getWorldCenter(), true);
    }

    public void update(float dt) {

        bulletToRemove = new Array<Bullet>();

        handleInput(dt);

        player.update(dt);

        world.step(1 / 60f, 6, 2);

        for (Bullet bullet : player.getBullets()) {
            bullet.update(dt);
            if (bullet.remove) {
                bulletToRemove.add(bullet);
                world.destroyBody(bullet.b2body);
            }
        }

        player.getBullets().removeAll(bulletToRemove, true);

        gameCam.position.x = player.b2body.getPosition().x;
        gameCam.position.y = player.b2body.getPosition().y;

        gameCam.update();
        renderer.setView(gameCam);
    }

    public World getWorld() {
        return world;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        b2dr.render(world, gameCam.combined);
        game.batch.setProjectionMatrix(gameCam.combined);

        game.batch.begin();
        player.draw(game.batch);
        for (Bullet bullet : player.getBullets()) {
            bullet.draw(game.batch);
        }
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

    public float getWorldWidth() {
        //FIX THIS
        return gamePort.getWorldWidth();
    }

    public float getWorldHeight() {
        //FIX THIS
        return gamePort.getWorldHeight();
    }
}
