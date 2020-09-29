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
import com.inventure.myaquaregypt.Model.OurServices.ourServicesObj;
import com.inventure.myaquaregypt.R;

import java.util.ArrayList;

public class OurServicesAdapter extends RecyclerView.Adapter<OurServicesAdapter.MyView> {

    private Context myContext;
    private ArrayList<ourServicesObj> ourServicesObjs = new ArrayList<>();

    public OurServicesAdapter(Context myContext, ArrayList<ourServicesObj> ourServicesObjs) {
        this.myContext = myContext;
        this.ourServicesObjs = ourServicesObjs;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyView(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_our_service, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyView myView, int i) {

        ourServicesObj ourServicesObj = ourServicesObjs.get(i);

        Glide.with(myContext).load(ourServicesObj.getServiceImage()).into(myView.ServiceImageIV);
        myView.ServiceNameTV.setText(ourServicesObj.getServiceName());
    }

    @Override
    public int getItemCount() {
        return ourServicesObjs.size();
    }

    class MyView extends RecyclerView.ViewHolder {
        private TextView ServiceNameTV;
        private ImageView ServiceImageIV;

        MyView(@NonNull View itemView) {
            super(itemView);
            ServiceNameTV = itemView.findViewById(R.id.ServiceNameTV);
            ServiceImageIV = itemView.findViewById(R.id.ServiceImageIV);
        }
    }
}
