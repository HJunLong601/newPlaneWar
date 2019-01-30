package com.example.newplanewar.Factory;

import android.content.res.Resources;

import com.example.newplanewar.GameObject.BigPlane;
import com.example.newplanewar.GameObject.Bullet;
import com.example.newplanewar.GameObject.GameObject;
import com.example.newplanewar.GameObject.MidPlane;
import com.example.newplanewar.GameObject.MyPlane;
import com.example.newplanewar.GameObject.SmallPlane;

public class GameObjectFactory {

    private volatile static GameObjectFactory factory;

    private GameObjectFactory(){}

    public static GameObjectFactory getInstance(){

        if (factory == null){
            synchronized (GameObjectFactory.class){
                if (factory == null){
                    factory = new GameObjectFactory();
                }
            }
        }

        return factory;
    }

    public GameObject createSmallPlane(Resources resources){
        return new SmallPlane(resources);
    }
    public GameObject createMyPlane(Resources resources){return  new MyPlane(resources);}
    public GameObject createBullet(Resources resources){return  new Bullet(resources);}
    public GameObject createMidPlane(Resources resources){return new MidPlane(resources);}
    public GameObject createBigPlane(Resources resources){return new BigPlane(resources);}

}
