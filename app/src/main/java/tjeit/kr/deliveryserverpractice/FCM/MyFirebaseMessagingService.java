package tjeit.kr.deliveryserverpractice.FCM;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.net.URL;

import tjeit.kr.deliveryserverpractice.NoticeListActivity;
import tjeit.kr.deliveryserverpractice.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d("발급된토큰",s);
    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d("알림Title수신",remoteMessage.getNotification().getTitle());
        Log.d("알림Body수신",remoteMessage.getNotification().getBody());

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//       알림 아이콘, 소리, 진동 등을 구성해주는 클래스
        NotificationCompat.Builder builder = null;
//        오레오 버젼부터 푸시알림 처리방식이 다름.
//        오레오 버전이상 or 이하냐에 따라 코드 처리가 달라져야함.

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("test","푸시테스트",NotificationManager.IMPORTANCE_DEFAULT);

            channel.setDescription("배송 앱 채널");

            channel.enableLights(true);

            channel.setLightColor(Color.GREEN);
            channel.enableVibration(true);

            Uri defaultUri  = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            channel.setSound(defaultUri,null);

            channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);


            notificationManager.createNotificationChannel(channel);



            builder = new NotificationCompat.Builder(this,channel.getId());

//            아이콘 설정
            builder.setSmallIcon(R.drawable.ic_launcher_background);


            builder.setContentTitle(remoteMessage.getNotification().getTitle());

            builder.setContentText(remoteMessage.getNotification().getBody());


            notificationManager.notify(0,builder.build());






        }else{
            builder = new NotificationCompat.Builder(this);

//            아이콘 설정
            builder.setSmallIcon(R.drawable.ic_launcher_background);


            builder.setContentTitle(remoteMessage.getNotification().getTitle());

            builder.setContentText(remoteMessage.getNotification().getBody());


            notificationManager.notify(0,builder.build());

        }

    }
}
