package com.mygdx.game.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.PlaguedGame;

public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private Integer lives;

    private float timeCount;
    private float zombieTime;
    private boolean isPaused;
    private float pausedTimer;

    private static Integer score;
    private Integer round;

    private final static int TEXT_SIZE = 2;

    private static Label scoreLabel;
    private static Label scoreTextLabel;

    private Label timeLabel;
    private static Label timeTextLabel;

    private Label livesLabel;
    private static Label livesTextLabel;

    private Label roundLabel;
    private static Label roundTextLabel;

    public Hud(SpriteBatch sb) {
        worldTimer = 0;
        timeCount = 0;
        zombieTime = 0;

        isPaused = false;
        pausedTimer = 15;

        score = 0;
        round = 1;
        lives = 3;

        viewport = new FitViewport(PlaguedGame.WIDTH, PlaguedGame.HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        timeLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeTextLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel.setFontScale(TEXT_SIZE);
        timeTextLabel.setFontScale(TEXT_SIZE);

        scoreLabel =new Label(String.format("%04d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreTextLabel  = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel.setFontScale(TEXT_SIZE);
        scoreTextLabel.setFontScale(TEXT_SIZE);

        livesTextLabel = new Label("LIVES", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        livesLabel = new Label(String.format("%01d", lives), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        livesTextLabel.setFontScale(TEXT_SIZE);
        livesLabel.setFontScale(TEXT_SIZE);

        roundTextLabel = new Label("ROUND", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        roundLabel = new Label(String.format("%02d", round), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        roundTextLabel.setFontScale(TEXT_SIZE);
        roundLabel.setFontScale(TEXT_SIZE);

        table.add(livesTextLabel).expandX().padTop(10);
        table.add(roundTextLabel).expandX().padTop(10);
        table.add(scoreTextLabel).expandX().padTop(10);
        table.add(timeTextLabel).expandX().padTop(10);

        table.row();
        table.add(livesLabel);
        table.add(roundLabel);
        table.add(scoreLabel);
        table.add(timeLabel);

        stage.addActor(table);

    }

    public void update(float dt) {
        timeCount += dt;
        zombieTime += dt;
        if (timeCount >= 1) {
            worldTimer++;
            timeLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }

        if (zombieTime >= getRoundTime()) {
            isPaused = !isPaused;
            if (isPaused) {
                timeLabel.setColor(Color.RED);
                timeTextLabel.setColor(Color.RED);
            } else {
                timeLabel.setColor(Color.WHITE);
                timeTextLabel.setColor(Color.WHITE);
                round++;
            }
            roundLabel.setText(String.format("%02d", round));
            zombieTime = 0;
        }
    }

    public static void addScore(int value){
        score += value;
        scoreLabel.setText(String.format("%06d", score));
    }

    public int getRound() {
        return round;
    }

    public boolean isPaused() {
        return isPaused;
    }

    @Override
    public void dispose() { stage.dispose(); }

    public int getRoundTime() {
        if (isPaused) return 15;
        return 30;
    }

}
