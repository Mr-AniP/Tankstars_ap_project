package com.approject.tankstars.screens;

import com.approject.tankstars.MytankstarsGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class Choosemap implements Screen {
    private MytankstarsGame mygame;
    private int screen;
    OrthographicCamera camera;
    private Texture t;
    public Choosemap(MytankstarsGame myGame,int stas) {
        this.mygame=myGame;
        this.screen=stas;
        camera=new OrthographicCamera();
        camera.setToOrtho(false, 1200, 561);
    }
    @Override
    public void show() {
        t= new Texture(Gdx.files.internal("m"+screen+".png"));

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        mygame.batch.setProjectionMatrix(camera.combined);
        mygame.batch.begin();
        mygame.batch.draw(t,0,0);
        mygame.fontstyle.getData().setScale(3);
        mygame.batch.end();
        if(Gdx.input.justTouched())
        {
            Vector3 tmp=new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
            camera.unproject(tmp);
            Rectangle b1=new Rectangle(1025,230,120,125);
            if(b1.contains(tmp.x,tmp.y))
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long cortime=System.currentTimeMillis();
                        while(System.currentTimeMillis()-cortime<100);
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                if(screen==6)screen=1;else screen+=1;
                                mygame.setScreen(new Choosemap(mygame,screen));
                            }
                        });
                    }
                }).start();
            }
            Rectangle b2=new Rectangle(50,225,120,130);
            if(b2.contains(tmp.x,tmp.y))
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long cortime=System.currentTimeMillis();
                        while(System.currentTimeMillis()-cortime<100);
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                if(screen==1)screen=6;else screen-=1;
                                mygame.setScreen(new Choosemap(mygame,screen));
                            }
                        });
                    }
                }).start();
            }
            Rectangle b3=new Rectangle(380,22,415,125);
            if(b3.contains(tmp.x,tmp.y))
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long cortime=System.currentTimeMillis();
                        while(System.currentTimeMillis()-cortime<100);
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                mygame.setMap(screen);
                                mygame.setScreen(new Gamescreen(mygame));
                            }
                        });
                    }
                }).start();
            }
            Rectangle b4=new Rectangle(1090,465,120,130);
            if(b4.contains(tmp.x,tmp.y))
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        long cortime=System.currentTimeMillis();
                        while(System.currentTimeMillis()-cortime<100);
                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                mygame.setScreen(new Newgame(mygame));
                            }
                        });
                    }
                }).start();
            }
        }
    }
    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
