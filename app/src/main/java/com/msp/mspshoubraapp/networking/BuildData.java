package com.msp.mspshoubraapp.networking;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.msp.mspshoubraapp.AppExecutors;
import com.msp.mspshoubraapp.Constants;
import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.data.PostData;
import com.msp.mspshoubraapp.db.AppDatabase;
import com.msp.mspshoubraapp.db.CommitteeEntity;
import com.msp.mspshoubraapp.db.CoworkingSpaceEntity;
import com.msp.mspshoubraapp.db.CoworkingSpaceImageEntity;
import com.msp.mspshoubraapp.db.CoworkingSpacePriceEntity;
import com.msp.mspshoubraapp.db.DayLecturesEntity;
import com.msp.mspshoubraapp.db.GroupDaysEntity;
import com.msp.mspshoubraapp.db.PostsIdEntity;
import com.msp.mspshoubraapp.db.RestaurantEntity;
import com.msp.mspshoubraapp.db.RestaurantMenuEntity;
import com.msp.mspshoubraapp.db.SectionsEntity;
import com.msp.mspshoubraapp.db.StudentActivityEntity;
import com.msp.mspshoubraapp.ui.HomeActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class BuildData {
    private static AppDatabase appDatabase;

    /*
     * extract data from Json obj
     * save data in db & internal storage
     *
     * */
    public static void extractStudentActivitiesJson(JSONObject response, final Context context) {
        appDatabase = AppDatabase.getInstance(context);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.studentActivitiesDao().deleteAllStudentActivities();
                appDatabase.committeesDao().deleteAllCommittees();
            }
        });
        Iterator<String> iter = response.keys();
        while (iter.hasNext()) {
            final String key = iter.next();
            try {
                final JSONObject jsonObject = (JSONObject) response.get(key);
                final String name = jsonObject.getString("name");
                final String description = jsonObject.getString("description");
                final String imgLogo = jsonObject.getString("imgLogo");
                final String[] logoPath = new String[1];


                ImageLoader imageLoader = VolleySingleton.getInstance(context).getImageLoader();
                imageLoader.get(imgLogo, new ImageLoader.ImageListener() {
                    @Override
                    public void onResponse(final ImageLoader.ImageContainer response, boolean isImmediate) {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                logoPath[0] = saveToInternalStorage(response.getBitmap(), context, name, "studentActivities");
                                appDatabase.studentActivitiesDao().insertStudentActivity(new StudentActivityEntity(key, logoPath[0], name, description));
                            }
                        });
                    }

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                final JSONArray jsonArray = jsonObject.optJSONArray("committeeList");
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonArray != null) {
                            for (int i = 0; i < jsonArray.length(); ++i) {
                                try {
                                    JSONObject obj = (JSONObject) jsonArray.get(i);
                                    String committeeName = obj.getString("committeeName");
                                    String committeeDescription = obj.getString("committeeDescription");
                                    appDatabase.committeesDao().insertCommittee(new CommitteeEntity(key, committeeName, committeeDescription));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    /*
     * extract data from Json obj
     * save data in db & internal storage
     *
     * */
    public static void extractRestaurantsJson(JSONObject response, final Context context) {

        appDatabase = AppDatabase.getInstance(context);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.restaurantDao().deleteAllRestaurants();
                appDatabase.restaurantMenuDao().deleteAllItems();
            }
        });

        Iterator<String> iter = response.keys();
        while (iter.hasNext()) {
            final String key = iter.next();
            try {
                final JSONObject jsonObject = (JSONObject) response.get(key);
                final String name = jsonObject.getString("placeName");
                final String address = jsonObject.getString("placeAddress");
                final String imgLogo = jsonObject.getString("placeLogo");
                final String phone1 = jsonObject.getString("placePhone1");
                final String phone2 = jsonObject.getString("placePhone2");
                final double lat = jsonObject.getDouble("placeLat");
                final double lng = jsonObject.getDouble("placeLng");
                final String[] logoPath = new String[1];


                ImageLoader imageLoader = VolleySingleton.getInstance(context).getImageLoader();
                imageLoader.get(imgLogo, new ImageLoader.ImageListener() {
                    @Override
                    public void onResponse(final ImageLoader.ImageContainer response, boolean isImmediate) {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                logoPath[0] = saveToInternalStorage(response.getBitmap(), context, name, "restaurants");
                                appDatabase.restaurantDao().insertRestaurant(new RestaurantEntity(key, name, logoPath[0], address, phone1, phone2, lat, lng));
                            }
                        });
                    }

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                final JSONArray jsonArray = jsonObject.optJSONArray("menuDataList");
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonArray != null) {
                            for (int i = 0; i < jsonArray.length(); ++i) {
                                try {
                                    JSONObject obj = (JSONObject) jsonArray.get(i);
                                    String itemName = obj.getString("name");
                                    String itemPrice = obj.getString("price");
                                    appDatabase.restaurantMenuDao().insertItem(new RestaurantMenuEntity(key, itemName, itemPrice));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

    /*
     * extract data from Json obj
     * save data in db & internal storage
     *
     * */

    public static ArrayList<PostData> extractNewsFeedJson(JSONObject response, Context context, boolean contextType) {

        appDatabase = AppDatabase.getInstance(context);
        ArrayList<PostData> postDataList = new ArrayList<>();
        Iterator<String> iter = response.keys();
        while (iter.hasNext()) {
            final String key = iter.next();
            if (!contextType) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        appDatabase.postsIdDao().insertPostId(new PostsIdEntity(key));

                    }
                });
            } else {
                pushNotification(key, context);
            }

            try {
                final JSONObject jsonObject = (JSONObject) response.get(key);
                final String body = jsonObject.getString("body");
                final int timePosted = jsonObject.getInt("timePosted");
                final JSONArray jsonArray = jsonObject.optJSONArray("images");
                ArrayList<String> urls = new ArrayList<>();
                if (jsonArray != null) {
                    for (int i = 0; i < jsonArray.length(); ++i) {
                        try {
                            String imgUrl = (String) jsonArray.get(i);
                            urls.add(imgUrl);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
                postDataList.add(new PostData(key, body, timePosted, urls));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        Collections.reverse(postDataList);
        return postDataList;
    }

    /*
     * extract data from Json obj
     * save data in db & internal storage
     *
     * */
    public static void extractCoworkingSpaceJson(JSONObject response, final Context context, boolean contextType) {
        appDatabase = AppDatabase.getInstance(context);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.coworkingSpaceDao().deleteAllCoworkingSpaces();
                appDatabase.coworkingSpacePriceDao().deleteAllPrices();
                appDatabase.coworkingSpaceImageDao().deleteAllImages();
            }
        });

        Iterator<String> iter = response.keys();
        while (iter.hasNext()) {
            final String key = iter.next();
            try {
                final JSONObject jsonObject = (JSONObject) response.get(key);
                final String name = jsonObject.getString("placeName");
                final String address = jsonObject.getString("placeAddress");
                final String imgLogo = jsonObject.getString("placeLogo");
                final String phone1 = jsonObject.getString("placePhone1");
                final String phone2 = jsonObject.getString("placePhone2");
                final double lat = jsonObject.getDouble("placeLat");
                final double lng = jsonObject.getDouble("placeLng");

                final String[] logoPath = new String[1];


                ImageLoader imageLoader = VolleySingleton.getInstance(context).getImageLoader();
                imageLoader.get(imgLogo, new ImageLoader.ImageListener() {
                    @Override
                    public void onResponse(final ImageLoader.ImageContainer response, boolean isImmediate) {
                        AppExecutors.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {
                                logoPath[0] = saveToInternalStorage(response.getBitmap(), context, name, "coworkingSpace");
                                appDatabase.coworkingSpaceDao().insertCoworkingSpace(new CoworkingSpaceEntity(key, name, address, logoPath[0] + "/" + name, phone1, phone2, lat, lng));
                            }
                        });
                    }

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                final JSONArray jsonArrayPrices = jsonObject.optJSONArray("priceList");
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (jsonArrayPrices != null) {
                            for (int i = 0; i < jsonArrayPrices.length(); ++i) {
                                try {
                                    JSONObject obj = (JSONObject) jsonArrayPrices.get(i);
                                    String itemName = obj.getString("name");
                                    String itemPrice = obj.getString("price");
                                    appDatabase.coworkingSpacePriceDao().insertCoworkingSpacePrice(new CoworkingSpacePriceEntity(key, itemName, itemPrice));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });

                JSONArray jsonArrayImages = jsonObject.optJSONArray("images");
                if (jsonArrayImages != null) {
                    for (int i = 0; i < jsonArrayImages.length(); ++i) {
                        try {
                            String url = (String) jsonArrayImages.get(i);

                            final String[] imgPath = new String[1];
                            final int finalI = i;
                            ImageLoader imageLoader1 = VolleySingleton.getInstance(context).getImageLoader();
                            imageLoader1.get(url, new ImageLoader.ImageListener() {
                                @Override
                                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                                    //final String tempKey = key;
                                    imgPath[0] = saveToInternalStorage(response.getBitmap(), context, key + finalI, "coworkingSpace");
                                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                                        @Override
                                        public void run() {
                                            ArrayList<CoworkingSpaceImageEntity> coworkingSpaceImageEntities =
                                                    (ArrayList<CoworkingSpaceImageEntity>) appDatabase.coworkingSpaceImageDao().findImage(imgPath[0] + "/" + key + finalI);
                                            if (coworkingSpaceImageEntities.size() == 0) {
                                                appDatabase.coworkingSpaceImageDao().insertImage(new CoworkingSpaceImageEntity(key, imgPath[0] + "/" + key + finalI));
                                            }
                                        }
                                    });
                                }

                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    /*
     * extract data from Json obj
     * save data in db & internal storage
     *
     * */
    public static void extractLecturesTableJson(JSONObject response, Context context, boolean contextType) {

        appDatabase = AppDatabase.getInstance(context);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.lecturesTableDao().deleteAllGroups();
                appDatabase.lecturesTableDao().deleteAllLectures();
                appDatabase.lecturesTableDao().deleteAllSections();
            }
        });
        Iterator<String> iter = response.keys();
        while (iter.hasNext()) {
            final String key = iter.next();
            try {
                final JSONObject jsonObject = (JSONObject) response.get(key);
                final String groupNum = jsonObject.getString("groupNum");
                final JSONArray dayDetails = jsonObject.getJSONArray("dayDetails");
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < dayDetails.length(); ++i) {
                            try {
                                JSONObject obj = (JSONObject) dayDetails.get(i);
                                String dayName = obj.getString("dayName");
                                JSONArray lectures = obj.getJSONArray("lectures");
                                long groupId = appDatabase.lecturesTableDao().insertGroups(new GroupDaysEntity(groupNum, dayName));

                                for (int j = 0; j < lectures.length(); ++j) {
                                    JSONObject lec = (JSONObject) lectures.get(j);
                                    int lecNum = lec.getInt("lecNum");
                                    String startTime = lec.getString("startTime");
                                    String endTime = lec.getString("endTime");
                                    long lectureId = appDatabase.lecturesTableDao().insertLecture(new DayLecturesEntity(groupId, lecNum, startTime, endTime));

                                    JSONArray sections = lec.getJSONArray("sections");
                                    for (int k = 0; k < sections.length(); ++k) {
                                        JSONObject sec = sections.getJSONObject(k);
                                        int secNum = sec.getInt("secNum");
                                        String subjectName = sec.getString("subjectName");
                                        String instructor = sec.getString("instructor");
                                        String place = sec.getString("place");
                                        appDatabase.lecturesTableDao().insertSection(new SectionsEntity(lecNum, lectureId, secNum, subjectName, place, instructor));
                                    }
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }

    /*
     * save image in internal storage
     * */
    private static String saveToInternalStorage(Bitmap bitmapImage, Context context, String name, String folderName) {

        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir(folderName, Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, name);
        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream(mypath);

            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return directory.getAbsolutePath();
    }


    /*
     * push notification for new news feed
     * */
    private static void pushNotification(final String key, final Context context) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<PostsIdEntity> postsIdEntities = (ArrayList<PostsIdEntity>) appDatabase.postsIdDao().findPost(key);
                if (postsIdEntities.isEmpty()) {
                    long id = appDatabase.postsIdDao().insertPostId(new PostsIdEntity(key));
                    buildNotification(context, id);
                }
            }
        });
    }

    private static void buildNotification(Context context, long id) {

        createNotificationChannel(context);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .setVibrate(new long[]{500, 600})
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(context.getResources().getString(R.string.app_name))
                        .setContentText("New Post Has Arrived")
                        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                                R.mipmap.ic_launcher));

        Intent notificationIntent = new Intent(context, HomeActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, (int) id, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify((int) id, builder.build());

    }

    private static void createNotificationChannel(Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            /*CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);*/
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(Constants.CHANNEL_ID, "MSP APP", importance);
            //channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
