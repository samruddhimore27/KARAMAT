package com.example.karamat.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.karamat.R
import com.example.karamat.adapters.ActivityAdapter
import com.example.karamat.models.Activity

class ActivityFragment : Fragment() {

    private lateinit var activityRecyclerView: RecyclerView
    private lateinit var activityAdapter: ActivityAdapter
    private lateinit var btnFilterAll: Button
    private lateinit var btnFilterRequest: Button
    private lateinit var btnFilterVolunteer: Button

    private val allActivities = mutableListOf<Activity>()
    private var currentFilter = "all"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        activityRecyclerView = view.findViewById(R.id.activityRecyclerView)
        btnFilterAll = view.findViewById(R.id.btnFilterAll)
        btnFilterRequest = view.findViewById(R.id.btnFilterRequest)
        btnFilterVolunteer = view.findViewById(R.id.btnFilterVolunteer)

        // Setup RecyclerView
        activityRecyclerView.layoutManager = LinearLayoutManager(context)

        // Load mock data
        loadMockActivities()

        // Initialize adapter
        activityAdapter = ActivityAdapter(allActivities)
        activityRecyclerView.adapter = activityAdapter

        // Setup filter buttons
        setupFilterButtons()
    }

    private fun loadMockActivities() {
        allActivities.clear()
        allActivities.addAll(
            listOf(
                Activity(
                    "1", "volunteer", "completed",
                    "Picked up 3 orders from gate",
                    "Block A", "2 hours ago",
                    "Helped: Rahul K., Priya S., Arjun P."
                ),
                Activity(
                    "2", "request", "completed",
                    "Swiggy - Dominos Pizza",
                    "Block B - Room 415", "5 hours ago",
                    "Helped by: Vikram Singh"
                ),
                Activity(
                    "3", "volunteer", "pending",
                    "Going to gate in 5 mins",
                    "Block C", "Just now",
                    null
                ),
                Activity(
                    "4", "request", "pending",
                    "Amazon - Books",
                    "Block A - Room 203", "10 min ago",
                    null
                ),
                Activity(
                    "5", "request", "cancelled",
                    "Zomato - Burger King",
                    "Block B - Room 512", "Yesterday",
                    null
                )
            )
        )
    }

    private fun setupFilterButtons() {
        btnFilterAll.setOnClickListener {
            currentFilter = "all"
            filterActivities()
            updateFilterButtons()
        }

        btnFilterRequest.setOnClickListener {
            currentFilter = "request"
            filterActivities()
            updateFilterButtons()
        }

        btnFilterVolunteer.setOnClickListener {
            currentFilter = "volunteer"
            filterActivities()
            updateFilterButtons()
        }
    }

    private fun filterActivities() {
        val filteredActivities = if (currentFilter == "all") {
            allActivities
        } else {
            allActivities.filter { it.type == currentFilter }
        }

        activityAdapter = ActivityAdapter(filteredActivities)
        activityRecyclerView.adapter = activityAdapter
    }

    private fun updateFilterButtons() {
        // Reset all
        btnFilterAll.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.white)
        btnFilterRequest.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.white)
        btnFilterVolunteer.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.white)

        btnFilterAll.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_600))
        btnFilterRequest.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_600))
        btnFilterVolunteer.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_600))

        // Highlight selected
        when (currentFilter) {
            "all" -> {
                btnFilterAll.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.green_600)
                btnFilterAll.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
            "request" -> {
                btnFilterRequest.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.green_600)
                btnFilterRequest.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
            "volunteer" -> {
                btnFilterVolunteer.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.green_600)
                btnFilterVolunteer.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            }
        }
    }
}
