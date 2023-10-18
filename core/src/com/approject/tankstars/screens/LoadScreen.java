package com.approject.tankstars.screens;

import com.approject.tankstars.MytankstarsGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class LoadScreen implements Screen {
    private MytankstarsGame mygame;
    Texture backgroundTexture;
    OrthographicCamera camera;
    public LoadScreen(MytankstarsGame game) {
        this.mygame=game;
        camera=new OrthographicCamera();
        camera.setToOrtho(false, 1200, 561);
    }

    @Override
    public void show() {
        backgroundTexture = new Texture(Gdx.files.internal("background.jpg"));

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        mygame.batch.setProjectionMatrix(camera.combined);
        mygame.batch.begin();
        mygame.batch.draw(backgroundTexture,0,0,0,0,1200,561);
        mygame.batch.end();
        new Thread(new Runnable() {
            @Override
            public void run() {
                long cortime=System.currentTimeMillis();
                while(System.currentTimeMillis()-cortime<500){}
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        mygame.setScreen(new MainScreen(mygame));
                    }
                });
            }
        }).start();
        if(Gdx.input.justTouched()){
            mygame.setScreen(new MainScreen(mygame));
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
