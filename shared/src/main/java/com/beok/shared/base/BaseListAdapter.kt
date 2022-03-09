package com.beok.shared.base

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.beok.shared.model.ClickAction

class BaseListAdapter<T>(
    @LayoutRes private val layoutResourceID: Int,
    private val bindingID: Int,
    private val clickAction: ClickAction<T>? = null,
    diffUtil: DiffUtil.ItemCallback<T>
) : ListAdapter<T, BaseViewHolder<T>>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> =
        BaseViewHolder.create(
            parent = parent,
            layoutResourceID = layoutResourceID,
            bindingID = bindingID,
            clickAction = clickAction
        )

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(item = getItem(position))
    }
}
