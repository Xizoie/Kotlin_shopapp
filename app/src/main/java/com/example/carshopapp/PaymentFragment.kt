package com.example.carshopapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class PaymentFragment : Fragment() {

    // Variables to store data from Bundle
    private var carName: String = ""
    private var originalPrice: Double = 0.0
    private var discountedPrice: Double = 0.0
    private var totalPrice: Double = 0.0
    private var isExpressShipping: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_payment, container, false)

        // Receive data from Bundle
        arguments?.let {
            carName = it.getString("name", "Unknown Car")
            originalPrice = it.getDouble("price", 0.0)
        }

        // Calculate 5% discount
        discountedPrice = originalPrice * 0.95  // 5% discount means paying 95% of original price

        // Initialize UI elements
        val carNameText = view.findViewById<TextView>(R.id.car_name)
        val originalPriceText = view.findViewById<TextView>(R.id.original_price)
        val discountedPriceText = view.findViewById<TextView>(R.id.discounted_price)
        val totalPriceText = view.findViewById<TextView>(R.id.total_price)
        val shippingRadioGroup = view.findViewById<RadioGroup>(R.id.shipping_group)
        val standardRadio = view.findViewById<RadioButton>(R.id.standard_shipping)
        val expressRadio = view.findViewById<RadioButton>(R.id.express_shipping)
        val payButton = view.findViewById<Button>(R.id.pay_button)

        // Display car info
        carNameText.text = carName
        originalPriceText.text = "Original Price: $$originalPrice"
        discountedPriceText.text = "After 5% Discount: $$discountedPrice"

        // Calculate initial total (Standard shipping - no extra cost)
        totalPrice = discountedPrice
        totalPriceText.text = "Total: $$totalPrice"

        // Handle shipping option changes
        shippingRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.standard_shipping -> {
                    isExpressShipping = false
                    totalPrice = discountedPrice  // No extra cost
                    totalPriceText.text = "Total: $$totalPrice"
                }
                R.id.express_shipping -> {
                    isExpressShipping = true
                    totalPrice = discountedPrice + 1700.0  // Add $1,700 for Express
                    totalPriceText.text = "Total: $$totalPrice"
                }
            }
        }

        // Pay button click listener
        payButton.setOnClickListener {
            // Navigate to DoneFragment
            val bundle = Bundle()
            bundle.putString("carName", carName)
            bundle.putDouble("originalPrice", originalPrice)
            bundle.putDouble("discountedPrice", discountedPrice)
            bundle.putDouble("totalPrice", totalPrice)
            bundle.putBoolean("isExpress", isExpressShipping)

            val doneFragment = DoneFragment()
            doneFragment.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.container, doneFragment)
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}