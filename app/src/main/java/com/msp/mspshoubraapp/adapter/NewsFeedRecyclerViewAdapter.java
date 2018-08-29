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

import nb.scode.lib.ExpandableTextView;


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
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        final NewsItem newsItem = newsItemList.get(position);
        //Log.d("QWEERTYUUIOP",position+" BIND "+newsItem.getImageList().size());
        final ArrayList<Integer> images = newsItem.getImageList();

        if (holder.getItemViewType() == 0) {
            Holder holder0 = (Holder) holder;
            holder0.news_title.setOnStateChangeListener(
                    new ExpandableTextView.OnStateChangeListener() {
                        @Override
                        public void onStateChange(boolean isShrink) {

                            newsItem.setShrink(isShrink);
                            newsItemList.set(holder.getAdapterPosition(), newsItem);
                        }
                    });
            holder0.news_title.setText(newsItem.getNewsTitle());
            holder0.news_title.resetState(newsItemList.get(position).isShrink());
        } else if (holder.getItemViewType() == 1) {
            HolderWithOneImage holder1 = (HolderWithOneImage) holder;
            holder1.news_title.setOnStateChangeListener(
                    new ExpandableTextView.OnStateChangeListener() {
                        @Override
                        public void onStateChange(boolean isShrink) {

                            newsItem.setShrink(isShrink);
                            newsItemList.set(holder.getAdapterPosition(), newsItem);
                        }
                    });
            holder1.news_title.setText(newsItem.getNewsTitle());
            holder1.news_title.resetState(newsItemList.get(position).isShrink());
            holder1.img1.setImageResource(images.get(0));
            holder1.img1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enterFullScreen(0, images);
                }
            });
        } else if (holder.getItemViewType() == 2) {
            HolderWithTwoImage holder2 = (HolderWithTwoImage) holder;
            holder2.news_title.setOnStateChangeListener(
                    new ExpandableTextView.OnStateChangeListener() {
                        @Override
                        public void onStateChange(boolean isShrink) {

                            newsItem.setShrink(isShrink);
                            newsItemList.set(holder.getAdapterPosition(), newsItem);
                        }
                    });
            holder2.news_title.setText(newsItem.getNewsTitle());
            holder2.news_title.resetState(newsItemList.get(position).isShrink());
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
            HolderWithThreeImage holder3 = (HolderWithThreeImage) holder;
            holder3.news_title.setOnStateChangeListener(
                    new ExpandableTextView.OnStateChangeListener() {
                        @Override
                        public void onStateChange(boolean isShrink) {

                            newsItem.setShrink(isShrink);
                            newsItemList.set(holder.getAdapterPosition(), newsItem);
                        }
                    });
            holder3.news_title.setText(newsItem.getNewsTitle());
            holder3.news_title.resetState(newsItemList.get(position).isShrink());
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
            HolderWithMoreThanThreeImage holder4 = (HolderWithMoreThanThreeImage) holder;
            holder4.news_title.setOnStateChangeListener(
                    new ExpandableTextView.OnStateChangeListener() {
                        @Override
                        public void onStateChange(boolean isShrink) {

                            newsItem.setShrink(isShrink);
                            newsItemList.set(holder.getAdapterPosition(), newsItem);
                        }
                    });
            holder4.news_title.setText(newsItem.getNewsTitle());
            holder4.news_title.resetState(newsItemList.get(position).isShrink());
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

        /*Log.d("JKLOIU","BIND "+position);
        holder.news_title.setOnStateChangeListener(
                new ExpandableTextView.OnStateChangeListener() {
                    @Override
                    public void onStateChange(boolean isShrink) {

                        newsItem.setShrink(isShrink);
                        newsItemList.set(holder.getAdapterPosition(), newsItem);
                    }
                });
        holder.news_title.setText(newsItem.getNewsTitle());
        holder.news_title.resetState(newsItemList.get(position).isShrink());*/
       /* ArrayList<Integer> images = newsItem.getImageList();
        Log.d("QWEERTYUUIOP",position+" BIND "+newsItem.getImageList().size());
        if(images.size() == 0)
        {
            holder.galleryView.setVisibility(View.GONE);

        }
        else if(images.size() == 1)
        {
            holder.vLine.setGuidelinePercent(1);
            holder.img1.setImageResource(images.get(0));
        }
        else if (images.size() == 2)
        {
            holder.hLine.setGuidelinePercent(1);
            holder.img1.setImageResource(images.get(0));
            holder.img2.setImageResource(images.get(1));
        }
        else // (images.size() == 3)
        {
            //holder.hLine.setGuidelinePercent(1);
            holder.img1.setImageResource(images.get(0));
            holder.img2.setImageResource(images.get(1));
            holder.img3.setImageResource(images.get(2));
        }
*/



        /*holder.news_img.setImageResource(newsItem.getNewsImg());
        holder.news_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(activity, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
                LayoutInflater inflater = activity.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.image_full_screen_dialog, null);
                builder.setView(dialogView);

                ImageView imageView = dialogView.findViewById(R.id.image_full_screen);
                imageView.setImageResource(newsItem.getNewsImg());

                builder.create().show();
            }
        });*/
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

        private ImageView news_img;
        private ExpandableTextView news_title;
        private ImageView up, down, img1, img2, img3;
        private ConstraintLayout galleryView;
        private android.support.constraint.Guideline vLine, hLine;

        public Holder(View itemView) {
            super(itemView);

            news_title = itemView.findViewById(R.id.recyclerview_item_txt);
            up = itemView.findViewById(R.id.up);
            down = itemView.findViewById(R.id.down);
        }
    }

    public class HolderWithOneImage extends RecyclerView.ViewHolder {

        private ImageView news_img;
        private ExpandableTextView news_title;
        private ImageView up, down, img1, img2, img3;
        private ConstraintLayout galleryView;
        private android.support.constraint.Guideline vLine, hLine;

        public HolderWithOneImage(View itemView) {
            super(itemView);

            //news_img = itemView.findViewById(R.id.recyclerview_item_img);
            news_title = itemView.findViewById(R.id.recyclerview_item_txt);
            up = itemView.findViewById(R.id.up);
            down = itemView.findViewById(R.id.down);
            galleryView = itemView.findViewById(R.id.gallery_view);
            img1 = itemView.findViewById(R.id.img1);
            //img2 = itemView.findViewById(R.id.img2);
            //img3 = itemView.findViewById(R.id.img3);
            vLine = itemView.findViewById(R.id.gallery_view_v_guideline);
            //hLine = itemView.findViewById(R.id.gallery_view_h_guideline);

            galleryView.setVisibility(View.VISIBLE);

            vLine.setGuidelinePercent(1);


        }
    }

    public class HolderWithTwoImage extends RecyclerView.ViewHolder {

        private ImageView news_img;
        private ExpandableTextView news_title;
        private ImageView up, down, img1, img2, img3;
        private ConstraintLayout galleryView;
        private android.support.constraint.Guideline vLine, hLine;

        public HolderWithTwoImage(View itemView) {
            super(itemView);

            //news_img = itemView.findViewById(R.id.recyclerview_item_img);
            news_title = itemView.findViewById(R.id.recyclerview_item_txt);
            up = itemView.findViewById(R.id.up);
            down = itemView.findViewById(R.id.down);
            galleryView = itemView.findViewById(R.id.gallery_view);
            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
            //img3 = itemView.findViewById(R.id.img3);
            //vLine = itemView.findViewById(R.id.gallery_view_v_guideline);
            hLine = itemView.findViewById(R.id.gallery_view_h_guideline);

            galleryView.setVisibility(View.VISIBLE);

            hLine.setGuidelinePercent(1);


        }
    }

    public class HolderWithThreeImage extends RecyclerView.ViewHolder {

        private ImageView news_img;
        private ExpandableTextView news_title;
        private ImageView up, down, img1, img2, img3;
        private ConstraintLayout galleryView;
        private android.support.constraint.Guideline vLine, hLine;

        public HolderWithThreeImage(View itemView) {
            super(itemView);

            //news_img = itemView.findViewById(R.id.recyclerview_item_img);
            news_title = itemView.findViewById(R.id.recyclerview_item_txt);
            up = itemView.findViewById(R.id.up);
            down = itemView.findViewById(R.id.down);
            galleryView = itemView.findViewById(R.id.gallery_view);
            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
            img3 = itemView.findViewById(R.id.img3);
            //vLine = itemView.findViewById(R.id.gallery_view_v_guideline);
            //hLine = itemView.findViewById(R.id.gallery_view_h_guideline);

            galleryView.setVisibility(View.VISIBLE);

            //hLine.setGuidelinePercent(1);


        }
    }

    public class HolderWithMoreThanThreeImage extends RecyclerView.ViewHolder {

        private ExpandableTextView news_title;
        private ImageView up, down, img1, img2, img3;
        private ConstraintLayout galleryView;
        private TextView numOfImage;

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
            galleryView.setVisibility(View.VISIBLE);
            numOfImage.setVisibility(View.VISIBLE);
            //hLine.setGuidelinePercent(1);


        }
    }
}
