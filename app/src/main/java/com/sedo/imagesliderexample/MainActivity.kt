package com.sedo.imagesliderexample

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sedo.imageslider.ui.ImageSlider
import com.sedo.imageslider.util.listeners.OnItemClickListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imageSlider = findViewById<ImageSlider>(R.id.imageSlider)
        imageSlider.sliderArrayListString = arrayListOf("","","","")
        imageSlider.sliderArrayListResource = arrayListOf(R.drawable.sample_amman,R.drawable.sample_amman,R.drawable.sample_amman,R.drawable.sample_amman)
        imageSlider.itemClickListener = object : OnItemClickListener {
            override fun onItemClick(view: View?, position: Int, item: Any) {
                Toast.makeText(this@MainActivity, "item clicked", Toast.LENGTH_SHORT).show()
            }

            override fun onItemLongClick(view: View?, position: Int, item: Any) {
                Toast.makeText(this@MainActivity, "item long clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }
}