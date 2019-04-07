package com.rombosaur.jsff.screen;

/**
 * @author rombus
 * @since 07/04/2019
 *
 * This interface is used for creating classes that defer the instantiation of a screen.
 */
public interface InstantiateClass {
    Screen newInstance();
}
