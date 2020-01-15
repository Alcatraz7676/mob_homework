package ru.movchinnikov.contacts.ui.main.contacts.adapters

import androidx.recyclerview.widget.DiffUtil

class ContactsDiffCallback(
    private val oldList: List<ContactAdapterModel>,
    private val newList: List<ContactAdapterModel>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size
}