package com.ddev.chasabad_adigitalfarmer.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ddev.chasabad_adigitalfarmer.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_disease_details.*
import kotlinx.android.synthetic.main.shop_product_details_row.*

class DiseaseDetailsFragment : BottomSheetDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_disease_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (requireArguments() != null) {
            crop_disease_details_name.text = requireArguments().getString("name")
            crop_disease_details_image.setImageResource(requireArguments().getInt("image"))
            crop_disease_details_Group_name.text = requireArguments().getString("dGroupName")
            crop_disease_details_details.text = requireArguments().getString("dDetails")
            //crop_disease_details_crop
            crop_disease_details_medicine_name.text = requireArguments().getString("dMedicine")
            crop_disease_details_medicine_rule.text = requireArguments().getString("dMedicineRule")
            crop_disease_details_medicine_group.text = requireArguments().getString("dMedicineGroupName")

        }
    }
}