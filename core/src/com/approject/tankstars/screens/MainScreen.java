package com.approject.tankstars.screens;

import com.approject.tankstars.MytankstarsGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class MainScreen implements Screen {
	final MytankstarsGame mygame;
	OrthographicCamera camera;
	Texture backgroundTexture,img1,img2,img3;
	public MainScreen(MytankstarsGame myGame) {
		this.mygame=myGame;
		camera=new OrthographicCamera();
		camera.setToOrtho(false, 1200, 561);
	}
	@Override
	public void show() {
		backgroundTexture = new Texture(Gdx.files.internal("mainscreen.png"));
		img1= new Texture(Gdx.files.internal("main1.png"));
		img2= new Texture(Gdx.files.internal("main2.png"));
		img3= new Texture(Gdx.files.internal("main3.png"));

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
			Rectangle b1=new Rectangle(730,390,415,125);
			if(b1.contains(tmp.x,tmp.y))
			{
				mygame.batch.begin();
				mygame.batch.draw(img1,2,0);
				mygame.batch.end();
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
			Rectangle b2=new Rectangle(730,230,415,125);
			if(b2.contains(tmp.x,tmp.y))
			{
				mygame.batch.begin();
				mygame.batch.draw(img2,0,0,2,2,1200,561);
				mygame.batch.end();
				new Thread(new Runnable() {
					@Override
					public void run() {
						long cortime=System.currentTimeMillis();
						while(System.currentTimeMillis()-cortime<100);
						Gdx.app.postRunnable(new Runnable() {
							@Override
							public void run() {
								mygame.savedinst.loadnewgame(mygame);
								dispose();
							}
						});
					}
				}).start();
			}
			Rectangle b3=new Rectangle(730,62,415,125);
			if(b3.contains(tmp.x,tmp.y))
			{
				mygame.batch.begin();
				mygame.batch.draw(img3,0,0);
				mygame.batch.end();
				new Thread(new Runnable() {
					@Override
					public void run() {
						long cortime=System.currentTimeMillis();
						while(System.currentTimeMillis()-cortime<100);
						Gdx.app.postRunnable(new Runnable() {
							@Override
							public void run() {
								System.exit(0);
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


	}

	@Override
	public void dispose() {
	}

}
