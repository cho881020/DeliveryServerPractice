package tjeit.kr.deliveryserverpractice;

import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import tjeit.kr.deliveryserverpractice.datas.User;

public class MainActivity extends BaseActivity {

    User mUser;
    private de.hdodenhof.circleimageview.CircleImageView profileImageView;
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
        mUser = (User) getIntent().getSerializableExtra("로그인한사람");
        String welcomMessage = String.format("%s님,\n오늘도 힘내주세요!",mUser.getName());
        welcomeMsgTxt.setText(welcomMessage);

        Glide.with(mContext).load(mUser.getProfile_image()).into(profileImageView);
    }

    @Override
    public void bindViews() {
        this.welcomeMsgTxt = (TextView) findViewById(R.id.welcomeMsgTxt);
        this.profileImageView = (CircleImageView) findViewById(R.id.profileImageView);

    }
}
