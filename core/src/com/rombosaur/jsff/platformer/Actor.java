package com.rombosaur.jsff.platformer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public abstract class Actor implements Drawable, Updateable{
    public Rectangle boundingBox;
    public float bbOffsetX, bbOffsetY;

    public Actor(float x, float y, int boundingBoxWidth, int boundingBoxHeight, float bbOffsetX, float bbOffsetY){
        this.boundingBox = new Rectangle(x,y,boundingBoxWidth,boundingBoxHeight);
        this.bbOffsetX = bbOffsetX;
        this.bbOffsetY = bbOffsetY;
    }

    public void setPosition(float x, float y){this.boundingBox.setPosition(x,y);}
    public void setX(float x){this.boundingBox.setX(x);}
    public void setY(float y){this.boundingBox.setY(y);}
    public float getX(){return this.boundingBox.x;}
    public float getY(){return this.boundingBox.y;}
    public void addToX(float delta){this.boundingBox.setX(this.boundingBox.x+delta);}
    public void subtractFromX(float delta){this.boundingBox.setX(this.boundingBox.x-delta);}
    public void addToY(float delta){this.boundingBox.setY(this.boundingBox.y+delta);}
    public void subtractFromY(float delta){this.boundingBox.setY(this.boundingBox.y-delta);}


    /* ABSTRACT */
    /*----------*/

    public abstract void update(float delta);

    /** Call this method inside a SpriteBatch begin(); end(); */
    public abstract void drawSpriteBatch(SpriteBatch sb);

    /** Call this method inside a ShapeRenderer begin(); end(); */
    public abstract void drawShapeRenderer(ShapeRenderer sr);
}
