package jake.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jakelu.soulmate.Detail;
import com.jakelu.soulmate.IPlayAidlInterface;
import com.jakelu.soulmate.IPlayCompleteCallback;

public class MainActivity extends AppCompatActivity {

    private boolean isConnect = false;
    private IPlayAidlInterface mIPlayManager;
    private Detail mDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDetail = new Detail();
        mDetail.name = "复仇者联盟3";
        mDetail.type = "0";
        mDetail.contentid = "123456789";

        findViewById(R.id.bt_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play(view);
            }
        });
    }

    public void play(View view) {
        if (!isConnect) {
            attempToBindService();
            Toast.makeText(this, "当前与服务端处于未连接状态，正在尝试重连，请稍后再试", Toast.LENGTH_SHORT).show();
        }
        if (mIPlayManager == null) {
            return;
        }
        try {
            mIPlayManager.playProgram(mDetail);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.e("MainActivity", "onStart:");
        if (!isConnect) {
            attempToBindService();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("MainActivity", "onStop:");
        if (isConnect) {
            unbindService(mConnection);
            isConnect = false;
        }
    }

    private void attempToBindService() {
        Log.e("MainActivity", "attempToBindService:");
        Intent intent = new Intent();
        intent.setAction("com.jakelu.soulmate.IPlayAidlInterface");
        intent.setPackage("com.jakelu.soulmate");
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }


    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.e("MainActivity", "onServiceConnected:");
            try {
                //注册
                iBinder.linkToDeath(mDeathRecipient, 0);
                mIPlayManager = IPlayAidlInterface.Stub.asInterface(iBinder);
                mIPlayManager.registeCallback(mCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            isConnect = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.e("MainActivity", "onServiceDisconnected:");
            isConnect = false;
        }
    };
    /**
     * 当client意外退出的时候将会回调
     */
    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            Log.e("MainActivity", "binderDied:");
            if (mIPlayManager == null) {
                return;
            }
            mIPlayManager.asBinder().unlinkToDeath(mDeathRecipient, 0);
            mIPlayManager = null;
            //重新绑定远程服务
            attempToBindService();
        }
    };

    private IPlayCompleteCallback mCallback = new IPlayCompleteCallback.Stub() {
        @Override
        public void playComplete(final String string) throws RemoteException {
            Log.e("MainActivity", "playComplete:");
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    Toast.makeText(MainActivity.this, string + "播放结束", Toast.LENGTH_SHORT).show();
                }
            });
        }
    };
}
