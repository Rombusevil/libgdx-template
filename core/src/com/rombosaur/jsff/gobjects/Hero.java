package com.rombosaur.jsff.gobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.rombosaur.jsff.engine.assets.Assets;
import com.rombosaur.jsff.engine.actor.AnimationFinishedCallback;
import com.rombosaur.jsff.engine.actor.SpriteActor;
import com.rombosaur.jsff.engine.utils.Gamepad;

public class Hero extends SpriteActor {
    private boolean jumping = false;
    private float gravity = 5;
    public boolean grounded = false;

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
        super(x, y, 16, 16,4 ,12, 7,0);
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
        boolean l = Gamepad.isLeftKeyPressed(),
                r = Gamepad.isRightKeyPressed(),
                u = Gamepad.isUpKeyPressed(),
                d = Gamepad.isDownKeyPressed();

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
            float curY = getY();
            if(!this.grounded) setY(getY()-this.gravity);

            if (l || r || d || u) {
                setAnimation("run");
            } else {
                setAnimation("idle");
            }
        }
    }
}
