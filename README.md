Jetpack Compose Android app using the SWAPI to explore the Star Wars universe. It showcases a clean, scalable architecture with Paging 3, Room caching, Hilt DI, and a custom saga-inspired theme.


🚀 Features

Dashboard

- Top app bar with logo + navigation drawer (Favorites, Map, etc.)

- Quick-access cards: Characters, Films, Species, Planets
  

Characters

- Paged list (Paging 3) with smooth loading states

- Search via SWAPI query (client-side debounce)

- Sorting (A→Z / Z→A), integrated with search

- Details screen with hero image, bio blocks, and dynamic story placeholder

- Favorites: add/view; graceful empty state
  

Compare Characters

- Pick two characters and compare attributes (name, gender, birth year, homeworld, species, etc.)

- Clear, side-by-side layout to spot differences quickly
  

Films / Species / Planets

- Consistent domain models + mappers

- List with pagination and search Star Wars Films, Species and Planets (cached in Room)


Planets Map

- Interactive map using OSMDroid (OpenStreetMap)

- Planet markers with basic details; click-through from details to map
  

Custom Theming & UX

- Global starry background

- StarJedi Special Edition font

- Primary color #FFE81F

- Custom splash: fade-in logo + crossed lightsaber
  

Darth Vader Mode

- High-contrast black/red variant that re-colors key UI elements

- Toggleable in-app (kept fun but non-intrusive)

	

🧩 Tech Stack

Kotlin + Coroutines/Flows

Jetpack Compose (Material 3)

Navigation Compose

Paging 3

Room (with KSP)

Hilt (DI, with KSP)

Retrofit + OkHttp + Moshi

Coil (image loading)

OSMDroid (OpenStreetMap map view)
