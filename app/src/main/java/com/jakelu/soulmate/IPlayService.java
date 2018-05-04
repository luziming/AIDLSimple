package com.jakelu.soulmate;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 勸君惜取少年時&莫待無花空折枝
 * ---------------------------------
 * Created by Jake on 2018/5/3.
 */

public class IPlayService extends Service {

    private RemoteCallbackList<IPlayCompleteCallback> mCallbackList = new RemoteCallbackList<>();
    private Detail mDetail;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("IPlayService", "onBind:");
        return mPlayManager;
    }


    private IPlayAidlInterface.Stub mPlayManager = new IPlayAidlInterface.Stub() {

        @Override
        public IBinder asBinder() {
            Log.e("IPlayService", "asBinder:");
            return mPlayManager;
        }

        @Override
        public void playProgram(Detail detail) throws RemoteException {
            Log.e("IPlayService", "playProgram:");
            mDetail = detail;
            new Thread(new PlayThread()).start();
        }

        @Override
        public void registeCallback(IPlayCompleteCallback callback) throws RemoteException {
            Log.e("IPlayService", "registeCallback:");
            mCallbackList.register(callback);
        }
    };

    private class PlayThread implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(500);
                callback();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void callback() {
        try {
            int N = mCallbackList.beginBroadcast();
            for (int i = 0; i < N; i++) {
                IPlayCompleteCallback broadcastItem = mCallbackList.getBroadcastItem(i);
                if (broadcastItem != null) {
                    try {
                        broadcastItem.playComplete(mDetail.name);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IllegalArgumentException exception) {
            Log.e("IPlayService", "callback: IllegalArgumentException");
        }finally {
            try {
                mCallbackList.finishBroadcast();
            }catch (IllegalArgumentException illegalArgumentException){
                Log.e("IPlayService", "callback: finishBroadcast IllegalArgumentException");
            }
        }

    }
}
