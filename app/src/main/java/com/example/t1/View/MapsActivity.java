package com.example.t1.View;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import com.example.t1.Model.Cattle;
import com.example.t1.Model.Coordinates;
import com.example.t1.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Handler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        myHandler = new Handler();
    }


    public void draw(View v) {

        Realm realm = Realm.getDefaultInstance();

        RealmResults<Cattle> cattles = realm.where(Cattle.class).findAll();

        for (Cattle cattle : cattles) {

            final RealmList<Coordinates> aux = cattle.getCoordinates();

            ArrayList<LatLng> cords = new ArrayList<LatLng>();

            for (Coordinates cor : aux) {
                cords.add(new LatLng(cor.getLat(), cor.getLng()));
            }

            mMap.addPolyline(new PolylineOptions()
                    .addAll(cords)
                    .width(5)
                    .color(R.color.colorPrimary));

        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Realm realm = Realm.getDefaultInstance();


        Cattle cattle1 = realm.where(Cattle.class).equalTo("id", 1).findFirst();
        Cattle cattle2 = realm.where(Cattle.class).equalTo("id", 2).findFirst();

        RealmList<Coordinates> coord1 = cattle1.getCoordinates();
        RealmList<Coordinates> coord2 = cattle2.getCoordinates();

        Coordinates first1 = coord1.first();
        Coordinates first2 = coord2.first();

        LatLng pos1 = new LatLng(first1.getLat(), first1.getLng());
        LatLng pos2 = new LatLng(first2.getLat(), first2.getLng());
        mMap.addMarker(new MarkerOptions().position(pos1));
        mMap.addMarker(new MarkerOptions().position(pos2));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pos1));

        //mMap.animateCamera( CameraUpdateFactory.zoomTo( 20.5f ) );

    }
}
