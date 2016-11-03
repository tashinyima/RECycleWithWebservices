package com.netforceinfotech.recycle;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by xyz on 11/1/2016.
 */

// View Holder links with the layout...
// Here we will initialize the ids of Xml individual items... such as TextView and ImageViews and EditText...

public class MovieViewHolder extends RecyclerView.ViewHolder {

    public TextView title,releasedate,description,rating;
    public ImageView rating_image,imageurl;
    View view;
    public MovieViewHolder(View itemView) {
        super(itemView);
        view=itemView;   // you have to set onclicklistener at onbind in Adapter class/./
        title= (TextView) itemView.findViewById(R.id.titleTextView);
        releasedate =(TextView) itemView.findViewById(R.id.releasedate);
        description= (TextView) itemView.findViewById(R.id.description);
        rating = (TextView) itemView.findViewById(R.id.rating);
        rating_image = (ImageView) itemView.findViewById(R.id.rating_image);
        imageurl = (ImageView) itemView.findViewById(R.id.frontimageView);


    }
}
