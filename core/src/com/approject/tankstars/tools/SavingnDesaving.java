package com.approject.tankstars.tools;

import com.approject.tankstars.MytankstarsGame;
import com.approject.tankstars.scenes.Status1;
import com.approject.tankstars.screens.Gamescreen;
import com.approject.tankstars.sprites.Tankobjects;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SavingnDesaving {
    private static SavingnDesaving gen =null;
    private String name="yo";

    public static SavingnDesaving getGen() {
        if(gen!=null)return gen;
        gen=new SavingnDesaving();
        return gen;
    }

    private void saveData(ArrayList<Object> t){
        try{
            ObjectOutputStream out1 = new ObjectOutputStream(new FileOutputStream(name));
            try{
                out1.writeObject(t);
            }
            finally {
                out1.close();
            }
        }
        catch(IOException e){e.printStackTrace();}
    }

    private void LoadData(ArrayList<Object> t){
        try{
            ObjectInputStream in1 =new ObjectInputStream(new FileInputStream(name));
            try{
                t.add(in1.readObject());
            }
            finally{
                in1.close();
            }
        }
        catch(Exception e){e.printStackTrace();}
    }

    public void loadnewgame(MytankstarsGame mygame){
        String t=name;
        JFrame f = new JFrame("file chooser");
        f.setSize(400, 400);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button1 = new JButton("open");
        JPanel p = new JPanel();
        button1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String com = e.getActionCommand();
                JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                j.showOpenDialog(null);
                name=j.getSelectedFile().getAbsolutePath();
            }
        });
        p.add(button1);
        f.add(p);
        f.show();
        while(t.equals(name));
        //name="Game_21_12_2022_23_52_47.ser";
        ArrayList<Object>arr=new ArrayList<Object>();
        LoadData(arr);
        mygame.setMap((int) arr.get(0));
        mygame.setMode((int) arr.get(1));
        mygame.setTankchosen_1((int) arr.get(2));
        mygame.setTankchosen_2((int) arr.get(3));
        mygame.setScreen(new Gamescreen(mygame,(float) arr.get(5),(float) arr.get(6),(int) arr.get(7),(float) arr.get(8),(float) arr.get(9),(Vector2) arr.get(10),(float) arr.get(11),(float) arr.get(12),(Vector2) arr.get(13)));
    }
    public void savenewgame(Gamescreen gi){
        int i=0;
        SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        Date now = new Date();
        name="Game_"+sdf.format(now)+".ser";
        ArrayList<Object>arr=new ArrayList<Object>();
        arr.add(gi.mygame.getMap());
        arr.add(gi.mygame.getMode());
        arr.add(gi.mygame.getTankchosen_1());
        arr.add(gi.mygame.getTankchosen_2());
        arr.add(i);
        arr.add(gi.header.getPower());
        arr.add(gi.header.getAngle());
        arr.add(gi.turn);
        arr.add(gi.t1.hp);
        arr.add(gi.t1.fuel);
        arr.add(gi.t1.b.getPosition());
        arr.add(gi.t2.hp);
        arr.add(gi.t2.fuel);
        arr.add(gi.t2.b.getPosition());
        saveData(arr);
    }

}
