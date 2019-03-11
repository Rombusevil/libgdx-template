package com.halfcut.template.platformer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ObjectMap;

public abstract class SpriteActor extends Actor {
    protected boolean debugShape;
    protected ObjectMap<String, AnimationMetadata> animations;
    protected AnimationMetadata curAnimation;
    private float stateTime = 0;

    private class AnimationMetadata{
        Animation animation;
        boolean loops;

        AnimationMetadata(Animation animation, boolean loops){
            this.animation = animation;
            this.loops = loops;
        }
    }

    public SpriteActor(float x, float y, int width, int height){
        super(x, y, width, height);
        this.animations = new ObjectMap<String, AnimationMetadata>();
        this.debugShape = false;
    }
    public void setDebugShape(boolean debug){this.debugShape=debug;}
    public void setAnimation(String name){this.curAnimation = animations.get(name);}

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

        sb.draw(curFrame, this.boundingBox.x, this.boundingBox.y);
    }

    @Override
    public void drawShapeRenderer(ShapeRenderer sr) {
        if(this.debugShape){
            sr.setColor(Color.VIOLET);
            sr.rect(this.boundingBox.x, this.boundingBox.y, this.boundingBox.width, this.boundingBox.height);
        }
    }
}
