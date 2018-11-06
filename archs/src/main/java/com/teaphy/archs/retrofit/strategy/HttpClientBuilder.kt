package com.teaphy.archs.retrofit.strategy

import okhttp3.*
import okhttp3.OkHttpClient.Builder
import java.net.Proxy
import java.net.ProxySelector
import java.util.concurrent.TimeUnit
import javax.net.SocketFactory
import javax.net.ssl.HostnameVerifier


/**
 * @desc
 * @author teaphy
 * @time 2018/7/28 下午1:38
 */
class HttpClientBuilder {
	private val builder = Builder()

	fun connectTimeout(timeout: Long, unit: TimeUnit) {
		builder.connectTimeout(timeout, unit)
	}

	fun readTimeout(timeout: Long, unit: TimeUnit) {
		builder.readTimeout(timeout, unit)
	}

	fun writeTimeout(timeout: Long, unit: TimeUnit) {
		builder.writeTimeout(timeout, unit)
	}

	fun pingInterval(interval: Long, unit: TimeUnit) {
		builder.pingInterval(interval, unit)
	}

	fun proxy(proxy: Proxy?) {
		builder.proxy(proxy)
	}

	fun proxySelector(proxySelector: ProxySelector) {
		builder.proxySelector(proxySelector)
	}

	fun cookieJar(cookieJar: CookieJar) {
		builder.cookieJar(cookieJar)
	}

	fun cache(cache: Cache?) {
		builder.cache(cache)
	}

	fun dns(dns: Dns) {
		builder.dns(dns)
	}

	fun socketFactory(socketFactory: SocketFactory) {
		builder.socketFactory(socketFactory)
	}


	fun hostnameVerifier(hostnameVerifier: HostnameVerifier) {
		builder.hostnameVerifier(hostnameVerifier)
	}

	fun certificatePinner(certificatePinner: CertificatePinner) {
		builder.certificatePinner(certificatePinner)
	}

	fun authenticator(authenticator: Authenticator) {
		builder.authenticator(authenticator)
	}

	fun proxyAuthenticator(proxyAuthenticator: Authenticator) {
		builder.proxyAuthenticator(proxyAuthenticator)
	}

	fun connectionPool(connectionPool: ConnectionPool) {
		builder.connectionPool(connectionPool)
	}

	fun followSslRedirects(followProtocolRedirects: Boolean) {
		builder.followSslRedirects(followProtocolRedirects)
	}

	fun followRedirects(followRedirects: Boolean) {
		builder.followRedirects(followRedirects)
	}

	fun retryOnConnectionFailure(retryOnConnectionFailure: Boolean) {
		builder.retryOnConnectionFailure(retryOnConnectionFailure)
	}

	fun dispatcher(dispatcher: Dispatcher) {
		builder.dispatcher(dispatcher)
	}

	fun protocols(protocols: List<Protocol>) {
		builder.protocols(protocols)
	}

	fun connectionSpecs(connectionSpecs: List<ConnectionSpec>) {
		builder.connectionSpecs(connectionSpecs)
	}

	fun addInterceptor(interceptor: Interceptor) {
		builder.addInterceptor(interceptor)
	}

	fun addNetworkInterceptor(interceptor: Interceptor) {
		builder.addNetworkInterceptor(interceptor)
	}

	fun eventListener(eventListener: EventListener) {
		builder.eventListener(eventListener)
	}

	fun eventListenerFactory(eventListenerFactory: EventListener.Factory) {
		builder.eventListenerFactory(eventListenerFactory)
	}

	fun build(): OkHttpClient {
		return builder.build()
	}
}