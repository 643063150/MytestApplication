package com.zpp.numberprogressbar.mytestapplication;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

/**
 * 创建日期：2018/5/4
 *
 * @author zpp
 * @version 1.0
 * 文件名称： $file_name$
 * 类说明：
 */
public class ScreenActionReceiver extends BroadcastReceiver {

    private String TAG = "TagForScreenActionReceiver";
    private boolean isRegisterReceiver = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_SCREEN_ON)) {
            Log.i(TAG, "屏幕解锁广播(即亮屏了)...");
        } else if (action.equals(Intent.ACTION_SCREEN_OFF)) {
            Log.i(TAG, "屏幕加锁广播(即灭屏了)...");
//            ((Activity) context).finish();
        }
    }
    /**
     * 广播注册
     *
     * @param mContext 上下文对象
     */
    public void registerScreenActionReceiver(Context mContext) {
        if (!isRegisterReceiver) {
            isRegisterReceiver = true;
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_SCREEN_OFF);
            filter.addAction(Intent.ACTION_SCREEN_ON);
            Log.i(TAG, "注册屏幕解锁、加锁广播接收者...");
            mContext.registerReceiver(ScreenActionReceiver.this, filter);
        }
    }
    /**
     * 广播注销
     *
     * @param mContext 上下文对象
     */
    public void unRegisterScreenActionReceiver(Context mContext) {
        if (isRegisterReceiver) {
            isRegisterReceiver = false;
            Log.i(TAG, "注销屏幕解锁、加锁广播接收者...");
            mContext.unregisterReceiver(ScreenActionReceiver.this);
        }
    }
}
