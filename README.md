#Description

![image](https://github.com/ahmedhappa/YassirMovieApp/assets/32523620/646da71d-890d-4f81-90f2-ca28522c927d)
![image](https://github.com/ahmedhappa/YassirMovieApp/assets/32523620/4bff99a0-82a1-4253-b29b-2b8720663603)

Android App that allows users to explore a curated collection of movies. It is built using Clean Architecture principles and follows the Model-View-Intent (MVI) pattern to ensure a scalable and maintainable codebase.

Features
Discover Movies: Browse a diverse selection of movies.
Movie Details: View detailed information about each movie.
Dark Mode: Enjoy a comfortable viewing experience in low-light conditions.
Architecture
YassirMovieApp is designed with a clean architecture approach, separating concerns into layers for better maintainability and testability. The project structure follows the principles of Clean Architecture, including:

Presentation Layer: Contains UI-related code.
Domain Layer: Holds business logic and use cases.
Data Layer: Manages data access and repository implementations.

#Libraries Used
Hilt: Dependency injection for managing the app's components.
Coroutines: For handling asynchronous operations and background tasks.
Retrofit: For making network requests to fetch movie data.
Room: Local database for storing favorite movies.
Coil: Image loading library for efficient loading and caching of images.
ViewModel: Architecture component for managing UI-related data.
Flow: For manipulating the streams of data
Jetpack compose: For developing the app UI
Paging3: For paginating the data that is fetched from API and from the database
