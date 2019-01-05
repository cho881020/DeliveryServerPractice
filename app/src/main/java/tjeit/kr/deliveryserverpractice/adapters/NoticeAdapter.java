package tjeit.kr.deliveryserverpractice.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

import tjeit.kr.deliveryserverpractice.R;
import tjeit.kr.deliveryserverpractice.datas.Announcement;

public class NoticeAdapter extends ArrayAdapter<Announcement> {

    public NoticeAdapter(Context context, List<Announcement> list) {
        super(context, R.layout.notice_list_item, list);
    }

}