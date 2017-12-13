package com.example.jh.notification;

import android.app.NotificationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by jinhui on 2017/12/13.
 */

public class NotificationActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //cancel()方法中的参数是我们在启动通知，调用manager.notify(1, notification)方法时，传的id
        manager.cancel(1);
    }
}
