package com.kalamou.playground

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.kalamou.playground.databinding.ActivityMainBinding
import com.kalamou.playground.devialet.Downloader
import com.kalamou.playground.devialet.Listener

class MainActivity : AppCompatActivity(), Listener {
    private val textView: TextView by lazy { findViewById(R.id.txt_v) }
    lateinit var binding: ActivityMainBinding

    companion object{
        private val tag = MainActivity::class.java.simpleName
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initViews()
    }

    private fun initViews(){
        binding.tv.text = "playground"
        binding.btGo.setOnClickListener {
            Toast.makeText(this, "Got it !", Toast.LENGTH_SHORT).show()
            binding.pb.isVisible = true
            binding.pb.isVisible?.let {
                binding.btGo.alpha = 0.5f
            }
        }

        binding.btBus.setOnClickListener {
            //open()
            Downloader.download(this)
        }

        binding.btCancel.setOnClickListener {
            Toast.makeText(this, "Stop it !", Toast.LENGTH_SHORT).show()
            binding.pb.isVisible = false
            binding.pb.isVisible?.let {
                binding.btGo.alpha = 1.0f
            }
        }

    }

    fun open(){
        startActivity(Intent(this, BusActivity::class.java))
    }

    override fun onComplete() {
        textView.text = "Success"
    }

    override fun onError() {
        textView.text = "Error"
    }
}