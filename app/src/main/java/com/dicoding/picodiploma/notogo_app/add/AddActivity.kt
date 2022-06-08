package com.dicoding.picodiploma.notogo_app.add

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.dicoding.picodiploma.notogo_app.R
import com.dicoding.picodiploma.notogo_app.add.utils.AlarmManager
import com.dicoding.picodiploma.notogo_app.add.utils.DatePickerFragment
import com.dicoding.picodiploma.notogo_app.add.utils.TimePickerFragment
import com.dicoding.picodiploma.notogo_app.add.utils.uriToFile
import com.dicoding.picodiploma.notogo_app.databinding.ActivityAddBinding
import com.google.android.material.textfield.TextInputEditText
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class AddActivity: AppCompatActivity(), View.OnClickListener, DatePickerFragment.DialogDateListener, TimePickerFragment.DialogTimeListener {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    private lateinit var binding: ActivityAddBinding
    private lateinit var billDateEditText: TextInputEditText
    private lateinit var alarmReceiver: AlarmManager
    private var getFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnImage.setOnClickListener {
            startGallery()
        }

        setupAction()

        binding.etBudget.doOnTextChanged{text, start, before, count ->
            if(text!!.length > 10){
                binding.layoutBudget.error = "No More!"
            } else {
                binding.layoutBudget.error = null
            }
        }


        billDateEditText = findViewById(R.id.et_date)

        showDatePicker()

        // Listener one time alarm
        binding.btnOnceDate.setOnClickListener(this)
        binding.btnOnceTime.setOnClickListener(this)

        alarmReceiver = AlarmManager()
    }

    // Option Fill Form
    private fun setupAction() {
        //Fill Category
        binding.layoutCategory.setOnClickListener {
            val dialog = CustomDialogFragment()

            dialog.show(supportFragmentManager, "customDialog")
        }

        // Fill Location
        binding.layoutLocation.setOnClickListener {
            startActivity(Intent(this, SearchLocationActivity::class.java))
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
            val myFile = uriToFile(selectedImg, this@AddActivity)
            getFile = myFile
            binding.imgPreview.setImageURI(selectedImg)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_once_date -> {
                val datePickerFragment = DatePickerFragment()
                datePickerFragment.show(supportFragmentManager, DATE_PICKER_TAG)
            }
            R.id.btn_once_time -> {
                val timePickerFragmentOne = TimePickerFragment()
                timePickerFragmentOne.show(supportFragmentManager, TIME_PICKER_ONCE_TAG)
            }
        }
    }

    override fun onDialogDateSet(tag: String?, year: Int, month: Int, dayOfMonth: Int) {

        // Siapkan date formatter-nya terlebih dahulu
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())

        // Set text dari textview once
        binding.tvOnceDate.text = dateFormat.format(calendar.time)
    }

    override fun onDialogTimeSet(tag: String?, hourOfDay: Int, minute: Int) {

        // Siapkan time formatter-nya terlebih dahulu
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)

        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        // Set text dari textview berdasarkan tag
        when (tag) {
            TIME_PICKER_ONCE_TAG -> binding.tvOnceTime.text = dateFormat.format(calendar.time)
            else -> {
            }
        }
    }

    private fun showDatePicker() {
        // DatePicker
        billDateEditText.setText(SimpleDateFormat("dd MMMM yyyy").format(System.currentTimeMillis()))

        val cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd MMMM yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            billDateEditText.setText(sdf.format(cal.time))
        }

        billDateEditText.setOnClickListener {

            Log.d("Clicked", "Interview Date Clicked")

            val dialog = DatePickerDialog(this, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH))
            dialog.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {
        private const val DATE_PICKER_TAG = "DatePicker"
        private const val TIME_PICKER_ONCE_TAG = "TimePickerOnce"
    }


}