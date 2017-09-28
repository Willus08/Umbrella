
package helpme_productions.com.umbrella.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NearbyWeatherStations implements Serializable
{

    @SerializedName("airport")
    @Expose
    private Airport airport;
    @SerializedName("pws")
    @Expose
    private Pws pws;
    private final static long serialVersionUID = -5235732655188140741L;

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public Pws getPws() {
        return pws;
    }

    public void setPws(Pws pws) {
        this.pws = pws;
    }

}
