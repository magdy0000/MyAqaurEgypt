package com.inventure.myaquaregypt.Adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.inventure.myaquaregypt.Model.ModelOfListOfPaymentMethod;
import com.inventure.myaquaregypt.R;

import java.util.ArrayList;

public class AdapterOfListOfPaymentMethod extends ArrayAdapter {


    ArrayList<ModelOfListOfPaymentMethod> mlist;

    public AdapterOfListOfPaymentMethod(@NonNull Context context, int resource, @NonNull ArrayList objects) {
        super(context, resource, objects);

        mlist = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.item_list_of_payment_method, parent, false);

        TextView text1 = convertView.findViewById(R.id.Percentage);
        TextView text2 = convertView.findViewById(R.id.years);


        text1.setText(mlist.get(position).Downpayment);
        text2.setText(mlist.get(position).installments);


        return convertView;
    }
}
