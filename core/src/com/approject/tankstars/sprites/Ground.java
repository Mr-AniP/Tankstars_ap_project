package com.approject.tankstars.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

public class Ground {
    private World world;
    private TiledMap mapy;
    private TiledMapTile tile;
    private Body b;
    private Rectangle poly;
    public Ground(World _world,TiledMap _mapy,Rectangle _bound){
        world=_world;
        mapy=_mapy;
        poly=_bound;
        BodyDef bd=new BodyDef();
        PolygonShape shape=new PolygonShape();
        FixtureDef fd=new FixtureDef();
        bd.type= BodyDef.BodyType.StaticBody;
        bd.position.set((poly.getX()+(poly.getWidth()/2)),(poly.getY()+(poly.getHeight())/2));
        shape.setAsBox(poly.getWidth(),poly.getHeight());
        fd.shape=shape;
        b= world.createBody(bd);
        b.createFixture(fd).setUserData("ground");
    }
}
