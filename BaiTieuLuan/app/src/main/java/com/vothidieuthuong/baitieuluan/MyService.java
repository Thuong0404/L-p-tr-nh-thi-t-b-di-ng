package com.vothidieuthuong.baitieuluan;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.v4.media.session.MediaSessionCompat;
import android.util.Log;
import android.widget.ListView;
import android.widget.RemoteViews;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationCompat.Builder;

import static com.vothidieuthuong.baitieuluan.MyApplication.CHANEL_ID;

public class MyService extends Service {
    @Override
    public void onCreate() {

        super.onCreate();

        Log.e("Tincoder","My Service");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Tincoder","My Service");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //String anh=intent.getStringExtra("Anh");
        String strDataIntent = intent.getStringExtra("Ten_ba_hat");
        String strDataIntentCasi = intent.getStringExtra("Ca_si");
        setNotification(strDataIntent, strDataIntentCasi);
        return START_NOT_STICKY;
    }

    private void setNotification( String strDataIntent, String strDataIntentCasi) {
        Intent intent=new Intent(this, ActivityBaihat.class);
        PendingIntent pendingIntent= PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.song_custom);
        MediaSessionCompat mediaSessionCompat= new MediaSessionCompat(this,"tag");
        Bitmap  bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.banner);
        remoteViews.setImageViewResource(R.id.imgsong,R.drawable.dia);
        remoteViews.setTextViewText(R.id.titlesong, strDataIntentCasi);
        remoteViews.setTextViewText(R.id.singglesong, strDataIntent);
        remoteViews.setImageViewResource(R.id.pause,R.drawable.pause);
        remoteViews.setImageViewResource(R.id.btnnext, R.drawable.next);
        remoteViews.setImageViewResource(R.id.back, R.drawable.rewind);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANEL_ID);
        builder.setContentText(strDataIntent);
        builder.setContentTitle(strDataIntentCasi);
     //  builder.setSubText("B??i ti???u lu???n");
         builder.setLargeIcon(bitmap);
        builder.setSmallIcon(R.drawable.ic_baseline_music_note_24);
//        builder.addAction(R.id.back, "Rewind", null);
//        builder.addAction(R.id.pause, "Pause", null);
//        builder.addAction(R.id.btnnext, "Next", null);
//
//        builder.setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
//                .setShowActionsInCompactView(0,1,2)
//                .setMediaSession(mediaSessionCompat.getSessionToken())
//        );
        builder.setContentIntent(pendingIntent);
        builder.setCustomContentView(remoteViews);
        Notification notification= builder
                .build();
        startForeground(1,notification);
    }

//    private void setNotificationMedia( String strDataIntent, String strDataIntentCasi) {
//       Intent intent=new Intent(this, ActivityBaihat.class);
//        PendingIntent pendingIntent= PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
//        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.song_custom);
//        MediaSessionCompat mediaSessionCompat= new MediaSessionCompat(this,"tag");
//
//
//        NotificationCompat.Builder notification= new NotificationCompat.Builder(this, CHANEL_ID);
//
//        notification.setContentTitle(strDataIntentCasi)
//                    .setSubText("B??i ti???u lu???n") // ti??u ????? khi thu g???n
//
//                   .setSmallIcon(R.drawable.ic_baseline_music_note_24)
//                     .addAction(R.drawable.rewind, "Rewind", null)//1
//                      .addAction(R.drawable.pause, "Pause", null)//1
//                .addAction(R.drawable.next, "Next", null)//2
//
//                   .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
//                .setShowActionsInCompactView(0,1,2) // hi???n c??? 3 n??t tr??n tahnh th??ng b??o khi thu g???n
//                .setMediaSession(mediaSessionCompat.getSessionToken())
//        );
//         notification.setContentIntent(pendingIntent);
//        notification.setCustomContentView(remoteViews);
//            Notification notifica= notification
//                .build();
//        startForeground(1,notifica);
//    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
}
