package com.rombosaur.jsff.engine.screen.loader;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class LoaderUtils {
    private AssetManager assetsManager;

    public LoaderUtils(AssetManager assetManager) {
        this.assetsManager = assetManager;
    }

    public void loadFont(String ref) {
        assetsManager.load(ref, BitmapFont.class);
    }
    public void loadMap(String ref) {
        assetsManager.load(ref, TiledMap.class);
    }
    public void loadSFX(String ref) {
        assetsManager.load(ref, Sound.class);
    }
    public void loadTexture(String ref) {
        assetsManager.load(ref, Texture.class);
    }
}
