
package helpme_productions.com.umbrella.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Airport implements Serializable
{

    @SerializedName("station")
    @Expose
    private List<Station> station = null;
    private final static long serialVersionUID = 1751415586963274155L;

    public List<Station> getStation() {
        return station;
    }

    public void setStation(List<Station> station) {
        this.station = station;
    }

}
