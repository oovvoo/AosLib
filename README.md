# AosLib ver.0.0.1
AosUtils.PrefManager
안드로이드 기기에 데이터 저장
## 지원되는 Type
1. 기본자료형(String,int,boolean 등)
2. ArrayList<String>
3. Bitmap 이미지  
***

### ex) 사용예

<code>PrefManager.setBitmap(getApplicationContext(), {BitmapImage});</code>

<code>Bitmap bitmap =  PrefManager.getBitmap(getApplicationContext(),"img1");</code>

***
  
### 적용방법
  
# build.gradle - project
<pre>
<code>
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://jitpack.io' }
    }
}
</code>
</pre>

# build.gradle - module
<pre>
<code>
dependencies {
    implementation 'com.github.oovvoo:AosLib:0.0.1'
}
</code>
</pre>
