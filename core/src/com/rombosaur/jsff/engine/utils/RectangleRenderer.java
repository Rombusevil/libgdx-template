package com.rombosaur.jsff.engine.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rombosaur.jsff.App;
import com.rombosaur.jsff.engine.assets.Assets;

/**
 * @author rombus
 * @since 30/03/2019
 */
public class RectangleRenderer {
    private TextureRegion pixel;

    public RectangleRenderer(){
        Assets assets = Assets.get();
        this.pixel = assets.findTextureRegion("pixel");
    }

    @SuppressWarnings("Duplicates")
    public void drawFilled(SpriteBatch sb, float x, float y, int width, int height, Color color){
        Color prevColor = sb.getColor();
        sb.setColor(color);
        sb.draw(this.pixel, x, y, width, height);
        sb.setColor(prevColor);
    }

    @SuppressWarnings("Duplicates")
    public void drawBordered(SpriteBatch sb, float x, float y, int width, int height, int borderWidth, Color color, Color borderColor){
        Color prevColor = sb.getColor();
        sb.setColor(borderColor);
        sb.draw(this.pixel, x, y, width, height);
        sb.setColor(color);

        int twiceBorderWidth = borderWidth*2;
        sb.draw(this.pixel, x+borderWidth, y+borderWidth, width-twiceBorderWidth, height-twiceBorderWidth);
        sb.setColor(prevColor);
    }

    /**
     * Draws 2 rectangles offseted by 1 pixel.
     * That way it misses 1 pixel in every corner.
     *
     *           xxx
     *          xxxxx
     *          xxxxx
     *          xxxxx
     *           xxx
     *
     */
    public void drawFrame1(SpriteBatch sb, float x, float y, int height, Color color) {
        drawFilled(sb, x, y, (int)(App.WIDTH-(x*2)), height, color);
        x = x-1;
        y = y+1;
        drawFilled(sb, x, y, (int)(App.WIDTH-(x*2)), height-2, color);
    }
}
