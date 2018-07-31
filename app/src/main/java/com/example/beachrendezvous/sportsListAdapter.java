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

---- Sports available are stored---

 */


public class sportsListAdapter extends ArrayAdapter<String> {

    String[] names;
    int[] flags;
    Context mContext;

    public sportsListAdapter(Context context, String[] gameNames, int[] gameImages) {
        super(context, R.layout.sports_listview);
        this.names = gameNames;
        this.flags = gameImages;
        this.mContext = context;
    }

       @Override
    public int getCount() {
        return names.length;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.sportsview, parent, false);
            viewHolder.mFlag = (ImageView) convertView.findViewById(R.id.imageView);
            viewHolder.mName = (TextView) convertView.findViewById(R.id.textView);
            viewHolder.mFlag1 = (ImageView) convertView.findViewById(R.id.arrowdown);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mFlag.setImageResource(flags[position]);
        viewHolder.mName.setText(names[position]);
        viewHolder.mFlag1.setImageResource(R.drawable.downloadarrow);

        return convertView;
    }

    static class ViewHolder {
        ImageView mFlag, mFlag1;
        TextView mName;

    }
}
