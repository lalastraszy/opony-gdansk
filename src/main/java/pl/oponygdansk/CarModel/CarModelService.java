package pl.oponygdansk.CarModel;

import com.mongodb.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by magdalena on 11/05/2017.
 */
public class CarModelService {

    private final DB db;
    private final DBCollection collection;

    public CarModelService(DB db) {
        this.db = db;
        this.collection = db.getCollection("carModel");
    }

    public List<CarModel> findAll() {
        List<CarModel> models = new ArrayList<>();
        DBCursor dbObjects = collection.find();
        while (dbObjects.hasNext()) {
            DBObject dbObject = dbObjects.next();
            models.add(new CarModel((BasicDBObject) dbObject));
        }
        return models;
    }

    public List<CarModel> find(String id) {
        List<CarModel> models = new ArrayList<>();
        DBCursor dbObjects = collection.find(new BasicDBObject("brandId", new ObjectId(id)));
        while (dbObjects.hasNext()) {
            DBObject dbObject = dbObjects.next();
            models.add(new CarModel((BasicDBObject) dbObject));
        }
        return models;
    }

    public CarModel findOne(String id) {
        DBObject dbObjects = collection.findOne(new BasicDBObject("_id", new ObjectId(id)));
        return new CarModel((BasicDBObject) dbObjects);
    }
}
