package tjeit.kr.deliveryserverpractice;

import android.app.FragmentManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ApartListActivity extends BaseActivity {



    MapFragment mapFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apart_list);


        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                LatLng seoul = new LatLng(37.56,126.97);

//                지도의 위치를 지정하고 마커를 추가
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(seoul);
                markerOptions.title("서울");
                markerOptions.snippet("한국의 수도입니다");
                googleMap.addMarker(markerOptions);

//                지도의 가운데점을 서울로 미리 세팅
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(seoul));

//               지도의 줌 레벨을 세팅

                googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));



            }
        });

    }

    @Override
    public void bindViews() {

        FragmentManager fragmentManager = getFragmentManager();
        mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.map);

    }
}
