package com.rombosaur.jsff.screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.rombosaur.jsff.App;
import com.rombosaur.jsff.engine.utils.RectangleRenderer;
import com.rombosaur.jsff.engine.assets.Assets;
import com.rombosaur.jsff.engine.screen.Screen;
import com.rombosaur.jsff.engine.screen.loader.LoadingScreen;
import com.rombosaur.jsff.engine.screen.loader.ScreenInstanceDefer;
import com.rombosaur.jsff.engine.text.TextWriter;
import com.rombosaur.jsff.engine.utils.Gamepad;
import com.rombosaur.jsff.engine.utils.Pico8Colors;

/**
 * @author rombus
 * @since 07/04/2019
 */
public class WinScreen extends Screen {
    private RectangleRenderer rectangleRenderer;
    private TextWriter writer;

    public WinScreen(App app) {
        super(app);
        Assets assets = Assets.get();
        System.out.println(assets.getAssetManager().getAssetNames());

        BitmapFont font = assets.getFont("fonts/pico8_05.fnt");
        writer = new TextWriter(font, 6.3f);
        rectangleRenderer = new RectangleRenderer();
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void update(float delta) {
        //TODO: add debounce timer/flag
        if (Gamepad.isButtonXPressed()) {
            ScreenInstanceDefer mm = new ScreenInstanceDefer() {
                @Override public Screen newInstance() {
                    return new MainMenuScreen(app);
                }
            };
            transitionToScreen(new LoadingScreen(app, mm, null));
        }
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void draw(SpriteBatch sb, ShapeRenderer sr) {
        sb.setProjectionMatrix(getSceneCamera().combined);
        sr.setProjectionMatrix(getSceneCamera().combined);
        sb.begin();

        rectangleRenderer.drawFilled(sb, 0,0,App.WIDTH, App.HEIGHT, Pico8Colors.DARK_GREEN);

        /* Frames */
        int topMargin = 25;
        int baseHeight = App.HEIGHT-topMargin;
        int baseY = 22;
        int baseX = 3;
        rectangleRenderer.drawFrame1(sb, baseX,baseY, baseHeight, Pico8Colors.BLACK);
        rectangleRenderer.drawFrame1(sb, baseX+1,baseY+1, baseHeight-2, Pico8Colors.DARK_GRAY);
        rectangleRenderer.drawFrame1(sb, baseX+2,baseY+2, baseHeight-4, Pico8Colors.LIGHT_GRAY);
        /* Frames */

        int titleY = App.HEIGHT - 8;
        int subtitleY = titleY - 10;
        writer.writeBorderShadow(sb, "you win", titleY, Pico8Colors.RED, Pico8Colors.BLACK, Pico8Colors.DARK_PURPLE);
        writer.write(sb, "congratulations", subtitleY, Pico8Colors.WHITE);
        writer.write(sb, "press X to reset", 10, Pico8Colors.LIGHT_GRAY);

        sb.end();
    }
}
