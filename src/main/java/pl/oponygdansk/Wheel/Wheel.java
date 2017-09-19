package pl.oponygdansk.Wheel;

import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * Created by magdalena on 19/02/2017.
 */
public class Wheel {

    private String id;
    private String carId;
    private String brandId;
    private String sizeId;
    private int quantity;
    private String rim;
    private String season;
    private String comments;
    private boolean isInUse;
    private Date createdOn;

    public Wheel(BasicDBObject dbObject) {
        this.id = ((ObjectId) dbObject.get("_id")).toString();
        this.carId = dbObject.getString("carId");
        this.brandId = dbObject.getString("brandId");
        this.sizeId = dbObject.getString("sizeId");
        this.quantity = dbObject.getInt("quantity");
        this.rim = dbObject.getString("rim");
        this.season = dbObject.getString("season");
        this.comments = dbObject.getString("comments");
        this.isInUse = dbObject.getBoolean("inUse");
        this.createdOn = dbObject.getDate("createdOn");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getSizeId() {
        return sizeId;
    }

    public void setSizeId(String sizeId) {
        this.sizeId = sizeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRim() {
        return rim;
    }

    public void setRim(String rim) {
        this.rim = rim;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean isInUse() {
        return isInUse;
    }

    public void setInUse(boolean inUse) {
        isInUse = inUse;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
