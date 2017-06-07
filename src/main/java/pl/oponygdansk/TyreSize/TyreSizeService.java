package pl.oponygdansk.TyreSize;

import com.mongodb.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by magdalena on 24/04/2017.
 */
public class TyreSizeService {

    private final DB db;
    private final DBCollection collection;

    public TyreSizeService(DB db) {
        this.db = db;
        this.collection = db.getCollection("tyreSize");
    }

    public List<TyreSize> findAll() {
        List<TyreSize> tyreSizes = new ArrayList<TyreSize>();
        DBCursor dbObjects = collection.find();
        while (dbObjects.hasNext()) {
            DBObject dbObject = dbObjects.next();
            tyreSizes.add(new TyreSize((BasicDBObject) dbObject));
        }
        return tyreSizes;
    }
}
