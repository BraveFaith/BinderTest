package com.banyan.bindertest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private com.banyan.bindertest.IBinderTest mLocalIBander;

    private com.banyan.bindertest.IBinderTest mRemoteIBander;

    private ServiceConnection mLocalConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mLocalIBander = com.banyan.bindertest.IBinderTest.Stub.asInterface(service);
            Log.i("BinderTest","mLocalConn = " + ProcessState.local + ",mLocalIBander = " + mLocalIBander + ",service = " + service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mLocalIBander = null;
        }
    };


    private ServiceConnection mRemoteConn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mRemoteIBander = com.banyan.bindertest.IBinderTest.Stub.asInterface(service);
            Log.i("BinderTest","mRemoteConn = " + ProcessState.remote + ",mRemoteIBander = " + mRemoteIBander + ",service = " + service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mRemoteIBander = null;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindService(new Intent(this, LocalService.class), mLocalConn, BIND_AUTO_CREATE);
        bindService(new Intent(this, RemoteService.class), mRemoteConn, Context.BIND_AUTO_CREATE);
    }


}
