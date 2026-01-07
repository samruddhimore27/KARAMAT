package com.example.karamat.models

data class Activity(
    val id: String,
    val type: String, // "volunteer" or "request"
    val status: String, // "completed", "pending", "cancelled"
    val description: String,
    val location: String,
    val time: String,
    val extraInfo: String? = null // Optional helper info
)