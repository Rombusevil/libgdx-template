package com.rombosaur.jsff.util;

import com.badlogic.gdx.math.Rectangle;

public class CollisionDetector {

    public static boolean areColliding(Rectangle rec1, Rectangle rec2){
        float topEdge1 = rec1.y + rec1.height;
        float rightEdge1 = rec1.x + rec1.width;
        float leftEdge1 = rec1.x;
        float bottomEdge1 = rec1.y;
        float topEdge2 = rec2.y + rec2.height;
        float rightEdge2 = rec2.x + rec2.width;
        float leftEdge2 = rec2.x;
        float bottomEdge2 = rec2.y;

        if( leftEdge1 < rightEdge2 && rightEdge1 > leftEdge2 && bottomEdge1 < topEdge2 && topEdge1 > bottomEdge2){
            return true;
        }
        return false;
    }
}
