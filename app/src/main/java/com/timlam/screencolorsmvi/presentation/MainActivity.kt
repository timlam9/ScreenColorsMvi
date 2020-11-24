package com.timlam.screencolorsmvi.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.timlam.screencolorsmvi.databinding.ActivityMainBinding
import com.timlam.screencolorsmvi.framework.ViewModelFactory
import com.timlam.screencolorsmvi.presentation.colors.ColorsContract
import com.timlam.screencolorsmvi.presentation.colors.ColorsRepository
import com.timlam.screencolorsmvi.presentation.colors.ColorsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val repository = ColorsRepository()
    private val colorsViewModel: ColorsViewModel by viewModels { ViewModelFactory(repository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListeners()
        onStateChanged()
        onEffectReceived { effect(it) }
    }

    private fun setClickListeners() {
        binding.changeColorButton.setOnClickListener {
            val event = ColorsContract.Event.OnChangeColorClicked
            colorsViewModel.onEvent(event)
        }

        binding.root.setOnClickListener {
//            TODO: Left this just to compare the 2 solutions. After i can remove it
//            val message = "Click the button to change the bg color"
//            val effect = ColorsContract.Effect.ShowToast(message)
//            colorsViewModel.showToast(effect)

            colorsViewModel.backgroundClicked()
        }
    }

    private fun onStateChanged() {
        lifecycleScope.launch {
            colorsViewModel.state().collect {
                // TODO how can I implement the loading state here?
                changeBackgroundColor(colorsViewModel.getColorFromColorNumber(it.colorNumber))
            }
        }
    }

    private fun changeBackgroundColor(color: Int) {
        binding.root.setBackgroundColor(ContextCompat.getColor(this@MainActivity, color))
    }

    private fun onEffectReceived(observe: (effect: ColorsContract.Effect) -> Unit) {
        lifecycleScope.launch {
            colorsViewModel.effect().collect { observe(it) }
        }
    }

    private fun effect(effect: ColorsContract.Effect) {
        when (effect) {
            is ColorsContract.Effect.ShowToast -> showToast(effect.message)
            is ColorsContract.Effect.ShowColorsToast -> showToast("Click the button to change color")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }

}
