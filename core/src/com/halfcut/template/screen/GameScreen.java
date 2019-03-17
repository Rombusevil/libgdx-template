package com.halfcut.template.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.halfcut.template.App;
import com.halfcut.template.assets.Assets;
import com.halfcut.template.game.Hero;
import com.halfcut.template.util.DisplayText;

import static com.halfcut.template.App.HEIGHT;
import static com.halfcut.template.App.WIDTH;

/**
 * @author halfcutdev
 * @since 22/12/2017
 */
public class GameScreen extends Screen {

    // Spinning box example.
    private float boxSize  = 10;
    private float boxX     = (WIDTH  - boxSize) / 2;
    private float boxY     = (HEIGHT - boxSize) / 2;
    private float boxTheta = 0.0f;

    private TextureRegion chobi;
    private DisplayText displayText;
    private Hero h;

    public GameScreen(App app) {
        super(app);
        Assets ass = Assets.get();
        System.out.println(ass.getAssetManager().getAssetNames());


        chobi = ass.findTextureRegion("run");
        h = new Hero(ass, 70, 20);

        BitmapFont font = ass.getFont("fonts/pico8_05.fnt");
        displayText = new DisplayText(font);
    }

    @Override
    public void update(float delta) {
        boxTheta -= 2f * delta;
        boxSize += (Math.sin(boxTheta)/2)*0.5;
        h.update(delta);
    }

    @Override
    public void draw(SpriteBatch sb, ShapeRenderer sr) {

        sb.setProjectionMatrix(getSceneCamera().combined);
        sr.setProjectionMatrix(getSceneCamera().combined);

        sb.begin();
            //sb.draw(chobi, 10,10);

            h.drawSpriteBatch(sb);

            int x = 10;
            int y = 50;
            displayText.write(sb, "Normal", x, y+20, Color.WHITE);
            displayText.writeShadow(sb, "Shadowed", x, y, Color.WHITE, Color.BLUE);
            displayText.writeBold(sb, "Bold", x, y+10, Color.WHITE, Color.BLUE);
        sb.end();

        // Draw the box
        sr.begin(ShapeRenderer.ShapeType.Line);
            sr.setColor(Color.PINK);
            sr.rect(boxX, boxY, boxSize * 0.5f, boxSize * 0.5f, boxSize, boxSize, 1, 1, boxTheta);

            sr.setColor(Color.VIOLET);
            sr.line(0,0,127,127);
            sr.setColor(Color.YELLOW);
            sr.rect(100,100, 27,27);

            h.drawShapeRenderer(sr);
        sr.end();
    }

}
