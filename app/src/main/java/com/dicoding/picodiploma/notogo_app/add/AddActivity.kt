package com.dicoding.picodiploma.notogo_app.add

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.notogo_app.*
import com.dicoding.picodiploma.notogo_app.add.category.CustomDialogFragment
import com.dicoding.picodiploma.notogo_app.add.location.LocationActivity
import com.dicoding.picodiploma.notogo_app.add.utils.AlarmManager
import com.dicoding.picodiploma.notogo_app.add.utils.DatePickerFragment
import com.dicoding.picodiploma.notogo_app.add.utils.TimePickerFragment
import com.dicoding.picodiploma.notogo_app.databinding.ActivityAddBinding
import com.dicoding.picodiploma.notogo_app.model.response.AddGoalResponse
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

class AddActivity: AppCompatActivity(), View.OnClickListener, DatePickerFragment.DialogDateListener, TimePickerFragment.DialogTimeListener {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    private lateinit var binding: ActivityAddBinding
    private lateinit var tokenViewModel: TokenViewModel
    private lateinit var fillEditText: TextInputEditText
    private lateinit var alarmReceiver: AlarmManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAction()
        setupViewModel()
        showDatePicker()
        fillLocation()

        // val location = intent.getStringExtra(EXTRA_LOCATION)
        // val locationId = intent.getIntExtra(EXTRA_LOCATION_ID, 0)

        // Create JSON Object
        binding.btnAddGoal.setOnClickListener {

        }

        // Listener one time alarm
        binding.btnOnceDate.setOnClickListener(this)
        binding.btnOnceTime.setOnClickListener(this)

        alarmReceiver = AlarmManager()
    }

    // Fill Location
    private fun fillLocation() {
        val location = intent.getStringExtra(EXTRA_LOCATION)

        fillEditText = findViewById(R.id.et_location)
        fillEditText.setText(location)
    }

        private fun setupViewModel() {
        tokenViewModel = ViewModelProvider(
            this,
            ViewModelFactory(TokenPreference.getInstance(dataStore))
        )[TokenViewModel::class.java]
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
            startActivity(Intent(this, LocationActivity::class.java))
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

        // date formatter
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())

        // Set text dari textview once
        binding.tvOnceDate.text = dateFormat.format(calendar.time)
    }

    override fun onDialogTimeSet(tag: String?, hourOfDay: Int, minute: Int) {

        // time formatter terlebih dahulu
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
        fillEditText = findViewById(R.id.et_date)
        // DatePicker
        fillEditText.setText(SimpleDateFormat("dd MMMM yyyy").format(System.currentTimeMillis()))

        val cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd MMMM yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            fillEditText.setText(sdf.format(cal.time))
        }

        fillEditText.setOnClickListener {

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
        const val EXTRA_LOCATION = "extra_location"
        const val EXTRA_LOCATION_ID = "extra_location_id"
        const val TAG = "add"
    }


}