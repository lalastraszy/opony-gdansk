package pl.oponygdansk.Wheel;

import com.mongodb.*;
import org.bson.types.ObjectId;

import java.util.*;

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

    public List<Wheel> create(String carId, List<Wheel> wheels) {
        List<Wheel> results = new ArrayList<>();
        for (Wheel wheel : wheels) {
            wheel.setCreatedOn(new Date());
            wheel.setCarId(carId);
            results.add(create(wheel));
        }
        return results;
    }

    public List<Wheel> update(String carId, List<Wheel> wheels) {
        List<Wheel> results = new ArrayList<>();
        for (Wheel wheel : wheels) {
            wheel.setModifiedOn(new Date());
            wheel.setCarId(carId);
            results.add(update(wheel));
        }
        return results;
    }

    public List<Wheel> delete(List<Wheel> wheels) {
        List<Wheel> results = new ArrayList<>();
        for (Wheel wheel : wheels) {
            results.add(delete(wheel));
        }
        return results;
    }

    private Wheel create(Wheel wheel) {
        BasicDBObject wheelDbObject = new BasicDBObject("carId", wheel.getCarId()).
                append("brandId", wheel.getBrandId()).
                append("sizeId", wheel.getSizeId()).
                append("quantity", wheel.getQuantity()).
                append("rim", wheel.getRim()).
                append("season", wheel.getSeason()).
                append("comments", wheel.getComments()).
                append("isInUse", wheel.isInUse()).
                append("createOn", new Date());
        collection.insert(wheelDbObject);
        wheel.setId((wheelDbObject.get("_id")).toString());
        return wheel;
    }

    private Wheel update(Wheel wheel) {
        BasicDBObject searchQuery = new BasicDBObject().append("_id", new ObjectId(wheel.getId()));
        BasicDBObject wheelDbObject = new BasicDBObject("carId", wheel.getCarId()).
                append("brandId", wheel.getBrandId()).
                append("sizeId", wheel.getSizeId()).
                append("quantity", wheel.getQuantity()).
                append("rim", wheel.getRim()).
                append("season", wheel.getSeason()).
                append("comments", wheel.getComments()).
                append("isInUse", wheel.isInUse()).
                append("modifiedOn", new Date());
        BasicDBObject updateQuery = new BasicDBObject().append("$set", wheelDbObject);
        collection.update(searchQuery, updateQuery);
        return this.find(wheel.getId());
    }

    private Wheel delete(Wheel wheel) {
        BasicDBObject searchQuery = new BasicDBObject().append("_id", new ObjectId(wheel.getId()));
        collection.findAndRemove(searchQuery);
        return wheel;
    }

    public Wheel find(String id) {
        return new Wheel((BasicDBObject) collection.findOne(new BasicDBObject("_id", new ObjectId(id))));
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

    public List<Wheel> updateWheels(String carId, List<Wheel> wheels) {
        Set<Wheel> existingWheelsSet = new HashSet<>(this.findAllByCarId(carId));
        List<Wheel> result = new ArrayList<>();

        Set<Wheel> updateWheelsSet = new HashSet<>(wheels);
        updateWheelsSet.retainAll(existingWheelsSet);
        result.addAll(update(carId, new ArrayList<>(updateWheelsSet)));

        Set<Wheel> insertWheelsSet = new HashSet<>(wheels);
        insertWheelsSet.removeAll(existingWheelsSet);
        result.addAll(create(carId,  new ArrayList<>(insertWheelsSet)));

        Set<Wheel> deleteWheelsSet = new HashSet<>(existingWheelsSet);
        deleteWheelsSet.removeAll(new HashSet<>(wheels));
        result.addAll(delete(new ArrayList<>(deleteWheelsSet)));

        return wheels;
    }

}
