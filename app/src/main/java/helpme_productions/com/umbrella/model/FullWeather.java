
package helpme_productions.com.umbrella.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FullWeather implements Serializable
{

    @SerializedName("response")
    @Expose
    private Response response;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("hourly_forecast")
    @Expose
    private List<HourlyForecast> hourlyForecast = null;
    private final static long serialVersionUID = 5952487143078856783L;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<HourlyForecast> getHourlyForecast() {
        return hourlyForecast;
    }

    public void setHourlyForecast(List<HourlyForecast> hourlyForecast) {
        this.hourlyForecast = hourlyForecast;
    }

}
