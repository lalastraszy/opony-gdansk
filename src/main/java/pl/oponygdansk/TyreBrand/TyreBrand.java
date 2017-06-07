package pl.oponygdansk.TyreBrand;

import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;

/**
 * Created by magdalena on 24/03/2017.
 */
public class TyreBrand {

    private String id;
    private String name;
    private TyreClass tyreClass;

    public enum TyreClass {
        ECONOMY,
        MEDIUM,
        PREMIUM;

        public static TyreClass findByValue(String value){
            for(TyreClass tyreClass : values()){
                if( tyreClass.toString().equals(value.toUpperCase())){
                    return tyreClass;
                }
            }
            return null;
        }
    }

    public TyreBrand(BasicDBObject dbObject) {
        this.id = dbObject.get("_id").toString();
        this.name = dbObject.get("name").toString();
        this.tyreClass = TyreClass.findByValue(dbObject.get("class").toString());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TyreClass getTyreClass() {
        return tyreClass;
    }

    public void setTyreClass(TyreClass tyreClass) {
        this.tyreClass = tyreClass;
    }
}
