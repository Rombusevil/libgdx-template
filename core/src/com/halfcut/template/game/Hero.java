package com.halfcut.template.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.halfcut.template.assets.Assets;
import com.halfcut.template.platformer.SpriteActor;

public class Hero extends SpriteActor {

    public Hero(Assets assets, int x, int y){
        super(x, y, 8, 16);
        createAnimation("run", assets.findTextureRegion("run"), 8, 16, 0.2f, true);
        setAnimation("run");
    }

    @Override
    public void update(float delta) {
        int s = 1;
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            if(!this.flipX) this.setFlipX(true);
            this.subtractFromX(s);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            if(this.flipX) this.setFlipX(false);
            this.addToX(s);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            this.subtractFromY(s);
            this.setRotation(this.rotation+0.5f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            this.addToY(s);
            this.setRotation(this.rotation-0.5f);
        }
    }
}
