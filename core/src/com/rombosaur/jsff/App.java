package com.rombosaur.jsff;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.rombosaur.jsff.screen.MainMenuScreen;
import com.rombosaur.jsff.screen.Screen;
import com.rombosaur.jsff.screen.loader.LoadingScreen;
import com.rombosaur.jsff.screen.loader.ScreenInstanceDefer;

public class App extends Game {
    static final public String TITLE = "game title abc dfg";
    static final public String SUB_TITLE = "ludum dare 45";
    static final public String BRAND = "rombosaur studios";
    static final public String VERSION = "v0.1";
    static final public int WIDTH  = 128; //227;
    static final public int HEIGHT = 128;
    static final public int TILE_SIZE = 8;
    static final public int SCALE  = 5;
    static final public int MIDDLE_WIDTH = WIDTH / 2;
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
        final App self = this;

        ScreenInstanceDefer mmScreen = new ScreenInstanceDefer() {
            @Override
            public Screen newInstance() {
                return new MainMenuScreen(self);
                //return new WinScreen(self);
                //return new GameOverScreen(self);
            }
        };
        setScreen(new LoadingScreen(this, mmScreen,null));
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
