package com.rombosaur.jsff.engine.screen.loader;

/**
 * @author rombus
 * @since 07/04/2019
 *
 * This interface is used for wrapping the method that will be called by the loading screens.
 * Load all the needed assets in `load()` method.
 */
public interface Loader {
    void load(LoaderUtils loaderUtils);
}
