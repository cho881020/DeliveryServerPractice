package tjeit.kr.deliveryserverpractice.fcm;

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

import tjeit.kr.deliveryserverpractice.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String s){
        super.onNewToken(s);

        Log.d("발급된토큰", s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d("알림수신", remoteMessage.getNotification().getTitle());
        Log.d("알림수신", remoteMessage.getNotification().getBody());

//        핸드폰 상태바에 뜨는 알림을 관리하는 안드로이드 서비스.
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

//        알림 아이콘, 소리, 진동 등을 구성해주는 클래스
        NotificationCompat.Builder builder = null;

//        오레오 버전부터는 푸시알림 처리방식이 다름.
//        오레오버전 이상 or 이하냐에 따라 코드 처리가 달라져야함.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            오레오 이상에 대한 코드
//            알림 채널 설정 필요.

            NotificationChannel channel =
                    new NotificationChannel("test","푸시테스트", NotificationManager.IMPORTANCE_DEFAULT);

//            채널에대한 설명 설정
            channel.setDescription("배송 앱 채널");

//            알림이 올때 조명 처리를 할것인지?
            channel.enableLights(true);

//            조명을 쓴다면 어떤 색으로 사용할 것인지?
            channel.setLightColor(Color.GREEN);

//            진동을 허용할 것인지?
            channel.enableVibration(true);
//            진동이 올린다면 어떻게 올릴지? 횟수 / 시간
            channel.setVibrationPattern(new long[]{1, 1000});

//            알림 소리를 설정. => 기본 알림 소리.
            Uri defaultUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//            찾아낸 소리를 실제로 세팅
            channel.setSound(defaultUri, null);

//            잠금 상태일때 알림을 어떻게 보여줄건지.
            channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

//            설정한 채널을 실제로 여는 코드
            notificationManager.createNotificationChannel(channel);

//            열린 채널에 실제로 알림을 전송
            builder = new NotificationCompat.Builder(this, channel.getId());

//            푸시알림 아이콘 설정
            builder.setSmallIcon(R.drawable.ic_launcher_background);

            builder.setContentTitle(remoteMessage.getNotification().getTitle());
            builder.setContentText(remoteMessage.getNotification().getBody());

            notificationManager.notify(0, builder.build());
        }
        else{
//            오레오 이전 안드로이대한 코드
//            채널 기능이 존재하지 않음. => 빌더만 만들어서 매니저를 통해 띄우면 됨.

//           실제로 알림을 전송
            builder = new NotificationCompat.Builder(this);

//            푸시알림 아이콘 설정
            builder.setSmallIcon(R.drawable.ic_launcher_background);

            builder.setContentTitle(remoteMessage.getNotification().getTitle());
            builder.setContentText(remoteMessage.getNotification().getBody());

            notificationManager.notify(0, builder.build());
        }
    }
}
