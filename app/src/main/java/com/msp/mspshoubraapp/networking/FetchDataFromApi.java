package com.msp.mspshoubraapp.networking;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.msp.mspshoubraapp.R;

import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;
import static com.msp.mspshoubraapp.Constants.URL_COWORKINGSPACE;
import static com.msp.mspshoubraapp.Constants.URL_LECTURESTABLE;
import static com.msp.mspshoubraapp.Constants.URL_NEWSFEED;
import static com.msp.mspshoubraapp.Constants.URL_RESTAURANT;
import static com.msp.mspshoubraapp.Constants.URL_STUDENT_ACTIVITY;

public class FetchDataFromApi {


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
                if (response != null) {
                    BuildData.extractStudentActivitiesJson(response, context);
                }
                if (!contextType) {
                    progressDialog.dismiss();
                    if (response != null) {
                        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.msp_preferences), MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(context.getResources().getString(R.string.student_activity), false);
                        editor.apply();
                    }
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
                if (response != null) {
                    BuildData.extractRestaurantsJson(response, context);
                }
                if (!contextType) {
                    progressDialog.dismiss();
                    if (response != null) {
                        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.msp_preferences), MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(context.getResources().getString(R.string.restaurant), false);
                        editor.apply();

                    }
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

        JsonObjectRequest postsJsonObject = new JsonObjectRequest(Request.Method.GET, URL_NEWSFEED, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response != null) {
                    BuildData.extractNewsFeedJson(response, context, contextType);
                }
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
                if (response != null) {
                    BuildData.extractCoworkingSpaceJson(response, context, contextType);
                }
                if (!contextType) {
                    progressDialog.dismiss();
                    if (response != null) {
                        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.msp_preferences), MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(context.getResources().getString(R.string.coworkingSpace), false);
                        editor.apply();
                    }
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
                if (response != null) {
                    BuildData.extractLecturesTableJson(response, context, contextType);
                }
                if (!contextType) {
                    progressDialog.dismiss();
                    if (response != null) {
                        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.msp_preferences), MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(context.getResources().getString(R.string.lecturesTable), false);
                        editor.apply();
                    }
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
