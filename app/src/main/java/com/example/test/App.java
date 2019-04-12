package com.example.test;

import android.app.Application;

import com.kk.taurus.playerbase.config.PlayerConfig;
import com.kk.taurus.playerbase.config.PlayerLibrary;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化视频插件
        PlayerConfig.setUseDefaultNetworkEventProducer(true);
        //初始化库
        PlayerLibrary.init(this);



    }
}
