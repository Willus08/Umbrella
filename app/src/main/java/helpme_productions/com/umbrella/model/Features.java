
package helpme_productions.com.umbrella.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Features implements Serializable
{

    @SerializedName("hourly")
    @Expose
    private Integer hourly;
    private final static long serialVersionUID = -8422821518632160936L;

    public Integer getHourly() {
        return hourly;
    }

    public void setHourly(Integer hourly) {
        this.hourly = hourly;
    }

}
