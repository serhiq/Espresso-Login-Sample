# Automator UI sample

This is a sample login/signup android project that is written in [Kotlin](https://github.com/JetBrains/kotlin) and uses [Espresso](https://developer.android.com/intl/ja/tools/testing-support-library/index.html#Espresso) to run instrumented tests.

All instrumented tests are written using the [PageObject](https://alexilyenko.github.io/uiautomator-page-object/) pattern, which makes the tests easy to maintain and read.



## Preview

<p align="left">   <img width="140"src="screenshots\phone_welcome.png">
        <img width="140"src="screenshots\phone_sign_in.png">
    <img width="140"src="screenshots\phone_recover_password.png">
</p>



## Feature test

Each test is a feature, such a grouping can immediately see what works and what doesn't in app.

<p align="left">   <img width="240"src="screenshots\feature_test.png">
  </p>

## Screenshoot test

Includes creating screens for all screens of the application, dialogues. Testing theme changers.

Use the Spoon library to run these tests. This will allow you to run tests simultaneously on all devices connected to Adb. Once all tests have completed a static HTML summary is generated with detailed information about each device and test (with screenshoots).

<p align="left">   <img width="240"src="screenshots\screenshoots-test.png">
  </p>


## Run Spoon test

<p align="left">   <img width="140"src="screenshots\spoon_test_run.png">
  <img width="240"src="screenshots\static_html_summary.png">
           <img width="240"src="screenshots\screenshoots.png">
</p>


## Libraries Used

- Foundation

  - [AppCompat](https://developer.android.com/topic/libraries/support-library/packages#v7-appcompat) - Degrade gracefully on older versions of Android.
  - [Android KTX](https://developer.android.com/kotlin/ktx) - Write more concise, idiomatic Kotlin code.
  - [Test](https://developer.android.com/training/testing/) - An Android testing framework for unit and runtime UI tests.

- Architecture

  - [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) - Handle everything needed for in-app navigation.

  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Store UI-related data that isn't destroyed on app rotations. Easily schedule asynchronous tasks for optimal execution.
    

- Testing libraries

  - Mockito - Most popular Mocking framework for unit tests written in Java.
  - [Falcon](https://github.com/jraska/Falcon/) - take Android screenshots 
  - Espresso - Android UI tests.
  - [Spoon](https://github.com/square/spoon/)-  runs the tests on multiple devices simultaneously.

## License

Copyright 2020 Ekaterina Ovcharenko

Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.