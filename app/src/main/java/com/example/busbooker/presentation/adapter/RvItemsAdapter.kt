package com.example.busbooker.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.busbooker.R
import com.example.busbooker.databinding.RvItemBusAdBinding
import com.example.busbooker.App
import com.example.busbooker.presentation.model.RouteItemPresentation
import com.example.busbooker.util.ResourceProvider
import com.squareup.picasso.Picasso
import java.time.LocalDate
import javax.inject.Inject

class RvItemsAdapter(private val mListener: ClickListener) :
    RecyclerView.Adapter<RvItemsAdapter.CategoryHolder>() {

    private val rvItems = mutableListOf<RouteItemPresentation>()

    class CategoryHolder(view: View) : RecyclerView.ViewHolder(view) {

        @Inject
        lateinit var resourceProvider: ResourceProvider

        private val mBinding = RvItemBusAdBinding.bind(view)

        init {
            App.instance.appComponent.inject(this)
        }

        fun bind(item: RouteItemPresentation, listener: ClickListener) = with(mBinding) {

            Picasso.get()
                .load(item.imageLink)
                .placeholder(R.drawable.im_flat_placeholder)
                .into(riv)

            tvRoute.text = item.route
            tvPrice.text = item.price.toString()
            tvTime.text = item.time
            tvDate.text = LocalDate.ofEpochDay(item.dateInDays.toLong()).toString()

            itemView.setOnClickListener {
                listener.onClickRvItem(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item_bus_ad, parent, false)
        return CategoryHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(rvItems[position], mListener)
    }

    override fun getItemCount(): Int {
        return rvItems.size
    }

    fun addHouseItems(categoriesToAdd: List<RouteItemPresentation>) {
        rvItems.clear()
        rvItems.addAll(categoriesToAdd)
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onClickRvItem(item : RouteItemPresentation)
    }
}