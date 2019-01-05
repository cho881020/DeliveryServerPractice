package tjeit.kr.deliveryserverpractice;

import android.os.Bundle;
import android.widget.TextView;

import tjeit.kr.deliveryserverpractice.datas.User;

public class MainActivity extends BaseActivity {

    User mUser;
    private de.hdodenhof.circleimageview.CircleImageView profileImgView;
    private android.widget.TextView welcomeMsgTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

//        serializable은 항상 캐스팅해줘야 함
        mUser = (User) getIntent().getSerializableExtra("로그인한사람");

        String welcomeMessage = String.format("%s님,\n오늘도 힘내주세요!", mUser.getName());

        welcomeMsgTxt.setText(welcomeMessage);


    }

    @Override
    public void bindViews() {
        this.welcomeMsgTxt = (TextView) findViewById(R.id.welcomeMsgTxt);
        this.profileImgView = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.profileImgView);

    }
}