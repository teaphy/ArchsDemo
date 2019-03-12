# 图片选择 配置ContentProvider
  
  1. AndroidManifest.xml
  
```
  <!-- 图片选择 -->
  <provider
    android:authorities="com.teaphy.archs.demos.provider"
    android:exported="false"
    android:grantUriPermissions="true"
    android:name="android.support.v4.content.FileProvider">
    <meta-data
      android:name="android.support.FILE_PROVIDER_PATHS"
      android:resource="@xml/file_paths"/>
  </provider>
```
  
  2. 在res中创建xml文件，并添加file_paths.xml
  
```
  <?xml version="1.0" encoding="utf-8"?>
  <resources>
  	<paths>
  		<!-- <files-path/>代表的根目录： Context.getFilesDir() -->
  		<!-- <external-path/>代表的根目录: Environment.getExternalStorageDirectory() -->
  		<!-- <cache-path/>代表的根目录: getCacheDir() -->
  		<external-path path="" name="camera_photos" />
  	</paths>
  </resources>
```

  3. 在Application中配置`ArchConfig.URL_PROVIDER`的值,其值为`AndroidManifest`中声明的`provider`的`android:authorities`的值
  
```
ArchConfig.URL_PROVIDER="com.teaphy.archs.demos.provider"
```  