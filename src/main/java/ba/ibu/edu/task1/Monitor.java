package ba.ibu.edu.task1;

import java.time.Year;

public class Monitor {
    private String manufacturer;
    private double screen_size;
    private double price;

    public void setYearOfManufacturing(int yearOfManufacturing) {
        this.yearOfManufacturing = yearOfManufacturing;
    }

    private int yearOfManufacturing;
    private int maximumRefreshRate;

    public Monitor(String manufacturer, double screen_size, double price, int yearOfManufacturing, int maximumRefreshRate) {
        if(price < 0){
            throw new IllegalArgumentException("Price must be greater than 0.");
        }
        this.manufacturer = manufacturer;
        this.screen_size = screen_size;
        this.price = price;
        this.yearOfManufacturing = yearOfManufacturing;
        this.maximumRefreshRate = maximumRefreshRate;
    }

    public int getAge(){
        return 2024 - this.yearOfManufacturing;
    }

    public double getPrice(){
        if(this.getAge() > 3) return 0.8 * this.price;
        return this.price;
    }

    public boolean isPremium(){
        return this.manufacturer.equals("Dell") && this.screen_size >= 27 && this.maximumRefreshRate >= 120;
    }

    public boolean is4K(){
        return this.screen_size >= 32 && this.maximumRefreshRate >= 60;
    }
}
