package com.example.karamat.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.example.karamat.R

class PostFragment : Fragment() {

    private lateinit var btnVolunteer: Button
    private lateinit var btnRequest: Button
    private lateinit var infoBannerTitle: TextView
    private lateinit var infoBannerMessage: TextView
    private lateinit var categoryContainer: LinearLayout
    private lateinit var btnCategoryFood: Button
    private lateinit var btnCategoryMedicine: Button
    private lateinit var btnCategoryOther: Button
    private lateinit var descriptionLabel: TextView
    private lateinit var descriptionInput: TextInputEditText
    private lateinit var blockSpinner: Spinner
    private lateinit var roomNumberInput: TextInputEditText
    private lateinit var extraFieldLabel: TextView
    private lateinit var timeInputLayout: View
    private lateinit var timeInput: TextInputEditText
    private lateinit var urgencyContainer: LinearLayout
    private lateinit var btnUrgencyLow: Button
    private lateinit var btnUrgencyMedium: Button
    private lateinit var btnUrgencyHigh: Button
    private lateinit var btnSubmit: Button

    private var postType = "volunteer" // "volunteer" or "request"
    private var selectedCategory = "food"
    private var selectedUrgency = "medium"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        initializeViews(view)
        setupBlockSpinner()
        setupClickListeners()
        updateUIForPostType()
    }

    private fun initializeViews(view: View) {
        btnVolunteer = view.findViewById(R.id.btnVolunteer)
        btnRequest = view.findViewById(R.id.btnRequest)
        infoBannerTitle = view.findViewById(R.id.infoBannerTitle)
        infoBannerMessage = view.findViewById(R.id.infoBannerMessage)
        categoryContainer = view.findViewById(R.id.categoryContainer)
        btnCategoryFood = view.findViewById(R.id.btnCategoryFood)
        btnCategoryMedicine = view.findViewById(R.id.btnCategoryMedicine)
        btnCategoryOther = view.findViewById(R.id.btnCategoryOther)
        descriptionLabel = view.findViewById(R.id.descriptionLabel)
        descriptionInput = view.findViewById(R.id.descriptionInput)
        blockSpinner = view.findViewById(R.id.blockSpinner)
        roomNumberInput = view.findViewById(R.id.roomNumberInput)
        extraFieldLabel = view.findViewById(R.id.extraFieldLabel)
        timeInputLayout = view.findViewById(R.id.timeInputLayout)
        timeInput = view.findViewById(R.id.timeInput)
        urgencyContainer = view.findViewById(R.id.urgencyContainer)
        btnUrgencyLow = view.findViewById(R.id.btnUrgencyLow)
        btnUrgencyMedium = view.findViewById(R.id.btnUrgencyMedium)
        btnUrgencyHigh = view.findViewById(R.id.btnUrgencyHigh)
        btnSubmit = view.findViewById(R.id.btnSubmit)
    }

    private fun setupBlockSpinner() {
        val blocks = arrayOf("Select Block", "Block A", "Block B", "Block C", "Block D")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, blocks)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        blockSpinner.adapter = adapter
    }

    private fun setupClickListeners() {
        // Post type selection
        btnVolunteer.setOnClickListener {
            postType = "volunteer"
            updateUIForPostType()
        }

        btnRequest.setOnClickListener {
            postType = "request"
            updateUIForPostType()
        }

        // Category selection
        btnCategoryFood.setOnClickListener {
            selectedCategory = "food"
            updateCategoryButtons()
        }

        btnCategoryMedicine.setOnClickListener {
            selectedCategory = "medicine"
            updateCategoryButtons()
        }

        btnCategoryOther.setOnClickListener {
            selectedCategory = "other"
            updateCategoryButtons()
        }

        // Urgency selection
        btnUrgencyLow.setOnClickListener {
            selectedUrgency = "low"
            updateUrgencyButtons()
        }

        btnUrgencyMedium.setOnClickListener {
            selectedUrgency = "medium"
            updateUrgencyButtons()
        }

        btnUrgencyHigh.setOnClickListener {
            selectedUrgency = "high"
            updateUrgencyButtons()
        }

        // Submit button
        btnSubmit.setOnClickListener {
            handleSubmit()
        }
    }

    private fun updateUIForPostType() {
        if (postType == "volunteer") {
            // Update type buttons
            btnVolunteer.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.purple_600)
            btnRequest.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.white)
            btnVolunteer.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            btnRequest.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_600))

            // Update info banner
            infoBannerTitle.text = "Be a Buddy!"
            infoBannerMessage.text = "Let others know you're heading to the gate and can bring their orders"

            // Show/hide fields
            categoryContainer.visibility = View.GONE
            descriptionLabel.text = "Additional Info (Optional)"
            descriptionInput.hint = "e.g., Can carry 3-4 packages"
            extraFieldLabel.text = "Reaching gate in (minutes)"
            timeInputLayout.visibility = View.VISIBLE
            urgencyContainer.visibility = View.GONE

            // Update submit button
            btnSubmit.text = "Start Helping"

        } else {
            // Update type buttons
            btnVolunteer.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.white)
            btnRequest.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.purple_600)
            btnVolunteer.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_600))
            btnRequest.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

            // Update info banner
            infoBannerTitle.text = "Need a Buddy"
            infoBannerMessage.text = "Post your delivery details and someone will help bring it to you"

            // Show/hide fields
            categoryContainer.visibility = View.VISIBLE
            descriptionLabel.text = "Description"
            descriptionInput.hint = "e.g., Zomato - McDonald's"
            extraFieldLabel.text = "Urgency"
            timeInputLayout.visibility = View.GONE
            urgencyContainer.visibility = View.VISIBLE

            // Update submit button
            btnSubmit.text = "Post Request"
        }
    }

    private fun updateCategoryButtons() {
        // Reset all
        btnCategoryFood.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.white)
        btnCategoryMedicine.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.white)
        btnCategoryOther.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.white)

        // Highlight selected
        when (selectedCategory) {
            "food" -> btnCategoryFood.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.orange_50)
            "medicine" -> btnCategoryMedicine.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.red_50)
            "other" -> btnCategoryOther.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.blue_50)
        }
    }

    private fun updateUrgencyButtons() {
        // Reset all
        btnUrgencyLow.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.white)
        btnUrgencyMedium.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.white)
        btnUrgencyHigh.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.white)

        // Highlight selected
        when (selectedUrgency) {
            "low" -> btnUrgencyLow.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.green_100)
            "medium" -> btnUrgencyMedium.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.amber_100)
            "high" -> btnUrgencyHigh.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.red_100)
        }
    }

    private fun handleSubmit() {
        val description = descriptionInput.text.toString()
        val block = blockSpinner.selectedItem.toString()
        val roomNumber = roomNumberInput.text.toString()

        // Validation
        if (block == "Select Block") {
            Toast.makeText(context, "Please select a block", Toast.LENGTH_SHORT).show()
            return
        }

        if (roomNumber.isEmpty()) {
            Toast.makeText(context, "Please enter room number", Toast.LENGTH_SHORT).show()
            return
        }

        // Success message
        val message = if (postType == "volunteer") {
            "Volunteering posted! Others can now see you're going to the gate."
        } else {
            "Request posted! Waiting for a buddy to help."
        }

        Toast.makeText(context, message, Toast.LENGTH_LONG).show()

        // Clear form
        descriptionInput.text?.clear()
        roomNumberInput.text?.clear()
        blockSpinner.setSelection(0)
    }
}