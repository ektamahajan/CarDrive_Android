package com.example.ekta.apfinalproject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarMap {

    List<Map<String, ?>> carList;

    public HashMap getItem(int i) {
        if (i >= 0 && i < carList.size()) {
            return (HashMap) carList.get(i);
        } else return null;
    }

    public List<Map<String, ?>> getCarList() {
        return carList;
    }

    public int getSize() {
        return carList.size();
    }

    public static HashMap createCar(String name, String description, String id, String brand,
                                    String email, String url, String model, String price,
                                    String number, String address, String milesDriven, String color, String city, String state) {
        HashMap car = new HashMap();
        car.put("name", name);
        car.put("description", description);
        car.put("id", id);
        car.put("brand", brand);
        car.put("email", email);
        car.put("model", model);
        car.put("price", price);
        car.put("number", number);
        car.put("address", address);
        car.put("milesDriven", milesDriven);
        car.put("url", url);
        car.put("color", color);
        car.put("city", city);
        car.put("state", state);
        return car;
    }
}
