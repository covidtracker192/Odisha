package com.covid19.odisha;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class hospitals extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LatLng latLng;
    private FusedLocationProviderClient fusedLocationClient;
    LocationRequest mLocationRequest;
    Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(180000);
        mLocationRequest.setFastestInterval(90000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        fusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
        mMap.setMyLocationEnabled(true);

        // Test Center 1
        final LatLng center1 = new LatLng(15.462846, 73.857513);
        mMap.addMarker(new MarkerOptions().position(center1).title("Goa Medical College & Hospital").snippet("website: http://www.gmc.goa.gov.in/index.php/en/"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center1));

        //Test Center 2
        final LatLng center2 = new LatLng(15.596924, 73.820260);
        mMap.addMarker(new MarkerOptions().position(center2).title("District Hospital, Mapusa").snippet("website: http://www.dhsgoa.gov.in/tel-nos.htm"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center2));

        //Test Center 3
        final LatLng center3 = new LatLng(15.605917, 73.813276);
        mMap.addMarker(new MarkerOptions().position(center3).title("Dr. Kolwalkar's Galaxy Hospital").snippet("website: http://galaxyhospitalgoa.com/"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center3));

        //Test Center 4
        final LatLng center4 = new LatLng(15.268915, 73.965600);
        mMap.addMarker(new MarkerOptions().position(center4).title("Victor Hospital").snippet("website: http://victorhospital.com/"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center4));

        //Test Center 5
        final LatLng center5 = new LatLng(15.460034, 73.813516);
        mMap.addMarker(new MarkerOptions().position(center5).title("Manipal Hospitals Goa").snippet("website: https://www.manipalhospitals.com/goa/"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center5));

        //Test Center 6
        final LatLng center6 = new LatLng(15.604626, 73.820396);
        mMap.addMarker(new MarkerOptions().position(center6).title("Vision Hospital in Mapusa Goa").snippet("website: https://visionhospitalgoa.com/"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center6));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(6));

        //Test Center 7
        final LatLng center7 = new LatLng(15.597050, 73.820198);
        mMap.addMarker(new MarkerOptions().position(center7).title("Asilo Hospital"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center7));

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                if(marker.getPosition().equals(center1)) {
                    Uri uriUrl = Uri.parse("http://www.gmc.goa.gov.in/index.php/en/");
                    Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    startActivity(launchBrowser);
                }
                else if(marker.getPosition().equals(center2)) {
                    Uri uriUrl = Uri.parse("http://www.dhsgoa.gov.in/tel-nos.htm");
                    Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    startActivity(launchBrowser);
                }

                else if(marker.getPosition().equals(center3)) {
                    Uri uriUrl = Uri.parse("http://galaxyhospitalgoa.com/");
                    Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    startActivity(launchBrowser);
                }
                else if(marker.getPosition().equals(center4)) {
                    Uri uriUrl = Uri.parse("http://victorhospital.com/");
                    Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    startActivity(launchBrowser);
                }
                else if(marker.getPosition().equals(center5)) {
                    Uri uriUrl = Uri.parse("https://www.manipalhospitals.com/goa/");
                    Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    startActivity(launchBrowser);
                }
                else if(marker.getPosition().equals(center6)) {
                    Uri uriUrl = Uri.parse("https://visionhospitalgoa.com/");
                    Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                    startActivity(launchBrowser);
                }
            }
        });

    }

    LocationCallback mLocationCallback=new LocationCallback(){
        @Override
        public void onLocationResult(LocationResult locationResult) {
            for(Location location1 : locationResult.getLocations()) {
                if (getApplicationContext() != null) {
                    mLastLocation = location1;
                    latLng = new LatLng(location1.getLatitude(), location1.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(7));
                }
            }

        }
    };
    public void onBackPressed(){
        Intent intent = new Intent(hospitals.this, home.class);
        startActivity(intent);
        finish();
    };
}
