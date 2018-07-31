package com.example.beachrendezvous;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/*
---Adapter to store details of sport events--

 */

public class sportsEventDetailsAdapter extends ArrayAdapter<String> {


    Context mContext;

    String[] type1;
    String [] date1;
    String[] place1;

    public sportsEventDetailsAdapter(@NonNull Context context, String[] type1, String[] date1, String[] place1) {
        super(context, 0);
        this.mContext = context;
        this.type1=type1;
        this.date1=date1;
        this.place1=place1;
        }
    @Override
    public int getCount() {
        return type1.length;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView1, ViewGroup parent) {
        ViewHolder mViewHolder = new ViewHolder();
        if (convertView1 == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView1 = mInflater.inflate(R.layout.sportsinfoview, parent, false);
            mViewHolder.adminImage= (ImageView) convertView1.findViewById(R.id.admin);
            mViewHolder.gametext = (TextView) convertView1.findViewById(R.id.typeofgame);
            mViewHolder.date=(TextView)convertView1.findViewById(R.id.dateofgame);
            mViewHolder.place=(TextView)convertView1.findViewById(R.id.placeofgame);
            convertView1.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView1.getTag();
        }
        mViewHolder.adminImage.setImageResource(R.drawable.face);
        mViewHolder.gametext.setText(type1[position]);
        mViewHolder.date.setText(date1[position]);
        mViewHolder.place.setText(place1[position]);

        return convertView1;
    }

    static class ViewHolder {
        ImageView adminImage;
        TextView gametext;
        TextView date;
        TextView place;
    }
}


