package pl.oponygdansk.CarModel;

import com.mongodb.BasicDBObject;

/**
 * Created by magdalena on 11/05/2017.
 */
public class CarModel {

    private String id;
    private String name;
    private String brandId;

    public CarModel(BasicDBObject dbObject) {
        this.id = dbObject.get("_id").toString();
        this.name = dbObject.get("name").toString();
        this.brandId = dbObject.get("brandId").toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }
}
