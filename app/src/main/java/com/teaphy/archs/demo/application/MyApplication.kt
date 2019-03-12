package com.teaphy.archs.demo.application

import com.teaphy.archs.base.BaseApplication
import com.teaphy.archs.config.ArchConfig
import com.teaphy.archs.demo.R
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import timber.log.Timber


class MyApplication : BaseApplication() {

	companion object {
		//设置全局的Header构建器


//		SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
//			@Override
//			public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
//				layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
//				return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
//			}
//		});
//		//设置全局的Footer构建器
//		SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
//			@Override
//			public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
//				//指定为经典Footer，默认是 BallPulseFooter
//				return new ClassicsFooter(context).setDrawableSize(20);
//			}
//		});
	}

	override fun onCreate() {
		super.onCreate()

		Timber.plant(Timber.DebugTree())
	}

	/**
	 * 初始化通用配置
	 */
	override fun initGeneralConfig() {

		SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
			layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
			ClassicsHeader(context)//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
		}

		SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
			layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white)//全局设置主题颜色
			ClassicsFooter(context).setDrawableSize(20f)//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
		}

		with(ArchConfig) {
			URL_REMOTE_BASE = "http://v.juhe.cn/"
			NAME_FILE_ICON_FONT = "iconfont_20181106.ttf"
			URL_PROVIDER = "com.teaphy.archs.demos.provider"
		}
	}
}
