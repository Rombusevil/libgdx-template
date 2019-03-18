package com.rombosaur.jsff.SpriteShapes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.rombosaur.jsff.assets.Assets;

public class RectangleRenderer {
    private TextureRegion pixel;

    public RectangleRenderer(){
        Assets assets = Assets.get();
        this.pixel = assets.findTextureRegion("pixel");
    }

    public void drawFilled(SpriteBatch sb, float x, float y, int width, int height, Color color){
        Color prevColor = sb.getColor();
        sb.setColor(color);
        sb.draw(this.pixel, x, y, width, height);
        sb.setColor(prevColor);
    }

    public void drawLine(SpriteBatch sb, float x, float y, int width, int height, int borderWidth, Color color, Color borderColor){
        Color prevColor = sb.getColor();
        sb.setColor(borderColor);
        sb.draw(this.pixel, x, y, width, height);
        sb.setColor(color);
        int twiceBorderWidth = borderWidth*2;
        sb.draw(this.pixel, x+borderWidth, y+borderWidth, width-twiceBorderWidth, height-twiceBorderWidth);
        sb.setColor(prevColor);
    }
}
