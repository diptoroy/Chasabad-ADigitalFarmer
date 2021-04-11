package com.ddev.chasabad_adigitalfarmer.util.clickListener

import com.ddev.chasabad_adigitalfarmer.model.disease.DiseaseData

interface DiseaseOnItemClickListener {
    fun onClick(item: DiseaseData, position: Int)
}