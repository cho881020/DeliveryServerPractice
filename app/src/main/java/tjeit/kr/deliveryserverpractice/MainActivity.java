package tjeit.kr.deliveryserverpractice;

import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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

        Glide.with(mContext).load(mUser.getProfile_image()).into(profileImgView);

//        서버에서 공지사항 받아오기
//        지저분하니까 밑에다 만들어놓고 여기선 실행만 하기
        getNoticesFromServer();


    }

    void getNoticesFromServer() {

    }




    @Override
    public void bindViews() {
        this.welcomeMsgTxt = (TextView) findViewById(R.id.welcomeMsgTxt);
        this.profileImgView = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.profileImgView);

    }
}
