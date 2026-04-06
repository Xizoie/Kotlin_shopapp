package com.example.carshopapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class DoneFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_done, container, false)

        // Receive payment data from Bundle
        val carName = arguments?.getString("carName", "Unknown Car")
        val originalPrice = arguments?.getDouble("originalPrice", 0.0)
        val discountedPrice = arguments?.getDouble("discountedPrice", 0.0)
        val totalPrice = arguments?.getDouble("totalPrice", 0.0)
        val isExpress = arguments?.getBoolean("isExpress", false)

        // Initialize UI elements
        val successMessage = view.findViewById<TextView>(R.id.success_message)
        val orderDetails = view.findViewById<TextView>(R.id.order_details)
        val backToShopButton = view.findViewById<Button>(R.id.back_to_shop_button)

        // Build success message
        successMessage.text = "✓ Payment Successful!"

        // Build order details
        val shippingType = if (isExpress == true) "Express" else "Standard"
        val details = """
            Order Confirmed!
            
            Car: $carName
            Original Price: $$originalPrice
            5% Discount Applied: $$discountedPrice
            Shipping: $shippingType
            Total Paid: $$totalPrice
            
            Thank you for your purchase!
        """.trimIndent()

        orderDetails.text = details

        // Back to shop button
        backToShopButton.setOnClickListener {
            // Navigate back to ShopFragment
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, ShopFragment())
                .commit()
        }

        return view
    }
}