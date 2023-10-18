package com.approject.tankstars.sprites;

import com.approject.tankstars.screens.Gamescreen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.physics.box2d.*;

public class Missile extends Sprite {
    public Body b;
    private Gamescreen gi;
    private World w;
    private TextureRegion neo;
    private float cx,cy;

    public Missile(float x, float y,Gamescreen g) {
        super(new TextureRegion(new Texture("bomb.png")));
        gi=g;
        w=g.world;
        cy=y+30f;
        if(g.turn==1){
            cx=x+30f;

        }
        else{
            cx=x-30f;
        }
        newbomb();
        neo=new TextureRegion(getTexture(),0,0,32,20);
        setBounds(0,0,32,20);
        setRegion(neo);
    }

    private void newbomb() {
        BodyDef bd=new BodyDef();
        bd.position.set(cx,cy);
        bd.type=BodyDef.BodyType.DynamicBody;
        CircleShape shape=new CircleShape();
        shape.setRadius(10);
        FixtureDef fd=new FixtureDef();
        fd.shape=shape;
        b=w.createBody(bd);
        b.createFixture(fd).setUserData("missile");
    }

    public void update(float dt){
        setPosition(b.getPosition().x-getWidth()/2,b.getPosition().y-getHeight()/2);
        if(gi.destroy){
            w.destroyBody(b);
            if( gi.turn==1){
                gi.turn=2;
                gi.t1.setFuel(100);
            }
            else{
                gi.turn=1;
                gi.t2.setFuel(100);
            }
            gi.header.setAngle(45,gi.mygame.fontstyle);
            gi.header.setPower(35,gi.mygame.fontstyle);
            gi.firing=false;
            gi.destroy=false;
        }
    }

    public void destroy() {
        w.destroyBody(b);
    }
}
