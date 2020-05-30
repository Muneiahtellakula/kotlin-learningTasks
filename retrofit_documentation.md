# Retrofit— A simple implimentation in Android 

* For this document, we are going to see how to use Retrofit HTTP client in your Android application.
* [Official link for Retrofit](https://square.github.io/retrofit/)

**Retrofit is an awesome type-safe HTTP client for Android and Java built by awesome folks at Square. Retrofit makes it easy to consume JSON or XML data which is parsed into Plain Old Java Objects (POJOs).**
####  So, without any further delays, lets get started by first creating a new project in Android Studio.
1. Go to File ⇒ New Project. When it prompts you to select the default activity, select Empty Activity and proceed.
2. Open **build.gradle in (Module:app)** and add **Retrofit, Picasso, RecyclerView, Gson dependencies** like this.
    * After adding below dependencies click on top right side corner **Sync Now** .Wait untill for successfull message came
``` xml

dependencies 
{
    implementation 'com.squareup.retrofit2:retrofit:2.8.1'
    implementation 'com.squareup.retrofit2:converter-gson:2.7.2'
    implementation 'com.jakewharton.picasso:picasso2-okhttp3-downloader:1.1.0'
    implementation 'com.squareup.picasso:picasso:2.71828'
    implementation 'androidx.recyclerview:recyclerview:1.2.0-alpha03'
    implementation 'androidx.cardview:cardview:1.0.0'
}


```

3. Don’t forget to add INTERNET permissions in AndroidManifest.xml file like this

```Xml

<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.muneiah.retrofitpractices1">
    
    <!--For Internet access -->
<uses-permission android:name="android.permission.INTERNET"/>
    
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>


```

4. Next, we will create data model to parse our sample JSON data with following structure.[For Json Url](https://jsonplaceholder.typicode.com/photos)
<img src="https://github.com/Muneiahtellakula/kotlin-learningTasks/blob/master/jsonplaceholder.png">

