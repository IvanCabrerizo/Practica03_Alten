package com.example.practicas03.data

import com.example.practicas03.data.model.CompatibleOperatingSystems
import com.example.practicas03.data.model.WebBrowserBo

fun mockBrowser(number: Int): MutableList<WebBrowserBo> {
    val listBrowser: MutableList<WebBrowserBo> = mutableListOf()
    for (index in 0..number) {
        if (index % 2 == 0) {
            listBrowser.add(
                WebBrowserBo(
                    "Chrome $index",
                    "Google",
                    2000,
                    "https://www.alten.com/wp-content/uploads/2019/03/LOGO_Alten_Couleurs_Black.png",
                    "www.alten.es",
                    true,
                    listOf(
                        CompatibleOperatingSystems.ANDROID,
                        CompatibleOperatingSystems.IOS,
                        CompatibleOperatingSystems.LINUX,
                        CompatibleOperatingSystems.MAC,
                        CompatibleOperatingSystems.WINDOWS
                    )
                )
            )
        } else {
            listBrowser.add(
                WebBrowserBo(
                    "Chrome $index",
                    "Google",
                    1990,
                    "https://www.alten.com/wp-content/uploads/2019/03/LOGO_Alten_Couleurs_Black.png",
                    "www.alten.es",
                    false,
                    listOf(
                        CompatibleOperatingSystems.LINUX,
                        CompatibleOperatingSystems.MAC,
                        CompatibleOperatingSystems.WINDOWS
                    )
                )
            )
        }
    }
    return listBrowser
}