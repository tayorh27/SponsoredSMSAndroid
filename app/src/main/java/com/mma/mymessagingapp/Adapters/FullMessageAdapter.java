package com.mma.mymessagingapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mma.mymessagingapp.Information.FullMessageContent;
import com.mma.mymessagingapp.R;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by tayo on 11/16/2015.
 */
public class FullMessageAdapter extends RecyclerView.Adapter<FullMessageAdapter.FullMessage>{

    private Context context;
    private ArrayList<FullMessageContent> informations = new ArrayList<>();
    private LayoutInflater inflater;

    public FullMessageAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setlist(ArrayList<FullMessageContent> list){
        this.informations = list;
        notifyDataSetChanged();
    }

    @Override
    public FullMessage onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_full_message_layout,parent,false);
        FullMessage holder = new FullMessage(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FullMessage holder, int position) {
        FullMessageContent current = informations.get(position);
        holder.name.setText(current.body);

        Long dt = Long.parseLong(current.date);
        Date date = new Date(dt);
        holder.message.setText(date.toLocaleString());
        //holder.imageView.setBackgroundColor();
    }

    @Override
    public int getItemCount() {
        return informations.size();
    }

    class FullMessage extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView avatar,name,message,date;
        Context context;

        public FullMessage(View itemView) {
            super(itemView);
            context = itemView.getContext();
            imageView = (ImageView)itemView.findViewById(R.id.thumbnail);
            avatar = (TextView)itemView.findViewById(R.id.textView2);
            name = (TextView)itemView.findViewById(R.id.custom_favorite_text);
            message = (TextView)itemView.findViewById(R.id.custom_favorite_subtext);
            date = (TextView)itemView.findViewById(R.id.custom_favorite_time);
        }
    }
}
