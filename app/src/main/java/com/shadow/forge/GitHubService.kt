// Responsibility: Atomic utility to upload images to GitHub repository using REST API.
package com.shadow.forge

import android.util.Base64
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.File
import java.io.IOException

object GitHubService {

    private const val GITHUB_TOKEN = BuildConfig.GH_TOKEN

    private const val REPO_OWNER = "Shadow-use"
    private const val REPO_NAME = "forge-assets"

    private val client = OkHttpClient()

    // Responsibility: Uploading a file by converting it to Base64 and sending a PUT request.
    fun uploadArtifact(file: File, callback: (Boolean, String) -> Unit) {
        val fileBytes = file.readBytes()
        val contentBase64 = Base64.encodeToString(fileBytes, Base64.NO_WRAP)
        
        val url = "https://api.github.com/repos/$REPO_OWNER/$REPO_NAME/contents/${file.name}"
        
        val json = JSONObject().apply {
            put("message", "Forge: Create artifact ${file.name}")
            put("content", contentBase64)
        }

        val body = json.toString().toRequestBody("application/json".toMediaTypeOrNull())
        
        val request = Request.Builder()
            .url(url)
            .addHeader("Authorization", "token $GITHUB_TOKEN")
            .put(body)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(false, e.message ?: "Unknown error")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    callback(true, "Сяйво передано на вівтар!")
                } else {
                    val code = response.code
                    callback(false, "Помилка сервера ($code)")
                }
            }
        })
    }
}
