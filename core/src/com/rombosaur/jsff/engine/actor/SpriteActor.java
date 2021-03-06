package com.rombosaur.jsff.engine.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ObjectMap;

public abstract class SpriteActor extends Actor {
    private float stateTime = 0;
    protected boolean debugShape, flipX, flipY;
    protected float scaleX, scaleY, rotation, spriteCenterX, spriteCenterY;
    protected ObjectMap<String, AnimationMetadata> animations;
    protected AnimationMetadata curAnimation;

    public SpriteActor(float x, float y, int spriteWidth, int spriteHeight, int boundingBoxWidth, int boundingBoxHeight, float bbOffsetX, float bbOffsetY){
        super(x, y, boundingBoxWidth, boundingBoxHeight, bbOffsetX, bbOffsetY);
        animations = new ObjectMap<String, AnimationMetadata>();
        debugShape = flipX = flipY = false;
        scaleX = scaleY = 1;
        rotation = 0;
        spriteCenterX = (float)spriteWidth/2;
        spriteCenterY = (float)spriteHeight/2;
    }
    public void setDebugShape(boolean debug){this.debugShape=debug;}
    public void setAnimation(String name){this.curAnimation = animations.get(name);}
    public void setFlipX(boolean doFlip){this.flipX = doFlip;}
    public void setFlipY(boolean doFlip){this.flipY = doFlip;}
    public void setRotation(float rotation){this.rotation = rotation;}
    public void setScale(float sx, float sy){this.scaleX = sx; this.scaleY = sy;}
    public void resetCurrentAnimation(){this.stateTime=0;}
    public AnimationMetadata createAnimation(String name, TextureRegion spriteSheet, int width, int height, float frameDuration, boolean loops){
        int cols = spriteSheet.getRegionWidth()/width;
        int rows = spriteSheet.getRegionHeight()/height;
        int numberOfFrames = cols*rows;
        TextureRegion[] frames = new TextureRegion[numberOfFrames];

        TextureRegion[][] tmpFrames = spriteSheet.split(width, height);
        for(int i=0, index=0; i<tmpFrames.length; i++){
            for(int j=0; j<tmpFrames[i].length; j++){
                frames[index++] = tmpFrames[i][j];
            }
        }
        Animation<TextureRegion> animation = new Animation<TextureRegion>(frameDuration, frames);
        AnimationMetadata adata = new AnimationMetadata(animation, frameDuration, numberOfFrames, loops, width, height,null);
        this.animations.put(name, adata);
        return adata;
    }
    public void createAnimation(String name, TextureRegion spriteSheet, int width, int height, float frameDuration, boolean loops, AnimationFinishedCallback c){
        AnimationMetadata adata = createAnimation(name,spriteSheet,width,height,frameDuration,loops);
        adata.callback = c;
    }

    @Override
    public void update(float delta) {
        AnimationMetadata curAnim = this.curAnimation;
        int frameNumber = (int)(stateTime / curAnim.frameDuration);
        if(frameNumber == curAnim.numberOfFrames && curAnim.callback != null){
            curAnim.callback.animationFinishedCallback(this);
        }
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
            this.curAnimation.width, this.curAnimation.height,
            this.scaleX, this.scaleY,
            this.rotation
        );
    }

    @Override
    public void drawShapeRenderer(ShapeRenderer sr) {
        if(this.debugShape){
            sr.setColor(Color.VIOLET);
            sr.rect(this.boundingBox.x+this.bbOffsetX, this.boundingBox.y+this.bbOffsetY, this.boundingBox.width, this.boundingBox.height);
        }
    }

    private class AnimationMetadata{
        Animation animation;
        AnimationFinishedCallback callback;
        float frameDuration;
        int numberOfFrames;
        boolean loops;
        int width, height;

        AnimationMetadata(Animation animation, float frameDuration, int numberOfFrames, boolean loops, int width, int height, AnimationFinishedCallback callback){
            this.animation = animation;
            this.callback = callback;
            this.frameDuration = frameDuration;
            this.numberOfFrames = numberOfFrames;
            this.width = width;
            this.height = height;
            this.loops = loops;
        }
    }
}
