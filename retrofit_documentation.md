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

 * Create a class named Repo.java under model package like this

```Java
package com.muneiah.retrofitpractices1;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Repo implements Serializable {

	@SerializedName("albumId")
	private int albumId;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("url")
	private String url;

	@SerializedName("thumbnailUrl")
	private String thumbnailUrl;

	public void setAlbumId(int albumId){
		this.albumId = albumId;
	}

	public int getAlbumId(){
		return albumId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setThumbnailUrl(String thumbnailUrl){
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getThumbnailUrl(){
		return thumbnailUrl;
	}

	@Override
 	public String toString(){
		return 
			"Repo{" + 
			"albumId = '" + albumId + '\'' + 
			",id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			",url = '" + url + '\'' + 
			",thumbnailUrl = '" + thumbnailUrl + '\'' + 
			"}";
		}
}

```

5. Create the Retrofit Instance
* To issue network requests to a REST API with Retrofit, we need to create an instance using the Retrofit.Builder class and configure it with a base URL.
* Create a class **RetrofitClientInstance.java** under **network** package. Here **BASE_URL** is the basic URL of our API where we will make a call.

```Java
package com.muneiah.retrofitpractices1;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance 
{
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

```
6. Define the Endpoints:Create New Interface name GetDataService

```
package com.muneiah.retrofitpractices1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService
{
    @GET("/photos")
    Call<List<Repo>> getAllPhotos();
}

7.Create layour resourse each_item_design.xml file like as 

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:weightSum="2"
    android:layout_weight="10"
    android:layout_margin="10dp"
    android:layout_marginTop="10dp"
    android:orientation="horizontal" android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <ImageView
        android:id="@+id/iv"
        android:layout_width="0dp"
        android:layout_weight="1"
        android:layout_height="wrap_content"
        app:srcCompat="@drawable/ic_launcher_background" />

    <TextView
        android:id="@+id/textView_tv"
        android:layout_width="0dp"
        android:layout_weight="1"
        android:layout_height="wrap_content"
        android:text="TextView" />
</LinearLayout>


```



```
8. Create custom adapter for binding data with RecycleView.
* Create a class named MyAdapter.java under java directory first package like this.
```


```



