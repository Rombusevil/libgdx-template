package com.rombosaur.jsff.game;

import com.rombosaur.jsff.assets.Assets;
import com.rombosaur.jsff.engine.SpriteActor;

public class Npc extends SpriteActor {

    public Npc(Assets assets, int x, int y){
        super(x, y, 16, 16,4 ,12, 7,0);
        createAnimation("idle", assets.findTextureRegion("hero_idle"), 16, 16, 0.3f, true);
        setAnimation("idle");
        debugShape = true;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }
}
