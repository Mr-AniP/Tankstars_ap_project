package com.approject.tankstars.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.io.Serializable;

public class Status1 implements Serializable, Disposable {
    public Stage mystage;
    public ProgressBar b1,b2,b3;
    private String name1,name2;
    private float power,angle;
    Label l1,l2,l3,l4;
    Image n1,n2,n3,n4,n5;
    private Viewport vport;
    public Status1(SpriteBatch sb,String sn2,BitmapFont fs){
        fs.getData().setScale(2f);
        power=0;angle=0;
        name1="Player-1";name2=sn2;
        b1=new ProgressBar((float) 0, (float)100, (float) 1, false, new ProgressBar.ProgressBarStyle());
        b3=new ProgressBar((float) 0, (float)100, (float) 1, false, new ProgressBar.ProgressBarStyle());
        b2=new ProgressBar((float) 0, (float)100, (float) 1, false, new ProgressBar.ProgressBarStyle());
        n1=new Image(new Texture(Gdx.files.internal("pauseicon.png")));
        n3=new Image(new Texture(Gdx.files.internal("power.png")));
        n4=new Image(new Texture(Gdx.files.internal("angle.png")));
        n5=new Image(new Texture(Gdx.files.internal("fuelicon.png")));
        vport= new FitViewport(1200,561,new OrthographicCamera());
        mystage=new Stage();
        Table tableleft=new Table();
        tableleft.left().top();
        tableleft.setFillParent(true);
        tableleft.add(n1).padTop(30);
        mystage.addActor(tableleft);
        b1.setHeight(40);
        b1.setWidth(250);
        b1.getStyle().background = getColoredDrawable(250, 40, Color.RED);
        b1.getStyle().knob = getColoredDrawable(0, 40, Color.GREEN);
        b1.getStyle().knobBefore = getColoredDrawable(250,40, Color.GREEN);
        b1.setValue(100);
        b2.setHeight(40);
        b2.setWidth(250);
        b2.setStyle(b1.getStyle());
        b2.setValue(100);
        b3.setHeight(40);
        b3.setWidth(250);
        b3.setStyle(b1.getStyle());
        b3.setValue(100);
        Table tableup=new Table();
        tableup.top();
        tableup.setFillParent(true);
        l1=new Label(name1,new Label.LabelStyle(fs, Color.WHITE));
        l2=new Label(name2,new Label.LabelStyle(fs, Color.WHITE));
        l3=new Label(""+power,new Label.LabelStyle(fs, Color.WHITE));
        l4=new Label(""+angle,new Label.LabelStyle(fs, Color.WHITE));
        n2=new Image(new Texture(Gdx.files.internal("vsicon.png")));
        tableup.add(l1).expandX().padLeft(170);
        tableup.add(n2).expandX().padTop(20);
        tableup.add(l2).expandX().padRight(170);
        mystage.addActor(tableup);
        Table tableup1=new Table();
        tableup1.top();
        tableup1.setFillParent(true);
        tableup1.add(b1).padLeft(50).expandX().padTop(90);
        tableup1.add(b2).expandX().padTop(90).padRight(50);
        mystage.addActor(tableup1);
        Table tabledl=new Table();
        tabledl.left().bottom();
        tabledl.setFillParent(true);
        tabledl.add(n3);
        tabledl.add(l3);
        mystage.addActor(tabledl);
        Table tablelr1=new Table();
        tablelr1.left().bottom();
        tablelr1.setFillParent(true);
        tablelr1.add(l3).padBottom(10).padLeft(110);
        mystage.addActor(tablelr1);
        Table tabledr=new Table();
        tabledr.right().bottom();
        tabledr.setFillParent(true);
        tabledr.add(n4);
        mystage.addActor(tabledr);
        Table tabledr1=new Table();
        tabledr1.right().bottom();
        tabledr1.setFillParent(true);
        tabledr1.add(l4).padBottom(10).padRight(15);
        mystage.addActor(tabledr1);
        Table tabled=new Table();
        tabled.bottom();
        tabled.setFillParent(true);
        tabled.add(n5);
        tabled.add(b3);
        mystage.addActor(tabled);
    }
    private Drawable getColoredDrawable(int w,int h,Color c){
        Pixmap pi=new Pixmap(w,h, Pixmap.Format.RGBA4444);
        pi.setColor(c);
        pi.fill();
        return new TextureRegionDrawable(new TextureRegion(new Texture(pi)));
    }
    public float getPower() {
        return power;
    }
    public float getAngle() {
        return angle;
    }
    public void setPower(float powery, BitmapFont fs) {
        power=powery;
        l3.setText(""+power);
    }
    public void setAngle(float anglei,BitmapFont fs) {
        this.angle = anglei;
        l4.setText(""+angle);
    }

    @Override
    public void dispose() {
        mystage.dispose();
    }
}
