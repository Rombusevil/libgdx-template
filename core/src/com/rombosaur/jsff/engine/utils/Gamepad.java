package com.rombosaur.jsff.engine.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

/**
 * @author rombus
 * @since 30/03/2019
 */
public class Gamepad {

    public static boolean isLeftKeyPressed(){
        return Gdx.input.isKeyPressed(Input.Keys.LEFT);
    }

    public static boolean isRightKeyPressed(){
        return Gdx.input.isKeyPressed(Input.Keys.RIGHT);
    }

    public static boolean isUpKeyPressed(){
        return Gdx.input.isKeyPressed(Input.Keys.UP);
    }

    public static boolean isDownKeyPressed(){
        return Gdx.input.isKeyPressed(Input.Keys.DOWN);
    }

    public static boolean isButtonOPressed(){
        return Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.Z);
    }

    public static boolean isButtonXPressed(){
        return Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.X);
    }
}
