package com.example.karamat.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.karamat.R
import com.example.karamat.models.Activity

class ActivityAdapter(
    private val activities: List<Activity>
) : RecyclerView.Adapter<ActivityAdapter.ActivityViewHolder>() {

    class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val activityIcon: ImageView = itemView.findViewById(R.id.activityIcon)
        val activityIconContainer: View = itemView.findViewById(R.id.activityIconContainer)
        val activityTypeText: TextView = itemView.findViewById(R.id.activityTypeText)
        val activityDescriptionText: TextView = itemView.findViewById(R.id.activityDescriptionText)
        val activityLocationText: TextView = itemView.findViewById(R.id.activityLocationText)
        val activityTimeText: TextView = itemView.findViewById(R.id.activityTimeText)
        val activityStatusBadge: TextView = itemView.findViewById(R.id.activityStatusBadge)
        val activityExtraInfo: TextView = itemView.findViewById(R.id.activityExtraInfo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_activity_card, parent, false)
        return ActivityViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        val activity = activities[position]
        val context = holder.itemView.context

        // Set type and icon
        if (activity.type == "volunteer") {
            holder.activityTypeText.text = "Volunteered"
            holder.activityIcon.setImageResource(R.drawable.ic_help)
            holder.activityIcon.setColorFilter(
                ContextCompat.getColor(context, R.color.green_700)
            )
        } else {
            holder.activityTypeText.text = "Requested"
            holder.activityIcon.setImageResource(R.drawable.ic_package)
            holder.activityIcon.setColorFilter(
                ContextCompat.getColor(context, R.color.blue_500)
            )
        }

        // Set status badge
        when (activity.status) {
            "completed" -> {
                holder.activityStatusBadge.text = "Completed"
                holder.activityStatusBadge.setBackgroundResource(R.drawable.status_completed_bg)
                holder.activityStatusBadge.setTextColor(
                    ContextCompat.getColor(context, R.color.green_700)
                )
            }
            "pending" -> {
                holder.activityStatusBadge.text = "Pending"
                holder.activityStatusBadge.setBackgroundResource(R.drawable.status_pending_bg)
                holder.activityStatusBadge.setTextColor(
                    ContextCompat.getColor(context, R.color.amber_700)
                )
            }
            else -> {
                holder.activityStatusBadge.text = "Cancelled"
                holder.activityStatusBadge.setTextColor(
                    ContextCompat.getColor(context, R.color.gray_500)
                )
            }
        }

        // Set other data
        holder.activityDescriptionText.text = activity.description
        holder.activityLocationText.text = activity.location
        holder.activityTimeText.text = activity.time

        // Show extra info if available
        if (activity.extraInfo != null) {
            holder.activityExtraInfo.visibility = View.VISIBLE
            holder.activityExtraInfo.text = activity.extraInfo
        } else {
            holder.activityExtraInfo.visibility = View.GONE
        }
    }

    override fun getItemCount() = activities.size
}