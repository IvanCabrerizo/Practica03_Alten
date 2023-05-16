package com.example.practicas03.data.model

data class WebBrowserDto(
    val name: String? = "Unknown name",
    val company: String? = "Unknown company",
    val year: Int? = 0,
    val logo: String? = "https://www.redplanet.es/wp-content/uploads/2022/03/estatus-404-not-found.jpg",
    val web: String? = "https://www.google.com",
    val mobile: Boolean? = false,
    val compatible: List<String?>? = emptyList(),
)
