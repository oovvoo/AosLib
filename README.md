# AosLib 
AosUtils.PrefManager
안드로이드 기기에 데이터 저장
## 지원되는 Type
1. 기본자료형(String,int,boolean 등)
2. ArrayList<String>
3. Bitmap 이미지  
***

### ex) 사용예

<code>PrefManager.setBitmap(getApplicationContext(),{key},{BitmapImage});</code>

<code>Bitmap bitmap =  PrefManager.getBitmap(getApplicationContext(),{key});</code>

***
### ex) 사용예  
<code>AosLib.UIUtils.setRainbowTextView(textView);</code>
- 텍스트가 무지개로 변함
  
<code>AosLib.UIUtils.setRainbowTextView(textView,ColorIntArray);</code>
  
- 텍스트가 원하는 색상으로 순환  
  
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
    implementation 'com.github.oovvoo:AosLib:0.0.4'
}
</code>
</pre>
