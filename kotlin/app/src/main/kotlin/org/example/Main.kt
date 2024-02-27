package org.example

import okhttp3.OkHttpClient


fun main() {
    CreateForm(
        typeformBaseUrl = "https://api.typeform.com",
        typeformAccessToken = "tfp_Dtx5hra9N1f8Px7Qj4y7x26n2a4MwprKCLgnG3i9vexV_3pf167qQXfiVm5",
        typeformWorkspace = "uRTUs6",
        okHttpClient = OkHttpClient()
    ).execute()
}
