package com.approject.tankstars.screens;

import com.approject.tankstars.MytankstarsGame;
import com.approject.tankstars.scenes.Status1;
import com.approject.tankstars.sprites.Missile;
import com.approject.tankstars.sprites.Tankobjects;
import com.approject.tankstars.tools.Worldcreator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.io.Serializable;

import static java.lang.Math.*;
import static java.lang.Math.PI;

public class Gamescreen implements Screen, Serializable {
	private final Gamescreen g1=this;
	public int turn=1;
	public final MytankstarsGame mygame;
	public World world;
	public boolean destroy=false;
	public int win;
	public boolean isgameover=false;
	public Box2DDebugRenderer boxkarender;
	private  OrthographicCamera camera;
	private Viewport port;
	private String name;
	public Status1 header;

	public Gamescreen(MytankstarsGame myGame, float p,float a, int _turn, float h1, float f1, Vector2 v1, float h2, float f2, Vector2 v2) {
		this.mygame = myGame;
		camera = new OrthographicCamera();
		port = new FitViewport(1200, 561, camera);
		loadme = new TmxMapLoader();
		if (mygame.getMode() == 1) name = "Player-2";
		else name = "Computer";
		header = new Status1(myGame.batch, name, myGame.fontstyle);
		header.setPower(p,mygame.fontstyle);
		header.setAngle(a,mygame.fontstyle);
		mapy = loadme.load("Background" + mygame.getMap() + "/Background" + mygame.getMap() + "/Background" + mygame.getMap() + ".tmx");
		mapykarender = new OrthogonalTiledMapRenderer(mapy);
		camera.position.set(port.getWorldWidth() / 2, port.getWorldHeight() / 2, 0);
		world = new World(new Vector2(0, -10), true);
		boxkarender = new Box2DDebugRenderer();
		new Worldcreator(world, mapy);
		isContact();
		turn=_turn;
		t1 = new Tankobjects(true, this, mygame.getTankchosen_1(), "-",h1,f1,v1);
		t2 = new Tankobjects(false, this, mygame.getTankchosen_2(), "i",h2,f2,v2);
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	transient private TmxMapLoader loadme;
	private OrthogonalTiledMapRenderer mapykarender;
	public TiledMap mapy;
	public Tankobjects t1;
	public Tankobjects t2;
	public Missile m;
	public boolean firing=false;
	public Gamescreen(MytankstarsGame myGame) {
		this.mygame = myGame;
		camera = new OrthographicCamera();
		port = new FitViewport(1200, 561, camera);
		loadme = new TmxMapLoader();
		if (mygame.getMode() == 1) name = "Player-2";
		else name = "Computer";
		header = new Status1(myGame.batch, name, myGame.fontstyle);
		mapy = loadme.load("Background" + mygame.getMap() + "/Background" + mygame.getMap() + "/Background" + mygame.getMap() + ".tmx");
		mapykarender = new OrthogonalTiledMapRenderer(mapy);
		camera.position.set(port.getWorldWidth() / 2, port.getWorldHeight() / 2, 0);
		world = new World(new Vector2(0, -10), true);
		boxkarender = new Box2DDebugRenderer();
		new Worldcreator(world, mapy);
		isContact();
		t1 = new Tankobjects(true, this, mygame.getTankchosen_1(), "-");
		t2 = new Tankobjects(false, this, mygame.getTankchosen_2(), "i");
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		updatemine(delta);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mapykarender.render();
		boxkarender.render(world,camera.combined);
		mygame.batch.setProjectionMatrix(camera.combined);
		mygame.batch.begin();
		t1.draw(mygame.batch);
		t2.draw(mygame.batch);
		if(firing)m.draw(mygame.batch);
		mygame.batch.end();
		mygame.batch.setProjectionMatrix(header.mystage.getCamera().combined);
		header.mystage.draw();
	}

	private void updatemine(float dst) {
		trychanging();
		handleinput(dst);
		world.step(1/60f,6,2);
		t1.updatetank();
		t2.updatetank();
		if(!firing){
			camera.position.x=(turn==1)?t1.b.getPosition().x+500:t2.b.getPosition().x-500;
//			showtrajectory();
		}
		else{
			m.update(dst);
			camera.position.x=m.b.getPosition().x;
			camera.position.y=m.b.getPosition().y;
		}
		camera.update();
		mapykarender.setView(camera);
	}

	private void trychanging() {
		if(isgameover){
			mygame.setScreen(new GameOverscreen(mygame));
			dispose();
		}
	}

//	private void showtrajectory() {
//		Pixmap pixmap = new Pixmap(5, 5, Pixmap.Format.RGBA8888);
//		pixmap.setColor(Color.WHITE);
//		pixmap.fillCircle(900, 400, 5);
//		Texture c = new Texture(pixmap);
//
//		if(turn==1){
//			float x=t1.b.getPosition().x+30f;
//			float y=t1.b.getPosition().y+30f;
//			mygame.batch.begin();
//			mygame.batch.draw(c,x,y);
//			x+=30f;y=fn1(x);
//			mygame.batch.draw(c,x,y);
//			x+=30f;y=fn1(x);
//			mygame.batch.draw(c,x,y);
//			x+=30f;y=fn1(x);
//			mygame.batch.draw(c,x,y);
//			x+=30f;y=fn1(x);
//			mygame.batch.draw(c,x,y);
//			mygame.batch.end();
//		}
//	}

//	private float fn1(float x) {
//		float nicef=(float)((x*tan(header.getAngle()))-(90*x*x/(2*cos(header.getAngle())*cos(header.getAngle())* header.getPower()*header.getPower())));
//		return nicef;
//	}

	private void handleinput(float dst) {
		if(Gdx.input.justTouched()){
			Vector3 tmp=new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
			Rectangle b1=new Rectangle(25,10,200,100);
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
								mygame.setScreen(new Pausescreen(mygame,g1));
							}
						});
					}
				}).start();
			}
			else{
			}
		}
		else if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)&&!firing){
			if(turn==1&&t1.fuel>0){
				t1.b.applyLinearImpulse(new Vector2(10f, 0), t1.b.getWorldCenter(), true);
				if(t1.b.getLinearVelocity().x >= 0)t1.decreaseFuel(10);
			}
			else if(turn==2 && t2.fuel>0){
				t2.b.applyLinearImpulse(new Vector2(10f, 0), t2.b.getWorldCenter(), true);
				if(t1.b.getLinearVelocity().x >= 0)t2.decreaseFuel(10);
			}
		}
		else if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)&&!firing){
			if(turn==1 && t1.fuel>0){
				t1.b.applyLinearImpulse(new Vector2(-10f, 0), t1.b.getWorldCenter(), true);
				if(t1.b.getLinearVelocity().x <= 0) t1.decreaseFuel(10);
			}
			else if(turn==2 && t2.fuel>0){
				t2.b.applyLinearImpulse(new Vector2(-10f, 0), t2.b.getWorldCenter(), true);
				if(t1.b.getLinearVelocity().x <= 0)t2.decreaseFuel(10);
			}
		}
		else if(Gdx.input.isKeyJustPressed(Input.Keys.UP)&&!firing){
			if(turn==1){
				t1.b.applyLinearImpulse(new Vector2(0, 10f), t1.b.getWorldCenter(), true);
			}
			else if(turn==2 && t2.fuel>0){
				t2.b.applyLinearImpulse(new Vector2(0, 10f), t2.b.getWorldCenter(), true);
			}
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyJustPressed(Input.Keys.P)&&!firing){
			if((header.getPower()<=90))
				header.setPower(header.getPower()+10,mygame.fontstyle);
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.UP) && Gdx.input.isKeyJustPressed(Input.Keys.A)&&!firing){
			if((header.getAngle()<=85))
				header.setAngle(header.getAngle()+5,mygame.fontstyle);
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && Gdx.input.isKeyJustPressed(Input.Keys.P)&&!firing){
			if((header.getPower()>=10))
				header.setPower(header.getPower()-10,mygame.fontstyle);
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && Gdx.input.isKeyJustPressed(Input.Keys.A)&&!firing){
			if((header.getAngle()>=5))
				header.setAngle(header.getAngle()-5,mygame.fontstyle);
		}
		else if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)&&!firing){
			if(turn==1){
				firing=true;
				m=new Missile(t1.b.getPosition().x,t1.b.getPosition().y,this);
				m.b.setLinearVelocity((float) (header.getPower()*cos(header.getAngle()*PI/180)*3), (float) (header.getPower()*sin(header.getAngle()*PI/180))*3);
			}
			else{
				firing=true;
				m=new Missile(t2.b.getPosition().x,t2.b.getPosition().y,this);
				m.b.setLinearVelocity((float) (-1*header.getPower()*cos(header.getAngle()*PI/180)*3), (float) (header.getPower()*sin(header.getAngle()*PI/180))*3);
			}
		}
	}
	@Override
	public void resize(int width, int height) {
		port.update(width,height);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume(){
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		mapy.dispose();
		//mapykarender.dispose();
		//world.dispose();
		//boxkarender.dispose();
		header.dispose();
	}
	private void isContact(){
		world.setContactListener(new ContactListener(){
			@Override
			public void beginContact(Contact contact) {
				Fixture fixtA=contact.getFixtureA();
				Fixture fixtB=contact.getFixtureB();
				if(fixtA.getUserData()=="missile" || fixtB.getUserData()=="missile"){
					Circle cs = new Circle();
					cs.setPosition(m.b.getPosition());
					cs.setRadius(50);
					if (cs.contains(t2.b.getPosition())) t2.decreasehp(40);
					else if (cs.contains(t1.b.getPosition())) t1.decreasehp(40);
					cs.setRadius(80);
					if (cs.contains(t2.b.getPosition())) t2.decreasehp(20);
					else if (cs.contains(t1.b.getPosition())) t1.decreasehp(20);
					cs.setRadius(150);
					if (cs.contains(t2.b.getPosition())) t2.decreasehp(10);
					else if (cs.contains(t1.b.getPosition())) t1.decreasehp(10);
					destroy=true;
				}
			}

			@Override
			public void endContact(Contact contact) {

			}

			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {

			}

			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {

			}
		});
	}


}

