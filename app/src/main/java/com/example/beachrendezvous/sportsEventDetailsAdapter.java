package com.example.beachrendezvous;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/*
---Adapter to store details of sport events--

 */

public class sportsEventDetailsAdapter extends ArrayAdapter<String> {


    Context mContext;

    ArrayList<String> type1;
    ArrayList<String> date1;
    ArrayList<String> place1;
    String name;
    public sportsEventDetailsAdapter(@NonNull Context context, ArrayList<String> type1, ArrayList<String> date1, ArrayList<String> place1, String name) {
        super(context, 0);
        this.mContext = context;
        this.type1=type1;
        this.date1=date1;
        this.place1=place1;
        this.name=name;
        }
    @Override
    public int getCount() {
        return type1.size();
    }
    @NonNull
    @Override
    public View getView(int position, View convertView1, ViewGroup parent) {
        ViewHolder mViewHolder = new ViewHolder();
        if (convertView1 == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView1 = mInflater.inflate(R.layout.sport_events_listview, parent, false);
            mViewHolder.admin = (TextView) convertView1.findViewById(R.id.admin);
            mViewHolder.gametext = (TextView) convertView1.findViewById(R.id.typeofgame);
            mViewHolder.date =(TextView)convertView1.findViewById(R.id.dateofgame);
            mViewHolder.place =(TextView)convertView1.findViewById(R.id.placeofgame);
            convertView1.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView1.getTag();
        }
        mViewHolder.admin.setText(name);
        mViewHolder.gametext.setText(type1.get(position));
        mViewHolder.date.setText(date1.get(position));
        mViewHolder.place.setText(place1.get(position));

        return convertView1;
    }

    static class ViewHolder {
        TextView admin;
        TextView gametext;
        TextView date;
        TextView place;
    }
}


