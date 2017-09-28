package helpme_productions.com.umbrella.injection.detailed_weather;


import dagger.Component;
import helpme_productions.com.umbrella.view.activities.detailed_weather.DetailedWeather;

@Component(modules = DetailedWeatherModule.class)
public interface DetailedWeatherComponent {
    void inject(DetailedWeather detailedWeather);
}
