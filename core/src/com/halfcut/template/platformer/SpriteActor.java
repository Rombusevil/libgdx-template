package com.halfcut.template.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ObjectMap;

public abstract class SpriteActor extends Actor {
    protected boolean debugShape, flipX, flipY;
    protected float scaleX, scaleY, rotation, spriteCenterX, spriteCenterY;
    protected ObjectMap<String, AnimationMetadata> animations;
    protected AnimationMetadata curAnimation;
    private float stateTime = 0;

    public SpriteActor(float x, float y, int width, int height){
        super(x, y, width, height);
        animations = new ObjectMap<String, AnimationMetadata>();
        debugShape = flipX = flipY = false;
        scaleX = scaleY = 1;
        rotation = 0;
        spriteCenterX = this.boundingBox.width/2;
        spriteCenterY = this.boundingBox.width/2;
    }
    public void setDebugShape(boolean debug){this.debugShape=debug;}
    public void setAnimation(String name){this.curAnimation = animations.get(name);}
    public void setFlipX(boolean doFlip){this.flipX = doFlip;}
    public void setFlipY(boolean doFlip){this.flipY = doFlip;}
    public void setRotation(float rotation){this.rotation = rotation;}
    public void setScale(float sx, float sy){this.scaleX = sx; this.scaleY = sy;}

    public void createAnimation(String name, TextureRegion spriteSheet, int width, int height, float frameDuration, boolean loops){
        int cols = spriteSheet.getRegionWidth()/width;
        int rows = spriteSheet.getRegionHeight()/height;
        TextureRegion[] frames = new TextureRegion[cols*rows];

        TextureRegion[][] tmpFrames = spriteSheet.split(width, height);
        for(int i=0, index=0; i<tmpFrames.length; i++){
            for(int j=0; j<tmpFrames[i].length; j++){
                frames[index++] = tmpFrames[i][j];
            }
        }

        Animation<TextureRegion> animation = new Animation<TextureRegion>(frameDuration, frames);
        AnimationMetadata adata = new AnimationMetadata(animation, loops);
        this.animations.put(name, adata);
    }

    @Override
    public void drawSpriteBatch(SpriteBatch sb) {
        if(this.curAnimation == null){
            throw new RuntimeException("No animation selected for SpriteActor.");
        }

        this.stateTime += Gdx.graphics.getDeltaTime();
        Animation animation = this.curAnimation.animation;
        TextureRegion curFrame = (TextureRegion)animation.getKeyFrame(this.stateTime, this.curAnimation.loops);

        if((this.flipX && this.scaleX > 0) || (!this.flipX && this.scaleX < 0)) this.scaleX = 0 - this.scaleX;
        if((this.flipY && this.scaleY > 0) || (!this.flipY && this.scaleY < 0)) this.scaleY = 0 - this.scaleY;
        sb.draw(
            curFrame,
            this.boundingBox.x, this.boundingBox.y,
            this.spriteCenterX, this.spriteCenterY,
            this.boundingBox.width, this.boundingBox.height,
            this.scaleX, this.scaleY,
            this.rotation
        );
    }

    @Override
    public void drawShapeRenderer(ShapeRenderer sr) {
        if(this.debugShape){
            sr.setColor(Color.VIOLET);
            sr.rect(this.boundingBox.x, this.boundingBox.y, this.boundingBox.width, this.boundingBox.height);
        }
    }

    private class AnimationMetadata{
        Animation animation;
        boolean loops;

        AnimationMetadata(Animation animation, boolean loops){
            this.animation = animation;
            this.loops = loops;
        }
    }
}
