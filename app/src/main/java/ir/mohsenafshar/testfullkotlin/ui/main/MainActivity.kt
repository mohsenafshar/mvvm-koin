package ir.mohsenafshar.testfullkotlin.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ir.mohsenafshar.testfullkotlin.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = viewModel.getUser()

        Toast.makeText(this, "$user", Toast.LENGTH_LONG).show()
    }
}
