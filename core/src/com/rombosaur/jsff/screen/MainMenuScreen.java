package com.rombosaur.jsff.screen;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.rombosaur.jsff.App;
import com.rombosaur.jsff.SpriteShapes.RectangleRenderer;
import com.rombosaur.jsff.assets.Assets;
import com.rombosaur.jsff.text.DisplayText;
import com.rombosaur.jsff.util.Pico8Colors;

/**
 * @author rombus
 * @since 30/02/2019
 */
public class MainMenuScreen extends Screen {
    private RectangleRenderer rectangleRenderer;
    private DisplayText textPrinter;

    public MainMenuScreen(App app) {
        super(app);
        Assets assets = Assets.get();
        System.out.println(assets.getAssetManager().getAssetNames());

        BitmapFont font = assets.getFont("fonts/pico8_05.fnt");
        textPrinter = new DisplayText(font, 6.3f);
        rectangleRenderer = new RectangleRenderer();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void draw(SpriteBatch sb, ShapeRenderer sr) {
        sb.setProjectionMatrix(getSceneCamera().combined);
        sr.setProjectionMatrix(getSceneCamera().combined);


        sb.begin();
        rectangleRenderer.drawFilled(sb, 0,0,App.WIDTH, App.HEIGHT, Pico8Colors.DARK_BLUE);

        rectangleRenderer.drawFilled(sb, 2, 3+50, App.WIDTH-4,App.HEIGHT-6-50, Pico8Colors.WHITE);
        rectangleRenderer.drawFilled(sb, 3, 2+50, App.WIDTH-6,App.HEIGHT-4-50, Pico8Colors.WHITE);

        rectangleRenderer.drawFilled(sb, 3, 4+50, App.WIDTH-6,App.HEIGHT-8-50, Pico8Colors.BLACK);
        rectangleRenderer.drawFilled(sb, 4, 3+50, App.WIDTH-8,App.HEIGHT-6-50, Pico8Colors.BLACK);

        rectangleRenderer.drawFilled(sb, 4, 5+50, App.WIDTH-8,App.HEIGHT-10-50, Pico8Colors.LIGHT_GRAY);
        rectangleRenderer.drawFilled(sb, 5, 4+50, App.WIDTH-10,App.HEIGHT-8-50, Pico8Colors.LIGHT_GRAY);

        textPrinter.writeBold(sb, "game title", 7, App.HEIGHT-7, Pico8Colors.RED, Pico8Colors.BLACK);
        textPrinter.writeShadow(sb, "rombosaur studios", 10, App.HEIGHT-20, Pico8Colors.YELLOW, Pico8Colors.ORANGE);

        sb.end();
    }
}
