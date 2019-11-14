package com.movchinnikov.chat.contactlist.ui;

import com.movchinnikov.chat.entities.User;

public interface OnItemClickListener {
    void onItemClick(User user);

    void onItemLongClick(User user);
}
