package ru.movchinnikov.contacts.ui.main.contacts.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.contact_item.view.*
import ru.movchinnikov.contacts.R

class ContactsAdapter(
    private val clickListener: (Long) -> Unit
) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    private val items: MutableList<ContactAdapterModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.contact_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun addItems(newItems: List<ContactAdapterModel>) {
        val oldItems = items.toList()
        items.clear()
        items.addAll(newItems)
        items.sortBy { it.fullName }
        val diff = DiffUtil.calculateDiff(ContactsDiffCallback(oldItems, items))
        diff.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(contact: ContactAdapterModel) {
            containerView.fullname_textview.text = contact.fullName
            containerView.contact_imageview.visibility = if (contact.isContact) View.VISIBLE else View.GONE
            if (contact.isContact.not()) {
                containerView.setOnClickListener {
                    clickListener(contact.id)
                }
            }
        }
    }
}