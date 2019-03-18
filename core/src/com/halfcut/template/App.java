package com.halfcut.template;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.halfcut.template.screen.LoadingScreen;

public class App extends Game {
    static final public String TITLE = "GAME XYZ";
    static final public int WIDTH  = 128; //800;
    static final public int HEIGHT = 128; //450;
    static final public int SCALE  = 5;
    static final public int MIDDLE_WIDHT  = WIDTH / 2;
    static final public int MIDDLE_HEIGHT = HEIGHT / 2;

    public enum Mode { DESKTOP, HTML }
    static public Mode mode;

    public SpriteBatch   sb;
    public ShapeRenderer sr;

    @Override
    public void create () {
        sb = new SpriteBatch();
        sr = new ShapeRenderer();
        sr.setAutoShapeType(true);
        setScreen(new LoadingScreen(this));
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        super.render();
    }

    @Override
    public void dispose () {
        sb.dispose();
        sr.dispose();
    }
}
