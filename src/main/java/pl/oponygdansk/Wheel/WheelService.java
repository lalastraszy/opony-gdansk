package pl.oponygdansk.Wheel;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

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

    public void createWheels(String carId, List<Wheel> wheels) {
        for (Wheel wheel : wheels) {
            wheel.setCreatedOn(new Date());
            wheel.setCarId(carId);

            createWheel(wheel);
        }
    }

    public void createWheel(Wheel wheel) {
        BasicDBObject wheelDbObject = new BasicDBObject("carId", wheel.getCarId()).
                append("brand", wheel.getBrandId()).
                append("size", wheel.getSizeId()).
                append("quantity", wheel.getQuantity()).
                append("rim", wheel.getRim()).
                append("season", wheel.getSeason()).
                append("comments", wheel.getComments()).
                append("isInUse", wheel.isInUse()).
                append("createOn", wheel.getCreatedOn());
        collection.insert(wheelDbObject);
        wheel.setId((wheelDbObject.get("_id")).toString());
    }
}
