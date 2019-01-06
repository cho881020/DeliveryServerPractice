package tjeit.kr.deliveryserverpractice.fcm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.media.audiofx.DynamicsProcessing;
import android.net.Uri;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.nio.channels.Channel;

import tjeit.kr.deliveryserverpractice.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);

        Log.d("발급된토큰",s);

    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d("알림수신",remoteMessage.getNotification().getTitle());
        Log.d("알림수신",remoteMessage.getNotification().getBody());


//        핸드폰 상태바에 뜨는 알림을 관리하는 안드로이드 서비스
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

//        알림 아이콘, 소리, 진동 등을 구성해주는 클래스
        NotificationCompat.Builder builder = null;

//        오레오 버전부터는  푸시알림 처리 방식이 다름.
//        오레오 버전 이상 or 이하 냐에 따라 코드 처리가 달라져야함.

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            오레오 이상에 대한 코드
//            알림 채널을 설정 기능 필요.

            NotificationChannel channelMessage = new NotificationChannel("test","푸시테스트",NotificationManager.IMPORTANCE_DEFAULT);


//            앱 설명
            channelMessage.setDescription("배송 앱 채널");

//            색상
            channelMessage.enableLights(true);
            channelMessage.setLightColor(Color.GREEN);
            channelMessage.enableVibration(true);
            channelMessage.setVibrationPattern(new long[]{}1,1000);

            Uri defaultUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);



                    channelMessage.setSound(defaultUri.null);

                    channelMessage.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);


            notificationManager.createNotificationChannel(channelMessage);

//            열린채널에 실제로 알림을 전송

            builder = new NotificationCompat.Builder(this,channelMessage.getId());

//            푸시알림 아이콘 설정

            builder.setSmallIcon(R.drawable.ic_launcher_background);

            builder.setContentTitle(remoteMessage.getNotification().getTitle());
            builder.setContentText(remoteMessage.getNotification().getBody());

            notificationManager.notify(0, builder.build());


        }else{
//            오레오 이전에 대한 코드




        }




    }
}
