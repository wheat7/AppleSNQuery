package com.wheat7.applesnquery;

import android.widget.ArrayAdapter;

/**
 * Created by Wheat7 on 08/11/2016.
 */



import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ResultAdapter extends ArrayAdapter<Result> {
    private int resourceId;

    public ResultAdapter(Context context,int textViewResourceId,List<Result> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }

    public View getView(int position,View convertView,ViewGroup parent){
        Result result = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view=LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder=new ViewHolder();
            viewHolder.resultItem = (TextView)view.findViewById(R.id.result_item);
            viewHolder.resultInfo = (TextView)view.findViewById(R.id.result_info);
            view.setTag(viewHolder);
        }else{
            view=convertView;
            viewHolder=(ViewHolder)view.getTag();
        }

        viewHolder.resultItem.setText(result.getItem());
        viewHolder.resultInfo.setText(result.getInfo());
        return view;
    }

    class ViewHolder{
        TextView resultItem;
        TextView resultInfo;
    }

}




