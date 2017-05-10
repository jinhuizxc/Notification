package com.example.jh.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {


	//通知管理器
	NotificationManager notimanager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findViewById(R.id.button1).setOnClickListener(this);
		findViewById(R.id.button2).setOnClickListener(this);
		/**Type mismatch: cannot convert from Object to NotificationManager*/
		notimanager =  (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v.getId() == R.id.button1){

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
			builder.setContentTitle("通知栏通知");
			//设置通知内容
			builder.setContentText("我来自Notification");

			// Notification notification = builder.build(); //4.1以上
			//builder.getNotification();

			//预制意图----如果不设置，状态栏不可点击,不会跳转到主程序，(类似于广告通知栏)。
			PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,MainActivity.class), 0);
			//启动预制意图
			builder.setContentIntent(pendingIntent);


			//取出通知对象
			Notification notification = builder.getNotification();
			//发送id=1的通知,notify-----显示通知栏：notify(id, notification);
			notimanager.notify(1, notification);

		}else if(v.getId() == R.id.button2){
			//取消id=1的通知------取消通知栏：cancle(id);
			notimanager.cancel(1);
		}
	}


}

