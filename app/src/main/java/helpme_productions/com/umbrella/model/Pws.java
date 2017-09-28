
package helpme_productions.com.umbrella.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pws implements Serializable
{

    @SerializedName("station")
    @Expose
    private List<Station_> station = null;
    private final static long serialVersionUID = 2025878648321180577L;

    public List<Station_> getStation() {
        return station;
    }

    public void setStation(List<Station_> station) {
        this.station = station;
    }

}
