package com.netforceinfotech.recycle;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;

public class MovieDetails extends AppCompatActivity {

    int id;
    String title;
    String imagpath;

    // initialise the XLM layout fields here....

    private TextView detailtitleTextView;
    private TextView detailDesTextView;
    private ImageView detailImageView;
    private TextView detailreleaseTextView;

    final String imageurl ="https://image.tmdb.org/t/p/w92";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        detailDesTextView = (TextView) findViewById(R.id.detailDesTextView);
        detailtitleTextView = (TextView) findViewById(R.id.detailtitleTextView);
        detailImageView = (ImageView) findViewById(R.id.detailimage);
        detailreleaseTextView = (TextView) findViewById(R.id.detailReleaseTextView);

        Bundle bundle = getIntent().getExtras();
        try {
            id = bundle.getInt("id");
            title = bundle.getString("title");
            imagpath = bundle.getString("imagepath");




            Log.i("id", String.valueOf(id)+title+imagpath);

        } catch (Exception ex) {
            Log.e("tc_bundle", "bundle error");
            return;
        }
        getSupportActionBar().setTitle(title);
        getMovieDetail(id);

    }


    private void getMovieDetail(int id) {
        //// when to call the GetHeader Function... APK key

        String api_key ="b8d0a325944118dd0f8dc56ede6beba7";
//        String url = "https://api.themoviedb.org/3/movie/top_rated?api_key="+api_key;
        String url = "https://api.themoviedb.org/3/movie/";

        final String imageurl ="https://image.tmdb.org/t/p/w92";

        Log.i("MovieUrl...", url+id+"?api_key="+api_key);
// recyclerView.setVisibility(View.GONE);
//
//      homeDatas.clear();

        Ion.with(this)
                .load(url+id+"?api_key="+api_key)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result)
                    {

                        if (result != null)
                        {

                            setAppDetail(result);

                        } else {
                            Log.e("error", e.toString());
                        }
                    }
                });


    }

    public void setAppDetail(JsonObject result) {

        // Get all the data from the database...
        // then set the images and text to the respective field...


        String title = result.get("original_title").getAsString();
        String description = result.get("overview").getAsString();
        String releasedate = result.get("release_date").getAsString();
        String detailimageurl=result.get("backdrop_path").getAsString();


        // Set text in the layout...

        detailtitleTextView.setText(title);
        detailDesTextView.setText(description);
        detailreleaseTextView.setText(releasedate);

        Glide.with(this).load(imageurl+detailimageurl).error(R.drawable.testimage).into(detailImageView);








    }
}
