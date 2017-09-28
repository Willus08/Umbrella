package helpme_productions.com.umbrella.data.remote;


import helpme_productions.com.umbrella.model.FullWeather;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {
    @GET("/api/{key}/{forecast}/{location}/q/{zip}.json")
    Call<FullWeather> getFullWeather(@Path("key") String key, @Path("forecast") String forecast, @Path("location") String location, @Path("zip") int zip);

}
