package pl.oponygdansk.CarBrand;

import com.mongodb.BasicDBObject;

/**
 * Created by magdalena on 11/05/2017.
 */
public class CarBrand {

    private String id;
    private String name;

    public CarBrand(BasicDBObject dbObject) {
        this.id = dbObject.get("_id").toString();
        this.name = dbObject.get("name").toString();
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
}
