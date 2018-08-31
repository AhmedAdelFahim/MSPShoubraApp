package com.msp.mspshoubraapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.msp.mspshoubraapp.adapter.NewsFeedRecyclerViewAdapter;
import com.msp.mspshoubraapp.data.NewsItem;
import com.msp.mspshoubraapp.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laila Al Ashkar on 8/14/2018.
 */

public class NewsFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    List<NewsItem> newsItems;
    ArrayList<Integer> imageList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news, container, false);

        recyclerView = view.findViewById(R.id.news_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        newsItems = new ArrayList<>();
        imageList = new ArrayList<>();
        imageList.add(R.drawable.ieee_cusb);
        //imageList.add(R.drawable.red);
       /* imageList.add(R.drawable.anonymous_half);
        imageList.add(R.drawable.ic_jerry);
        imageList.add(R.drawable.article);
        imageList.add(R.drawable.doc);*/
        NewsItem newsItem = new NewsItem("أحسن واحد يشتغل على الArduino و يعمل Robot يقدر يحل ال the Rubik cube.\n" +
                "\n" +
                "اختراعك هيكون المركز الأول في منافسة الElectronics في يوم الهندسة المصري 2017\n" +
                "\n" +
                "صاحب أكبر شركة Web Development؟ \n" +
                "\n" +
                "هتكون وزير الاتصالات لسنة 2022.... وصلت لنوبل ؟؟\n" +
                "\n" +
                "مستقبلنا مليان فرص كتير ومن خلال ال Participants' recruitment ل IEEE CUSB '17 هنقدملك workshops محتواها قيم جدا متقسمين على أربع مجالات من خلالهم هتقدر تتخصص و تبتكر أكتر في المجال اللي بتحب تدرس فيه و تقدر تHunt what's beyond \n" +
                "\n" +
                ":Our workshops are\n" +
                "\n" +
                "Embedded Systems* \n" +
                "AVRDunio- \n" +
                "Duino Geeks-\n" +
                "Pi-Window- \n" +
                "Sheeld1-\n" +
                "\n" +
                "Electronics*\n" +
                "\n" +
                "Logic wizards-\n" +
                "Digital Ninjas- \n" +
                "Analog arts v1.1- \n" +
                "\n" +
                "Power*\n" +
                "\n" +
                "GAINereation- \n" +
                "DistribYOUtion- \n" +
                "Control Freaks- \n" +
                "\n" +
                "Computer*\n" +
                "\n" +
                "Xamaroid: Mobile Development- \n" +
                "Webbometer: Web development- \n" +
                "\n" +
                "دلوقتي التقديم مفتوح من خلال ال Online form:\n" +
                "https://goo.gl/YD345P \n" +
                "\n" +
                "و لو عايز تقرأ أكتر عن تفاصيل كل workshop تقدر تدخل على الlink \n" +
                ": goo.gl/2xhHDK\n" +
                "Event Link: https://www.facebook.com/events/310759875949323/", imageList);
        newsItems.add(newsItem);

        imageList = new ArrayList<>();

        imageList.add(R.drawable.red);
        imageList.add(R.drawable.anonymous_half);
        imageList.add(R.drawable.ic_jerry);
        imageList.add(R.drawable.article);
        imageList.add(R.drawable.doc);

        newsItem = new NewsItem("السلام عليكم\nازيكم \nتيست تيست تيست", imageList);
        newsItems.add(newsItem);

       /* NewsItem newsItem = new NewsItem("السلام عليكم\nازيكم \nتيست تيست تيست", imageList);
        newsItems.add(newsItem);


        imageList = new ArrayList<>();
        imageList.add(R.drawable.doc);*/
        /*imageList.add(R.drawable.doc);

        newsItem = new NewsItem("السلام عليكم\nازيكم \nتيست تيست تيست", imageList);
       // newsItems.add(newsItem);
        imageList.add(R.drawable.doc);
        imageList.add(R.drawable.doc);

        newsItem = new NewsItem("السلام عليكم\nازيكم \nتيست تيست تيست", imageList);
       // newsItems.add(newsItem);
        imageList.add(R.drawable.doc);
        imageList.add(R.drawable.doc);
        imageList.add(R.drawable.doc);*/

        // newsItem = new NewsItem("السلام عليكم\nازيكم \nتيست تيست تيست", imageList);
        // newsItems.add(newsItem);

        /*imageList.add(R.drawable.doc);
        imageList.add(R.drawable.doc);
        imageList.add(R.drawable.doc);
        imageList.add(R.drawable.doc);
        imageList.add(R.drawable.doc);
*/
        for (int i = 0; i < 20; i++) {
            imageList = new ArrayList<>();
            for (int j = 0; j < i; ++j) {
                imageList.add(R.drawable.doc);
            }
            //Log.d("QWEERTYUUIOP","BIND "+imageList.size());
            newsItem = new NewsItem("السلام عليكم\nازيكم \nتيست تيست تيست", imageList);
            newsItems.add(newsItem);
            //imageList.remove(4-i);
        }
        adapter = new NewsFeedRecyclerViewAdapter(newsItems, getActivity());

        recyclerView.setAdapter(adapter);

        return view;
    }
}
