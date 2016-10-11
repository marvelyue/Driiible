package com.example.yuewu.driiible.view.shot_detail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuewu.driiible.R;
import com.example.yuewu.driiible.model.Shot;

/**
 * Created by YueWu on 10/10/16.
 */

public class ShotAdapter extends RecyclerView.Adapter {

    private static final int TYPE_SHOT_IMAGE = 0;
    private static final int TYPE_SHOT_INFO = 1;

    private Shot shot;

    public ShotAdapter(@NonNull Shot shot) {
        this.shot = shot;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_SHOT_IMAGE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shot_item_image, parent, false);
            return new ImageViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shot_item_info, parent, false);
            return new InfoViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_SHOT_IMAGE) {

        } else { // TYPE_INFO
            InfoViewHolder shotDetailViewHolder = (InfoViewHolder) holder;
            shotDetailViewHolder.title.setText(shot.title);
            shotDetailViewHolder.authorName.setText(shot.user.name);
            shotDetailViewHolder.description.setText(shot.description);

            shotDetailViewHolder.likeCount.setText(String.valueOf(shot.likes_count));
            shotDetailViewHolder.bucketCount.setText(String.valueOf(shot.buckets_count));
            shotDetailViewHolder.viewCount.setText(String.valueOf(shot.views_count));

        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_SHOT_IMAGE;
        } else {
            return TYPE_SHOT_INFO;
        }
    }
}
