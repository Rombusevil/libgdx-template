package com.rombosaur.jsff.screen;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Matrix4;
import com.rombosaur.jsff.App;
import com.rombosaur.jsff.assets.Assets;
import com.rombosaur.jsff.util.Pico8Colors;

/**
 * @author halfcutdev
 * @since 22/12/2017
 */
public class LoadingScreen extends Screen {

    static final public int BAR_WIDTH        = 38;
    static final public int BAR_HEIGHT       =  2;
    static final public int BORDER_GAP       =  1;
    static final public int BORDER_THICKNESS =  1;
    static final public Color BACKGROUND_COLOUR = Pico8Colors.DARK_BLUE;

    private AssetManager assets;
    private boolean loaded;

    public LoadingScreen(App app) {
        super(app);
        assets = new AssetManager();
        assets.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));

        assets.load("packed/textures.atlas", TextureAtlas.class);
        loadMap("map/test2.tmx");
        loadFont("fonts/pico8_05.fnt");
    }

    @Override
    public void update(float delta) {
        if(assets.update()) {
            Assets.get().provide(assets);
            if(!loaded) {
                loaded = true;
                transitionToScreen(new MainMenuScreen(app));
            }
        }
    }

    @Override
    public void draw(SpriteBatch sb, ShapeRenderer sr) {
        float x, y, width, height;

        // Reset the camera's projection matrix.
        Matrix4 uiMatrix = getSceneCamera().combined.cpy();
        uiMatrix.setToOrtho2D(0, 0, App.WIDTH, App.HEIGHT);
        sr.setProjectionMatrix(uiMatrix);
        sb.setProjectionMatrix(uiMatrix);

        sr.begin(ShapeRenderer.ShapeType.Filled);

            // Background
            sr.setColor(BACKGROUND_COLOUR);
            sr.rect(0, 0, App.WIDTH * App.SCALE, App.HEIGHT * App.SCALE);

            // Outer
            width  = BAR_WIDTH  + (BORDER_THICKNESS + BORDER_GAP) * 2;
            height = BAR_HEIGHT + (BORDER_THICKNESS + BORDER_GAP) * 2;
            x = (App.WIDTH  - width)  / 2;
            y = (App.HEIGHT - height) / 2;
            sr.setColor(Color.WHITE);
            sr.rect(x, y, width, height);

            // Inner
            width  = BAR_WIDTH  + (BORDER_GAP) * 2;
            height = BAR_HEIGHT + (BORDER_GAP) * 2;
            x = (App.WIDTH  - width)  / 2;
            y = (App.HEIGHT - height) / 2;
            sr.setColor(BACKGROUND_COLOUR);
            sr.rect(x, y, width, height);

            // Bar
            width  = BAR_WIDTH;
            height = BAR_HEIGHT;
            x = (App.WIDTH  - width)  / 2;
            y = (App.HEIGHT - height) / 2;

            // If in HTML mode, start the loading bar from 50% progress so it appears to continue from the splash screen.
            if(App.mode == App.Mode.HTML) {
                width = (1 + assets.getProgress()) * (width * 0.5f);
            } else {
                width = assets.getProgress() * width;
            }

            // Draw the bar.
            sr.setColor(Color.WHITE);
            sr.rect(x, y, width, height);

        sr.end();
    }

    private void loadFont(String ref) {
        assets.load(ref, BitmapFont.class);
    }
    private void loadMap(String ref) {
        assets.load(ref, TiledMap.class);
    }
    private void loadSFX(String ref) {
        assets.load(ref, Sound.class);
    }
    private void loadTexture(String ref) {
        assets.load(ref, Texture.class);
    }
}
