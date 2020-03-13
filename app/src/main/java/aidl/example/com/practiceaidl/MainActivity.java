package aidl.example.com.practiceaidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private IBookInterface iBookInterface;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iBookInterface = IBookInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iBookInterface = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt_bind).setOnClickListener(this);
        findViewById(R.id.bt_add).setOnClickListener(this);
        findViewById(R.id.bt_print).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_bind:
                Intent intent = new Intent(this, MainService.class);
                bindService(intent, serviceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.bt_add:
                if (iBookInterface != null) {
                    try {
                        Log.i("MDL", "activity:" + android.os.Process.myPid());
                        Book book = new Book("猴子", "猴子他爸");
                        iBookInterface.addBook(book);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.bt_print:
                if (iBookInterface != null) {
                    try {
                        iBookInterface.printlnBookList();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}
