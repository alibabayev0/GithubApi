

# GithubAPI
GithubAPI is simple version of a user and repository part of the GitHub app 📱 The project was done in 2.5 hrs ⏰
# About
It simply loads User and Repository data from API and shows it. Both data will be loaded the following way: loader → data from database → data from API Dummy API is used in this app. 
The list of users from this request: [https://api.github.com/users](https://api.github.com/users) 
The user’s repositories from this request: [https://api.github.com/users/{login}/repos](https://api.github.com/users/%7Blogin%7D/repos)
## Screenshots
![HomePage](https://github.com/alibabayev0/GithubApi/blob/main/screenshots/home.jpg?raw=true)
![Repository Page](https://github.com/alibabayev0/GithubApi/blob/main/screenshots/repos.jpg?raw=true)
## Built With  🛠
-   [Kotlin](https://kotlinlang.org/)  - First class and official programming language for Android development.
-   [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)  - For asynchronous and more.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.

	-  [Flow](https://developer.android.com/kotlin/flow)  - Data objects that notify views when the underlying changes.
	-   [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)  - Stores UI-related data that isn't destroyed on UI changes.
- [Dependency Injection](https://developer.android.com/training/dependency-injection) -  
-   [Retrofit](https://square.github.io/retrofit/)  - A type-safe HTTP client for Android and Java.
- [Room](https://developer.android.com/jetpack/androidx/releases/room) - The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
- [Material](https://developer.android.com/jetpack/androidx/releases/compose-material) - Build Jetpack Compose UIs with ready-to-use Material Design Components.
## Lint  ✅
Following Linters are used internally by super linter (enabled for this project):

-   XML:  [LibXML](http://xmlsoft.org/)
-   Kotlin:  [ktlint](https://github.com/pinterest/ktlint)
# Package Structure

```
com.alibabayev.githubapi    # App Module
.
├── ui                 		# Store MainActivity / Compose Screens / Theme / VM, etc.
│
├── data                    # Network / Database / DataSource / Repository
│
├── domain                  # Repository Interface / UseCase / Util
│
└── di                      # AppModule / DataBaseModule / DataSource / Network 
