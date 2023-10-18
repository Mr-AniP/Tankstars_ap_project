package com.approject.tankstars.tools;

import com.approject.tankstars.sprites.Ground;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

public class Worldcreator {
    public Worldcreator(World world, TiledMap mapy){
//        private void createterainobject() {
//            boxkarender=new Box2DDebugRenderer();
//            BodyDef bd=new BodyDef();
//            for(MapObject object: objects){
//                Shape shape;
//                if(object instanceof PolylineMapObject) {
//                    shape= createPolyline(PolylineMapObject) object);
//                }else{continue;}
//            }
//            Body body;
//            BodyDef bdef=new BodyDef();
//            bdef.type=BodyDef.BodyType.StaticBody;
//            body=world.createBody(bdef);
//            body.createFixture(shape, 1.0f);
//            for(MapObject obj:mapy.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
//                Rectangle poly=((RectangleMapObject)obj).getRectangle();
//                bd.type=BodyDef.BodyType.StaticBody;
//                bd.position.set((poly.getX()+(poly.getWidth()/2)),(poly.getY()+(poly.getHeight())/2));
//                shape.setAsBox(poly.getWidth(),poly.getHeight());
//                fd.shape=shape;
//                terainbody= world.createBody(bd);
//                terainbody.createFixture(fd);
//            }
//        }
//        private static ChainShape createPolyline(PolylineMapObject polyline){
//            float[] vertices = polyline.getPolyline().getTransformedVertices();
//            Vector2[] worldVertices = new Vector2[vertices.length/2];
//            for(int i=0;i<worldVertices.length; i++){
//                worldVertices[i]=new Vector2[i*2]/ Constants.PPM, vertices[i*(2+1)]/Constants.PPM);
//            }
//            ChainShape cs= new ChainShape();
//            cs.createChain(worldVertices);
//            return cs;
//        }

        for(MapObject obj:mapy.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle poly=((RectangleMapObject)obj).getRectangle();
            new Ground(world,mapy,poly);
        }
    }
}
