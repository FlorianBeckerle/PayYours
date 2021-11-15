package com.example.payyours

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_settings.*
import java.text.DecimalFormat
import java.util.*

class SettingsActivity : AppCompatActivity() {

    companion object {

        val LOG_TAG= SettingsActivity::class.java.simpleName
        val PREFERENCES_FILENAME = "PayYoursPreferences"
        val PRICE_PER_UNIT_KEY = "PRICE_PER_UNIT"
        val PLAYERS_KEY = "DEFAULT_PLAYERS"
        val COURTS_KEY = "DEFAULT_COURTS"

        private const val DEFAULT_PRICE_PER_UNIT = "9.1"
        private const val DEFAULT_PLAYERS = 5
        private const val DEFAULT_COURTS = 5
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        buttonOk.setOnClickListener{ onSave() }
        val payment = getStoredPayment(this)
        editTextPreisJePlatz.setText("${payment.pricePerUnitText}")
        editTextPlayers.setText("${payment.players}")
        editTextCourts.setText("${payment.courts}")
    }

    private fun onSave() {
        val decimalFormat = DecimalFormat.getInstance(Locale.getDefault())
        val pricePerUnit = decimalFormat.parse(this.editTextPreisJePlatz.text.toString()).toString()
        val players = decimalFormat.parse(this.editTextSpieler.text.toString()).toInt()
        val courts = decimalFormat.parse(this.editTextCourts.text.toString()).toInt()

        val preferences =
            this.applicationContext.getSharedPreferences(PREFERENCES_FILENAME, Context.MODE_PRIVATE).edit()
        preferences.putString(PRICE_PER_UNIT_KEY, pricePerUnit)
        preferences.putInt(PLAYERS_KEY, players)
        preferences.putInt(COURTS_KEY, courts)

        preferences.commit()

        finish()

    }

    fun getStoredPayment(context: Context) :Payment {
        val preferences = context.applicationContext.getSharedPreferences(PREFERENCES_FILENAME, Context.MODE_PRIVATE)
        val payment = Payment(
            preferences.getInt(COURTS_KEY, DEFAULT_COURTS),
            preferences.getInt(PLAYERS_KEY, DEFAULT_PLAYERS),
            preferences.getString(PRICE_PER_UNIT_KEY, DEFAULT_PRICE_PER_UNIT).toString()
        )
        Log.e(LOG_TAG, "getStoredPayment")
        return payment
    }
}