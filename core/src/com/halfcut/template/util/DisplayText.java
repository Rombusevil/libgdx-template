package com.halfcut.template.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author rombus
 * @since 10/03/2019
 */
public class DisplayText {
    private BitmapFont font;

    public DisplayText(BitmapFont font){
        this.font = font;
    }

    public void write(SpriteBatch sb, String text, int x, int y, Color color){
        this.font.setColor(color);
        this.font.draw(sb, text, x,y);
    }

    public void writeBold(SpriteBatch sb, String text, int x, int y, Color foregroundColor, Color backgroundColor){
        this.font.setColor(backgroundColor);
        this.font.draw(sb, text, x+1,y+1);
        this.font.draw(sb, text, x-1,y+1);
        this.font.draw(sb, text, x,y+1);

        this.font.draw(sb, text, x-1,y-1);
        this.font.draw(sb, text, x+1,y-1);
        this.font.draw(sb, text, x,y-1);

        this.font.setColor(foregroundColor);
        this.font.draw(sb, text, x,y);
    }

    public void writeShadow(SpriteBatch sb, String text, int x, int y, Color foregroundColor, Color shadowColor){
        this.font.setColor(shadowColor);
        this.font.draw(sb, text, x-1,y-1);

        this.font.setColor(foregroundColor);
        this.font.draw(sb, text, x,y);
    }
}
