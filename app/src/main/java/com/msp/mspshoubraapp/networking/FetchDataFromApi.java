package com.msp.mspshoubraapp.networking;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.msp.mspshoubraapp.AppExecutors;
import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.db.AppDatabase;
import com.msp.mspshoubraapp.db.CommitteeEntity;
import com.msp.mspshoubraapp.db.StudentActivityEntity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import static android.content.Context.MODE_PRIVATE;

public class FetchDataFromApi {

    private static final String URL_STUDENT_ACTIVITY = "https://msp-dashboard-35144.firebaseio.com/studentActivity.json";
    private static final String URL_RESTAURANT = "https://msp-dashboard-35144.firebaseio.com/restaurant.json";
    private static final String URL_NEWSFEED = "https://msp-dashboard-35144.firebaseio.com/newsFeed.json";
    private static final String URL_COWORKINGSPACE = "https://msp-dashboard-35144.firebaseio.com/coworkingspace.json";
    private static final String URL_LECTURESTABLE = "https://msp-dashboard-35144.firebaseio.com/lectures/groups.json";
    private static ProgressDialog progressDialog;

    public static void loadStudentActivities(final Context context, final boolean contextType) {
        if (!contextType) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(context.getResources().getString(R.string.wait));
            progressDialog.show();
        }

        JsonObjectRequest studentActivitiesJsonObject = new JsonObjectRequest(Request.Method.GET, URL_STUDENT_ACTIVITY, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                BuildData.extractStudentActivitiesJson(response, context);
                if (!contextType) {
                    progressDialog.dismiss();
                    SharedPreferences sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.msp_preferences), MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(context.getResources().getString(R.string.student_activity), false);
                    editor.apply();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (!contextType) {
                    progressDialog.dismiss();
                    //Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        VolleySingleton.getInstance(context).addToRequestQueue(studentActivitiesJsonObject);
    }

    public static void loadRestaurants(final Context context, final boolean contextType) {
        if (!contextType) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(context.getResources().getString(R.string.wait));
            progressDialog.show();
        }

        JsonObjectRequest restaurantsJsonObject = new JsonObjectRequest(Request.Method.GET, URL_RESTAURANT, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                BuildData.extractRestaurantsJson(response, context);

                if (!contextType) {
                    progressDialog.dismiss();
                    SharedPreferences sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.msp_preferences), MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(context.getResources().getString(R.string.restaurant), false);
                    editor.apply();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (!contextType) {
                    progressDialog.dismiss();
                }
            }
        });

        VolleySingleton.getInstance(context).addToRequestQueue(restaurantsJsonObject);
    }

    public static void loadPosts(final Context context, final boolean contextType) {
        if (!contextType) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(context.getResources().getString(R.string.wait));
            progressDialog.show();

        }

        JsonObjectRequest postsJsonObject = new JsonObjectRequest(Request.Method.GET, URL_RESTAURANT, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                BuildData.extractNewsFeedJson(response, context, contextType);
                if (!contextType) progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (!contextType) {
                    progressDialog.dismiss();
                    //Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        VolleySingleton.getInstance(context).addToRequestQueue(postsJsonObject);
    }

    public static void loadcoworkingSpaces(final Context context, final boolean contextType) {
        if (!contextType) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(context.getResources().getString(R.string.wait));
            progressDialog.show();

        }

        JsonObjectRequest coworkingSpaceObject = new JsonObjectRequest(Request.Method.GET, URL_COWORKINGSPACE, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                BuildData.extractCoworkingSpaceJson(response, context, contextType);
                if (!contextType) {
                    progressDialog.dismiss();
                    SharedPreferences sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.msp_preferences), MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(context.getResources().getString(R.string.coworkingSpace), false);
                    editor.apply();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (!contextType) {
                    progressDialog.dismiss();
                    //Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        VolleySingleton.getInstance(context).addToRequestQueue(coworkingSpaceObject);
    }


    public static void loadLecturesTable(final Context context, final boolean contextType) {
        if (!contextType) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(context.getResources().getString(R.string.wait));
            progressDialog.show();

        }

        JsonObjectRequest lecturesTableObject = new JsonObjectRequest(Request.Method.GET, URL_LECTURESTABLE, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                BuildData.extractLecturesTableJson(response, context, contextType);
                if (!contextType) {
                    progressDialog.dismiss();
                    SharedPreferences sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.msp_preferences), MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(context.getResources().getString(R.string.lecturesTable), false);
                    editor.apply();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (!contextType) {
                    progressDialog.dismiss();
                    //Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        VolleySingleton.getInstance(context).addToRequestQueue(lecturesTableObject);
    }


}
