package com.halfcut.template.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.halfcut.template.assets.Assets;
import com.halfcut.template.platformer.SpriteActor;

public class Hero extends SpriteActor {
    private boolean jumping = false;

    private class JumpAnimationFinished extends AnimationFinishedCallback {
        public Hero hero;

        public JumpAnimationFinished(Hero hero){
            this.hero = hero;
        }

        @Override
        public void animationFinishedCallback(SpriteActor actor) {
            actor.setAnimation("idle");
            hero.jumping = false;
        }
    }

    public Hero(Assets assets, int x, int y){
        super(x, y, 16, 16);
        createAnimation("run", assets.findTextureRegion("hero_run"), 16, 16, 0.1f, true);
        createAnimation("idle", assets.findTextureRegion("hero_idle"), 16, 16, 0.3f, true);
        JumpAnimationFinished j = new JumpAnimationFinished(this);
        createAnimation("jump", assets.findTextureRegion("hero_jump"), 16, 16, 0.1f, false, j);
        setAnimation("idle");

        debugShape = false;
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        int s = 1;
        boolean l = Gdx.input.isKeyPressed(Input.Keys.LEFT),
                r = Gdx.input.isKeyPressed(Input.Keys.RIGHT),
                u = Gdx.input.isKeyPressed(Input.Keys.UP),
                d = Gdx.input.isKeyPressed(Input.Keys.DOWN);

        if(l){
            if(!this.flipX) this.setFlipX(true);
            this.subtractFromX(s);
        } else if(r){
            if(this.flipX) this.setFlipX(false);
            this.addToX(s);
        }

        if(d){
            this.subtractFromY(s);
        } else if(u){
            this.addToY(s);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            jumping = true;
            setAnimation("jump");
            resetCurrentAnimation();
        }


        if(!jumping){
            if (l || r || d || u) {
                setAnimation("run");
            } else {
                setAnimation("idle");
            }
        }
    }
}
