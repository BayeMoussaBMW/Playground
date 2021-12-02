package com.kalamou.playground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kalamou.playground.eventbus.CartEvent

import org.greenrobot.eventbus.EventBus

import android.view.View




class BusActivity2: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus2)
    }

    fun addItemToCart(view: View?) {
        EventBus.getDefault().post(CartEvent("new Cart Item"))
    }

}