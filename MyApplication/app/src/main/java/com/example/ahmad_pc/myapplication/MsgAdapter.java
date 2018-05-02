package com.example.ahmad_pc.myapplication;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import android.support.annotation.NonNull;

import java.util.ArrayList;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.MessageViewHolder> {

    final ArrayList<Msg> data;

    private final OnClickListener listener;



    public MsgAdapter(ArrayList<Msg> input, OnClickListener listener) {
        this.data = input;
        this.listener = listener;
    }

    public void removeItem(Msg msg) {
        for(int i = 0, size = data.size(); i < size; i++) {
            if (msg.equals(data.get(i))) {
                data.remove(i);
                notifyItemRemoved(i);
                return;
            }
        }

    }
    
    public void addMessage(Msg msg) {
        data.add(msg);
        notifyItemInserted(data.size() - 1);
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_view, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Msg msg = data.get(position);
        holder.time.setText(DateUtils.getRelativeTimeSpanString(holder.itemView.getContext(), msg.getTime()));
        holder.msg.setText(msg.getText());
        holder.name.setText(msg.getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface MessageDeletedListener {
        void onMessageDeleted(Msg msg);

    }

    static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView msg;
        TextView time;

        public MessageViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            msg = itemView.findViewById(R.id.msg);
            time = itemView.findViewById(R.id.time);
        }



        interface OnClickListener {
            void onClick(Msg message);
        }
    }


    public interface OnClickListener {
        void onClick(Msg message);
    }
}

