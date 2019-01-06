package tjeit.kr.deliveryserverpractice.datas;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Apartment implements Serializable {


    private int id;
    private String name;
    private double latitude;
    private  double longitude;

//    JSON -> 객체로 변환하는 Static 메쏘드

    public  static  Apartment getApartmentFromJson(JSONObject apartJson){

        Apartment apartment = new Apartment();

        try {
            apartment.setId(apartJson.getInt("id"));
            apartment.setName(apartJson.getString("name"));
            apartment.setLatitude(apartJson.getDouble("latitude"));
            apartment.setLongitude(apartJson.getDouble("longitude"));

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return apartment;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
