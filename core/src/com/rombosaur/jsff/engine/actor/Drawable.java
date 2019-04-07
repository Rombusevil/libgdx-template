package com.rombosaur.jsff.engine.actor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public interface Drawable {
    void drawSpriteBatch(SpriteBatch sb);
    void drawShapeRenderer(ShapeRenderer sr);
}
