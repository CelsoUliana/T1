package com.example.t1.Model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.LinkingObjects;
import io.realm.annotations.PrimaryKey;

public class Cattle extends RealmObject {
    @PrimaryKey
    private long id;
    private RealmList<Coordinates> Coordinates;

    @LinkingObjects("Cattle")
    private final RealmResults<Root> roots = null;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RealmList<Coordinates> getCoordinates() {
        return Coordinates;
    }

    public void setCoordinates(RealmList<Coordinates> points) {
        this.Coordinates = points;
    }

}
