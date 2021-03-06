package com.rombosaur.jsff.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import com.rombosaur.jsff.App;
import com.rombosaur.jsff.engine.actor.Drawable;
import com.rombosaur.jsff.engine.actor.Updateable;
import com.rombosaur.jsff.engine.assets.Assets;
import com.rombosaur.jsff.engine.screen.Screen;
import com.rombosaur.jsff.engine.screen.loader.Loader;
import com.rombosaur.jsff.engine.screen.loader.LoaderUtils;
import com.rombosaur.jsff.engine.screen.loader.LoadingScreen;
import com.rombosaur.jsff.engine.screen.loader.ScreenInstanceDefer;
import com.rombosaur.jsff.engine.text.TextBubble;
import com.rombosaur.jsff.engine.text.TextWriter;
import com.rombosaur.jsff.engine.utils.CollisionDetector;
import com.rombosaur.jsff.engine.utils.RectangleRenderer;
import com.rombosaur.jsff.gobjects.Hero;
import com.rombosaur.jsff.gobjects.Npc;

/**
 * @author halfcutdev
 * @since 22/12/2017
 *
 * @author rombus
 * @since 07/04/2019
 */
public class GameScreen extends Screen {
    private RectangleRenderer rectangleRenderer;
    private TextWriter textPrinter;
    private Hero h;
    private Npc h2;
    private TextBubble tb;
    private TextureRegion heroFace, timotyFace;
    private Texture heroFaceTx;
    private Array<Updateable> updateables;
    private Array<Drawable> drawables;
    private TiledMap map;
    private MapRenderer mapRenderer;
    private MapObjects platforms;


    public GameScreen(App app) {
        super(app);
        Assets assets = Assets.get();
        System.out.println(assets.getAssetManager().getAssetNames());

        rectangleRenderer = new RectangleRenderer();
        this.updateables = new Array<Updateable>();
        this.drawables = new Array<Drawable>();

        h = new Hero(assets, 70, 20);
        h2 = new Npc(assets, 80, 50);
        this.updateables.add(h);
        this.drawables.add(h);

        this.updateables.add(h2);
        this.drawables.add(h2);

        map = assets.getTiledMap("map/plat1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        platforms = map.getLayers().get("platforms").getObjects();

        tb = new TextBubble(rectangleRenderer, 2, 90, 124, 19);
        heroFace = assets.findTextureRegion("hero_face");
        timotyFace = assets.findTextureRegion("timoty_face");

        BitmapFont font = assets.getFont("fonts/pico8_05.fnt");
        textPrinter = new TextWriter(font, 6.3f);
    }

    @Override
    public void update(float delta) {
        for (Updateable u : updateables) {
            u.update(delta);
        }
        if (CollisionDetector.areColliding(h.boundingBox, h2.boundingBox)) {
            System.out.println("COLLIDE");
            setScreen(new LoadingScreen(app, new ScreenInstanceDefer() {
                @Override
                public Screen newInstance() {
                    return new GameScreen2(app);
                }
            }, new Loader() {
                @Override
                public void load(LoaderUtils loaderUtils) {
                    loaderUtils.loadMap("map/test.tmx");
                }
            }));
        }

        /*
        TiledMapTileLayer.Cell p = platforms.getCell((int)h.getX()/App.TILE_SIZE, (int)h.getY()/App.TILE_SIZE);

        for (MapObject plat: platforms) {
            //plat.getProperties().
        }
        if(p != null) {
            h.grounded = true;
        } else {
            h.grounded = false;
        }

         */
    }

    @Override
    public void draw(SpriteBatch sb, ShapeRenderer sr) {
        sb.setProjectionMatrix(getSceneCamera().combined);
        sr.setProjectionMatrix(getSceneCamera().combined);

        mapRenderer.setView(getSceneCamera());
        mapRenderer.render();

        sb.begin();
            for (Drawable d : drawables) {
                d.drawSpriteBatch(sb);
            }

            /*
            int textHeight = 6;
            /*Line 1* / textPrinter.write(sb, "ut semper elementum metus ac sa", 2, textHeight*3, Color.WHITE);
            /*Line 2* / textPrinter.write(sb, "ut semper elementum metus ac sa", 2, textHeight*2, Color.WHITE);
            /*Line 3* / textPrinter.write(sb, "ut semper elementum metus ac sa", 2, textHeight, Color.WHITE);
            */

            /*
            tb.draw(sb, textPrinter,
                    "are you ready for the summer?" +
                        "\nare you ready for some fun?" +
                        "\nare you ready for the na na na",
                    Color.RED, Color.SLATE, timotyFace);
            */

        sb.end();

        // Draw the box
        sr.begin(ShapeRenderer.ShapeType.Line);
            /*
            sr.setColor(Color.VIOLET);
            sr.line(0,0,127,127);
            sr.setColor(Color.YELLOW);
            sr.rect(100,100, 27,27);
            */
            for(Drawable d: drawables){
                d.drawShapeRenderer(sr);
            }
        sr.end();
    }

}
