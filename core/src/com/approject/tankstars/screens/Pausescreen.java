package com.approject.tankstars.screens;

import com.approject.tankstars.MytankstarsGame;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Pausescreen implements Screen {final MytankstarsGame mygame;
	private Texture backgroundTexture,img1,img2,img3;
	private Gamescreen g;
	private OrthographicCamera camera;
	public Pausescreen(MytankstarsGame myGame,Gamescreen g1) {
		this.mygame=myGame;
		g=g1;
		camera=new OrthographicCamera();
		camera.setToOrtho(false, 1200, 561);
	}
	@Override
	public void show() {
		backgroundTexture = new Texture(Gdx.files.internal("Pausescreen.png"));
		img1= new Texture(Gdx.files.internal("pause1.png"));
		img2= new Texture(Gdx.files.internal("pause2.png"));
		img3= new Texture(Gdx.files.internal("pause3.png"));
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
								mygame.setScreen(g);
								dispose();
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
								mygame.savedinst.savenewgame(g);
								mygame.setScreen(g);
								dispose();
							}
						});
					}
				}).start();
			}
			Rectangle b3=new Rectangle(730,62,415,125);
			if(b3.contains(tmp.x,tmp.y))
			{g.dispose();
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
								mygame.setScreen(new Newgame(mygame));
								dispose();
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
		backgroundTexture.dispose();img1.dispose();img2.dispose();img3.dispose();
	}

}

