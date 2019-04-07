package com.rombosaur.jsff.screen;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.rombosaur.jsff.App;
import com.rombosaur.jsff.SpriteShapes.RectangleRenderer;
import com.rombosaur.jsff.assets.Assets;
import com.rombosaur.jsff.screen.loader.Loader;
import com.rombosaur.jsff.screen.loader.LoaderUtils;
import com.rombosaur.jsff.screen.loader.LoadingScreen;
import com.rombosaur.jsff.screen.loader.ScreenInstanceDefer;
import com.rombosaur.jsff.text.TextWriter;
import com.rombosaur.jsff.util.Gamepad;
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
        if (Gamepad.isButtonXPressed()) {
            ScreenInstanceDefer gs = new ScreenInstanceDefer() {
                @Override public Screen newInstance() {
                    return new GameScreen(app);
                }
            };

            transitionToScreen(new LoadingScreen(app, gs,
                new Loader() {
                    @Override
                    public void load(LoaderUtils loaderUtils) {
                        loaderUtils.loadMap("map/test2.tmx");
                    }
                }
            ));
        }
    }

    @Override
    public void draw(SpriteBatch sb, ShapeRenderer sr) {
        sb.setProjectionMatrix(getSceneCamera().combined);
        sr.setProjectionMatrix(getSceneCamera().combined);
        sb.begin();

        rectangleRenderer.drawFilled(sb, 0,0,App.WIDTH, App.HEIGHT, Pico8Colors.DARK_BLUE);

        /* Frames */
        int topMargin = 25;
        int baseHeight = App.HEIGHT-topMargin;
        int baseY = 22;
        int baseX = 3;
        rectangleRenderer.drawFrame1(sb, baseX,baseY, baseHeight, Pico8Colors.WHITE);
        rectangleRenderer.drawFrame1(sb, baseX+1,baseY+1, baseHeight-2, Pico8Colors.BLACK);
        rectangleRenderer.drawFrame1(sb, baseX+2,baseY+2, baseHeight-4, Pico8Colors.LIGHT_GRAY);

        float brandFrameWidth = writer.getTextWith(App.BRAND) + 4;
        float brandFrameX = App.MIDDLE_WIDTH -((brandFrameWidth)/2);
        rectangleRenderer.drawFrame1(sb, brandFrameX,((float)App.HEIGHT/7)+1, 10, Pico8Colors.DARK_BLUE);

        rectangleRenderer.drawFrame1(sb, (float)App.WIDTH/6, -1, 20, Pico8Colors.BLACK);
        rectangleRenderer.drawFrame1(sb, ((float)App.WIDTH/6)+1, -1, 19, Pico8Colors.LIGHT_GRAY);
        /* Frames */

        int titleY = App.HEIGHT - 8;
        int subtitleY = titleY - 10;
        int rsY = (App.HEIGHT / 4) -4;
        writer.writeBorderShadow(sb, App.TITLE, titleY, Pico8Colors.RED, Pico8Colors.BLACK, Pico8Colors.DARK_PURPLE);
        writer.write(sb, App.SUB_TITLE, subtitleY, Pico8Colors.BLACK);
        /* Explain controls here */
        writer.writeShadow(sb, "press X to start", rsY+15, Pico8Colors.BLACK, Pico8Colors.WHITE);
        writer.writeShadow(sb, App.BRAND, rsY, Pico8Colors.ORANGE, Pico8Colors.DARK_PURPLE);
        writer.write(sb, App.VERSION, App.WIDTH-22, rsY+2, Pico8Colors.WHITE);

        writer.writeShadow(sb, "buttons", 17, Pico8Colors.WHITE, Pico8Colors.BLACK);
        writer.writeBorderShadow(sb, "O", (App.WIDTH/6)+5, 16, Pico8Colors.RED, Pico8Colors.BLACK, Pico8Colors.DARK_PURPLE);
        writer.writeBorder(sb, "a/z", (App.WIDTH/6)+3, 7, Pico8Colors.RED, Pico8Colors.BLACK);

        writer.writeBorderShadow(sb, "LRUD", 7, Pico8Colors.RED, Pico8Colors.BLACK, Pico8Colors.DARK_PURPLE);

        int bspace = (App.WIDTH/2)+6;
        writer.writeBorderShadow(sb, "X", bspace+(App.WIDTH/6)+5, 16, Pico8Colors.RED, Pico8Colors.BLACK, Pico8Colors.DARK_PURPLE);
        writer.writeBorder(sb, "s/x", bspace+(App.WIDTH/6)+3, 7, Pico8Colors.RED, Pico8Colors.BLACK);

        sb.end();
    }
}
