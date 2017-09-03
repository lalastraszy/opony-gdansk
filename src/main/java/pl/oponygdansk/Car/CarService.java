package pl.oponygdansk.Car;

import com.mongodb.*;
import pl.oponygdansk.CarBrand.CarBrandService;
import pl.oponygdansk.CarModel.CarModelService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by magdalena on 05/11/2016.
 */
public class CarService {

    private final DB db;
    private final DBCollection collection;
    private final CarModelService carModelService;
    private final CarBrandService carBrandService;

    public CarService(DB db) {
        this.db = db;
        this.collection = db.getCollection("car");
        this.carModelService = new CarModelService(db);
        this.carBrandService = new CarBrandService(db);
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
            Car car = new Car((BasicDBObject) dbObject);
            car.setModel(carModelService.findOne(car.getModelId()));
            car.setBrand(carBrandService.findOne(car.getBrandId()));
            customers.add(car);
        }
        return customers;
    }
}
