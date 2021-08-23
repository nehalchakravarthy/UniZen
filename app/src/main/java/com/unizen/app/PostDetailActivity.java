package com.unizen.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Locale;

public class PostDetailActivity extends AppCompatActivity {

    /**
     * Displays detailed information of a selected post
     **/

    ImageView imgPost, imgUserPost;
    TextView txtPostDesc, txtPostDateName, txtPostTitle;
    String PostKey;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        imgPost = findViewById(R.id.post_detail_img);
        imgUserPost = findViewById(R.id.post_detail_user_img);
        txtPostTitle = findViewById(R.id.post_detail_title);
        txtPostDesc = findViewById(R.id.post_detail_desc);
        txtPostDateName = findViewById(R.id.post_detail_date_name);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        String postImage = getIntent().getExtras().getString("postImage");
        Glide.with(this).load(postImage).into(imgPost);

        String postTitle = getIntent().getExtras().getString("title");
        txtPostTitle.setText(postTitle);

        String postDescription = getIntent().getExtras().getString("description");
        txtPostDesc.setText(postDescription);

        PostKey = getIntent().getExtras().getString("postKey");

        String userName = getIntent().getExtras().getString("userName");
        String date = timestampToString(getIntent().getExtras().getLong("postDate"));
        txtPostDateName.setText(date + " | by " + userName);

        String userPhoto = getIntent().getExtras().getString("userPhoto") ;
        if(userPhoto != null)
            Glide.with(this).load(userPhoto).apply(RequestOptions.circleCropTransform()).into(imgUserPost);
        else
            Glide.with(this).load(R.drawable.no_pic).apply(RequestOptions.circleCropTransform()).into(imgUserPost);
    }

    private String timestampToString(long time) {
        // Convert timestamp to string format
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
        String date = DateFormat.format("dd-MM-yyyy",   calendar).toString();
        return date;
    }
}