package com.example.practicas03.data.model

enum class CompatibleOperatingSystems(val operatingSystem: String) {
    WINDOWS("Windows"),
    MAC("Mac OS"),
    LINUX("Linux"),
    ANDROID("Android"),
    IOS("iOS"),
    WINDOWS_PHONE("Windows Phone"),
}

data class WebBrowserBo(
    val name: String,
    val company: String,
    val year: Int,
    val logo: String,
    val web: String,
    val mobile: Boolean,
    val compatible: List<CompatibleOperatingSystems?>?,
)