package com.example.practicas03.data.model

data class WebBrowserDto(
    val name: String,
    val company: String,
    val year: Int,
    val logo: String,
    val web: String,
    val mobile: Boolean,
    val compatible: List<String>,
)