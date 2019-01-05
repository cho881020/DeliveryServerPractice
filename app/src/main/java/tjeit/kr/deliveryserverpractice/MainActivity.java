package tjeit.kr.deliveryserverpractice;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import tjeit.kr.deliveryserverpractice.datas.Announcement;
import tjeit.kr.deliveryserverpractice.datas.User;
import tjeit.kr.deliveryserverpractice.utils.ConnectServer;

public class MainActivity extends BaseActivity {

    User mUser;
    List<Announcement> announcementList = new ArrayList<Announcement>();

    private de.hdodenhof.circleimageview.CircleImageView profileImageView;
    private android.widget.TextView welcomeMsgTxt;
    private TextView announcementTxt;

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

//        서버에서 공지사항 받아오기
        getNoticesFromServer();
    }

    void getNoticesFromServer(){
        ConnectServer.getRequestNotice(mContext, new ConnectServer.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                Log.d("공지응답", json.toString());

                try {
                    int code = json.getInt("code");
                    if (code == 200) {
                        JSONObject data = json.getJSONObject("data");
                        JSONArray announcements = data.getJSONArray("announcements");

                        for (int i = 0; i < announcements.length(); i++) {
                            JSONObject jsonObject = announcements.getJSONObject(i);

                            Announcement an = Announcement.getAnnouncementFromJson(jsonObject);
                            announcementList.add(an);
                        }
                        Announcement firstAn = announcementList.get(0);
                        announcementTxt.setText(firstAn.getTitle());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void bindViews() {
        this.welcomeMsgTxt = (TextView) findViewById(R.id.welcomeMsgTxt);
        this.profileImageView = (CircleImageView) findViewById(R.id.profileImageView);
        this.announcementTxt = (TextView) findViewById(R.id.announcementTxt);

    }
}
