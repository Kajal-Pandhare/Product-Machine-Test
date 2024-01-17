package com.bitcodetech.machine2.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bitcodetech.machine2.databinding.ProductDetailsFragmentBinding
import com.bitcodetech.machine2.models.Product

class DetailsProductFragment : Fragment() {
    private lateinit var binding : ProductDetailsFragmentBinding
    private var product : Product? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProductDetailsFragmentBinding.inflate(layoutInflater)

        if(arguments != null) {
            product = requireArguments().getSerializable("products") as Product
            Log.e("tag--", product.toString())
            binding.product = product

            binding.root.setOnClickListener {

            }
        }
        return binding.root
    }
}