package com.mankart.myreactivesearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.mankart.myreactivesearch.databinding.ActivityMainBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch

@FlowPreview
@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.edPlace.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                lifecycleScope.launch {
                    mainViewModel.queryChannel.send(s.toString())
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })

        mainViewModel.searchResult.observe(this) { placeItem ->
            val placeName = arrayListOf<String>()
            placeItem.map {
                placeName.add(it.placeName)
            }
            val adapter = ArrayAdapter(this@MainActivity, android.R.layout.select_dialog_item, placeName)
            adapter.notifyDataSetChanged()
            binding.edPlace.setAdapter(adapter)
        }
    }
}