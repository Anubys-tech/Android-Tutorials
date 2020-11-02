package com.anubys.example.sharebuttondemo

/** @Author Created by Anubys on the 22.10.2020 */

import android.app.Activity
import android.util.Log

import androidx.core.app.ShareCompat


class Share (var activity: Activity) {
    private val tagFragment = Share::class.java.simpleName

    init {
        Log.d(tagFragment, "TAG - Share - init()")
        initSharingProgress()
    }

    //TODO noch Anpassen, sobald es im PlayStore ist
    private fun initSharingProgress() {
        Log.d(tagFragment, "TAG - Share - initSharingProgress()")

        ShareCompat.IntentBuilder.from(activity)
            .setType("text/*")
            .setChooserTitle(activity.resources.getString(R.string.app_name))
            .setText(activity.resources.getString(R.string.text_share_content) + "\n\n" + activity.resources.getString(R.string.text_share_link) + activity.packageName)
            .startChooser()
    }
}
