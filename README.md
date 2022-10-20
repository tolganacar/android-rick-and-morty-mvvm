# App Presetation
![app_gif](https://user-images.githubusercontent.com/83028055/196927182-b2765af0-cce8-436e-9d30-5fecec489079.gif)

## Architecture
- Single Activity
- MVVM Pattern
- Clean Code

**View:** Renders UI and delegates user actions to ViewModel

**ViewModel:** Can have simple UI logic but most of the time just gets the data from UseCase

## Tech Stack
#### Dependencies

- **[Fragment](https://developer.android.com/jetpack/androidx/releases/fragment):** Independent screens that are hosted within an Activity 
- **[Navigation Component](https://developer.android.com/jetpack/androidx/releases/navigation):** Consistent navigation between views
- **[LiveData](https://developer.android.com/topic/libraries/architecture/livedata):** Lifecycle aware observable and data holder
- **[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel):** Holds UI data across configuration changes
- **[Databinding](https://developer.android.com/topic/libraries/data-binding/):** Binds UI components in layouts to data sources
- **[Retrofit](https://github.com/square/retrofit):** Type safe HTTP client
- **[RxJava](https://developers.google.com/maps/documentation/android-sdk/rx):** A reactive programming library for composing asynchronous
- **[Glide](https://github.com/bumptech/glide):** Media management and image loading framework
- **[KTX](https://developer.android.com/kotlin/ktx):** a set of Kotlin extensions

## License

```
Copyright tolganacar

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
