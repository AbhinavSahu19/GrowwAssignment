# Stocks App - explore, search and view stocks detail.

### TechStack
**Language**	Kotlin
**UI**	Jetpack Compose	
**Architecture**	MVVM (Model-View-ViewModel)
**Dependency Injection**	Hilt
**Database**	Room
**Network**	Retrofit + Gson	HTTP client
**Charting**	candlestickschartkmm by yashctn88

### Folder Structure
com.example.stocksapp/
├─ api/
|    ├──responsedto/         # Api response data models
|    ├── Api Service         # Api call methods
|
├── db/                     # API layer
│   ├── dao/                # Dao for entities 
│   ├── entity/             # Room db entities
│   ├── MapConverter        # To convert graph map to string to store
|   └── StocksDatabase.kt   # Room DB setup
│
├── di/                        # Hilt Modules
│   ├── NetworkModule.kt       # Retrofit & API provider
│   ├── DatabaseModule.kt      # Room & DAO providers
│   └── RepositoryModule.kt    # Provide Repository Implementation
│
├── presentation/         # UI Layer
|   ├── common/            # Reusable Compose UI components
|   ├── details/           # Stock detail screen with graph
│   ├── explore/           # Explore screen: Top gainers/losers, search
│   ├── search/            # Search screen and recent keyword handling
│   ├── viewall/           # View All stocks/ searches screen
│   ├── MainApp.kt         # Contains NavGraph.
│   └── Screen.kt          # Sealed class containing all screens.
|
├── repository/              # Repository
│   ├── Repository.kt        # Repository interface which contains abstract functions.
│   └── RepositoryImpl.kt    # Implement methods of Repository class
|
├── utils/                # Utility helpers & extensions
│
└── MainActivity.kt       # App entry point
