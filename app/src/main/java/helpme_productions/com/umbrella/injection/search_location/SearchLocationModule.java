package helpme_productions.com.umbrella.injection.search_location;

import dagger.Module;

import dagger.Provides;
import helpme_productions.com.umbrella.view.activities.search_location.SearchLocationPresenter;

@Module
public class SearchLocationModule {
    @Provides
    public SearchLocationPresenter searchLocationPresenterProvider(){
        return new SearchLocationPresenter();
    }
}
