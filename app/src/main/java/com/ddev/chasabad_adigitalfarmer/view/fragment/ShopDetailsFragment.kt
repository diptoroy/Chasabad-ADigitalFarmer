package com.ddev.chasabad_adigitalfarmer.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ddev.chasabad_adigitalfarmer.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.shop_product_details_row.*


class ShopDetailsFragment : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.shop_product_details_row, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (requireArguments() != null) {
            shop_product_name.text = requireArguments().getString("name")
            shop_product_image.setImageResource(requireArguments().getInt("image"))
            shop_product_details.text = requireArguments().getString("details")
            shop_product_price.text = requireArguments().getString("price")
            shop_author.text = requireArguments().getString("author")
            shop_name.text = requireArguments().getString("store")
            shop_location.text = requireArguments().getString("location")
            shop_phone.text = requireArguments().getString("phone")
            shop_website.text = requireArguments().getString("site")

        }
    }

}