package com.msp.mspshoubraapp.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.jsibbold.zoomage.ZoomageView;
import com.msp.mspshoubraapp.R;
import com.msp.mspshoubraapp.data.NewsItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laila Al Ashkar on 8/14/2018.
 */

public class NewsFeedRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<NewsItem> newsItemList;
    private Activity activity;

    public NewsFeedRecyclerViewAdapter(List<NewsItem> newsItemList, Activity activity) {
        this.newsItemList = newsItemList;
        this.activity = activity;
    }

    @Override
    public int getItemViewType(int position) {
        return newsItemList.get(position).getImageList().size();
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
        final NewsItem newsItem = newsItemList.get(position);
        //Log.d("QWEERTYUUIOP",position+" BIND "+newsItem.getImageList().size());
        final ArrayList<Integer> images = newsItem.getImageList();
        if (holder.getItemViewType() == 0) {
            final Holder holder0 = (Holder) holder;
            holder0.news_title.setText(newsItem.getNewsTitle());
            //Log.d("QWEERTYUUIOP",position+" AAA "+holder0.news_title.getText()+"");
            holder0.news_title.post(new Runnable() {
                @Override
                public void run() {
                    //Log.d("QWEERTYUUIOP",position+" AAA "+holder0.news_title.getText().length()+" "+newsItem.getNewsTitle().length());
                    //Log.d("QWEERTYUUIOP",holder0.news_title.getLineCount()+"");
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
                    //Log.d("QWEERTYUUIOP",newsItem.isExpanded()+"");
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
                    //Log.d("QWEERTYUUIOP",newsItem.isExpanded()+"");
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
            //Log.d("QWEERTYUUIOP",position + " BIND " + holder0.post.getLineCount());

        } else if (holder.getItemViewType() == 1) {
            final HolderWithOneImage holder1 = (HolderWithOneImage) holder;
            holder1.news_title.setText(newsItem.getNewsTitle());
            //Log.d("QWEERTYUUIOP",position+" AAA "+holder0.news_title.getText()+"");
            holder1.news_title.post(new Runnable() {
                @Override
                public void run() {
                    //Log.d("QWEERTYUUIOP",position+" AAA "+holder0.news_title.getText().length()+" "+newsItem.getNewsTitle().length());
                    //Log.d("QWEERTYUUIOP",holder0.news_title.getLineCount()+"");
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
            holder1.img1.setImageResource(images.get(0));
            holder1.img1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enterFullScreen(0, images);
                }
            });
        } else if (holder.getItemViewType() == 2) {
            final HolderWithTwoImage holder2 = (HolderWithTwoImage) holder;
            holder2.news_title.setText(newsItem.getNewsTitle());
            //Log.d("QWEERTYUUIOP",position+" AAA "+holder0.news_title.getText()+"");
            holder2.news_title.post(new Runnable() {
                @Override
                public void run() {
                    //Log.d("QWEERTYUUIOP",position+" AAA "+holder0.news_title.getText().length()+" "+newsItem.getNewsTitle().length());
                    //Log.d("QWEERTYUUIOP",holder0.news_title.getLineCount()+"");
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
                    //Log.d("QWEERTYUUIOP",newsItem.isExpanded()+"");
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
                    //Log.d("QWEERTYUUIOP",newsItem.isExpanded()+"");
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
            holder2.img1.setImageResource(images.get(0));
            holder2.img2.setImageResource(images.get(1));

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
            holder3.news_title.setText(newsItem.getNewsTitle());
            //Log.d("QWEERTYUUIOP",position+" AAA "+holder0.news_title.getText()+"");
            holder3.news_title.post(new Runnable() {
                @Override
                public void run() {
                    //Log.d("QWEERTYUUIOP",position+" AAA "+holder0.news_title.getText().length()+" "+newsItem.getNewsTitle().length());
                    //Log.d("QWEERTYUUIOP",holder0.news_title.getLineCount()+"");
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
            holder3.img1.setImageResource(images.get(0));
            holder3.img2.setImageResource(images.get(1));
            holder3.img3.setImageResource(images.get(2));
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
            holder4.news_title.setText(newsItem.getNewsTitle());
            //Log.d("QWEERTYUUIOP",position+" AAA "+holder0.news_title.getText()+"");
            holder4.news_title.post(new Runnable() {
                @Override
                public void run() {
                    //Log.d("QWEERTYUUIOP",position+" AAA "+holder0.news_title.getText().length()+" "+newsItem.getNewsTitle().length());
                    //Log.d("QWEERTYUUIOP",holder0.news_title.getLineCount()+"");
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
                    //Log.d("QWEERTYUUIOP",newsItem.isExpanded()+"");
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
                    //Log.d("QWEERTYUUIOP",newsItem.isExpanded()+"");
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
            holder4.img1.setImageResource(images.get(0));
            holder4.img2.setImageResource(images.get(1));
            holder4.img3.setImageResource(images.get(2));

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

    public void enterFullScreen(int pos, final ArrayList<Integer> imageList) {
        final int[] index = {pos};
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.image_full_screen_dialog, null);
        builder.setView(dialogView);

        final ZoomageView imageView = dialogView.findViewById(R.id.image_full_screen);
        imageView.setImageResource(imageList.get(pos));

        ImageView backBtn = dialogView.findViewById(R.id.back_btn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index[0] > 0)
                    index[0]--;
                imageView.setImageResource(imageList.get(index[0]));
                imageView.setZoomable(true);
                imageView.setAnimateOnReset(true);
                imageView.setAutoCenter(true);
                imageView.setRestrictBounds(false);
                imageView.setTranslatable(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }
        });

        ImageView forwardBtn = dialogView.findViewById(R.id.forward_btn);
        forwardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index[0] < imageList.size() - 1)
                    index[0]++;
                imageView.setImageResource(imageList.get(index[0]));
                imageView.setZoomable(true);
                imageView.setAnimateOnReset(true);
                imageView.setAutoCenter(true);
                imageView.setRestrictBounds(false);
                imageView.setTranslatable(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }
        });

        builder.create().show();
    }
    @Override
    public int getItemCount() {
        return newsItemList.size();
    }


    public class Holder extends RecyclerView.ViewHolder {


        private TextView news_title, seeMoreTxt;
        private ImageView up, down, seeMoreImg;
        public Holder(View itemView) {
            super(itemView);
            news_title = itemView.findViewById(R.id.recyclerview_item_txt);
            up = itemView.findViewById(R.id.up);
            down = itemView.findViewById(R.id.down);
            seeMoreImg = itemView.findViewById(R.id.see_more_img);
            seeMoreTxt = itemView.findViewById(R.id.see_more_txt);
        }
    }

    public class HolderWithOneImage extends RecyclerView.ViewHolder {

        private TextView news_title, seeMoreTxt;
        private ImageView up, down, img1, seeMoreImg;
        private ConstraintLayout galleryView;
        private android.support.constraint.Guideline vLine;

        public HolderWithOneImage(View itemView) {
            super(itemView);

            //news_img = itemView.findViewById(R.id.recyclerview_item_img);
            news_title = itemView.findViewById(R.id.recyclerview_item_txt);
            up = itemView.findViewById(R.id.up);
            down = itemView.findViewById(R.id.down);
            galleryView = itemView.findViewById(R.id.gallery_view);
            img1 = itemView.findViewById(R.id.img1);
            vLine = itemView.findViewById(R.id.gallery_view_v_guideline);
            seeMoreImg = itemView.findViewById(R.id.see_more_img);
            seeMoreTxt = itemView.findViewById(R.id.see_more_txt);

            galleryView.setVisibility(View.VISIBLE);
            vLine.setGuidelinePercent(1);


        }
    }

    public class HolderWithTwoImage extends RecyclerView.ViewHolder {

        private TextView news_title, seeMoreTxt;
        private ImageView up, down, img1, img2, seeMoreImg;
        private ConstraintLayout galleryView;
        private android.support.constraint.Guideline hLine;

        public HolderWithTwoImage(View itemView) {
            super(itemView);

            news_title = itemView.findViewById(R.id.recyclerview_item_txt);
            up = itemView.findViewById(R.id.up);
            down = itemView.findViewById(R.id.down);
            galleryView = itemView.findViewById(R.id.gallery_view);
            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
            hLine = itemView.findViewById(R.id.gallery_view_h_guideline);
            seeMoreImg = itemView.findViewById(R.id.see_more_img);
            seeMoreTxt = itemView.findViewById(R.id.see_more_txt);

            galleryView.setVisibility(View.VISIBLE);
            hLine.setGuidelinePercent(1);


        }
    }

    public class HolderWithThreeImage extends RecyclerView.ViewHolder {

        private TextView news_title, seeMoreTxt;
        private ImageView up, down, img1, img2, img3, seeMoreImg;
        private ConstraintLayout galleryView;


        public HolderWithThreeImage(View itemView) {
            super(itemView);

            news_title = itemView.findViewById(R.id.recyclerview_item_txt);
            up = itemView.findViewById(R.id.up);
            down = itemView.findViewById(R.id.down);
            galleryView = itemView.findViewById(R.id.gallery_view);
            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
            img3 = itemView.findViewById(R.id.img3);
            seeMoreImg = itemView.findViewById(R.id.see_more_img);
            seeMoreTxt = itemView.findViewById(R.id.see_more_txt);

            galleryView.setVisibility(View.VISIBLE);

            //hLine.setGuidelinePercent(1);


        }
    }

    public class HolderWithMoreThanThreeImage extends RecyclerView.ViewHolder {

        private TextView news_title, seeMoreTxt, numOfImage;
        private ImageView up, down, img1, img2, img3, seeMoreImg;
        private ConstraintLayout galleryView;

        public HolderWithMoreThanThreeImage(View itemView) {
            super(itemView);

            news_title = itemView.findViewById(R.id.recyclerview_item_txt);
            up = itemView.findViewById(R.id.up);
            down = itemView.findViewById(R.id.down);
            galleryView = itemView.findViewById(R.id.gallery_view);
            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
            img3 = itemView.findViewById(R.id.img3);
            numOfImage = itemView.findViewById(R.id.num_of_images);
            seeMoreImg = itemView.findViewById(R.id.see_more_img);
            seeMoreTxt = itemView.findViewById(R.id.see_more_txt);

            galleryView.setVisibility(View.VISIBLE);
            numOfImage.setVisibility(View.VISIBLE);
            //hLine.setGuidelinePercent(1);


        }
    }
}
