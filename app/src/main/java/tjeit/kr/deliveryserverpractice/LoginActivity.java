package tjeit.kr.deliveryserverpractice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import tjeit.kr.deliveryserverpractice.utils.ConnectServer;

public class LoginActivity extends BaseActivity {

    private android.widget.EditText userIdEdt;
    private android.widget.EditText passwordEdt;
    private android.widget.Button signUpBtn;
    private android.widget.Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SignUpActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                로그인 버튼이 눌리면?
//                서버에 아이디 비번을 들고 /auth로 접속해서 로그인 결과 물어보기.

                ConnectServer.postRequestLogin(mContext,
                        userIdEdt.getText().toString(),
                        passwordEdt.getText().toString(),
                        new ConnectServer.JsonResponseHandler() {
                            @Override
                            public void onResponse(JSONObject json) {
//                                서버에서 돌려주는 응답 처리.
                                Log.d("로그인서버응답", json.toString());
                            }
                        });

            }
        });
    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {

        this.loginBtn = (Button) findViewById(R.id.loginBtn);
        this.signUpBtn = (Button) findViewById(R.id.signUpBtn);
        this.passwordEdt = (EditText) findViewById(R.id.passwordEdt);
        this.userIdEdt = (EditText) findViewById(R.id.userIdEdt);
    }
}
