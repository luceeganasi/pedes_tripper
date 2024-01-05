package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Pedes;

import java.awt.*;

public class GameOverScreen implements Screen {

    private Viewport viewport;
    private Stage stage;

    private Game game;
    public GameOverScreen (Game game){
        this.game = game;
        viewport = new FitViewport(Pedes.V_WIDTH, Pedes.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((Pedes) game).batch);

        BitmapFont font = new BitmapFont(Gdx.files.internal("arcade.fnt")); // Replace with your font file
        font.getData().setScale(3);
        BitmapFont font1 = new BitmapFont(Gdx.files.internal("arcade.fnt")); // Replace with your font file
        font1.getData().setScale(1);

        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
        Label.LabelStyle labelStyle1 = new Label.LabelStyle(font1, Color.WHITE);


        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label gameOverLabel = new Label("GAME OVER", labelStyle);
        Label playAgainLabel = new Label("Click to Play Again", labelStyle1);

        table.add(gameOverLabel).expandX();
        table.row();
        table.add(playAgainLabel).expandX().padTop(10f);


        stage.addActor(table);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()) {
            game.setScreen(new PlayScreen((Pedes) game));
            PlayScreen.music1.stop();
            dispose();
        }

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {

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
        stage.dispose();

    }
}
