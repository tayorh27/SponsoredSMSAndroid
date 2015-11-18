package com.mma.mymessagingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mma.mymessagingapp.Extras.GetNameByNumber;
import com.mma.mymessagingapp.Information.MessageContents;
import com.mma.mymessagingapp.R;
import com.mma.mymessagingapp.activities.MessagesActivity;
import com.mma.mymessagingapp.design.SetBackgroundColor;
import com.mma.mymessagingapp.model.Conversation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.everything.providers.android.telephony.TelephonyProvider;

/**
 * Created by tayo on 11/14/2015.
 */
public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageHolder>{

    ArrayList<MessageContents> information = new ArrayList<>();

    //ArrayList<Conversation> information = new ArrayList<>();

    Context context_;
    LayoutInflater inflater;

    public MessagesAdapter(Context context){
        this.context_ = context;
        inflater = LayoutInflater.from(context_);
    }

    public void setAdapter(ArrayList<MessageContents> information){
        this.information = information;
        notifyDataSetChanged();
    }

    @Override
    public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_message_layout,parent,false);
        view.setBackgroundResource(R.drawable.list_selector);
        view.setClickable(true);
        MessageHolder holder = new MessageHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MessageHolder holder, int position) {

        //Conversation conversation = information.get(position);

        //TelephonyProvider telephonyProvider = new TelephonyProvider(context_);
        //List<me.everything.providers.android.telephony.Conversation> conversations = telephonyProvider.getConversations().getList();

        MessageContents current = information.get(position);

        String nm = current.name;
        String mg = current.message;
        String dt = current.date;
//
        String new_name = nm;//new GetNameByNumber(context_).returnName(nm);
        if(new_name.contentEquals("")){
            new_name = nm;
        }

        Long getLongDate = Long.parseLong(dt);

        Date date = new Date(getLongDate);
        String new_date = date.toLocaleString();

        if(mg.length() > 30) {
            String new_msg = mg.substring(0, 30)+"...";
            holder.message.setText(new_msg);
        }else if(mg.length() > 25){
            String new_msg = mg.substring(0, 25)+"...";
            holder.message.setText(new_msg);
        }
        else{
            holder.message.setText(mg);
        }
        holder.name.setText(current.name_from_number);
        String gf = SetBackgroundColor.FirstLetter(current.name_from_number);
        if(gf.contains("0") || gf.contains("1")|| gf.contains("2")|| gf.contains("3")|| gf.contains("4")|| gf.contains("5")
                || gf.contains("6")|| gf.contains("7")|| gf.contains("8")|| gf.contains("9")|| gf.contains("0")
                || gf.contains("+")){
            gf = "#";
        }
        if(current.uri != null){
            holder.imageView.setImageBitmap(BitmapFactory.decodeFile(current.uri));
            holder.avatar.setText("");
        }else {
            holder.imageView.setBackgroundColor(current.color);
            holder.avatar.setText(gf);
        }
        holder.date.setText(new_date);

    }

    @Override
    public int getItemCount() {
        return information.size();
    }

    class MessageHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView avatar,name,message,date;
        Context context;

        public MessageHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            imageView = (ImageView)itemView.findViewById(R.id.thumbnail);
            avatar = (TextView)itemView.findViewById(R.id.textView2);
            name = (TextView)itemView.findViewById(R.id.custom_favorite_text);
            message = (TextView)itemView.findViewById(R.id.custom_favorite_subtext);
            date = (TextView)itemView.findViewById(R.id.custom_favorite_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MessageContents contents = information.get(getPosition());
                    Bundle bundle = new Bundle();
                    bundle.putLong("thread", contents.id);
                    bundle.putInt("color",contents.color);
                    Intent intent = new Intent(context, MessagesActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            });
        }
    }
}
