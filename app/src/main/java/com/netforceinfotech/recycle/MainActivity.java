package com.netforceinfotech.recycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayoutManager mlinearLayoutManager;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private List<JsonDataMovie> movieList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mlinearLayoutManager= new LinearLayoutManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);

   // Download Dependencies such as cardview,recycle and picasso...
   // Set RecycleView in the layout... in this case activity_main.xml
  // Create a class which initialise all the variables followed by setting setter and getter and also consctructor...
  // Create a layout where each data item is displayed.. data item layout for each row...
        movieAdapter = new MovieAdapter(movieList,this);
        mlinearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mlinearLayoutManager);
        recyclerView.setAdapter(movieAdapter);

//        String imageurl="http://kunsang.png";
//        ImageView imageView= (ImageView) findViewById(R.id.frontimageView);
//        Glide.with(this).load(imageurl).error(R.drawable.star).into(imageView);
        DownloadMovieDB();



//        https://image.tmdb.org/t/p/w92/
    }

    private void DownloadMovieDB() {
 //// when to call the GetHeader Function... APK key

        String api_key ="b8d0a325944118dd0f8dc56ede6beba7";
        String url = "https://api.themoviedb.org/3/movie/top_rated?api_key="+api_key;

        final String imageurl ="https://image.tmdb.org/t/p/w92";


// recyclerView.setVisibility(View.GONE);
//
//      homeDatas.clear();

        Ion.with(this)
                .load(String.valueOf(url))
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result)
                    {

                        if (result != null)
                        {

                            JsonArray totalresults = result.getAsJsonArray("results");
//                            System.out.println("imageurl ======================" + result);



                            for (int i = 0; i < totalresults.size(); i++)
                            {

                                    JsonObject jsonObject = (JsonObject) totalresults.get(i);
                                    String title= jsonObject.get("title").getAsString();
                                    String releasedate =jsonObject.get("release_date").getAsString();
                                    String description =jsonObject.get("overview").getAsString();
                                    String imagepath =jsonObject.get("poster_path").getAsString();
                                    Double rating =jsonObject.get("vote_average").getAsDouble();
                                    int movieid=jsonObject.get("id").getAsInt();
//                                   String  posterpath = jsonObject.get("poster_path").getAsString();

                                    movieList.add(new JsonDataMovie(title,rating,description,releasedate,imageurl+imagepath,movieid));
                                    // String a = String.valueOf(movieList.add(new JsonDataMovie("title")));

                                     Log.i("Wow", imageurl+imagepath);

//                                    JsonObject vo = jsonObject.getAsJsonObject("Product");
//                                    String name = vo.get("name").toString();
//                                    String price = vo.get("price").toString();
//                                    homeDatas.add(new HomeData("url", name, price));

//                                    System.out.println("imageurl ======================"  + name);

                            }

//                                mSwipyRefreshLayout.setRefreshing(false);
//                                adapter.notifyDataSetChanged();
//                                recyclerView.setVisibility(View.VISIBLE);
                            // it will notifiy the data is changed... or ...

                            movieAdapter.notifyDataSetChanged();

                        } else {
                            Log.e("error", e.toString());
                        }
                    }
                });


    }


}
