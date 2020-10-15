package com.bitsplease.qrcodescanner;
public class Database extends android.app.Application{
    

    @Override
    public void oncreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
