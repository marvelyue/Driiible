package com.example.yuewu.driiible;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by YueWu on 10/22/16.
 */

public class DriiibleApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
