package com.inventure.myaquaregypt.Adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.inventure.myaquaregypt.Model.ModelOfNewsAndEvent.ModelOfEventAndNews;
import com.inventure.myaquaregypt.R;

import java.util.ArrayList;

public class EventsAndNewsAdapter  extends RecyclerView.Adapter<EventsAndNewsAdapter.ExampleViewHolder> {
    /////
    private ArrayList<ModelOfEventAndNews> mExampleList;
    private Context context;

    private EventsAndNewsAdapter.OnItemClickListener mListener;




    public interface OnItemClickListener {

        void intent_to_detales(int pos, RelativeLayout relativeLayout);


    }

    public EventsAndNewsAdapter() {
    }

    public void setOnItemClickListener(EventsAndNewsAdapter.OnItemClickListener listener) {
        mListener = listener;
    }
    /////
    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        private TextView textView_1;

        private ImageView
                imageView_one_one;
        private RelativeLayout relativeLayout;


        public ExampleViewHolder(@NonNull View itemView, final EventsAndNewsAdapter.OnItemClickListener listener) {
            super(itemView);

            /////
            imageView_one_one=itemView.findViewById(R.id.image_of_events);
            textView_1=itemView.findViewById(R.id.text_of_events);
           relativeLayout=itemView.findViewById(R.id.header);


            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.intent_to_detales(position, relativeLayout);
                        }
                    } }
            });
        }
    }

    public EventsAndNewsAdapter(Context applicationContext, ArrayList<ModelOfEventAndNews> exampleList) {
        mExampleList = exampleList;
        context=applicationContext;
    }


    @NonNull
    @Override
    public EventsAndNewsAdapter.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_of_events,viewGroup,false);

        return new EventsAndNewsAdapter.ExampleViewHolder(v,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsAndNewsAdapter.ExampleViewHolder exampleViewHolder, final int i) {
        ModelOfEventAndNews currentitem = mExampleList.get(i);
////
//        exampleViewHolder.imageView_one_one.setImageResource(Integer.parseInt(currentitem.getProject_img()));

        Glide.with(context).load(currentitem.getProject_img()).into(exampleViewHolder.imageView_one_one);
////



        exampleViewHolder.textView_1.setText( currentitem.getProject_name());



    }

    @Override
    public int getItemCount() {

        return mExampleList.size();
    }


}
