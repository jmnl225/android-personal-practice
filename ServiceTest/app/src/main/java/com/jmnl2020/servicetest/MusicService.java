package com.jmnl2020.servicetest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;


public class MusicService extends Service {

    MediaPlayer mp;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            NotificationChannel channel = new NotificationChannel("ch1", "뮤직서비스", NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(channel);

            NotificationCompat.Builder builder= new NotificationCompat.Builder(this, "ch1");

            //알림설정들
            builder.setSmallIcon(R.drawable.ic_stat_name);
            builder.setContentTitle("Music Service");
            builder.setContentText("뮤직서비스가 실행중입니다.");

            //알림창을 클릭했을 때 뮤직제어화면으로 전환되도록
            Intent i = new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, 10, i, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);

            builder.setAutoCancel(true);


            Notification notification = builder.build();

            startForeground(5, notification);

        }



        if(mp == null){
            mp = MediaPlayer.create(this, R.raw.kalimba);
            mp.setLooping(true);
            mp.setVolume(0.7f,0.7f);
        }

        mp.start();

        //메모리 문제로 서비스를 강제로 kill시켰을 때! 메모리문제가 해결됐다면 다시 실행해달라!

        return START_STICKY;
    }

    //stopService()를 통해 서비스가 종료되면 자동으로 실행되는 메소드
    @Override
    public void onDestroy() {

        if(mp!=null && mp.isPlaying()){
            mp.stop();
            mp.release();;
            mp= null;
        }

        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
