package at.htl.leonding.payyours

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var payment: Payment = Payment(0, 0, "");
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item?.itemId) {
            R.id.menu_item_quit -> {
                finish()
                true
            }
            R.id.menu_item_settings -> {
                Log.d("MainActivity", "Setting menu called")
                val intent = Intent(this, SettingsActivity::class.java)
                getResult.launch(intent)
                //(Intent(this, SettingsActivity::class.java), SETTINGS_REQUEST)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    // Receiver
    private val getResult =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            payment = SettingsActivity.getStoredPayment(this)
            updatePaymentTextView()
        }

    private fun updatePaymentTextView() {
        spielerEingabe.setText(payment.players.toString())
        plaetzeEingabe.setText(payment.courts.toString())

    }

}