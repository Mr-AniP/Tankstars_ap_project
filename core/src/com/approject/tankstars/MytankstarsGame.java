package com.approject.tankstars;

import com.approject.tankstars.screens.LoadScreen;
import com.approject.tankstars.tools.SavingnDesaving;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


public class MytankstarsGame extends Game {
	public SpriteBatch batch;
	public BitmapFont fontstyle;
    public SavingnDesaving savedinst=SavingnDesaving.getGen();
    public int whowon=0;

    public int getTankchosen_1() {
		return tankchosen_1;
	}

	public void setTankchosen_1(int tankchosen_1) {
		this.tankchosen_1 = tankchosen_1;
	}

	public int getTankchosen_2() {
		return tankchosen_2;
	}

	public void setTankchosen_2(int tankchosen_2) {
		this.tankchosen_2 = tankchosen_2;
	}

	private int tankchosen_1=2;
	private int tankchosen_2=1;
	private int mode;

	public int getMap() {
		return map;
	}

	public void setMap(int map) {
		this.map = map;
	}

	private int map=1;

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public void create() {
		batch = new SpriteBatch();
		fontstyle = new BitmapFont();
		this.setScreen(new LoadScreen(this));
	}

	public void render() {
		super.render();
	}

	public void dispose() {
		batch.dispose();
		fontstyle.dispose();
	}
}
