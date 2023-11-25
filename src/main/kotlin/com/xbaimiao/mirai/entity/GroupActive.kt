package com.xbaimiao.mirai.entity

import com.google.gson.annotations.SerializedName

class GroupActive(
    @SerializedName("isHonorVisible")
    val isHonorVisible: Boolean,
    @SerializedName("isTemperatureVisible")
    val isTemperatureVisible: Boolean,
    @SerializedName("rankTitles")
    val rankTitles: Map<Int, String>,
    @SerializedName("isTitleVisible")
    val isTitleVisible: Boolean,
    @SerializedName("temperatureTitles")
    val temperatureTitles: Map<Int, String>
)