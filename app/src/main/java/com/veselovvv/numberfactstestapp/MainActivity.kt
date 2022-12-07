package com.veselovvv.numberfactstestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.veselovvv.numberfactstestapp.presentation.main.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment())
            .commit()
    }
}