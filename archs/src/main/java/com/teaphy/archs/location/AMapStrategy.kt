package com.teaphy.archs.location

import android.content.Context
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationListener
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode

/**
 * @desc 高德地图定位
 * @author teaphy
 * @time 2018/8/30 下午4:46
 */
class AMapStrategy : ILocationStrategy {

	private lateinit var context: Context
	private var isSingle: Boolean = true
	private var intervalLocation: Long = 1000L

	// 声明AMapLocationClient类对象
	private lateinit var locationClient: AMapLocationClient
	// 声明AMapLocationClientOption对象
	private lateinit var locationOption: AMapLocationClientOption
	private var locationCallback: ILocationCallback? = null

	//声明定位回调监听器
	private var locationListener = AMapLocationListener {
		var locationResult: LocationResult? = null
		if (null != it) {
			locationResult = produceLocationResult(it)
		}

		locationCallback?.onChange(locationResult)
	}

	/**
	 * 初始化定位
	 */
	override fun initLocation(context: Context, isSingle: Boolean, intervalLocation: Long, locationCallback: ILocationCallback?) {

		this.context = context
		this.isSingle = isSingle
		this.intervalLocation = intervalLocation
		this.locationCallback = locationCallback

		//初始化定位
		locationClient = AMapLocationClient(context)
		// 设置定位回调监听
		locationClient.setLocationListener(locationListener)

		// 初始化AMapLocationClientOption对象
		locationOption = AMapLocationClientOption()

		fillOption()
	}

	/**
	 * 配置定位参数
	 */
	private fun fillOption() {
		with(locationOption) {
			// 设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
			locationMode = AMapLocationMode.Hight_Accuracy

			//获取一次定位结果：
			//该方法默认为false
			isOnceLocation = isSingle
			//获取最近3s内精度最高的一次定位结果：
			//设置setOnceLocationLatest(boolean b)接口为true，
			// 启动定位时SDK会返回最近3s内精度最高的一次定位结果。
			// 如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
			isOnceLocationLatest = true

			// 若连续定位，指定间隔时间
			// 单位毫秒,默认为2000ms，最低1000ms。
			if (!isSingle
					&& intervalLocation != -1L) {
				interval = intervalLocation
			}
		}
	}

	/**
	 * 开启定位
	 */
	override fun startLocation() {
		with(locationClient) {
			// 给定位客户端对象设置定位参数
			setLocationOption(locationOption)
			// 启动定位
			startLocation()
		}
	}

	/**
	 * 停止定位
	 */
	override fun stopLocation() {
		// 停止定位后，本地定位服务并不会被销毁
		if (locationClient.isStarted) {
			locationClient.stopLocation()
		}
	}

	/**
	 * 销毁定位客户端，同时销毁本地定位服务
	 */
	override fun destroyClient() {
		locationClient.onDestroy()
	}

	/**
	 * 创建LocationResult实例
	 */
	private fun produceLocationResult(aMapLocation: AMapLocation): LocationResult {
		return LocationResult(
				altitude = aMapLocation.altitude,
				speed = aMapLocation.speed,
				citycode = aMapLocation.cityCode,
				adcode = aMapLocation.adCode,
				country = aMapLocation.country,
				province = aMapLocation.province,
				city = aMapLocation.city,
				district = aMapLocation.district,
				street = aMapLocation.street,
				streetNum = aMapLocation.streetNum,
				errorCode = aMapLocation.errorCode,
				errorInfo = aMapLocation.errorInfo,
				locationDetail = aMapLocation.locationDetail,
				address = aMapLocation.address,
				floor = aMapLocation.floor,
				description = aMapLocation.description,
				time = aMapLocation.time,
				provicecode = aMapLocation.provider,
				longitude = aMapLocation.longitude,
				latitude = aMapLocation.latitude,
				accuracy = aMapLocation.accuracy,
				locationType = getLocationType(aMapLocation.locationType)
		)
	}

	/**
	 * 获取定位类型
	 */
	private fun getLocationType(type: Int): LocationType? {
		return when (type) {
			0 -> LocationType.FAILURE
			1 -> LocationType.LOCATION_GPS
			2 -> LocationType.LOCATION_PRE
			4 -> LocationType.LOCATION_CACHE
			5 -> LocationType.LOCATION_WIFI
			6 -> LocationType.LOCATION_BS
			8 -> LocationType.LOCATION_OFFLINE
			else -> null
		}
	}
}