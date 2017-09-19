package pl.oponygdansk.Car;

import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;
import pl.oponygdansk.CarBrand.CarBrand;
import pl.oponygdansk.CarModel.CarModel;
import pl.oponygdansk.Wheel.Wheel;

import java.util.Date;
import java.util.List;

/**
 * Created by magdalena on 05/11/2016.
 */
public class Car {

    private String id;
    private String customerId;
    private String brandId;
    private CarBrand brand;
    private String modelId;
    private CarModel model;
    private String registrationNumber;
    private String year;
    private Date createdOn;
    private Boolean isInUse;
    private List<Wheel> wheels;

    public Car(BasicDBObject dbObject) {
        this.id = ((ObjectId) dbObject.get("_id")).toString();
        this.customerId = dbObject.getString("customerId");
        this.brandId = dbObject.getString("brandId");
        this.modelId = dbObject.getString("modelId");
        this.registrationNumber = dbObject.getString("registrationNumber");
        this.year = dbObject.getString("year");
        this.createdOn = dbObject.getDate("createdOn");
        this.isInUse = dbObject.getBoolean("inUse");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public CarBrand getBrand() {
        return brand;
    }

    public void setBrand(CarBrand brand) {
        this.brand = brand;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public CarModel getModel() {
        return model;
    }

    public void setModel(CarModel model) {
        this.model = model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Boolean getInUse() {
        return isInUse;
    }

    public void setInUse(Boolean inUse) {
        isInUse = inUse;
    }

    public List<Wheel> getWheels() {
        return wheels;
    }

    public void setWheels(List<Wheel> wheels) {
        this.wheels = wheels;
    }
}
