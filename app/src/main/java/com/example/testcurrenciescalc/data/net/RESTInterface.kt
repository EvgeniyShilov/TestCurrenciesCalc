package com.example.testcurrenciescalc.data.net

import com.example.testcurrenciescalc.data.models.Rate
import io.reactivex.Single

interface RESTInterface {

    fun getDailyCodes(): Single<List<Rate>>
}
