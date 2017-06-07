package pl.oponygdansk.TyreBrand;

import com.mongodb.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by magdalena on 10/04/2017.
 */
public class TyreBrandService {

    private final DB db;
    private final DBCollection collection;

    public TyreBrandService(DB db) {
        this.db = db;
        this.collection = db.getCollection("tyreBrand");
    }

    public List<TyreBrand> findAll() {
        List<TyreBrand> tyreBrands = new ArrayList<>();
        DBCursor dbObjects = collection.find();
        while (dbObjects.hasNext()) {
            DBObject dbObject = dbObjects.next();
            tyreBrands.add(new TyreBrand((BasicDBObject) dbObject));
        }
        return tyreBrands;
    }
}

