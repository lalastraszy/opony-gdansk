package pl.oponygdansk.TyreSize;

import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;

/**
 * Created by magdalena on 24/04/2017.
 */
public class TyreSize {

    private String id;
    private Integer nominalSectionWidth;
    private Integer aspectRatio;
    private Integer rimDiameter;
    private String name;

    public TyreSize(BasicDBObject dbObject) {
        this.id = ((ObjectId) dbObject.get("_id")).toString();
        this.nominalSectionWidth = dbObject.getInt("nominalSectionWidth");
        this.aspectRatio = dbObject.getInt("aspectRatio");
        this.rimDiameter = dbObject.getInt("rimDiameter");
        this.name = this.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNominalSectionWidth() {
        return nominalSectionWidth;
    }

    public void setNominalSectionWidth(Integer nominalSectionWidth) {
        this.nominalSectionWidth = nominalSectionWidth;
    }

    public Integer getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(Integer aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public Integer getRimDiameter() {
        return rimDiameter;
    }

    public void setRimDiameter(Integer rimDiameter) {
        this.rimDiameter = rimDiameter;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return nominalSectionWidth + "/" + aspectRatio + "R" + rimDiameter;
    }
}
