package com.example.t1.Model;

import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;

public class Coordinates extends RealmObject {
    private double lat;
    private double lng;


    @LinkingObjects("Coordinates")
    private final RealmResults<Cattle> contents = null;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
