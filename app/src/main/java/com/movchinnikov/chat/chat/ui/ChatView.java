package com.movchinnikov.chat.chat.ui;

import com.movchinnikov.chat.entities.ChatMessage;

public interface ChatView {
    void sendMessage();

    void onMessageReceived(ChatMessage msg);
}
