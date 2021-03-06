package cn.rygel.gd.receiver;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import cn.rygel.gd.bean.OnDateChangedEvent;
import cn.rygel.gd.service.LocalService;
import cn.rygel.gd.service.RemoteService;
import cn.rygel.gd.setting.Settings;

public class DateChangeBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(Settings.getInstance().isKeepAlive()){
            // 如果服务已经停止，则重启服务
            if (!isServiceRunning(context, "cn.rygel.gd.service.LocalService") ||
                    !isServiceRunning(context, "cn.rygel.gd.service.RemoteService")) {
                context.startService(new Intent(context, LocalService.class));
                context.startService(new Intent(context, RemoteService.class));
            }
        }
        String action = intent.getAction();
        if(Intent.ACTION_TIME_CHANGED.equals(action)
                || Intent.ACTION_TIMEZONE_CHANGED.equals(action)) {
            EventBus.getDefault().post(new OnDateChangedEvent());
        }
    }

    // 判断服务是否运行
    public boolean isServiceRunning(Context context, String serviceName) {
        boolean isRunning = false;
        ActivityManager am = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> lists = am.getRunningAppProcesses();


        for (ActivityManager.RunningAppProcessInfo info : lists) {// 获取运行服务再启动
            System.out.println(info.processName);
            if (info.processName.equals(serviceName)) {
                isRunning = true;
            }
        }
        return isRunning;

    }


}
