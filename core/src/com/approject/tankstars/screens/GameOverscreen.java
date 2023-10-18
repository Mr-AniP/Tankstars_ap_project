package com.approject.tankstars.screens;

import com.approject.tankstars.MytankstarsGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

import javax.swing.*;

public class GameOverscreen implements Screen {
    private MytankstarsGame mygame;
    Texture backgroundTexture;
    OrthographicCamera camera;
    public GameOverscreen(MytankstarsGame myGame) {
        this.mygame=myGame;
        camera=new OrthographicCamera();
        camera.setToOrtho(false, 1200, 561);
    }

    @Override
    public void show() {
        backgroundTexture = new Texture(Gdx.files.internal("over.png"));
    }

    @Override
    public void render(float delta) {
        //Gdx.gl.glClearColor(, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        mygame.batch.setProjectionMatrix(camera.combined);
        mygame.batch.begin();
        mygame.batch.draw(backgroundTexture,0,0,0,0,1200,561);
        mygame.fontstyle.getData().setScale(3);
        if(mygame.whowon==1)
            mygame.fontstyle.draw(mygame.batch, "Congratulations to Player-"+mygame.whowon+"!! You won!!", 200, 300);
        else if(mygame.getMode()!=1)
            mygame.fontstyle.draw(mygame.batch, "Congratulations to Computer!! You lost!!", 200, 300);
        else
            mygame.fontstyle.draw(mygame.batch, "Congratulations to Player-"+mygame.whowon+"!! You won!!", 200, 300);
        mygame.fontstyle.draw(mygame.batch, "Click anywhere to return to Startmenu!!", 200, 150);
        mygame.batch.end();
        if(Gdx.input.justTouched()){
            mygame.setScreen(new MainScreen(mygame));
            dispose();
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
        backgroundTexture.dispose();
    }
}
