package ba.ibu.edu.task1;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MonitorTest {

    @Test
    @Order(1)
    public void testGetAge() {
        Monitor monitor = new Monitor("HP", 24, 300.0, 2023, 60);

        // Positive
        assertEquals(1, monitor.getAge());

        // Negative
        assertNotEquals(3, monitor.getAge());
    }

    @Test
    @Order(2)
    public void testGetPrice() {
        Monitor monitor2 = new Monitor("HP", 24, 300.0, 2020, 60);

        // Positive
        assertEquals(240.0, monitor2.getPrice());

        // Negative
        assertNotEquals(300.0, monitor2.getPrice());

        // Additional for branch coverage
        monitor2.setYearOfManufacturing(2023);
        assertEquals(300, monitor2.getPrice());
    }

    @Test
    @Order(3)
    public void testIsPremium() {
        Monitor premiumMonitor = new Monitor("Dell", 27, 1000.0, 2023, 120);
        Monitor nonPremiumMonitor = new Monitor("HP", 24, 300.0, 2024, 60);

        // Positive
        assertTrue(premiumMonitor.isPremium());

        // Negative
        assertFalse(nonPremiumMonitor.isPremium());

        // Additional for branch coverage
        Monitor m1 = new Monitor("Dell", 15.4, 900, 2023, 55);
        assertFalse(m1.isPremium());
    }

    @Test
    @Order(4)
    public void testIs4K() {
        Monitor monitor4K = new Monitor("Samsung", 32, 800.0, 2023, 60);
        Monitor non4KMonitor = new Monitor("HP", 24, 300.0, 2024, 60);

        // Positive
        assertTrue(monitor4K.is4K());

        // Negative
        assertFalse(non4KMonitor.is4K());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/premium_monitors.csv", numLinesToSkip = 1)
    public void testMonitorsForPremium(String manufacturer, double screenSize, double price, int yearOfManufacturing, int maximumRefreshRate){
        Monitor m = new Monitor(manufacturer, screenSize, price, yearOfManufacturing, maximumRefreshRate);
        assertTrue(m.isPremium(), "Monitor " + m + " should be premium");
    }

    @Test
    void testConstructorException(){
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            Monitor m = new Monitor("Xiaomi", 15.4, -199.99, 2019, 60);
        });
        assertEquals("Price must be greater than 0.", e.getMessage());
    }

}