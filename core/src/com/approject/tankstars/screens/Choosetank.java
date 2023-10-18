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

public class Choosetank implements Screen {
	final MytankstarsGame mygame;
	private int screen;
	OrthographicCamera camera;
	private Texture t;
	private int stat;
	private int player;
	public Choosetank(MytankstarsGame myGame,int stati,int val,int stas) {
		this.stat=stati;
		this.player=val;
		this.mygame=myGame;
		this.screen=stas;
		camera=new OrthographicCamera();
		camera.setToOrtho(false, 1200, 561);
	}
	@Override
	public void show() {
		t= new Texture(Gdx.files.internal("t"+screen+".png"));

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		mygame.batch.setProjectionMatrix(camera.combined);
		mygame.batch.begin();
		mygame.batch.draw(t,0,0);
		mygame.fontstyle.getData().setScale(3);
		if(this.stat==1 ){
			mygame.fontstyle.draw(mygame.batch, "Player - 1", 10, 525);
		}
		else if(this.player==1){
			mygame.fontstyle.draw(mygame.batch, "Player - 2", 10, 525);
		}
		else{
			mygame.fontstyle.draw(mygame.batch, "Player - 1", 10, 525);
		}
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
								if(screen==4)screen=1;else screen+=1;
								mygame.setScreen(new Choosetank(mygame,stat,player,screen));
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
								if(screen==1)screen=4;else screen-=1;
								mygame.setScreen(new Choosetank(mygame,stat,player,screen));
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
								if (stat == 1) {
									mygame.setTankchosen_2(new Random().nextInt(1,5));
									mygame.setTankchosen_1(screen);
									mygame.setScreen(new Choosemap(mygame,1));
								}
								else if(player==1){
									mygame.setTankchosen_2(screen);
									mygame.setScreen(new Choosemap(mygame,1));
								}
								else{
									mygame.setTankchosen_1(screen);
									mygame.setScreen(new Choosetank(mygame,stat,1,1));
								}
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
