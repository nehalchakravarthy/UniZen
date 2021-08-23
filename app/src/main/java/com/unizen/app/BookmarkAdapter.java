package com.unizen.app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.MyViewHolder> {

    /**
     * Acts as a bridge between RecyclerView and bookmarked post data
     * Responsible for making a View for each post in the list of bookmarked posts
     **/

    Context mContext;
    List<Post> mData ;

    public BookmarkAdapter(Context mContext, List<Post> mData) {
        // Constructor
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflates a new view with post data
        View row = LayoutInflater.from(mContext).inflate(R.layout.bookmark_card, parent,false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Sets values to fields in the new view
        holder.postTitle.setText(mData.get(position).getTitle());
        holder.userName.setText("Posted by " + mData.get(position).getUserName());
        holder.postDesc.setText(mData.get(position).getDescription());
        Glide.with(mContext).load(mData.get(position).getPicture()).into(holder.imgPost);
        if(mData.get(position).getUserPhoto() != null)
            Glide.with(mContext).load(mData.get(position).getUserPhoto()).apply(RequestOptions.circleCropTransform()).into(holder.imgPostProfile);
        else
            Glide.with(mContext).load(R.drawable.no_pic).apply(RequestOptions.circleCropTransform()).into(holder.imgPostProfile);
    }

    @Override
    public int getItemCount() {
        // Returns total number of items
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        // View holder to hold bookmarked post data
        TextView postTitle, userName, postDesc;
        ImageView imgPost, imgPostProfile;
        //CheckBox bookmark;

        public MyViewHolder(View itemView) {
            super(itemView);

            postTitle = itemView.findViewById(R.id.row_post_title);
            userName = itemView.findViewById(R.id.row_user_name);
            postDesc = itemView.findViewById(R.id.row_post_desc);
            imgPost = itemView.findViewById(R.id.row_post_img);
            imgPostProfile = itemView.findViewById(R.id.row_user_pic);

            itemView.setOnClickListener(new View.OnClickListener() {
                // Open detailed view of post
                @Override
                public void onClick(View view) {
                    Intent postDetailActivity = new Intent(mContext, PostDetailActivity.class);
                    int position = getAdapterPosition();

                    postDetailActivity.putExtra("title", mData.get(position).getTitle());
                    postDetailActivity.putExtra("postImage", mData.get(position).getPicture());
                    postDetailActivity.putExtra("description", mData.get(position).getDescription());
                    postDetailActivity.putExtra("postKey", mData.get(position).getPostKey());
                    postDetailActivity.putExtra("userPhoto", mData.get(position).getUserPhoto());
                    postDetailActivity.putExtra("userName", mData.get(position).getUserName());
                    long timestamp  = (long) mData.get(position).getTimeStamp();
                    postDetailActivity.putExtra("postDate", timestamp) ;
                    mContext.startActivity(postDetailActivity);
                }
            });
        }
    }
}
