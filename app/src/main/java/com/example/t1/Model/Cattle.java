package com.example.t1.Model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Cattle extends RealmObject {
    @PrimaryKey
    private long id;
    private RealmList<Double> Coordinates;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RealmList<Double> getCoordinates() {
        return Coordinates;
    }

    public void setCoordinates(RealmList<Double> points) {
        this.Coordinates = points;
    }

}
