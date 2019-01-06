package tjeit.kr.deliveryserverpractice.datas;

import org.json.JSONObject;

import java.io.Serializable;

public class Apartment implements Serializable {
    private int id;
    private String name;
    private double latitude;
    private double longitude;

//    JSON -> 객체로 변환하는 static 메소드
    public static Apartment getApartmentFromJson(JSONObject apartJson){
        Apartment apartment = new Apartment();

        apartment.setId(apartJson.getInt("id"));
        apartment.setName(apartJson.getString("name"));
        apartment.setLatitude(apartJson.getDouble("latitude"));

        return apartment;
    }


}
