package helpme_productions.com.umbrella.injection.detailed_weather;

import dagger.Module;
import dagger.Provides;
import helpme_productions.com.umbrella.view.activities.detailed_weather.DetailedWeatherPresenter;


@Module
public class DetailedWeatherModule {
    @Provides
    public DetailedWeatherPresenter detailedWeatherPresenterProvider(){
        return new DetailedWeatherPresenter();
    }
}
