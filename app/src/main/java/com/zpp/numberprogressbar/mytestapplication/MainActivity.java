package com.zpp.numberprogressbar.mytestapplication;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ScreenActionReceiver screenActionReceiver;
    private Notification progressNotification;
    private RemoteViews contentView;
    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screenActionReceiver = new ScreenActionReceiver();
        screenActionReceiver.registerScreenActionReceiver(this);
        TestInfo();
    }

    public void TestInfo() {
        final int[] index = {0};// 计算循环输出的次数


        final Timer t = new Timer();//创建1个定时器
        t.schedule(new TimerTask() {

            @Override
            public void run() {
                index[0]++;
                show3();
                handler.sendEmptyMessage(index[0] * 10);
                if (index[0] >= 10) {//如果输出了10次,那么取消定时器
                    t.cancel();
                }
            }
        }, 0, 1000);// 首次运行,延迟0毫秒,循环间隔1000毫秒


    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            contentView.setTextViewText(R.id.notificationPercent, "正在下载" + msg.what + "%");
            contentView.setProgressBar(R.id.notificationProgress, 100, msg.what, false);
            progressNotification.contentView = contentView;
            notificationManager.notify(R.layout.notification_item, progressNotification);
        }
    };

    public void show3() {
        Notification.Builder builder = new Notification.Builder(getBaseContext());
        builder.setSmallIcon(R.mipmap.ic_launcher);
        progressNotification = builder.getNotification();
        progressNotification.flags = Notification.FLAG_ONGOING_EVENT;
        /*** 自定义  Notification 的显示****/
        contentView = new RemoteViews(getPackageName(), R.layout.notification_item);
        contentView.setProgressBar(R.id.notificationProgress, 100, 0, false);
        progressNotification.contentView = contentView;
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(R.layout.notification_item, progressNotification);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy");
        screenActionReceiver.unRegisterScreenActionReceiver(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("onStop");
    }

}
