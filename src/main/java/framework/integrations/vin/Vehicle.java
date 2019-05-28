package framework.integrations.vin;

public class Vehicle {
    private String year;
    private String make;
    private String model;
    private String vinNumber;

    public Vehicle(String year, String make, String model, String vinNumber){
        this.year = year;
        this.make = make;
        this.model = model;
        this.vinNumber = vinNumber;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getVinNumber() {
        return vinNumber;
    }
}
