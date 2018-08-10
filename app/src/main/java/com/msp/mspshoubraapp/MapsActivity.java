package com.msp.mspshoubraapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.location.LocationManager.NETWORK_PROVIDER;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int MIN_TIME_BW_UPDATES = 10;
    private GoogleMap mMap;
    LocationManager locationManager;
    ArrayList<LatLng> locList = new ArrayList<LatLng>();
    LatLng currentLocaion;
    private boolean canGetLocation;
    private float MIN_DISTANCE_CHANGE_FOR_UPDATES = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private LatLng getCurrLocation() {
        double latitude, longitude;
        LatLng ret = new LatLng(30.0819, 31.2446);
        try {
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            // getting GPS status
            boolean isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            boolean isNetworkEnabled = locationManager
                    .isProviderEnabled(NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {

                Toast.makeText(this, "Cannot find location no provider", Toast.LENGTH_SHORT).show();
            } else {
                this.canGetLocation = true;
                Location location;

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
//                    return TODO;
                }
                locationManager.requestLocationUpdates(
                        NETWORK_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, (LocationListener) this);
                    Log.d("Network", "Network");
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(NETWORK_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
//                            Toast.makeText(this, "+"+latitude, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    Location location=null;
                    if (location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, (LocationListener) this);
                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                                ret=new LatLng(latitude,longitude);
//                                Toast.makeText(this, "+"+latitude, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    private String getDirectionsUrl(LatLng origin,LatLng dest){

        // Origin of route
        String str_origin = "origin="+origin.latitude+","+origin.longitude;

        // Destination of route
        String str_dest = "destination="+dest.latitude+","+dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin+"&"+str_dest+"&"+sensor;

        // Output format
        String output = "json";

        return "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=AIzaSyB6p2mWkU0gvPM5Q20iH5q6CtSSmr6MITw";
    }
    LatLng SFE = new LatLng(30.0996, 31.2486);
    LatLng cL = getCurrLocation();

    String url = getDirectionsUrl(cL, SFE);

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        locList.add(SFE);
//        locList.add(cL);
        // Add a marker in SFE and move the camera
//        30.0884332,31.2430711,15
        LatLng SFE = new LatLng(30.0996, 31.2486);
//        mMap.addMarker(new MarkerOptions().position(SFE).title("Faculty of Engineering at Shoubra"));
        JsonObjectRequest roadPointsJsonObject = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Log.d("QWERTY", response.toString());
                ArrayList<LatLng> locList = buildData(response);


                mMap.addPolyline((new PolylineOptions()).addAll(locList)
                        .width(5)
                        .color(Color.RED));
                //                .geodesic(false));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });

        VolleySingleton.getInstance(this).addToRequestQueue(roadPointsJsonObject);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SFE,17.0F));
    }

    private static ArrayList<LatLng> buildData(JSONObject jsonObject) {
        ArrayList<LatLng> latLngs = new ArrayList<>();

        try {
            String status = jsonObject.getString("status");
            if (!status.equals("OK")) {
                return null;
            }

            JSONArray routes = jsonObject.getJSONArray("routes");
            JSONObject object = routes.getJSONObject(0);
            JSONArray legs = object.getJSONArray("legs");
            JSONObject obj = legs.getJSONObject(0);
            JSONArray steps = obj.getJSONArray("steps");

            for (int i = 0; i < steps.length(); ++i) {
                JSONObject jsonObj = steps.getJSONObject(i);

                JSONObject end_location = jsonObj.getJSONObject("end_location");

                double lat = end_location.getDouble("lat");
                double lng = end_location.getDouble("lng");
                latLngs.add(new LatLng(lat, lng));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return latLngs;
    }

}