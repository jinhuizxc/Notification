package com.example.jh.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {


    //通知管理器
    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);

        /**Type mismatch: cannot convert from Object to NotificationManager*/
        /**
         * 创建通知管理类NotificationManager的实例，用来管理通知
         */
        notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v.getId() == R.id.button1) {

            //建立builder对象，配置相应设置,builder为notification下的引用
            Builder builder = new Builder(this);
            //设置状态栏图片，文字
            builder.setSmallIcon(R.drawable.ic_launcher);
            builder.setTicker("紧急通知");
            //------设置通知面板
            //设置时间
            builder.setWhen(System.currentTimeMillis());
            //点击后自动取消通知面板
            builder.setAutoCancel(true);
            //设置标题
            builder.setContentTitle("通知？？");
            //设置通知内容
            builder.setContentText("我来自Notification");

            // Notification notification = builder.build(); //4.1以上
            //builder.getNotification();


            /**
             * 实例化Intent，构建意图：从当前页面条状到NotificationActivity页面
             */
            Intent intent = new Intent(this, NotificationActivity.class);


            //预制意图----如果不设置，状态栏不可点击,不会跳转到主程序，(类似于广告通知栏)。
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            //启动预制意图
            builder.setContentIntent(pendingIntent);


            //取出通知对象
            Notification notification = builder.getNotification();
            //发送id=1的通知,notify-----显示通知栏：notify(id, notification);
            notificationManager.notify(1, notification);

        } else if (v.getId() == R.id.button2) {
            //取消id=1的通知------取消通知栏：cancle(id);
            notificationManager.cancel(1);
        } else if (v.getId() == R.id.button3) {

            /**
             * 实例化Intent，构建意图：从当前页面条状到NotificationActivity页面
             */
            Intent intent = new Intent(this, NotificationActivity.class);

            /**
             * 用于启动活动、启动服务以及发送广播等。 根据需求来选择是使用
             * getActivity()方法、getBroadcast()方法、还是 getService() 方法。 一共四个参数：
             * 1)、第一个参数是 上下文Context ； 2)、 第二个参数一般用不到，通常都是传入 0 即可 3)、第三个参数是一个
             * Intent对象，我们可以通过这个对象构建出 PendingIntent 的“意图”； 4)、第四个参数用于确定
             * PendingIntent 的行为
             */
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                    intent, PendingIntent.FLAG_CANCEL_CURRENT);

            /**
             * 设定通知标准的布局。四个参数： 1)、上下文对象Context； 2)、指定通知的标题内容，下拉系统状态栏可看到；
             * 3)、指定通知的正文，下拉系统状态栏可看到； 4)、用于启动活动、启动服务以及发送广播
             */
            /**
             * 在APi6.0时方法2，和3步骤被弃用，用过Notification.Builder来创建
             */
//            notification.setLatestEventInfo(this, "股票大涨", "今日万科的股票停牌，应对恶意收购",
//                    pendingIntent);
            Builder builder = new Builder(this);
            builder.setSmallIcon(R.drawable.ic_launcher);
            builder.setContentTitle("股票大涨");
            builder.setContentText("今日万科的股票停牌，应对恶意收购");
            //启动预制意图
            builder.setContentIntent(pendingIntent);

            /**
             * 创建通知类Notification实例(用来存储通知所需的信息)； 一共三个参数：
             * 1)、指定通知使用的图标，如：R.drawable.ic_launcher ；
             * 2)、指定通知的ticker内容，通知被创建的时候，在状态栏一闪而过，属于瞬时提示信息。
             * 3)、指定通知被创建的时间，以毫秒为单位，下拉状态栏时，这个时间会显示在相应的通知上。
             */
            Notification notification1 = builder.getNotification();
            /**
             * 手机设置的默认提示音
             */
            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
            /**
             * sound属性是一个 Uri 对象。 可以在通知发出的时候播放一段音频，这样就能够更好地告知用户有通知到来.
             * 如：手机的/system/media/audio/ringtones 目录下有一个 Basic_tone.ogg音频文件，
             * 可以写成： Uri soundUri = Uri.fromFile(new
             * File("/system/media/audio/ringtones/Basic_tone.ogg"));
             * notification.sound = soundUri; 我这里为了省事，就去了手机默认设置的铃声
             */
            notification1.sound = uri;
            /**
             * vibrate属性是一个长整型的数组，用于设置手机静止和振动的时长，以毫秒为单位。
             * 参数中下标为0的值表示手机静止的时长，下标为1的值表示手机振动的时长， 下标为2的值又表示手机静止的时长，以此类推。
             */
            long[] vibrates = { 0, 1000, 1000, 1000 };
            notification1.vibrate = vibrates;
            /**
             * 手机处于锁屏状态时， LED灯就会不停地闪烁， 提醒用户去查看手机,下面是绿色的灯光一 闪一闪的效果
             */
            notification1.ledARGB = Color.GREEN;// 控制 LED 灯的颜色，一般有红绿蓝三种颜色可选
            notification1.ledOnMS = 1000;// 指定 LED 灯亮起的时长，以毫秒为单位
            notification1.ledOffMS = 1000;// 指定 LED 灯暗去的时长，也是以毫秒为单位
            notification1.flags = Notification.FLAG_SHOW_LIGHTS;// 指定通知的一些行为，其中就包括显示
            // LED 灯这一选项
            /**
             * 如果不想进行那么多繁杂的设置，也可以直接使用通知的默认效果，它会根据当前手机的环境来决定播放什么铃声，以及如何振动
             */
            // notification.defaults = Notification.DEFAULT_ALL;

            /**
             * 启动通知. 两个参数： 1)、id，保证每个通知的id唯一； 2)、Notification对象
             */
            notificationManager.notify(1, notification1);

        }
    }


}

