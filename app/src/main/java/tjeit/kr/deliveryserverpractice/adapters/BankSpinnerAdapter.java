package tjeit.kr.deliveryserverpractice.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import tjeit.kr.deliveryserverpractice.R;
import tjeit.kr.deliveryserverpractice.datas.Bank;

public class BankSpinnerAdapter extends ArrayAdapter<Bank> {

    Context mContext;
    List<Bank> mList;
    LayoutInflater inf;

//    생성자
    public BankSpinnerAdapter(Context context, List<Bank> list) {

        super(context, R.layout.bank_spinner_list_item, list);

        this.mContext = context;
        this.mList = list;
        this.inf = LayoutInflater.from(mContext);


    }

//    getDropDownView: 스피너에만 있는 부분
//    리스트가 나올 때 모양
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            row = inf.inflate(R.layout.bank_spinner_list_item, null);
        }

        ImageView logoImg = row.findViewById(R.id.logoImg);
        TextView bankNameTxt = row.findViewById(R.id.bankNameTxt);

        Bank data = mList.get(position);

        Glide.with(mContext).load(data.getLogo()).into(logoImg);
        bankNameTxt.setText(data.getName());

        return row;
    }

//    선택이 됐을 때 모양
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            row = inf.inflate(R.layout.bank_spinner_selected_item, null);
        }

        ImageView logoImg = row.findViewById(R.id.logoImg);
        TextView bankNameTxt = row.findViewById(R.id.bankNameTxt);

        Bank data = mList.get(position);

        Glide.with(mContext).load(data.getLogo()).into(logoImg);
        bankNameTxt.setText(data.getName());

        return row;
    }
}
