package com.msp.mspshoubraapp;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;
    ArrayList<LatLng> locList = new ArrayList<LatLng>();
    LatLng currentLocaion;

    // The entry points to the Places API.
    private GeoDataClient mGeoDataClient;
    private PlaceDetectionClient mPlaceDetectionClient;

    // The entry point to the Fused Location Provider.
    private FusedLocationProviderClient mFusedLocationProviderClient;

    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted;
    private static final int DEFAULT_ZOOM = 15;


    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private Location mLastKnownLocation;

    private final LatLng mDefaultLocation = new LatLng(30.0996, 31.2486);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Construct a GeoDataClient.
        mGeoDataClient = Places.getGeoDataClient(this, null);

        // Construct a PlaceDetectionClient.
        mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);

        // Construct a FusedLocationProviderClient.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    //GPSTracker gps = new GPSTracker(this);

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

        return "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters /*+ "&key=AIzaSyB6p2mWkU0gvPM5Q20iH5q6CtSSmr6MITw"*/;
    }
    LatLng SFE = new LatLng(30.0996, 31.2486);
    //LatLng cL = new LatLng(gps.getLatitude(), gps.getLongitude());

    //String url = getDirectionsUrl(cL, SFE);

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        locList.add(SFE);
//        locList.add(cL);
        // Add a marker in SFE and move the camera
//        30.0884332,31.2430711,15
        LatLng SFE = new LatLng(30.0996, 31.2486);
        // Prompt the user for permission.
        getLocationPermission();

        getDeviceLocation();
//        mMap.addMarker(new MarkerOptions().position(SFE).title("Faculty of Engineering at Shoubra"));
       /* JsonObjectRequest roadPointsJsonObject = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                //Log.d("QWERTY", response.toString());
                ArrayList<LatLng> locList = buildData(response);

                Log.d("QWERTY", locList.size()+"");
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

        VolleySingleton.getInstance(this).addToRequestQueue(roadPointsJsonObject);*/
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SFE,17.0F));
    }

    private static ArrayList<LatLng> buildData(JSONObject jsonObject) {
        ArrayList<LatLng> latLngs = new ArrayList<>();

        try {
            String status = jsonObject.getString("status");
            Log.d("QWERTY", status + "");
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


    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (mLocationPermissionGranted) {
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {

                            Log.d("QWERTYU", task.getResult().getLatitude() + "  " + task.getResult().getLongitude());
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = task.getResult();
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(mLastKnownLocation.getLatitude(),
                                            mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                        } else {
                            Log.d("QWERTYU", "Current location is null. Using defaults.");
                            Log.e("QWERTYU", "Exception: %s", task.getException());
                            mMap.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }


    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        //updateLocationUI();
    }
}