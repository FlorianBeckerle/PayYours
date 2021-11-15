package com.example.payyours

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    companion object {
        val LOG_TAG= SettingsActivity::class.java.simpleName
        val PREFERENCES_FILENAME = "PayYoursPreferences"
        val PRICE_PER_UNIT_KEY = "PRICE_PER_UNIT"
        val PLAYERS_KEY = "DEFAULT_PLAYERS"
        val COURTS_KEY = "DEFAULT_COURTS"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        buttonOk.setOnClickListener{ onSave() }
    }

    private fun onSave() {
        finish()
    }
}