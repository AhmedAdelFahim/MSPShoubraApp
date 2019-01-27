package com.msp.mspshoubraapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.Time;
import com.msp.mspshoubraapp.data.PostData;
import com.msp.mspshoubraapp.ui.ImageFullScreenActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import com.squareup.picasso.Callback;

/**
 * Created by Laila Al Ashkar on 8/14/2018.
 */

public class NewsFeedRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PostData> newsItemList;
    private Activity activity;

    public NewsFeedRecyclerViewAdapter(List<PostData> newsItemList, Activity activity) {
        this.newsItemList = newsItemList;
        this.activity = activity;
    }

    @Override
    public int getItemViewType(int position) {
        return newsItemList.get(position).getImages().size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview_news, parent, false);
        if (viewType == 0) {
            return new Holder(view);
        } else if (viewType == 1) {
            return new HolderWithOneImage(view);
        } else if (viewType == 2) {
            return new HolderWithTwoImage(view);
        } else if (viewType == 3) {
            return new HolderWithThreeImage(view);
        } else {
            return new HolderWithMoreThanThreeImage(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        final PostData newsItem = newsItemList.get(position);
        final ArrayList<String> images = newsItem.getImages();
        if (holder.getItemViewType() == 0) {
            final Holder holder0 = (Holder) holder;
            holder0.news_title.setText(newsItem.getBody());
            holder0.timePosted.setText(Time.convertUnixToString(newsItem.getTimePosted()));
            holder0.news_title.post(new Runnable() {
                @Override
                public void run() {

                    if (holder0.news_title.getLineCount() <= 2) {
                        holder0.seeMoreImg.setVisibility(View.GONE);
                        holder0.seeMoreTxt.setVisibility(View.GONE);
                    }
                    holder0.news_title.setMaxLines(2);

                }
            });
            holder0.seeMoreImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!newsItem.isExpanded()) {
                        holder0.news_title.setMaxLines(100);
                        holder0.seeMoreImg.setImageResource(R.drawable.ic_arrow_up_24px);
                        holder0.seeMoreTxt.setText("See Less");
                        newsItem.setExpanded(true);
                    } else {
                        holder0.news_title.setMaxLines(2);
                        holder0.seeMoreImg.setImageResource(R.drawable.ic_arrow_down_24px);
                        holder0.seeMoreTxt.setText("See More");
                        newsItem.setExpanded(false);
                    }
                }
            });

            holder0.seeMoreTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!newsItem.isExpanded()) {
                        holder0.news_title.setMaxLines(100);
                        holder0.seeMoreImg.setImageResource(R.drawable.ic_arrow_up_24px);
                        holder0.seeMoreTxt.setText("See Less");
                        newsItem.setExpanded(true);
                    } else {
                        holder0.news_title.setMaxLines(2);
                        holder0.seeMoreImg.setImageResource(R.drawable.ic_arrow_down_24px);
                        holder0.seeMoreTxt.setText("See More");
                        newsItem.setExpanded(false);
                    }
                }
            });
        } else if (holder.getItemViewType() == 1) {
            final HolderWithOneImage holder1 = (HolderWithOneImage) holder;
            holder1.news_title.setText(newsItem.getBody());
            holder1.timePosted.setText(Time.convertUnixToString(newsItem.getTimePosted()));
            holder1.news_title.post(new Runnable() {
                @Override
                public void run() {
                    if (holder1.news_title.getLineCount() <= 2) {
                        holder1.seeMoreImg.setVisibility(View.GONE);
                        holder1.seeMoreTxt.setVisibility(View.GONE);
                    }
                    holder1.news_title.setMaxLines(2);

                }
            });
            holder1.seeMoreImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!newsItem.isExpanded()) {
                        holder1.news_title.setMaxLines(100);
                        holder1.seeMoreImg.setImageResource(R.drawable.ic_arrow_up_24px);
                        holder1.seeMoreTxt.setText("See Less");
                        newsItem.setExpanded(true);
                    } else {
                        holder1.news_title.setMaxLines(2);
                        holder1.seeMoreImg.setImageResource(R.drawable.ic_arrow_down_24px);
                        holder1.seeMoreTxt.setText("See More");
                        newsItem.setExpanded(false);
                    }
                }
            });

            holder1.seeMoreTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.d("QWEERTYUUIOP",newsItem.isExpanded()+"");
                    if (!newsItem.isExpanded()) {
                        holder1.news_title.setMaxLines(100);
                        holder1.seeMoreImg.setImageResource(R.drawable.ic_arrow_up_24px);
                        holder1.seeMoreTxt.setText("See Less");
                        newsItem.setExpanded(true);
                    } else {
                        holder1.news_title.setMaxLines(2);
                        holder1.seeMoreImg.setImageResource(R.drawable.ic_arrow_down_24px);
                        holder1.seeMoreTxt.setText("See More");
                        newsItem.setExpanded(false);
                    }
                }
            });
            Picasso
                    .get()
                    .load(images.get(0))
                    .error(R.drawable.ic_error_black_24dp)
                    .into(holder1.img1, new Callback() {
                        @Override
                        public void onSuccess() {
                            holder1.img1ProgressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });
            holder1.img1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enterFullScreen(0, images);
                }
            });
        } else if (holder.getItemViewType() == 2) {
            final HolderWithTwoImage holder2 = (HolderWithTwoImage) holder;
            holder2.news_title.setText(newsItem.getBody());
            holder2.timePosted.setText(Time.convertUnixToString(newsItem.getTimePosted()));
            holder2.news_title.post(new Runnable() {
                @Override
                public void run() {
                    if (holder2.news_title.getLineCount() <= 2) {
                        holder2.seeMoreImg.setVisibility(View.GONE);
                        holder2.seeMoreTxt.setVisibility(View.GONE);
                    }
                    holder2.news_title.setMaxLines(2);

                }
            });
            holder2.seeMoreImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!newsItem.isExpanded()) {
                        holder2.news_title.setMaxLines(100);
                        holder2.seeMoreImg.setImageResource(R.drawable.ic_arrow_up_24px);
                        holder2.seeMoreTxt.setText("See Less");
                        newsItem.setExpanded(true);
                    } else {
                        holder2.news_title.setMaxLines(2);
                        holder2.seeMoreImg.setImageResource(R.drawable.ic_arrow_down_24px);
                        holder2.seeMoreTxt.setText("See More");
                        newsItem.setExpanded(false);
                    }
                }
            });

            holder2.seeMoreTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!newsItem.isExpanded()) {
                        holder2.news_title.setMaxLines(100);
                        holder2.seeMoreImg.setImageResource(R.drawable.ic_arrow_up_24px);
                        holder2.seeMoreTxt.setText("See Less");
                        newsItem.setExpanded(true);
                    } else {
                        holder2.news_title.setMaxLines(2);
                        holder2.seeMoreImg.setImageResource(R.drawable.ic_arrow_down_24px);
                        holder2.seeMoreTxt.setText("See More");
                        newsItem.setExpanded(false);
                    }
                }
            });

            Picasso
                    .get()
                    .load(images.get(0))
                    .error(R.drawable.ic_error_black_24dp)
                    .into(holder2.img1, new Callback() {
                        @Override
                        public void onSuccess() {
                            holder2.img1ProgressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });


            Picasso
                    .get()
                    .load(images.get(1))
                    .error(R.drawable.ic_error_black_24dp)
                    .into(holder2.img2, new Callback() {
                        @Override
                        public void onSuccess() {
                            holder2.img2ProgressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });
            holder2.img1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enterFullScreen(0, images);
                }
            });
            holder2.img2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enterFullScreen(1, images);
                }
            });
        } else if (holder.getItemViewType() == 3) {
            final HolderWithThreeImage holder3 = (HolderWithThreeImage) holder;
            holder3.news_title.setText(newsItem.getBody());
            holder3.timePosted.setText(Time.convertUnixToString(newsItem.getTimePosted()));
            holder3.news_title.post(new Runnable() {
                @Override
                public void run() {
                    if (holder3.news_title.getLineCount() <= 2) {
                        holder3.seeMoreImg.setVisibility(View.GONE);
                        holder3.seeMoreTxt.setVisibility(View.GONE);
                    }
                    holder3.news_title.setMaxLines(2);

                }
            });
            holder3.seeMoreImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.d("QWEERTYUUIOP",newsItem.isExpanded()+"");
                    if (!newsItem.isExpanded()) {
                        holder3.news_title.setMaxLines(100);
                        holder3.seeMoreImg.setImageResource(R.drawable.ic_arrow_up_24px);
                        holder3.seeMoreTxt.setText("See Less");
                        newsItem.setExpanded(true);
                    } else {
                        holder3.news_title.setMaxLines(2);
                        holder3.seeMoreImg.setImageResource(R.drawable.ic_arrow_down_24px);
                        holder3.seeMoreTxt.setText("See More");
                        newsItem.setExpanded(false);
                    }
                }
            });

            holder3.seeMoreTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.d("QWEERTYUUIOP",newsItem.isExpanded()+"");
                    if (!newsItem.isExpanded()) {
                        holder3.news_title.setMaxLines(100);
                        holder3.seeMoreImg.setImageResource(R.drawable.ic_arrow_up_24px);
                        holder3.seeMoreTxt.setText("See Less");
                        newsItem.setExpanded(true);
                    } else {
                        holder3.news_title.setMaxLines(2);
                        holder3.seeMoreImg.setImageResource(R.drawable.ic_arrow_down_24px);
                        holder3.seeMoreTxt.setText("See More");
                        newsItem.setExpanded(false);
                    }
                }
            });
            Picasso
                    .get()
                    .load(images.get(0))
                    .error(R.drawable.ic_error_black_24dp)
                    .into(holder3.img1, new Callback() {
                        @Override
                        public void onSuccess() {
                            holder3.img1ProgressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });
            Picasso
                    .get()
                    .load(images.get(1))
                    .error(R.drawable.ic_error_black_24dp)
                    .into(holder3.img2, new Callback() {
                        @Override
                        public void onSuccess() {
                            holder3.img2ProgressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });
            Picasso
                    .get()
                    .load(images.get(2))
                    .error(R.drawable.ic_error_black_24dp)
                    .into(holder3.img3, new Callback() {
                        @Override
                        public void onSuccess() {
                            holder3.img3ProgressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });
            holder3.img1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enterFullScreen(0, images);
                }
            });
            holder3.img2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enterFullScreen(1, images);
                }
            });
            holder3.img3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enterFullScreen(2, images);
                }
            });
        } else if (holder.getItemViewType() > 3) {
            final HolderWithMoreThanThreeImage holder4 = (HolderWithMoreThanThreeImage) holder;
            holder4.news_title.setText(newsItem.getBody());
            holder4.timePosted.setText(Time.convertUnixToString(newsItem.getTimePosted()));
            holder4.news_title.post(new Runnable() {
                @Override
                public void run() {

                    if (holder4.news_title.getLineCount() <= 2) {
                        holder4.seeMoreImg.setVisibility(View.GONE);
                        holder4.seeMoreTxt.setVisibility(View.GONE);
                    }
                    holder4.news_title.setMaxLines(2);

                }
            });
            holder4.seeMoreImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!newsItem.isExpanded()) {
                        holder4.news_title.setMaxLines(100);
                        holder4.seeMoreImg.setImageResource(R.drawable.ic_arrow_up_24px);
                        holder4.seeMoreTxt.setText("See Less");
                        newsItem.setExpanded(true);
                    } else {
                        holder4.news_title.setMaxLines(2);
                        holder4.seeMoreImg.setImageResource(R.drawable.ic_arrow_down_24px);
                        holder4.seeMoreTxt.setText("See More");
                        newsItem.setExpanded(false);
                    }
                }
            });

            holder4.seeMoreTxt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!newsItem.isExpanded()) {
                        holder4.news_title.setMaxLines(100);
                        holder4.seeMoreImg.setImageResource(R.drawable.ic_arrow_up_24px);
                        holder4.seeMoreTxt.setText("See Less");
                        newsItem.setExpanded(true);
                    } else {
                        holder4.news_title.setMaxLines(2);
                        holder4.seeMoreImg.setImageResource(R.drawable.ic_arrow_down_24px);
                        holder4.seeMoreTxt.setText("See More");
                        newsItem.setExpanded(false);
                    }
                }
            });

            Picasso
                    .get()
                    .load(images.get(0))
                    .error(R.drawable.ic_error_black_24dp)
                    .into(holder4.img1, new Callback() {
                        @Override
                        public void onSuccess() {
                            holder4.img1ProgressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });
            Picasso
                    .get()
                    .load(images.get(1))
                    .error(R.drawable.ic_error_black_24dp)
                    .into(holder4.img2, new Callback() {
                        @Override
                        public void onSuccess() {
                            holder4.img2ProgressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });
            Picasso
                    .get()
                    .load(images.get(2))
                    .error(R.drawable.ic_error_black_24dp)
                    .into(holder4.img3, new Callback() {
                        @Override
                        public void onSuccess() {
                            holder4.img3ProgressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });
            holder4.numOfImage.setText("+" + (images.size() - 3));

            holder4.img1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enterFullScreen(0, images);
                }
            });
            holder4.img2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enterFullScreen(1, images);
                }
            });
            holder4.numOfImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enterFullScreen(2, images);
                }
            });
        }

    }

    public void enterFullScreen(int pos, final ArrayList<String> imageList) {
        Intent intent = new Intent(activity, ImageFullScreenActivity.class);
        intent.putExtra("pos", pos);
        intent.putExtra("images", imageList);
        activity.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return newsItemList.size();
    }

    public void setNewsFeed(List<PostData> newsItems) {
        if (newsItems == null) {
            return;
        }
        this.newsItemList = newsItems;
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder {


        private TextView news_title, seeMoreTxt, timePosted;
        private ImageView up, down, seeMoreImg;
        private ProgressBar img1ProgressBar, img2ProgressBar, img3ProgressBar;

        public Holder(View itemView) {
            super(itemView);
            news_title = itemView.findViewById(R.id.recyclerview_item_txt);
            seeMoreImg = itemView.findViewById(R.id.see_more_img);
            seeMoreTxt = itemView.findViewById(R.id.see_more_txt);
            timePosted = itemView.findViewById(R.id.time_post);

            img1ProgressBar = itemView.findViewById(R.id.img1_progressBar);
            img2ProgressBar = itemView.findViewById(R.id.img2_progressBar);
            img3ProgressBar = itemView.findViewById(R.id.img3_progressBar);
        }
    }

    public class HolderWithOneImage extends RecyclerView.ViewHolder {

        private TextView news_title, seeMoreTxt, timePosted;
        private ImageView img1, seeMoreImg;
        private ConstraintLayout galleryView;
        private android.support.constraint.Guideline vLine;
        private ProgressBar img1ProgressBar, img2ProgressBar, img3ProgressBar;

        public HolderWithOneImage(View itemView) {
            super(itemView);

            news_title = itemView.findViewById(R.id.recyclerview_item_txt);

            galleryView = itemView.findViewById(R.id.gallery_view);
            img1 = itemView.findViewById(R.id.img1);
            vLine = itemView.findViewById(R.id.gallery_view_v_guideline);
            seeMoreImg = itemView.findViewById(R.id.see_more_img);
            seeMoreTxt = itemView.findViewById(R.id.see_more_txt);
            timePosted = itemView.findViewById(R.id.time_post);

            img1ProgressBar = itemView.findViewById(R.id.img1_progressBar);
            img2ProgressBar = itemView.findViewById(R.id.img2_progressBar);
            img3ProgressBar = itemView.findViewById(R.id.img3_progressBar);

            galleryView.setVisibility(View.VISIBLE);
            vLine.setGuidelinePercent(1);


        }
    }

    public class HolderWithTwoImage extends RecyclerView.ViewHolder {

        private TextView news_title, seeMoreTxt, timePosted;
        private ImageView img1, img2, seeMoreImg;
        private ConstraintLayout galleryView;
        private android.support.constraint.Guideline hLine;
        private ProgressBar img1ProgressBar, img2ProgressBar, img3ProgressBar;

        public HolderWithTwoImage(View itemView) {
            super(itemView);

            news_title = itemView.findViewById(R.id.recyclerview_item_txt);

            galleryView = itemView.findViewById(R.id.gallery_view);
            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
            hLine = itemView.findViewById(R.id.gallery_view_h_guideline);
            seeMoreImg = itemView.findViewById(R.id.see_more_img);
            seeMoreTxt = itemView.findViewById(R.id.see_more_txt);
            timePosted = itemView.findViewById(R.id.time_post);

            img1ProgressBar = itemView.findViewById(R.id.img1_progressBar);
            img2ProgressBar = itemView.findViewById(R.id.img2_progressBar);
            img3ProgressBar = itemView.findViewById(R.id.img3_progressBar);

            galleryView.setVisibility(View.VISIBLE);
            hLine.setGuidelinePercent(1);


        }
    }

    public class HolderWithThreeImage extends RecyclerView.ViewHolder {

        private TextView news_title, seeMoreTxt, timePosted;
        private ImageView img1, img2, img3, seeMoreImg;
        private ConstraintLayout galleryView;
        private ProgressBar img1ProgressBar, img2ProgressBar, img3ProgressBar;


        public HolderWithThreeImage(View itemView) {
            super(itemView);

            news_title = itemView.findViewById(R.id.recyclerview_item_txt);
            galleryView = itemView.findViewById(R.id.gallery_view);
            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
            img3 = itemView.findViewById(R.id.img3);
            seeMoreImg = itemView.findViewById(R.id.see_more_img);
            seeMoreTxt = itemView.findViewById(R.id.see_more_txt);
            timePosted = itemView.findViewById(R.id.time_post);

            img1ProgressBar = itemView.findViewById(R.id.img1_progressBar);
            img2ProgressBar = itemView.findViewById(R.id.img2_progressBar);
            img3ProgressBar = itemView.findViewById(R.id.img3_progressBar);

            galleryView.setVisibility(View.VISIBLE);



        }
    }

    public class HolderWithMoreThanThreeImage extends RecyclerView.ViewHolder {

        private TextView news_title, seeMoreTxt, numOfImage, timePosted;
        private ImageView img1, img2, img3, seeMoreImg;
        private ConstraintLayout galleryView;
        private ProgressBar img1ProgressBar, img2ProgressBar, img3ProgressBar;

        public HolderWithMoreThanThreeImage(View itemView) {
            super(itemView);

            news_title = itemView.findViewById(R.id.recyclerview_item_txt);
            galleryView = itemView.findViewById(R.id.gallery_view);
            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
            img3 = itemView.findViewById(R.id.img3);
            numOfImage = itemView.findViewById(R.id.num_of_images);
            seeMoreImg = itemView.findViewById(R.id.see_more_img);
            seeMoreTxt = itemView.findViewById(R.id.see_more_txt);
            timePosted = itemView.findViewById(R.id.time_post);

            img1ProgressBar = itemView.findViewById(R.id.img1_progressBar);
            img2ProgressBar = itemView.findViewById(R.id.img2_progressBar);
            img3ProgressBar = itemView.findViewById(R.id.img3_progressBar);

            galleryView.setVisibility(View.VISIBLE);
            numOfImage.setVisibility(View.VISIBLE);

        }
    }
}
