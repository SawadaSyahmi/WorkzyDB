package com.example.workzydb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ConversationViewHolder> {
    ArrayList<ConversationModel> conversationList;
//
    public ConversationAdapter(ArrayList<ConversationModel> conversationList) {
        this.conversationList = conversationList;
    }
//
    @NonNull
    @Override
    public ConversationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false);
        return new ConversationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConversationViewHolder holder, int position) {
        ConversationModel item = conversationList.get(position);
        holder.sender.setText(item.getSender());
        holder.message.setText(item.getMessage());
    }

    @Override
    public int getItemCount() {
        return conversationList.size();
    }

    static class ConversationViewHolder extends RecyclerView.ViewHolder {
        TextView sender;
        TextView message;

        public ConversationViewHolder(@NonNull View itemView) {
            super(itemView);
            sender = itemView.findViewById(android.R.id.text1);
            message = itemView.findViewById(android.R.id.text2);
        }
    }
}
