package com.rombosaur.jsff.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Array;
import com.rombosaur.jsff.App;
import com.rombosaur.jsff.engine.utils.RectangleRenderer;
import com.rombosaur.jsff.engine.assets.Assets;
import com.rombosaur.jsff.engine.actor.Drawable;
import com.rombosaur.jsff.engine.actor.Updateable;
import com.rombosaur.jsff.engine.screen.Screen;
import com.rombosaur.jsff.gobjects.Hero;
import com.rombosaur.jsff.gobjects.Npc;
import com.rombosaur.jsff.engine.text.TextBubble;
import com.rombosaur.jsff.engine.text.TextWriter;
import com.rombosaur.jsff.engine.utils.CollisionDetector;

/**
 * @author rombus
 * @since 07/04/2019
 */
public class GameScreen2 extends Screen {
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
    private TiledMapTileLayer walls;


    public GameScreen2(App app) {
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

        map = assets.getTiledMap("map/test.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        walls = (TiledMapTileLayer) map.getLayers().get("walls");

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
        }

        getSceneCamera().position.x = h.getX();
        getSceneCamera().position.y = h.getY();
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
        sb.end();

        // Draw the box
        sr.begin(ShapeRenderer.ShapeType.Line);
            for(Drawable d: drawables){
                d.drawShapeRenderer(sr);
            }
        sr.end();
    }

}
