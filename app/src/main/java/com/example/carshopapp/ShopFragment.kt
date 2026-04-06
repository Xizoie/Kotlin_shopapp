package com.example.carshopapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class ShopFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_shop, container, false)

        // Buttons from XML
        val bmw = view.findViewById<Button>(R.id.bmw)
        val mercedes = view.findViewById<Button>(R.id.mercedes)
        val porsche = view.findViewById<Button>(R.id.porsche)
        val ferrari = view.findViewById<Button>(R.id.ferrari)

        // BMW click
        bmw.setOnClickListener {
            navigateToPayment("BMW M3", 38000.0)
        }

        // Mercedes click
        mercedes.setOnClickListener {
            navigateToPayment("Mercedes CLA", 46400.0)
        }

        // Porsche click
        porsche.setOnClickListener {
            navigateToPayment("Porsche 911", 189000.0)
        }

        // Ferrari click
        ferrari.setOnClickListener {
            navigateToPayment("Ferrari 488", 260000.0)
        }

        return view
    }

    private fun navigateToPayment(name: String, price: Double) {

        val bundle = Bundle()
        bundle.putString("name", name)
        bundle.putDouble("price", price)

        val fragment = PaymentFragment()
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }
}