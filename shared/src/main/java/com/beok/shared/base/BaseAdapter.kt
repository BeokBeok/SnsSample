package com.beok.shared.base

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.beok.shared.model.ClickAction

class BaseAdapter<T>(
    @LayoutRes private val layoutResourceID: Int,
    private val bindingID: Int,
    private val clickAction: ClickAction<T>? = null
) : RecyclerView.Adapter<BaseViewHolder<T>>() {

    private val itemGroup = mutableListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> =
        BaseViewHolder.create(
            parent = parent,
            layoutResourceID = layoutResourceID,
            bindingID = bindingID,
            clickAction = clickAction
        )

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(item = itemGroup[position])
    }

    override fun getItemCount(): Int = itemGroup.size

    @SuppressLint("NotifyDataSetChanged")
    fun replaceItems(items: List<T>) {
        itemGroup.run {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }
}
