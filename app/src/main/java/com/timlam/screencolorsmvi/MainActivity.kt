package com.timlam.screencolorsmvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
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
        initViewModel()
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
    }

    private fun initViewModel() {
        lifecycleScope.launch {
            coreViewModel.state()
                .collect {
                    Log.d("TAGARA", "Collect: ${it.color}")
                    when (it.color) {
                        0 -> mainBg.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.purple_200))
                        1 -> mainBg.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.purple_500))
                        2 -> mainBg.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.purple_700))
                        3 -> mainBg.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.teal_200))
                        4 -> mainBg.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.teal_700))
                        5 -> mainBg.setBackgroundColor(ContextCompat.getColor(this@MainActivity, R.color.black))
                    }
                }
        }
    }

}
