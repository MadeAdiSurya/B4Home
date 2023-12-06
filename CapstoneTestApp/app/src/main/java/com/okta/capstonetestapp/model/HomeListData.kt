package com.okta.capstonetestapp.model

data class HomeListData(val pageName: String, val imageUrl: String, val title: String, val description: String)

val HomeList = listOf(
    HomeListData(
        "PerkiraanForm",
        "https://th.bing.com/th/id/OIP.k-ckp0YD1SWZWwKt3_AT8AHaE8?rs=1&pid=ImgDetMain",
        "Cari Tau Kapan Uang Anda Cukup Buat Beli Rumah",
        "Cari tau estimasi harga rumah sesuai tabungan, target tahun, dan spesifikasi rumah"
    ),
    HomeListData(
        "TipeForm",
        "https://media.istockphoto.com/photos/latin-american-business-man-isolated-on-blue-background-picture-id669601126?k=6&m=669601126&s=170667a&w=0&h=ui_JURwx_m1v5q4ayJZsxg9dK7FW3ny7SHOWy9dbfNE=",
        "Cari Tau Rumah yang Cocok Buat Anda",
        "Cari tipe rumah sesuai tabungan dan target anda"
    ),
    HomeListData(
        "EstimasiForm",
        "https://th.bing.com/th/id/OIP.k-ckp0YD1SWZWwKt3_AT8AHaE8?rs=1&pid=ImgDetMain",
        "Cari Tau Cara Menabung Untuk Membeli Rumah",
        "Cari target menabung sesuai dengan preferensi rumah dan target tahun anda"
    )
)