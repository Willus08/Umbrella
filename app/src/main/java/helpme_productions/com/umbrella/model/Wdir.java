
package helpme_productions.com.umbrella.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wdir implements Serializable
{

    @SerializedName("dir")
    @Expose
    private String dir;
    @SerializedName("degrees")
    @Expose
    private String degrees;
    private final static long serialVersionUID = -1342632407269900986L;

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getDegrees() {
        return degrees;
    }

    public void setDegrees(String degrees) {
        this.degrees = degrees;
    }

}
