package com.timlam.screencolorsmvi.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.timlam.screencolorsmvi.presentation.colors.ColorsContract
import com.timlam.screencolorsmvi.presentation.colors.ColorsRepository
import com.timlam.screencolorsmvi.presentation.colors.ColorsViewModel
import com.timlam.screencolorsmvi.R
import com.timlam.screencolorsmvi.framework.ViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var mainBg: ConstraintLayout
    private lateinit var changeColorButton: Button

    private val repository = ColorsRepository()
    private val colorsViewModel: ColorsViewModel by viewModels {
        ViewModelFactory(
            repository
        )
    }

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
            val event = ColorsContract.Event.OnChangeColorClicked
            colorsViewModel.onEvent(event)
        }

        mainBg.setOnClickListener {
            val message = "Click the button to change the bg color"
            val effect = ColorsContract.Effect.ShowToast(message)
            colorsViewModel.showToast(effect)
        }
    }

    private fun onStateChanged() {
        lifecycleScope.launch {
            colorsViewModel.state().collect {
                when (it.color) {
                    0 -> mainBg.setBackgroundColor(
                        ContextCompat.getColor(
                            this@MainActivity,
                            R.color.purple_200
                        )
                    )
                    1 -> mainBg.setBackgroundColor(
                        ContextCompat.getColor(
                            this@MainActivity,
                            R.color.purple_500
                        )
                    )
                    2 -> mainBg.setBackgroundColor(
                        ContextCompat.getColor(
                            this@MainActivity,
                            R.color.purple_700
                        )
                    )
                    3 -> mainBg.setBackgroundColor(
                        ContextCompat.getColor(
                            this@MainActivity,
                            R.color.teal_200
                        )
                    )
                    4 -> mainBg.setBackgroundColor(
                        ContextCompat.getColor(
                            this@MainActivity,
                            R.color.teal_700
                        )
                    )
                    5 -> mainBg.setBackgroundColor(
                        ContextCompat.getColor(
                            this@MainActivity,
                            R.color.black
                        )
                    )
                    else -> mainBg.setBackgroundColor(
                        ContextCompat.getColor(
                            this@MainActivity,
                            R.color.white
                        )
                    )
                }
            }
        }
    }

    private fun onEffectReceived(observe: (effect: ColorsContract.Effect) -> Unit) {
        lifecycleScope.launch {
            colorsViewModel.effect().collect { observe(it) }
        }
    }

    private fun effect(effect: ColorsContract.Effect) {
        when (effect) {
            is ColorsContract.Effect.ShowToast -> showToast(effect.message)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }

}