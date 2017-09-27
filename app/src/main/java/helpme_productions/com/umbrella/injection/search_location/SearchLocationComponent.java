package helpme_productions.com.umbrella.injection.search_location;


import dagger.Component;
import helpme_productions.com.umbrella.view.activities.search_location.SearchLocation;
@Component(modules = SearchLocationModule.class)
public interface SearchLocationComponent {
    void inject(SearchLocation searchLocation);
}
