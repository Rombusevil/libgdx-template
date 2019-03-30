package com.rombosaur.jsff.text;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rombosaur.jsff.App;
import com.rombosaur.jsff.SpriteShapes.RectangleRenderer;
import com.rombosaur.jsff.assets.Assets;

public class TextBubble {
    private static final int HEAD_SIZE = 32;
    private static final int HEAD_POS_X = App.MIDDLE_WIDHT - (HEAD_SIZE / 2);
    private static final int HEAD_POS_Y = (int)(App.HEIGHT - (App.HEIGHT / 3f));
    private static final int ALLOWED_TEXT_ROWS = 3;

    private TextureRegion pixel;
    private TextureRegion faceFrame;
    private RectangleRenderer rectRenderer;
    public float x, y;
    int width, height;

    public TextBubble(RectangleRenderer rr, float x, float y, int width, int height){
        Assets assets = Assets.get();
        this.pixel = assets.findTextureRegion("pixel");
        this.faceFrame = assets.findTextureRegion("face_frame");
        this.rectRenderer = rr;
        this.x = 2;
        this.y = HEAD_POS_Y;
        this.width = (int)(App.WIDTH-(x*2));
        this.height = 19;
    }

    public void draw(SpriteBatch sb, TextWriter textWriter, String text, Color textColor, Color frameColor, TextureRegion character){
        float yy = y - (textWriter.fontHeightPx * ALLOWED_TEXT_ROWS) - 3;
        Color prevColor = sb.getColor();
        sb.setColor(frameColor);
        sb.draw(pixel, x, yy, width, height);

        sb.setColor(textColor);
        textWriter.write(sb, text, (int)x+1, (int)y-4, textColor);

        sb.setColor(prevColor);
        if(character != null){
            sb.draw(character, HEAD_POS_X, HEAD_POS_Y, HEAD_SIZE,HEAD_SIZE);
            //sb.draw(faceFrame, HEAD_POS_X-3, HEAD_POS_Y-3, 19*2, 19*2);

        }
    }
}
