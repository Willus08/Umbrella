# Umbrella Coding Chalange
Coding Challenge

## Chalange:
Create a simple weather application named “Umbrella”. The application
will download both the current conditions and an hour by hour forecast from Weather Underground.
This application is intended to only be released in the United States.

### Approach:

#### Architecture:
MVP

Librairies Used:
RetroFit (Network Calls)
ButterKnife (View Binding)
RecyclerView (Scrolling View)
CardView (UX)
Glide (Image Loading and Caching)
Dagger (Dependency Injection)

#### Steps:
1. Using an EditText and a Spinner aquire the Zip Code of the location that the user wishes to see
    and get the prfrence of the Temperture Scheme the user wishes to use/
2. Use Retrofit to complete a rest call to the Api to aquire the location and the weather for the
    next 36 hours.

### Application:

#### Activities:
SearchLocation: Contains a search bar to allow the user to search for a specific zip code and a
    spinner to select the prefered temperture scale.
DetailedWeather Activity: Contains detailed Information about the selected location's current
    weather and the weather displayed for the next 36 hours.

#### Utilities:
DetailedWeatherRecyclerAdapter: RecyclerView Adapter for the Detailed Weather Activity
DetailedWeatherGridAdapter: Grid View Adapter for the Grid Layout in the recycler view;
RetrofitHelper: Retrofit factory class and interface for network calls.

