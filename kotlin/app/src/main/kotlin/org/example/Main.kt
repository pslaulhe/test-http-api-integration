package org.example

import okhttp3.OkHttpClient


fun main() {
    CreateForm(
        typeformBaseUrl = "https://api.typeform.com",
        typeformAccessToken = "YOUR_ACCESS_TOKEN",
        typeformWorkspace = "WORKSPACE_ID",
        okHttpClient = OkHttpClient()
    ).execute().fold(
        { createFormFailure -> println(createFormFailure) },
        { formId -> println(formId) }
    )
}
