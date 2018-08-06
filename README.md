# PersistCookieOkHttp3
Library to save session cookies with OkHttp3
</br>
</br>


# Requirements
PersistCookie requires at minimum Android 4.0.3 (API level 15).
</br>
</br>


# Gradle Dependency

## Repository
The Gradle dependency is available via maven. maven is the default Maven repository used by Android Studio.
</br>

## Add repository
<pre><code>
repositories {
    maven {
        url  "https://dl.bintray.com/rod120/persistCookiesOkHttp3" 
    }
}
</code></pre>



## Add dependency

#### Gradle:
<pre><code>
dependencies {
    implementation 'com.github.rodlibs:persistCookiesOkHttp3:1.0'
}
</code></pre>


#### Maven:
```xml
 <dependency>
  <groupId>com.github.rodlibs</groupId>
  <artifactId>persistCookiesOkHttp3</artifactId>
  <version>1.0</version>
  <type>pom</type>
</dependency>
```
</br>
</br>


# Simple usage
#### .java
<pre><code>
  PersistentCookieStore myCookie= new PersistentCookieStore(context);
</code></pre>
###### write cookies
<pre><code>
  CookieManager cookieManage = new CookieManager(myCookie, CookiePolicy.ACCEPT_ALL)
</code></pre>
###### read cookies
<pre><code>
  myCookie.getCookies().get(0).getDomain()
</code></pre>
</br>


#### .kt
<pre><code>
  var myCookie: PersistentCookieStore = PersistentCookieStore(context)
</code></pre>
###### write cookies
<pre><code>
  var cookieManage: CookieManager  = CookieManager(myCookie, CookiePolicy.ACCEPT_ALL)
</code></pre>
###### read cookies
<pre><code>
  myCookie.cookies.get(0).domain
</code></pre>
</br>




#### Example with OkHttp
<pre><code>
  OkHttpClient client = new OkHttpClient();
  client.setConnectTimeout(30, TimeUnit.SECONDS);
  client.setCookieHandler(new CookieManager(myCookie, CookiePolicy.ACCEPT_ALL)); 
</code></pre>
</br>




# License
<pre><code>
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
</code></pre>

