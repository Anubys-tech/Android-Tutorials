package com.anubys.example.datepickerdialogdemo

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View

import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

import java.text.SimpleDateFormat
import java.util.*

/** @Autor Created by Anubys on the 29.03.2020 */


class MainActivity : AppCompatActivity() {
    private val tag = MainActivity::class.java.simpleName

    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(tag, "TAG - MainActivity - onCreate()")
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_main)
        /*This is implemented by android studio it self when we select the Basic Activity while creating the project.*/
        setSupportActionBar(toolbar)

        // OnClickListener is set to the button for launching the DatePicker Dialog.
        btn_DatePicker.setOnClickListener { view -> clickDataPicker(view) }
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    /**
     * The function to show the DatePicker Dialog.
     */
    fun clickDataPicker(view: View) {
        Log.d(tag, "TAG - MainActivity - onCreate()")

        /**
         * This Gets a calendar using the default time zone and locale.
         * The calender returned is based on the current time
         * in the default time zone with the default.
         */
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR) // Returns the value of the given calendar field. This indicates YEAR
        val month = calendar.get(Calendar.MONTH) // This indicates the Month
        val day = calendar.get(Calendar.DAY_OF_MONTH) // This indicates the Day

        /**
         * Creates a new date picker dialog for the specified date using the parent
         * context's default date picker dialog theme.
         */
        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, _, monthOfYear, dayOfMonth ->
            Log.d(tag, "TAG - MainActivity - DatePickerDialogOnDateSetListener()")

            /**
             * The listener used to indicate the user has finished selecting a date.
             */

            /**
             *Here the selected date is set into format i.e : day/Month/Year
             * And the month is counted in java is 0 to 11 so we need to add +1 so it can be as selected.
             * */
            val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"

            // Selected date it set to the TextView to make it visible to user.
            tv_Selected_Date.text = selectedDate

            /**
             * Here we have taken an instance of Date Formatter as it will format our
             * selected date in the format which we pass it as an parameter and Locale.
             * Here I have passed the format as dd/MM/yyyy.
             */
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.GERMANY)

            // The formatter will parse the selected date in to Date object
            // so we can simply get date in to milliseconds.
            val theDate = simpleDateFormat.parse(selectedDate)

            /** Here we have get the time in milliSeconds from Date object
             * And as we know the formula as milliseconds can be converted to second by dividing it by 1000.
             * And the seconds can be converted to minutes by dividing it by 60.
             * So now in the selected date into minutes.
             */
            val selectedDateToMinutes = theDate!!.time / 60000

            // Here we have parsed the current date with the Date Formatter which is used above.
            val currentDate = simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))
            // Current date in to minutes.
            val currentDateToMinutes = currentDate!!.time / 60000

            /**
             * Now to get the difference into minutes.
             * We will subtract the selectedDateToMinutes from currentDateToMinutes.
             * Which will provide the difference in minutes.
             */

            val differenceInMinutes = currentDateToMinutes - selectedDateToMinutes

            // Set the difference in minutes to textview to show the user.
            tv_Selected_Date_In_Minutes.text = differenceInMinutes.toString()
        }, year, month, day)

        /**
         * Sets the maximal date supported by this in
         * milliseconds since January 1, 1970 00:00:00 in time zone.
         *
         * @param maxDate The maximal supported date.
         */
        // 86400000 is milliseconds of 24 Hours. Which is used to restrict the user to select today and future day.
        datePickerDialog.datePicker.maxDate = Date().time - 86400000
        datePickerDialog.show() // It is used to show the datePicker Dialog.
    }
}
