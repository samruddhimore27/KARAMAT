package com.example.karamat.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.karamat.R
import com.example.karamat.models.Order


class OrdersAdapter (
    private val orders: List<Order>,
    private val onOrderClick: (Order) -> Unit
) : RecyclerView.Adapter<OrdersAdapter.OrderViewHolder>(){
    private val selectedOrders = mutableSetOf<String>()

    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryIcon: ImageView = itemView.findViewById(R.id.categoryIcon)
        val categoryIconContainer: View = itemView.findViewById(R.id.categoryIconContainer)
        val studentNameText: TextView = itemView.findViewById(R.id.studentNameText)
        val descriptionText: TextView = itemView.findViewById(R.id.descriptionText)
        val locationText: TextView = itemView.findViewById(R.id.locationText)
        val timeText: TextView = itemView.findViewById(R.id.timeText)
        val urgencyBadge: TextView = itemView.findViewById(R.id.urgencyBadge)
        val selectCheckbox: CheckBox = itemView.findViewById(R.id.selectCheckbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_order_card, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        val context = holder.itemView.context

        // Set text data
        holder.studentNameText.text = order.studentName
        holder.descriptionText.text = order.description
        holder.locationText.text = "${order.hostelBlock} - ${order.roomNumber}"
        holder.timeText.text = order.timePosted

        // Set category icon
        when (order.category) {
            "food" -> {
                holder.categoryIcon.setImageResource(R.drawable.ic_food)
                holder.categoryIcon.setColorFilter(
                    ContextCompat.getColor(context, R.color.orange_500)
                )
            }
            "medicine" -> {
                holder.categoryIcon.setImageResource(R.drawable.ic_medicine)
                holder.categoryIcon.setColorFilter(
                    ContextCompat.getColor(context, R.color.red_700)
                )
            }
            else -> {
                holder.categoryIcon.setImageResource(R.drawable.ic_shopping)
                holder.categoryIcon.setColorFilter(
                    ContextCompat.getColor(context, R.color.blue_500)
                )
            }
        }

        // Set urgency badge
        holder.urgencyBadge.text = order.urgency.capitalize()
        when (order.urgency) {
            "high" -> {
                holder.urgencyBadge.setBackgroundResource(R.drawable.urgency_badge)
                holder.urgencyBadge.setTextColor(
                    ContextCompat.getColor(context, R.color.red_700)
                )
            }
            "medium" -> {
                holder.urgencyBadge.setTextColor(
                    ContextCompat.getColor(context, R.color.amber_700)
                )
            }
            else -> {
                holder.urgencyBadge.setTextColor(
                    ContextCompat.getColor(context, R.color.green_700_text)
                )
            }
        }

        // Checkbox state
        holder.selectCheckbox.isChecked = selectedOrders.contains(order.id)

        // Click listeners
        holder.itemView.setOnClickListener {
            if (selectedOrders.contains(order.id)) {
                selectedOrders.remove(order.id)
            } else {
                selectedOrders.add(order.id)
            }
            holder.selectCheckbox.isChecked = selectedOrders.contains(order.id)
            onOrderClick(order)
        }

        holder.selectCheckbox.setOnClickListener {
            if (selectedOrders.contains(order.id)) {
                selectedOrders.remove(order.id)
            } else {
                selectedOrders.add(order.id)
            }
            onOrderClick(order)
        }
    }

    override fun getItemCount() = orders.size

    fun getSelectedCount() = selectedOrders.size


}