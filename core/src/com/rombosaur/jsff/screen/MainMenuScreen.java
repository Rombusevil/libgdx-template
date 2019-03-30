package com.rombosaur.jsff.screen;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.rombosaur.jsff.App;
import com.rombosaur.jsff.SpriteShapes.RectangleRenderer;
import com.rombosaur.jsff.assets.Assets;
import com.rombosaur.jsff.text.TextWriter;
import com.rombosaur.jsff.util.Pico8Colors;

/**
 * @author rombus
 * @since 30/02/2019
 */
public class MainMenuScreen extends Screen {
    private RectangleRenderer rectangleRenderer;
    private TextWriter writer;

    public MainMenuScreen(App app) {
        super(app);
        Assets assets = Assets.get();
        System.out.println(assets.getAssetManager().getAssetNames());

        BitmapFont font = assets.getFont("fonts/pico8_05.fnt");
        writer = new TextWriter(font, 6.3f);
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

        int frameBottomMargin = 20;
        rectangleRenderer.drawFilled(sb, 2, 3+frameBottomMargin, App.WIDTH-4,App.HEIGHT-6-frameBottomMargin, Pico8Colors.WHITE);
        rectangleRenderer.drawFilled(sb, 3, 2+frameBottomMargin, App.WIDTH-6,App.HEIGHT-4-frameBottomMargin, Pico8Colors.WHITE);

        rectangleRenderer.drawFilled(sb, 3, 4+frameBottomMargin, App.WIDTH-6,App.HEIGHT-8-frameBottomMargin, Pico8Colors.BLACK);
        rectangleRenderer.drawFilled(sb, 4, 3+frameBottomMargin, App.WIDTH-8,App.HEIGHT-6-frameBottomMargin, Pico8Colors.BLACK);

        rectangleRenderer.drawFilled(sb, 4, 5+frameBottomMargin, App.WIDTH-8,App.HEIGHT-10-frameBottomMargin, Pico8Colors.LIGHT_GRAY);
        rectangleRenderer.drawFilled(sb, 5, 4+frameBottomMargin, App.WIDTH-10,App.HEIGHT-8-frameBottomMargin, Pico8Colors.LIGHT_GRAY);

        writer.writeBorderShadow(sb, "billy goes to college", App.HEIGHT-7, Pico8Colors.RED, Pico8Colors.BLACK, Pico8Colors.DARK_PURPLE);
        writer.write(sb, "ludum dare 44", App.HEIGHT-15, Pico8Colors.BLACK);
        writer.writeShadow(sb, "rombosaur studios", App.HEIGHT-30, Pico8Colors.YELLOW, Pico8Colors.ORANGE);

        sb.end();
    }
}
