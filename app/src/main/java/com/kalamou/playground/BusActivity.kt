package com.kalamou.playground

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.kalamou.playground.databinding.ActivityBusBinding
import com.kalamou.playground.eventbus.CartEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe


class BusActivity : AppCompatActivity() {

    var cartEventList: ArrayList<CartEvent> = ArrayList()
    var cartTextView: TextView? = null

    lateinit var binding: ActivityBusBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bus)
        initViews()
    }

    fun initViews() {
        binding.butAdd.setOnClickListener {
            startActivity(Intent(this, BusActivity2::class.java))
        }
    }


    @Subscribe
    fun onCartItemAdd(cartEvent: CartEvent?) {
        cartEvent?.let {
            cartEventList.add(it)
        }
        val cartTotalItems = "Cart Items: " + cartEventList.size
        binding.cartTextView.text = cartTotalItems
        Toast.makeText(this, "Item added to cart.", Toast.LENGTH_SHORT).show()
    }

  /*  @Subscribe
    fun onCartIntemRemove(cartEvent: CartEvent?) {
        cartEvent.let {
            cartEventList.remove(it)
        }
    }*/

    override fun onStart() {
        super.onStart()
        if (!EventBus.getDefault().isRegistered(this)) EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

}