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
    maven { url 'https://jitpack.io' }
}
</code></pre>



## Add dependency

#### Gradle:
<pre><code>
dependencies {
    implementation 'com.github.rodLibs:PersistCookieOkHttp3:master-SNAPSHOT'
}
</code></pre>
</br>


# Simple usage
#### .java
<pre><code>
  PersistencesCookies myCookie= new PersistencesCookies(context);
</code></pre>
###### write cookies
<pre><code>
  ... cookieJar(myCookie.cookieJar) ...
</code></pre>
###### read cookies
<pre><code>
  myCookie.getCookies().domain()
</code></pre>
###### has cookies
<pre><code>
  myCookie.hasCookies(String host)
</code></pre>
</br>


#### .kt
<pre><code>
  var myCookie: PersistencesCookies = PersistencesCookies(context)
</code></pre>
###### write cookies
<pre><code>
 ... cookieJar(myCookie.cookieJar) ...
</code></pre>
###### read cookies
<pre><code>
  myCookie.getCookies().domain()
</code></pre>
###### has cookies
<pre><code>
  myCookie.hasCookies(String host)
</code></pre>
</br>




#### Example with OkHttp
<pre><code>
   val client = OkHttpClient.Builder()
   .cookieJar(myCookie.cookieJar)
   .build()
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

