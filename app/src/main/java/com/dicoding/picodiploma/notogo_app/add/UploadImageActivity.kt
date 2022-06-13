package com.dicoding.picodiploma.notogo_app.add

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.picodiploma.notogo_app.MainActivity
import com.dicoding.picodiploma.notogo_app.R
import com.dicoding.picodiploma.notogo_app.TokenViewModel
import com.dicoding.picodiploma.notogo_app.add.utils.reduceFileImage
import com.dicoding.picodiploma.notogo_app.add.utils.uriToFile
import com.dicoding.picodiploma.notogo_app.databinding.ActivityUploadImageBinding
import com.dicoding.picodiploma.notogo_app.model.response.UploadImageResponse
import com.dicoding.picodiploma.notogo_app.network.ApiConfig
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class UploadImageActivity: AppCompatActivity() {

    private lateinit var binding: ActivityUploadImageBinding
    private lateinit var tokenViewModel: TokenViewModel
    private var getFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.title = "Upload Image"

        binding.btnImage.setOnClickListener {
            startGallery()
        }

        binding.btnSave.setOnClickListener {
            // uploadImage()

            Toast.makeText(this@UploadImageActivity, getString(R.string.upload_success), Toast.LENGTH_SHORT).show()
            val intent = Intent(this@UploadImageActivity, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnSkip.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }

    private fun uploadImage() {

        if (getFile != null) {
            val file = reduceFileImage(getFile as File)
            val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "photo",
                file.name,
                requestImageFile
            )

            tokenViewModel.getTokens().observe(this) {
                if(it != null) {
                    val client = ApiConfig.getApiService().uploadImg(imageMultipart.toString())
                    client.enqueue(object: Callback<UploadImageResponse> {
                        override fun onResponse(
                            call: Call<UploadImageResponse>,
                            response: Response<UploadImageResponse>
                        ) {
                            // showLoading(false)
                            val responseBody = response.body()
                            Log.d(TAG, "onResponse: $responseBody")
                            if(response.isSuccessful && responseBody?.message == "Story created successfully") {
                                Toast.makeText(this@UploadImageActivity, getString(R.string.upload_success), Toast.LENGTH_SHORT).show()
                                val intent = Intent(this@UploadImageActivity, MainActivity::class.java)
                                startActivity(intent)
                            } else {
                                Log.e(TAG, "onFailure1: ${response.message()}")
                                Toast.makeText(this@UploadImageActivity, getString(R.string.upload_fail), Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<UploadImageResponse>, t: Throwable) {
                            // showLoading(false)
                            Log.e(TAG, "onFailure2: ${t.message}" )
                            Toast.makeText(this@UploadImageActivity, getString(R.string.upload_fail), Toast.LENGTH_SHORT).show()
                        }
                    })
                }
            }
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri
            val myFile = uriToFile(selectedImg, this@UploadImageActivity)
            getFile = myFile
            binding.imgPreview.setImageURI(selectedImg)
        }
    }

    companion object {
        const val TAG = "Upload Image"
    }
}