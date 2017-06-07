package pl.oponygdansk.Car;

import com.mongodb.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by magdalena on 05/11/2016.
 */
public class CarService {

    private final DB db;
    private final DBCollection collection;

    public CarService(DB db) {
        this.db = db;
        this.collection = db.getCollection("car");
    }

    public void createCar(Car car) {
        BasicDBObject carDbObject = new BasicDBObject("customerId", car.getCustomerId()).
                append("brandId", car.getBrandId()).
                append("modelId", car.getModelId()).
                append("registrationNumber", car.getRegistrationNumber()).
                append("year", car.getYear()).
                append("createOn", new Date()).
                append("inUse", car.getInUse());
        collection.insert(carDbObject);
        car.setId(carDbObject.get("_id").toString());
    }

    public List<Car> findAll() {
        List<Car> customers = new ArrayList<>();
        DBCursor dbObjects = collection.find();
        while (dbObjects.hasNext()) {
            DBObject dbObject = dbObjects.next();
            customers.add(new Car((BasicDBObject) dbObject));
        }
        return customers;
    }
}
