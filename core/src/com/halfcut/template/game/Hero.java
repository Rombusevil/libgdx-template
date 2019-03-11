package com.halfcut.template.game;

import com.halfcut.template.assets.Assets;
import com.halfcut.template.platformer.SpriteActor;

public class Hero extends SpriteActor {

    public Hero(Assets assets, int x, int y){
        super(x, y, 8, 16);
        createAnimation("run", assets.findTextureRegion("run"), 8, 16, 0.2f, true);
        setAnimation("run");
    }

    @Override
    public void update() {

    }
}
