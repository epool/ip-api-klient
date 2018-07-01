# IpApiKlient

This is a utility written in [Kotlin](http://kotlinlang.org/) for checking ip's info by fetching the [ip-api.com](http://ip-api.com/) API.

## Libraries

This project uses the following libraries internally to work.

### Development libraries

- [Retrofit](http://square.github.io/retrofit/) used to consume the Picasa api.
- [Retrofit Gson Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/gson) converter to deserialize JSON responses from the picasa api.
- [RxJava2](https://github.com/ReactiveX/RxJava) async requests.
- [Okhttp Logging Interceptor](https://github.com/ReactiveX/RxJava) for logging http requests/responses for the Picasa api.

### Testing libraries

- [Spek](http://spekframework.org/) Used to write some integration tests as specs.

## Usage

### Dependency

```groovy
repositories {
    ...
    maven { url 'https://jitpack.io' }
    ...
}

dependencies {
    ...
    implementation "com.github.epool:ip-api-klient:1.0.0"
    ...
}
```

### Blocking

Use it when blocking is safe to use like on web servers.

###### Java

```java
IpCheckResult ipCheckResult = IpApiKlient.getIpInfo("8.8.8.8").blockingGet();
if (ipCheckResult.isSuccess()) {
    IpInfo ipInfo = ipCheckResult.getIpInfo();
} else {
    IpError ipError = ipCheckResult.getIpError();
}
```

###### Kotlin

```kotlin
val ipCheckResult = IpApiKlient.getIpInfo("8.8.8.8").blockingGet()
if (ipCheckResult.isSuccess()) {
    val ipInfo = ipCheckResult.ipInfo
} else {
    val ipError = ipCheckResult.ipError
}
```

### Async

Use it when blocking is not safe to use like on Android main thread.

###### Java

```java
IpApiKlient.getIpInfo("8.8.8.8")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread()) // In case you are using it on Android or use any other scheduler you need
        .subscribe(
                new Consumer<IpCheckResult>() {
                    @Override
                    public void accept(IpCheckResult ipCheckResult) throws Exception {
                        System.out.println(profile.isSuccess());
                    }
                },
                new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        System.out.println(throwable.getCause().toString());
                    }
                }
        );
```

###### Kotlin

```kotlin
IpApiKlient.getIpInfo("8.8.8.8")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                { println(it.isSuccess()) },
                { println(it.cause.toString()) }
        )
```

**NOTE:** If the ip check has an error `IpCheckResult.isSuccess()` will be false. 

