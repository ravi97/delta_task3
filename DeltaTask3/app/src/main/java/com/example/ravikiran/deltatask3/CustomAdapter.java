package com.example.ravikiran.deltatask3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Ravikiran on 7/5/2016.
 */
public class CustomAdapter extends BaseAdapter {

    ArrayList<ContactDetails> details;
    Context context;
    LayoutInflater inflater=null;

    public CustomAdapter(ArrayList<ContactDetails> details, Context context) {
        this.details = details;
        this.context = context;
        inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return details.size();
    }

    @Override
    public Object getItem(int position) {
        return details.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public class RowData{
        ImageView profile;
        TextView name;
        TextView number;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        RowData data=new RowData();
        View rowView;
        rowView=inflater.inflate(R.layout.listview_row,null);
        data.name=(TextView)rowView.findViewById(R.id.textView);
        data.name.setText(details.get(position).getName());
        data.number=(TextView)rowView.findViewById(R.id.textView2);
        data.number.setText(details.get(position).getPh_no());
        data.profile=(ImageView)rowView.findViewById(R.id.imageView);
        data.profile.setImageBitmap(details.get(position).getImage());



        return rowView;
    }



}
