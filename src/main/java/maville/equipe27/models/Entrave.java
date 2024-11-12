package maville.equipe27.models;

import com.google.gson.annotations.SerializedName;

public class Entrave {

    @SerializedName("streetimpactwidth")
    private String impactWidth;

    @SerializedName("streetimpacttype")
    private String impactType;

    @SerializedName("sidewalk_blockedtype")
    private String sidewalkStatus;

    @SerializedName("bikepath_blockedtype")
    private String bikepathStatus;

    @SerializedName("fromname")
    private String from;

    @SerializedName("toname")
    private String to;

    @Override
    public String toString() {
        return String.format("impactWidth: %s\nimpactType: %s\nsidewalkStatus: %s\nbikepathStatus: %s\nfrom: %s\nto: %s\n",
                impactWidth, impactType, sidewalkStatus, bikepathStatus, from, to);
    }
}
