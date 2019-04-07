package com.rombosaur.jsff.engine.screen.loader;

import com.rombosaur.jsff.engine.screen.Screen;

/**
 * @author rombus
 * @since 07/04/2019
 *
 * This interface is used for creating classes that defer the instantiation of a screens.
 */
public interface ScreenInstanceDefer {
    Screen newInstance();
}
