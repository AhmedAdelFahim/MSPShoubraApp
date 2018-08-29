package com.msp.mspshoubraapp.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.networking.ConnectivityStatus;
import com.msp.mspshoubraapp.networking.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by Laila Al Ashkar on 8/14/2018.
 */

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private MapView mapView;


    private GoogleMap mMap;
    ArrayList<LatLng> locList = new ArrayList<LatLng>();
    String url;
    // The entry points to the Places API.
    private GeoDataClient mGeoDataClient;
    private PlaceDetectionClient mPlaceDetectionClient;

    // The entry point to the Fused Location Provider.
    private FusedLocationProviderClient mFusedLocationProviderClient;

    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static final int ACCESS_COARSE_LOCATION =2;
    private boolean mLocationPermissionGranted=false;
    private boolean mLocationPermissionGranted2=false;
    private static final int DEFAULT_ZOOM = 15;


    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private Location mLastKnownLocation;

    private final LatLng mDefaultLocation = new LatLng(30.0996, 31.2486);
    private LatLng cL;
    private LatLng dist;
    private LocationManager manager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        manager = (LocationManager) getActivity().getSystemService( Context.LOCATION_SERVICE );

        View view = inflater.inflate(R.layout.fragment_map, null);

        mapView = view.findViewById(R.id.map_view);

        mapView.onCreate(savedInstanceState);

        mapView.onResume(); // needed to get the map to display immediately
        // Construct a GeoDataClient.
        mGeoDataClient = Places.getGeoDataClient(getActivity(), null);

        // Construct a PlaceDetectionClient.
        mPlaceDetectionClient = Places.getPlaceDetectionClient(getActivity(), null);

        // Construct a FusedLocationProviderClient.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        dist = new LatLng(30.0996, 31.2486);
        mapView.getMapAsync(this);

        return view;
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        locList.add(dist);
        getGPSpermisssion();
        getLocationPermission();
        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertNoGPS();
        }
        if(!ConnectivityStatus.isConnected(getActivity())){
            buildAlertNoInternet();
        }
        getDeviceLocation();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dist, 17.0F));
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
            Log.d("QWERTYU",mLocationPermissionGranted+" "+mLocationPermissionGranted2);
            if (mLocationPermissionGranted&&mLocationPermissionGranted2) {
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(getActivity(), new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            Log.d("QWERTYU", task.getResult().getLatitude() + "  " + task.getResult().getLongitude());
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = task.getResult();
                            cL = new LatLng(mLastKnownLocation.getLatitude(),
                                    mLastKnownLocation.getLongitude());
                            url = getDirectionsUrl(cL, dist);

                            JsonObjectRequest roadPointsJsonObject = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    ArrayList<LatLng> locList = buildData(response);

                                    Log.d("QWERTY", locList.size() + "");
                                    locList.add(cL);
                                    mMap.addPolyline((new PolylineOptions()).addAll(locList)
                                            .width(2)
                                            .color(Color.RED)
                                            .geodesic(true));
                                    mMap.setMyLocationEnabled(true);
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {


                                }
                            }
                            );
                            Context context = MapFragment.this.getActivity();
                            VolleySingleton.getInstance(context).addToRequestQueue(roadPointsJsonObject);
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    cL, DEFAULT_ZOOM));
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
    private void getGPSpermisssion(){
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted2 = true;
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    ACCESS_COARSE_LOCATION);
        }
    }

    private void buildAlertNoGPS() {
        mLocationPermissionGranted2=false;
        mLocationPermissionGranted=false;
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Your GPS is disabled, can you enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
    private void buildAlertNoInternet() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Your not connected to internet, can connect it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(getActivity(),
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
    }

    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        // Output format
        String output = "json";

        return "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters /*+ "&key=AIzaSyB6p2mWkU0gvPM5Q20iH5q6CtSSmr6MITw"*/;
    }

}
