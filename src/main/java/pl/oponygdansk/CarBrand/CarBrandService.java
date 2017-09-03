package pl.oponygdansk.CarBrand;

import com.mongodb.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by magdalena on 11/05/2017.
 */
public class CarBrandService {

    private final DB db;
    private final DBCollection collection;

    public CarBrandService(DB db) {
        this.db = db;
        this.collection = db.getCollection("carBrand");
    }

    public List<CarBrand> findAll() {
        List<CarBrand> tyreBrands = new ArrayList<>();
        DBCursor dbObjects = collection.find();
        while (dbObjects.hasNext()) {
            DBObject dbObject = dbObjects.next();
            tyreBrands.add(new CarBrand((BasicDBObject) dbObject));
        }
        return tyreBrands;
    }

    public CarBrand findOne(String id) {
        DBObject dbObjects = collection.findOne(new BasicDBObject("_id", new ObjectId(id)));
        return new CarBrand((BasicDBObject) dbObjects);
    }
}
