package com.example.practicas03

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.practicas03.data.model.CompatibleOperatingSystems
import com.example.practicas03.data.model.WebBrowserBo
import com.example.practicas03.data.model.WebBrowserDto
import kotlin.random.Random

fun ImageView.imageUrl(url: String) {
    Glide.with(this).load(url).into(this)
}

fun List<CompatibleOperatingSystems>.randomList(): List<CompatibleOperatingSystems> {
    var size = Random.nextInt(this.size)
    val randomItems = mutableListOf<CompatibleOperatingSystems>()
    while (size > 0) {
        val nextRandom = this.random()
        if (!randomItems.contains(nextRandom)) {
            randomItems.add(nextRandom)
            size--
        }
    }
    return randomItems.toList()
}

fun WebBrowserDto.toBo(): WebBrowserBo {
    val compatibleList = this.compatible?.map { when(it){
        "Windows" -> CompatibleOperatingSystems.WINDOWS
        "MACOS" -> CompatibleOperatingSystems.MAC
        "LINUX" -> CompatibleOperatingSystems.LINUX
        "ANDROID" -> CompatibleOperatingSystems.ANDROID
        "IOS" -> CompatibleOperatingSystems.IOS
        else -> null
        }
    } ?: emptyList()

    return WebBrowserBo(
        this.name ?: "Unknown name",
        this.company ?: "Unknown company",
        this.year ?: 0,
        this.logo ?: "https://www.redplanet.es/wp-content/uploads/2022/03/estatus-404-not-found.jpg",
        this.web ?: "https://www.google.com",
        this.mobile ?: false,
        compatibleList
    )
}
