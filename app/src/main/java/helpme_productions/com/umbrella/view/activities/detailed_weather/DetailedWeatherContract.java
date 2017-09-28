package helpme_productions.com.umbrella.view.activities.detailed_weather;


import android.content.Intent;

import helpme_productions.com.umbrella.BasePresenter;
import helpme_productions.com.umbrella.BaseView;
import helpme_productions.com.umbrella.model.FullWeather;

interface DetailedWeatherContract {
    interface View extends BaseView{
        void setupUI(FullWeather fullWeather, String tempSeting);

    }
    interface Presenter extends BasePresenter<View>{
        void getWeather(Intent intent);
    }
}
