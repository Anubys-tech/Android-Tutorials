package com.anubys.example.countdowntimerapp

import android.os.Bundle

import androidx.preference.PreferenceFragmentCompat

/** @Author Created by Anubys on the 09.05.2020 */


class SettingsActivityFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
    }
}
