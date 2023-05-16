package com.example.practicas03.data

import com.example.practicas03.data.model.CompatibleOperatingSystems
import com.example.practicas03.data.model.WebBrowserBo
import com.example.practicas03.randomList

fun mockBrowser(number: Int): List<WebBrowserBo> {
    /*for (index in 0..number) {
        if (index % 2 == 0) {
            listBrowser.add(
                WebBrowserBo(
                    "Chrome $index",
                    "Google",
                    2000,
                    "https://www.alten.com/wp-content/uploads/2019/03/LOGO_Alten_Couleurs_Black.png",
                    "https://www.alten.es",
                    true,
                    listOf(
                        CompatibleOperatingSystems.ANDROID,
                        CompatibleOperatingSystems.IOS,
                        CompatibleOperatingSystems.LINUX,
                        CompatibleOperatingSystems.MAC,
                        CompatibleOperatingSystems.WINDOWS,
                        CompatibleOperatingSystems.WINDOWS_PHONE,
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
                    "https://www.alten.es",
                    false,
                    listOf(
                        CompatibleOperatingSystems.LINUX,
                        CompatibleOperatingSystems.MAC,
                        CompatibleOperatingSystems.WINDOWS
                    )
                )
            )
        }
    }*/
    return mutableListOf(
        WebBrowserBo(
            "Brave",
            "Google",
            2002,
            "https://www.alten.com/wp-content/uploads/2019/03/LOGO_Alten_Couleurs_Black.png",
            "https://www.alten.es",
            false,
            CompatibleOperatingSystems.values().toList().randomList()
        ),
        WebBrowserBo(
            "Chromium",
            "Google",
            1200,
            "https://www.alten.com/wp-content/uploads/2019/03/LOGO_Alten_Couleurs_Black.png",
            "https://www.alten.es",
            true,
            CompatibleOperatingSystems.values().toList().randomList()
        ),
        WebBrowserBo(
            "Opera",
            "Microsoft",
            2000,
            "https://www.alten.com/wp-content/uploads/2019/03/LOGO_Alten_Couleurs_Black.png",
            "https://www.alten.es",
            false,
            CompatibleOperatingSystems.values().toList().randomList()
        ),
        WebBrowserBo(
            "Internet Explorer",
            "Microsoft",
            1990,
            "https://www.alten.com/wp-content/uploads/2019/03/LOGO_Alten_Couleurs_Black.png",
            "https://www.alten.es",
            true,
            CompatibleOperatingSystems.values().toList().randomList()
        ),
        WebBrowserBo(
            "Mozilla",
            "Google",
            2000,
            "https://www.alten.com/wp-content/uploads/2019/03/LOGO_Alten_Couleurs_Black.png",
            "https://www.alten.es",
            true,
            CompatibleOperatingSystems.values().toList().randomList()
        ),
        WebBrowserBo(
            "Chrome",
            "Google",
            2000,
            "https://www.alten.com/wp-content/uploads/2019/03/LOGO_Alten_Couleurs_Black.png",
            "https://www.alten.es",
            true,
            CompatibleOperatingSystems.values().toList().randomList()
        )
    )
}