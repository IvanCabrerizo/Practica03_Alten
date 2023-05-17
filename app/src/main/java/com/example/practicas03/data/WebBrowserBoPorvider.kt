package com.example.practicas03.data

import android.util.Log
import com.example.practicas03.data.model.CompatibleOperatingSystems
import com.example.practicas03.data.model.WebBrowserBo
import com.example.practicas03.data.model.WebBrowserDto
import com.example.practicas03.randomList
import com.example.practicas03.toBo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

val browserList = mutableListOf<WebBrowserDto>()

fun transformListWebBrowserBo(): MutableList<WebBrowserBo> {
    val browserListBo = mutableListOf<WebBrowserBo>()
    browserList.map {
        browserListBo.add(it.toBo())
        Log.i("PROBLEMA", browserListBo.toString())
    }
    return browserListBo
}

fun getRetrofit(): Retrofit {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY

    val httpClient = OkHttpClient.Builder()
    httpClient.addInterceptor(interceptor)

    return Retrofit.Builder()
        .baseUrl("https://mocki.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()
}

fun getWebBrowserDto() {
    val retrofit = getRetrofit()
    val webBrowserApiService = retrofit.create(WebBrowserApiService::class.java)
    val call = webBrowserApiService.getWebBrowsers()

    call.enqueue(object : retrofit2.Callback<List<WebBrowserDto>> {
        override fun onResponse(
            call: Call<List<WebBrowserDto>>,
            response: retrofit2.Response<List<WebBrowserDto>>
        ) {
            if (response.isSuccessful) {
                Log.i("SUCCESSFUL", "Correcto")
                response.body()?.forEach { browser ->
                    val newBrowser = WebBrowserDto(
                        name = browser.name,
                        company = browser.company,
                        year = browser.year,
                        logo = browser.logo,
                        web = browser.web,
                        mobile = browser.mobile,
                        compatible = browser.compatible
                    )
                    browserList.add(newBrowser)
                }
            } else {
                Log.e("FALLO", "Petición a la Api Fallida")
            }
        }

        override fun onFailure(call: Call<List<WebBrowserDto>>, t: Throwable) {
            Log.e("FALLO", "Conexion Api Fallido")
        }
    })
}