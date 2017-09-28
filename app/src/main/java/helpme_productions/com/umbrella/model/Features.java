
package helpme_productions.com.umbrella.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Features implements Serializable
{

    @SerializedName("hourly")
    @Expose
    private Integer hourly;
    @SerializedName("geolookup")
    @Expose
    private Integer geolookup;
    private final static long serialVersionUID = -6661493372562295358L;

    public Integer getHourly() {
        return hourly;
    }

    public void setHourly(Integer hourly) {
        this.hourly = hourly;
    }

    public Integer getGeolookup() {
        return geolookup;
    }

    public void setGeolookup(Integer geolookup) {
        this.geolookup = geolookup;
    }

}
