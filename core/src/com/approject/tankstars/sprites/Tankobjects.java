package com.approject.tankstars.sprites;

import com.approject.tankstars.screens.Gamescreen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.io.Serializable;


public class Tankobjects extends Sprite implements Serializable {
    transient private Gamescreen gi;
    public World w;
    public Body b;
    public float hp,fuel;
    private Boolean t;
    private TextureRegion neo;
    public Tankobjects(Boolean val, Gamescreen g,int no,String s){
        super(new TextureRegion(new Texture("tank"+s+no+".png")));
        w=g.world;
        gi=g;
        t=val;
        newtank();
        hp=100;
        fuel=100;
        if(no==1||no==4){
            neo=new TextureRegion(getTexture(),0,0,107,60);
            setBounds(0,0,107,60);
        }
        else if(no==2){
            neo=new TextureRegion(getTexture(),0,0,104,60);
            setBounds(0,0,104,60);
        }
        else{
            neo=new TextureRegion(getTexture(),0,0,77,60);
            setBounds(0,0,77,60);
        }
        setRegion(neo);
    }

    public Tankobjects(Boolean val, Gamescreen g,int no,String s, float h1, float f1, Vector2 v1) {
        super(new TextureRegion(new Texture("tank"+s+no+".png")));
        w=g.world;
        gi=g;
        t=val;
        newtank(v1);
        setFuel(f1);
        setHp(h1);
        if(no==1||no==4){
            neo=new TextureRegion(getTexture(),0,0,107,60);
            setBounds(0,0,107,60);
        }
        else if(no==2){
            neo=new TextureRegion(getTexture(),0,0,104,60);
            setBounds(0,0,104,60);
        }
        else{
            neo=new TextureRegion(getTexture(),0,0,77,60);
            setBounds(0,0,77,60);
        }
        setRegion(neo);
    }
    private void newtank(Vector2 v1) {
        BodyDef bd=new BodyDef();
        bd.position.set(v1.x,v1.y);
        bd.type=BodyDef.BodyType.DynamicBody;
        CircleShape shape=new CircleShape();
        shape.setRadius(30);
        FixtureDef fd=new FixtureDef();
        fd.shape=shape;
        b=w.createBody(bd);
        b.createFixture(fd).setUserData("tank");

    }

    private void setHp(float _hp) {
        hp=_hp;
        if(t){
            gi.header.b1.setValue(hp);
        }
        else {
            gi.header.b2.setValue(hp);
        }
    }

    private void newtank() {
        BodyDef bd=new BodyDef();
        if(t)
            bd.position.set(650,200);
        else if(!t)
            bd.position.set(1650,200);
        bd.type=BodyDef.BodyType.DynamicBody;
        CircleShape shape=new CircleShape();
        shape.setRadius(30);
        FixtureDef fd=new FixtureDef();
        fd.shape=shape;
        b=w.createBody(bd);
        b.createFixture(fd).setUserData("tank");
    }
    public void decreaseFuel(float v) {
        fuel-=v;
        gi.header.b3.setValue(fuel);
    }
    public void setFuel(float v) {
        fuel=v;
        gi.header.b3.setValue(fuel);
    }

    public void decreasehp(float v) {
        hp-=v;
        if(t){
            gi.header.b1.setValue(hp);
        }
        else {
            gi.header.b2.setValue(hp);
        }
    }
    public void updatetank(){
        setPosition(b.getPosition().x-getWidth()/2,b.getPosition().y-getHeight()/2);
        if(hp<=0){
            gi.isgameover=true;
            if(t){
                gi.win=2;
            }
            else{
                gi.win=1;
            }
            gi.mygame.whowon=gi.win;
        }
    }
}
