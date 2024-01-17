package com.bitcodetech.machine2.fragments

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.bitcodetech.machine2.R
import com.bitcodetech.machine2.VollyRequestQueue
import com.bitcodetech.machine2.adapter.ProductAdapter
import com.bitcodetech.machine2.databinding.ProductFragmentBinding
import com.bitcodetech.machine2.models.Product
import com.bitcodetech.machine2.models.ProductResponse
import com.google.gson.Gson
import okhttp3.Response
import org.json.JSONObject

class ProductFragment : Fragment() {
    private lateinit var binding : ProductFragmentBinding
    private lateinit var recyclerView: RecyclerView
    private var products = ArrayList<Product>()
    private lateinit var productAdapter: ProductAdapter
    private lateinit var progressDialog : ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.product_fragment, null)


        showProgressDialog()
        initViews(view)
        initListeners()

        val userApiRequest = JsonObjectRequest(
            Request.Method.GET,
            "https://dummyjson.com/products",
            null,
            object : com.android.volley.Response.Listener<JSONObject> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(response: JSONObject?) {
                    Log.e("tag", response!!.toString())
                    val apiResponse = Gson().fromJson<ProductResponse>(response.toString(), ProductResponse::class.java)
                    products.addAll(apiResponse!!.products!!)
                    productAdapter.notifyDataSetChanged()
                    progressDialog.dismiss()
                }
            },
            object : com.android.volley.Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {
                    Log.e("tag", "${error?.message}")
                }
            }
        )

        VollyRequestQueue.getRequestQueue(requireContext()).add(userApiRequest)

        return view
    }

    private fun showProgressDialog() {
        progressDialog = ProgressDialog(requireContext())
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog.setMessage("Fetching users....")
        progressDialog.show()
    }

    private fun initViews(view: View) {
        recyclerView = view.findViewById(R.id.recyclerProducts)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        productAdapter = ProductAdapter(products)
        recyclerView.adapter = productAdapter


    }
   private fun initListeners() {
        productAdapter.onProductImageListener =
            object : ProductAdapter.OnProductImageListener{
                override fun onProductCLick(product: Product, position: Int, productAdapter: ProductAdapter) {
                    Log.e("tag", product.toString())
                    showDetailsFragment(product)
                }
            }
            }
    private fun showDetailsFragment(product: Product){
        val detailsProductFragment = DetailsProductFragment()

        val bundles = Bundle()
        bundles.putSerializable("products",product)
        detailsProductFragment.arguments = bundles

        parentFragmentManager.beginTransaction()
            .add(R.id.mainContainer,detailsProductFragment,null)
            .addToBackStack(null)
            .commit()

    }
}