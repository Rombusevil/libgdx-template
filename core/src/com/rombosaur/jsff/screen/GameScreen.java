package com.rombosaur.jsff.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.rombosaur.jsff.App;
import com.rombosaur.jsff.SpriteShapes.RectangleRenderer;
import com.rombosaur.jsff.assets.Assets;
import com.rombosaur.jsff.game.Hero;
import com.rombosaur.jsff.text.DisplayText;
import com.rombosaur.jsff.text.TextBubble;

/**
 * @author halfcutdev
 * @since 22/12/2017
 */
public class GameScreen extends Screen {
    private RectangleRenderer rect;
    private DisplayText displayText;
    private Hero h;
    private TextBubble tb;
    private TextureRegion heroFace, timotyFace;
    private Texture heroFaceTx;

    public GameScreen(App app) {
        super(app);
        Assets assets = Assets.get();
        System.out.println(assets.getAssetManager().getAssetNames());

        rect = new RectangleRenderer();
        h = new Hero(assets, 70, 20);
        tb = new TextBubble(rect, 2, 90, 124, 19);
        heroFace = assets.findTextureRegion("hero_face");
        timotyFace = assets.findTextureRegion("timoty_face");

        BitmapFont font = assets.getFont("fonts/pico8_05.fnt");
        displayText = new DisplayText(font, 6.3f);
    }

    @Override
    public void update(float delta) {
        h.update(delta);
    }

    @Override
    public void draw(SpriteBatch sb, ShapeRenderer sr) {

        sb.setProjectionMatrix(getSceneCamera().combined);
        sr.setProjectionMatrix(getSceneCamera().combined);

        sb.begin();
            //sb.draw(chobi, 10,10);

            h.drawSpriteBatch(sb);
            rect.drawLine(sb, 10, 100, 50,25, 1, Color.VIOLET, Color.BLACK);
            rect.drawFilled(sb, 30, 10, 10, 50, Color.GREEN);

            int x = 10;
            int y = 50;
            displayText.write(sb, "Normal", x, y+20, Color.WHITE);
            displayText.writeShadow(sb, "Shadowed", x, y, Color.WHITE, Color.BLUE);
            displayText.writeBold(sb, "Bold", x, y+10, Color.WHITE, Color.BLUE);

            int textHeight = 6;
            /*Line 1*/ displayText.write(sb, "ut semper elementum metus ac sa", 2, textHeight*3, Color.WHITE);
            /*Line 2*/ displayText.write(sb, "ut semper elementum metus ac sa", 2, textHeight*2, Color.WHITE);
            /*Line 3*/ displayText.write(sb, "ut semper elementum metus ac sa", 2, textHeight, Color.WHITE);

            tb.draw(sb, displayText,
                    "are you ready for the summer?" +
                        "\nare you ready for some fun?" +
                        "\nare you ready for the na na na",
                    Color.RED, Color.SLATE, timotyFace);

        sb.end();

        // Draw the box
        sr.begin(ShapeRenderer.ShapeType.Line);
            /*
            sr.setColor(Color.VIOLET);
            sr.line(0,0,127,127);
            sr.setColor(Color.YELLOW);
            sr.rect(100,100, 27,27);
            */
            h.drawShapeRenderer(sr);
        sr.end();
    }

}
