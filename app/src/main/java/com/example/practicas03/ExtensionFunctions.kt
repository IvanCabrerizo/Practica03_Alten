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
    val result = WebBrowserBo(
        this.name.toString(),
        this.company.toString(),

    )
}
