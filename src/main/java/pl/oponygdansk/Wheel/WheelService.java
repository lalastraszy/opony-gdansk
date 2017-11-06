package pl.oponygdansk.Wheel;

import com.mongodb.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by magdalena on 19/02/2017.
 */
public class WheelService {

    private final DB db;
    private final DBCollection collection;

    public WheelService(DB db) {
        this.db = db;
        this.collection = db.getCollection("wheel");
    }

    public List<Wheel> createWheels(String carId, List<Wheel> wheels) {
        List<Wheel> results = new ArrayList<>();
        for (Wheel wheel : wheels) {
            wheel.setCreatedOn(new Date());
            wheel.setCarId(carId);
            results.add(createWheel(wheel));
        }
        return results;
    }

    private Wheel createWheel(Wheel wheel) {
        BasicDBObject wheelDbObject = new BasicDBObject("carId", wheel.getCarId()).
                append("brandId", wheel.getBrandId()).
                append("sizeId", wheel.getSizeId()).
                append("quantity", wheel.getQuantity()).
                append("rim", wheel.getRim()).
                append("season", wheel.getSeason()).
                append("comments", wheel.getComments()).
                append("isInUse", wheel.isInUse()).
                append("createOn", wheel.getCreatedOn());
        collection.insert(wheelDbObject);
        wheel.setId((wheelDbObject.get("_id")).toString());
        return wheel;
    }

    public List<Wheel> findAllByCarId(String carId) {
        List<Wheel> wheels = new ArrayList<>();
        DBCursor dbObjects = collection.find(new BasicDBObject("carId", carId));
        while (dbObjects.hasNext()) {
            DBObject dbObject = dbObjects.next();
            wheels.add(new Wheel((BasicDBObject) dbObject));
        }
        return wheels;
    }
}
