package com.example.workzydb;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<ConversationModel> conversationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerView = findViewById(R.id.recycler_view_chat);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        int chatId = getIntent().getIntExtra("chatId", 0);
        conversationList = generateConversation(chatId);

        ConversationAdapter adapter = new ConversationAdapter(conversationList);
        recyclerView.setAdapter(adapter);
    }

    private ArrayList<ConversationModel> generateConversation(int chatId) {
        ArrayList<ConversationModel> list = new ArrayList<>();
        switch (chatId) {
            case 0: // Chat with Alice
                list.add(new ConversationModel("Hi Alice!", "You"));
                list.add(new ConversationModel("Hey! How are you?", "Alice"));
                list.add(new ConversationModel("I’m good, thanks!", "You"));
                break;
            case 1: // Chat with Bob
                list.add(new ConversationModel("Hi Bob!", "You"));
                list.add(new ConversationModel("Hello there!", "Bob"));
                list.add(new ConversationModel("What’s up?", "You"));
                break;
            case 2: // Chat with Carol
                list.add(new ConversationModel("Hi Carol!", "You"));
                list.add(new ConversationModel("Hi! How’s your day?", "Carol"));
                list.add(new ConversationModel("Pretty busy, yours?", "You"));
                break;
        }
        return list;
    }
}
