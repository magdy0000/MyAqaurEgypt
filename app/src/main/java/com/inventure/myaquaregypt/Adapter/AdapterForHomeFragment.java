package com.inventure.myaquaregypt.Adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.inventure.myaquaregypt.Model.HomeApi.ModelObjects;
import com.inventure.myaquaregypt.R;

import java.util.ArrayList;

public class AdapterForHomeFragment extends RecyclerView.Adapter<AdapterForHomeFragment.ExampleViewHolder> {
    /////
    private ArrayList<ModelObjects> mExampleList;
    private Context context;

    private OnItemClickListener mListener;


    public interface OnItemClickListener {

        void intent_to_detales(int pos, ImageView imageView);

        void make_love(int pos, ImageView img);


    }

    public AdapterForHomeFragment() {
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    /////
    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        private TextView textView_1, textView_2, textView_start, textView_end, concurncy;

        private ImageView
                imageView_one_one;


        public ExampleViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            /////
            imageView_one_one = itemView.findViewById(R.id.image_pro);
            textView_1 = itemView.findViewById(R.id.text_one_id);
//            textView_2 = itemView.findViewById(R.id.text_two_id);
            textView_end = itemView.findViewById(R.id.number_end_id);
            concurncy = itemView.findViewById(R.id.concurncy);

            /*
            textView_1_2_type = itemView.findViewById(R.id.type_of_project_one_two);
            textView_1_2_price = itemView.findViewById(R.id.price_home_one_two);
            textView_1_2_number = itemView.findViewById(R.id.number_Of_year_one_two);
            love=itemView.findViewById(R.id.love_button);
*/


/*

            love.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.make_love(position,love);
                        }
                    }
                }
            });



*/
            imageView_one_one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.intent_to_detales(position, imageView_one_one);
                        }
                    }
                }
            });
        }
    }

    public AdapterForHomeFragment(Context applicationContext, ArrayList<ModelObjects> exampleList) {
        mExampleList = exampleList;
        context = applicationContext;
    }


    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_exampl_home_one, viewGroup, false);

        return new ExampleViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, final int i) {
        ModelObjects currentitem = mExampleList.get(i);
////
//        exampleViewHolder.imageView_one_one.setImageResource(Integer.parseInt(currentitem.getProject_img()));

        Glide.with(context).load(currentitem.getProjectImg()).into(exampleViewHolder.imageView_one_one);
////


//        if (currentitem.getProjectName().length() > 10)

//            exampleViewHolder.textView_1.setText(currentitem.getProjectName().substring(0, 12) + "...");
//        else
        exampleViewHolder.textView_1.setText(currentitem.getProjectName());


//        exampleViewHolder.textView_2.setText(currentitem.getLocation());
        exampleViewHolder.textView_end.setText(currentitem.getPrice() + " ");
        exampleViewHolder.concurncy.setText(currentitem.getPrice_label());
        ///


    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


}
