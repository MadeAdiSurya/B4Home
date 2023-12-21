package com.okta.capstonetestapp.model

data class HomeListData(val pageName: String, val imageUrl: String, val title: String, val description: String)

val HomeList = listOf(
    HomeListData(
        "PerkiraanForm",
        "https://lh3.googleusercontent.com/drive-viewer/AEYmBYR-I__fiNZsy7Mx1D0V0-GJjJOQTw9Btsvb5EXNHPBeVuF0--TGVslZpn_7gKakPcYBKQaGPsKCtckZbmJnacOWmyyD=s1600",
        "Find Out When You Have Enough Money to Buy a House",
        "Find out the estimated house price according to your types and target year."
    ),
    HomeListData(
        "TipeForm",
        "https://lh3.googleusercontent.com/drive-viewer/AEYmBYTSv34wtYeqRSPv95WCXoZRF2P3Zrx8MTB8V5AG-L3jZ9BHydXOstwiZoCkQ9oYlnZAbtRPYANXzOSRoXilzaGElcrKLw=s1600",
        "Find Out Which House is The Right One For You",
        "Find the type of house that suits your savings and target"
    ),
    HomeListData(
        "KprForm",
        "https://lh3.googleusercontent.com/drive-viewer/AEYmBYTbfcPu1MyJT5NWyBwjbU-8ZX1fb9VuhbRD-iE73uZg6l4Va0X1N5x1dsdFd3Ho3TfpSawZl8pDM-EmGePVS7q2oGULWg=s1600",
        "Find Out About KPR Interest",
        "Find the amount for the KPR you need to pay"
    ),
)