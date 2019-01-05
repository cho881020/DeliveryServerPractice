package tjeit.kr.deliveryserverpractice.utils;

//    서버와 통신하는 코드들을 모아두는 클래스

import android.content.Context;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ConnectServer {

//    서버통신은 대부분 하나의 서버에 요청을 날림.
//    서버의 주소url을 변수로 보관.
    private final static String serverURL = "http://13.124.249.254/";

//    서버 통신은 이 ConnectSErver 클래스가 전담.
//    응답이 돌아온 후의 데이터 반영 : Activity들이 처리
//    응답을 Activity단으로 넘겨주기 위한 조치.
    public interface JsonResponseHandler {
        void onResponse(JSONObject json);
    }

//    로그인 기능 처리 메쏘드
//        메소들의 재료
//        1)Context context : 어느 화면에서 쓰이는지.
//        2)user_id,password : 서버에서 요구하는 데이터들(파라미터들)
//        3)jsonRexpinseHadler handler : 응답을 받으면 화면에서 처리할 코드 덩어리.

    public static void postRequestLogin(Context context, String user_id, String password, final JsonResponseHandler handler){
//        우리가 만드는 앱을 클라이언트로 활용
        OkHttpClient client = new OkHttpClient();

//        post메쏘드는 formBody에 필요 데이터를 첨부.
        RequestBody requestBody = new FormBody.Builder().add("user_id",user_id).add("password",password).build();
        Request request = new Request.Builder().url(serverURL+"aush").post(requestBody).build();


        //        client를 이용해 실제로 서버에 접근
//        new Call 뒤로는 서버가 돌려주는 response에 대해ㅏㄴ 처리.

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("서버연결실패",e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(body);
                    if(handler!=null){
                        handler.onResponse(jsonObject);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }



}
