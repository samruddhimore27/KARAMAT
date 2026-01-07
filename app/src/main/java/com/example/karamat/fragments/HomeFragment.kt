package com.example.karamat.fragments
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.karamat.R
import com.example.karamat.adapters.OrdersAdapter
import com.example.karamat.models.Order

class HomeFragment : Fragment() {

    private lateinit var ordersRecyclerView: RecyclerView
    private lateinit var ordersAdapter: OrdersAdapter
    private lateinit var orderCountText: TextView

    private val allOrders = mutableListOf<Order>()
    private var currentFilter = "all"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        ordersRecyclerView = view.findViewById(R.id.ordersRecyclerView)
        orderCountText = view.findViewById(R.id.orderCountText)

        val btnAllOrders: Button = view.findViewById(R.id.btnAllOrders)
        val btnFood: Button = view.findViewById(R.id.btnFood)
        val btnMedicine: Button = view.findViewById(R.id.btnMedicine)
        val btnOther: Button = view.findViewById(R.id.btnOther)

        // Setup RecyclerView
        ordersRecyclerView.layoutManager = LinearLayoutManager(context)

        // Load mock data
        loadMockOrders()

        // Initialize adapter
        ordersAdapter = OrdersAdapter(allOrders) { order ->
            // Handle order selection
        }
        ordersRecyclerView.adapter = ordersAdapter

        // Update count
        updateOrderCount()

        // Filter button click listeners
        btnAllOrders.setOnClickListener {
            currentFilter = "all"
            filterOrders()
        }

        btnFood.setOnClickListener {
            currentFilter = "food"
            filterOrders()
        }

        btnMedicine.setOnClickListener {
            currentFilter = "medicine"
            filterOrders()
        }

        btnOther.setOnClickListener {
            currentFilter = "other"
            filterOrders()
        }
    }

    private fun loadMockOrders() {
        allOrders.clear()
        allOrders.addAll(
            listOf(
                Order(
                    "1", "Rahul Kumar", "Block A", "203",
                    "food", "Zomato - Burger King", "5 min ago", "medium"
                ),
                Order(
                    "2", "Priya Sharma", "Block B", "415",
                    "medicine", "Pharmacy - Cold medicine", "12 min ago", "high"
                ),
                Order(
                    "3", "Arjun Patel", "Block A", "108",
                    "food", "Swiggy - Pizza Hut", "8 min ago", "low"
                ),
                Order(
                    "4", "Sneha Reddy", "Block C", "301",
                    "other", "Amazon - Books", "15 min ago", "low"
                ),
                Order(
                    "5", "Vikram Singh", "Block B", "512",
                    "food", "Dunzo - Groceries", "3 min ago", "medium"
                )
            )
        )
    }

    private fun filterOrders() {
        val filteredOrders = if (currentFilter == "all") {
            allOrders
        } else {
            allOrders.filter { it.category == currentFilter }
        }

        ordersAdapter = OrdersAdapter(filteredOrders) { order ->
            // Handle selection
        }
        ordersRecyclerView.adapter = ordersAdapter
        updateOrderCount()
    }

    private fun updateOrderCount() {
        val count = if (currentFilter == "all") {
            allOrders.size
        } else {
            allOrders.count { it.category == currentFilter }
        }
        orderCountText.text = count.toString()
    }
}
