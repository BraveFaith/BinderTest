package com.banyan.bindertest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

public class RemoteService extends Service {

    private com.banyan.bindertest.IBinderTest.Stub stub = new com.banyan.bindertest.IBinderTest.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        ProcessState.remote = Thread.currentThread().getName();
        Log.i("BinderTest","remote = " + ProcessState.remote);
        return stub;
    }


}
