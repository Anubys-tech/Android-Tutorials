package com.anubys.example.customstyledtoastdemo

/** @Author Created by Anubys on the 24.01.2021 */

import android.app.Activity
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast


fun Toast.showCustomToast(message: String, activity: Activity) {
    val layout = activity.layoutInflater.inflate (R.layout.layout_custom_toast, activity.findViewById(R.id.toast_container))

    // set the text of the TextView of the message
    val textView = layout.findViewById<TextView>(R.id.tv_toast_message)
    textView.text = message

    // use the application extension function
    this.apply {
        setGravity(Gravity.CENTER, 0, 0)
        duration = Toast.LENGTH_LONG
        view = layout
        show()
    }
}
