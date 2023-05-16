package com.example.practicas03.data.model

data class WebBrowserDto(
    val company: String,
    val compatible: List<String>,
    val id: Int,
    val logo: String,
    val mobile: Boolean,
    val name: String,
    val web: String,
    val year: Int
)