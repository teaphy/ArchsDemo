package com.teaphy.archs.location

/**
 * 定位信息
 */
data class LocationResult(
		val altitude: Double = 0.0, // 获取海拔高度(单位：米) 默认值：0.0
		val speed: Float = 0F, // 获取当前速度(单位：米/秒) 默认值：0.0
		val provicecode: String? = "", // 省编码
		val citycode: String? = "", // 城市编码
		val adcode: String? = "", // 区域编码
		val country: String? = "", // 国家名称
		val province: String? = "", // 省名称
		val city: String? = "", // 市名称
		val district: String? = "", // 区名称
		val street: String? = "", // 街道名称
		val streetNum: String? = "", // 门牌号
		val errorCode: Int? = 0, // 错误码
		val errorInfo: String? = "", // 错误信息
		val locationType: LocationType? = null, // 当前定位结果来源，如网络定位结果
		val locationDetail: String? = "", // 获取定位信息描述
		val address: String? = "", // 地址
		val floor: String? = "",// 室内定位楼层信息
		val description: String? = "", // 位置语义信息
		val time: Long? = 0, // 定位时间
		val provider: String? = "", // 定位提供者
		val longitude: Double? = 0.0, // 经度
		val latitude: Double? = 0.0, // 纬度
		val accuracy: Float? = 0F, // 定位精度 单位:米
		val aoiName: String? = "" // 获取兴趣面名称
)