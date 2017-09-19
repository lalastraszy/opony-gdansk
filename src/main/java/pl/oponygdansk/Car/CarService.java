package pl.oponygdansk.Car;

import com.mongodb.*;
import pl.oponygdansk.CarBrand.CarBrandService;
import pl.oponygdansk.CarModel.CarModelService;
import pl.oponygdansk.Wheel.WheelService;


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
    private final WheelService wheelService;

    public CarService(DB db) {
        this.db = db;
        this.collection = db.getCollection("car");
        this.carModelService = new CarModelService(db);
        this.carBrandService = new CarBrandService(db);
        this.wheelService = new WheelService(db);

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
        String id = carDbObject.get("_id").toString();
        car.setId(id);
        wheelService.createWheels(id, car.getWheels());
    }

    public List<Car> findAll() {
        List<Car> cars = new ArrayList<>();
        DBCursor dbObjects = collection.find();
        while (dbObjects.hasNext()) {
            DBObject dbObject = dbObjects.next();
            Car car = new Car((BasicDBObject) dbObject);
            car.setModel(carModelService.findOne(car.getModelId()));
            car.setBrand(carBrandService.findOne(car.getBrandId()));
            car.setWheels(wheelService.findAllByCarId(car.getId()));
            cars.add(car);
        }
        return cars;
    }
}
