package com.sonkins.bikeindex.presentation.mapper

import com.sonkins.bikeindex.domain.model.StolenRecord
import com.sonkins.bikeindex.presentation.model.StolenRecordModel
import javax.inject.Inject

/**
 * Created by Vlad Sonkin
 * on 04 April 2018.
 */
class StolenRecordModelDataMapper @Inject constructor() {

    fun transform(stolenRecord: StolenRecord) = StolenRecordModel(
            stolenRecord.dateStolen,
            stolenRecord.location,
            stolenRecord.latitude,
            stolenRecord.longitude,
            stolenRecord.theftDescription,
            stolenRecord.lockingDescription,
            stolenRecord.lockDefeatDescription,
            stolenRecord.policeReportNumber,
            stolenRecord.policeReportDepartment,
            stolenRecord.createdAt,
            stolenRecord.createOpen311,
            stolenRecord.id
    )

}