package tjeit.kr.deliveryserverpractice;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

public class EditProfileActivity extends BaseActivity {

    private android.widget.Spinner bankSpinner;
    private android.widget.EditText accountNumberEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {
//        서버에서 은행 목록을 받아와서 Spinner에 뿌려주기.

    }

    @Override
    public void bindViews() {

        this.accountNumberEdt = (EditText) findViewById(R.id.accountNumberEdt);
        this.bankSpinner = (Spinner) findViewById(R.id.bankSpinner);

    }
}
