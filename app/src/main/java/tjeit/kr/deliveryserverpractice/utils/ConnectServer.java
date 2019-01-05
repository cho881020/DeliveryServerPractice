package tjeit.kr.deliveryserverpractice.utils;


import android.content.Context;

import org.json.JSONObject;

// 서버와 통신하는 코드들을 모아두는 클래스
public class ConnectServer  {

//    서버통신은 대부분 하나의 서버에 요청을 날림.
//    서버의 주소 url 을 변수로 보관.
    private  final static String serverURL = "http://13.124.249.254/";


//    서버통신은 이 ConnectServer 클래스가 전담.
//    응답이 돌아온 후의 데이터 반영: Activity들이 처리.
//    응답을 Activity 단으로 덤겨주기 위한 조치.
    public interface  JsonResponseHandler {

        void onResponse(JSONObject json);
    }

//    로그인 기능 처리 메쏘드

//    메쏘드의 재료
//    1) Context context : 어느 화면에서 쓰이는지
//    2) user_id, password : 서버에서 요구하는 데이터들(파라미터들)
//    3) JsonResponseHandler handler : 응답을 받으면 화면에서 처리할 코드 덩어리.

    public  static  void postRequestLogin(Context context, String user_id,
                                          String password, final JsonResponseHandler handler){





    }



}




