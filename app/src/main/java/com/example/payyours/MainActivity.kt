package com.example.payyours

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item?.itemId){
            R.id.menu_item_quit -> {
                finish()
                true
            }
            R.id.menu_item_settings ->{
                Log.d("MainActivity", "Settings menu called")
                val intent = Intent(Context, SettingsActivity::class.java)
                getResult.launch(intent)
                //registerActivit(Intent(this, SettingsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //Reciver
    private val getResult =
            registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) {
                payment = SettingsActivity.getStoredPayment(this)
                if(it.resultCode == Activity.RESULT_OK) {
                    Log.d("", "test")
                }
            }


}