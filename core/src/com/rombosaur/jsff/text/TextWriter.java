package com.rombosaur.jsff.text;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rombosaur.jsff.App;

/**
 * @author rombus
 * @since 10/03/2019
 */
public class TextWriter {
    private BitmapFont font;
    private GlyphLayout glyphLayout;
    public float fontHeightPx;

    public TextWriter(BitmapFont font, float fontHeight){
        this.font = font;
        this.fontHeightPx = fontHeight;
        this.glyphLayout = new GlyphLayout();
    }

    public void write(SpriteBatch sb, String text, int x, int y, Color color){
        this.font.setColor(color);
        this.font.draw(sb, text, x,y);
    }
    public void write(SpriteBatch sb, String text, int y, Color color) {
        float textMiddle = this.getTextWith(text) / 2;
        float x = App.MIDDLE_WIDTH - textMiddle;
        this.write(sb, text, (int)x, y, color);
    }

    public void writeBorder(SpriteBatch sb, String text, int x, int y, Color foregroundColor, Color borderColor){
        this.font.setColor(borderColor);
        this.font.draw(sb, text, x+1,y+1);
        this.font.draw(sb, text, x-1,y+1);
        this.font.draw(sb, text, x,y+1);

        this.font.draw(sb, text, x-1,y-1);
        this.font.draw(sb, text, x+1,y-1);
        this.font.draw(sb, text, x,y-1);

        this.font.draw(sb, text, x-1,y);
        this.font.draw(sb, text, x+1,y);
        this.font.draw(sb, text, x,y);

        this.font.setColor(foregroundColor);
        this.font.draw(sb, text, x,y);
    }
    public void writeBorder(SpriteBatch sb, String text, int y, Color foregroundColor, Color borderColor) {
        float textMiddle = this.getTextWith(text) / 2;
        float x = App.MIDDLE_WIDTH - textMiddle;
        this.writeBorder(sb, text, (int)x, y, foregroundColor, borderColor);
    }

    public void writeShadow(SpriteBatch sb, String text, int x, int y, Color foregroundColor, Color shadowColor){
        this.font.setColor(shadowColor);
        this.font.draw(sb, text, x,y-1);

        this.font.setColor(foregroundColor);
        this.font.draw(sb, text, x,y);
    }
    public void writeShadow(SpriteBatch sb, String text, int y, Color foregroundColor, Color shadowColor) {
        float textMiddle = this.getTextWith(text) / 2;
        float x = App.MIDDLE_WIDTH - textMiddle;
        this.writeShadow(sb, text, (int)x, y, foregroundColor, shadowColor);
    }

    public void writeBorderShadow(SpriteBatch sb, String text, int x, int y, Color foregroundColor, Color backgroundColor, Color shadowColor) {
        this.font.setColor(shadowColor);
        int yy = y - 2;
        this.font.draw(sb, text, x, yy);
        this.font.draw(sb, text, x-1, yy);
        this.font.draw(sb, text, x+1, yy);
        writeBorder(sb, text, x, y, foregroundColor, backgroundColor);
    }
    public void writeBorderShadow(SpriteBatch sb, String text, int y, Color foregroundColor, Color backgroundColor, Color shadowColor) {
        float textMiddle = this.getTextWith(text) / 2;
        float x = App.MIDDLE_WIDTH - textMiddle;
        this.writeBorderShadow(sb, text, (int)x, y, foregroundColor, backgroundColor, shadowColor);
    }

    public float getTextWith(String text) {
        this.glyphLayout.setText(this.font, text);
        return this.glyphLayout.width;
    }
}
