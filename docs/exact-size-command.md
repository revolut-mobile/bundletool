# Context

There is a command in the [bundletool](https://developer.android.com/tools/bundletool) to measure the estimated download
sizes of APKs in an APK set as they would be served compressed over the wire:

```shell
java -jar bundletool.jar get-size total --apks=/MyApp/my_app.apks --modules=my_on_demand_module
```

The command by default includes all install-time modules in the estimation (see [`ApkMatcher#getRequestedModules`](../src/main/java/com/android/tools/build/bundletool/device/ApkMatcher.java)).
Additional on-demand modules can be included by specifying the `--modules` argument.

# Problem statement
While the `total` estimation is suitable for alerting systems to keep the app within [Google Play size limits](https://support.google.com/googleplay/android-developer/answer/9859372),
it may also be useful to estimate the app size that most users actually get. The command `get-size total` does not suit this
use case as it estimates the worst-case scenario, treating [conditional install-time](https://developer.android.com/guide/playcore/feature-delivery/conditional)
modules as install-time ones even though they are configured for a narrow audience (specific region, OS API level, etc.).

# Solution
To address this, `get-size` can be extended with the `exact` subcommand, which calculates the size only for the modules
explicitly specified via the `--modules` argument, keeping the `total` subcommand untouched.

```shell
java -jar bundletool.jar get-size exact --apks=/MyApp/my_app.apks --modules=base,widely_used_on_demand_module
```
