package com.timlam.screencolorsmvi

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var mainBg: ConstraintLayout
    private lateinit var changeColorButton: Button

    private val repository = CoreRepository()
    private val coreViewModel: CoreViewModel by viewModels { ViewModelFactory(repository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        setClickListeners()
        onStateChanged()
        onEffectReceived { effect(it) }
    }

    private fun initViews() {
        mainBg = findViewById(R.id.main_bg)
        changeColorButton = findViewById(R.id.change_color_button)
    }

    private fun setClickListeners() {
        changeColorButton.setOnClickListener {
            val event = CoreContract.Event.OnChangeColorClicked
            coreViewModel.onEvent(event)
        }

        mainBg.setOnClickListener {
            val message = "Click the button to change the bg color"
            val effect = CoreContract.Effect.ShowToast(message)
            coreViewModel.setEffect { effect }
        }
    }

    private fun onStateChanged() {
        lifecycleScope.launch {
            coreViewModel.state()
                .collect {
                    when (it.color) {
                        0 -> mainBg.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.purple_200))
                        1 -> mainBg.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.purple_500))
                        2 -> mainBg.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.purple_700))
                        3 -> mainBg.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.teal_200))
                        4 -> mainBg.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.teal_700))
                        5 -> mainBg.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.black))
                        else -> mainBg.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.white))
                    }
                }
        }
    }

    private fun onEffectReceived(observe: (effect: CoreContract.Effect) -> Unit) {
        lifecycleScope.launch {
            coreViewModel.effect().collect { observe(it) }
        }
    }

    private fun effect(effect: CoreContract.Effect) {
        when (effect) {
            is CoreContract.Effect.ShowToast -> showToast(effect.message)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }

}
