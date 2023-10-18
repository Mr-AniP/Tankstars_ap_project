package com.approject.tankstars.screens;

import com.approject.tankstars.MytankstarsGame;
import com.approject.tankstars.screens.Choosetank;
import com.approject.tankstars.screens.MainScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Newgame implements Screen {

	final MytankstarsGame mygame;
	OrthographicCamera camera;
	Texture backgroundTexture,img1,img2;
	public Newgame(MytankstarsGame myGame) {
		this.mygame=myGame;
		camera=new OrthographicCamera();
		camera.setToOrtho(false, 1200, 561);
		mygame.setMode(1);
	}
	@Override
	public void show() {
		backgroundTexture = new Texture(Gdx.files.internal("new.png"));
		img1= new Texture(Gdx.files.internal("new1.png"));
		img2= new Texture(Gdx.files.internal("new2.png"));

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		mygame.batch.setProjectionMatrix(camera.combined);
		mygame.batch.begin();
		mygame.batch.draw(backgroundTexture,0,0);
		mygame.batch.end();
		if(Gdx.input.justTouched())
		{
			Vector3 tmp=new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
			camera.unproject(tmp);
			Rectangle b1=new Rectangle(730,360,415,125);
			if(b1.contains(tmp.x,tmp.y))
			{
				mygame.batch.begin();
				mygame.batch.draw(img1,0,0);
				mygame.batch.end();
				new Thread(new Runnable() {
					@Override
					public void run() {
						long cortime=System.currentTimeMillis();
						while(System.currentTimeMillis()-cortime<100);
						Gdx.app.postRunnable(new Runnable() {
							@Override
							public void run() {
								mygame.setScreen(new Choosetank(mygame,0,0,1));
							}
						});
					}
				}).start();
			}
			Rectangle b2=new Rectangle(730,105,415,125);
			if(b2.contains(tmp.x,tmp.y)) {
				mygame.batch.begin();
				mygame.batch.draw(img2, 0, 0, 2, 2, 1200, 561);
				mygame.batch.end();
				new Thread(new Runnable() {
					@Override
					public void run() {
						long cortime = System.currentTimeMillis();
						while (System.currentTimeMillis() - cortime < 100) ;
						mygame.setMode(0);
						Gdx.app.postRunnable(new Runnable() {
							@Override
							public void run() {
								mygame.setScreen(new Choosetank(mygame, 1,0,1));
							}
						});
					}
				}).start();
			}
			Rectangle b3=new Rectangle(20,500,30,50);
			if(b3.contains(tmp.x,tmp.y)) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						long cortime = System.currentTimeMillis();
						while (System.currentTimeMillis() - cortime < 100) ;
						Gdx.app.postRunnable(new Runnable() {
							@Override
							public void run() {
								mygame.setScreen(new MainScreen(mygame));
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
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
	}

}
