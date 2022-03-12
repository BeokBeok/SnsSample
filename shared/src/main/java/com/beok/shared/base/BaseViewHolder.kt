package com.beok.shared.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.beok.shared.model.ClickAction

class BaseViewHolder<T> private constructor(
    private val binding: ViewDataBinding,
    private val bindingID: Int,
    private val clickAction: ClickAction<T>?
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T?) {
        if (item == null) return
        binding.setVariable(bindingID, item)

        clickAction?.run {
            binding.setVariable(bindingID, action)
        }
        binding.executePendingBindings()
    }

    companion object {
        fun <T> create(
            parent: ViewGroup,
            @LayoutRes layoutResourceID: Int,
            bindingID: Int,
            clickAction: ClickAction<T>?
        ): BaseViewHolder<T> {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<ViewDataBinding>(
                inflater,
                layoutResourceID,
                parent,
                false
            )
            return BaseViewHolder(
                binding = binding,
                bindingID = bindingID,
                clickAction = clickAction
            )
        }
    }
}
