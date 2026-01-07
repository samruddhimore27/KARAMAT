package com.example.karamat.models
data class Order(
    val id: String,
    val studentName: String,
    val hostelBlock: String,
    val roomNumber: String,
    val category: String, // "food", "medicine", "other"
    val description: String,
    val timePosted: String,
    val urgency: String // "low", "medium", "high"
)

