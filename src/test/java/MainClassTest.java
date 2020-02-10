import org.junit.Assert;
import org.junit.Test;
import startApp.MainClass;

public class MainClassTest {

    @Test
    public void findPriceByZoneTest() {
        int price = MainClass.findPriceByZone(1, 2);
        Assert.assertEquals(240, price);
    }

    @Test
    public void findZoneByGivenStationTest() {
        int zone = MainClass.findZoneByGivenStation("A");
        Assert.assertEquals(1, zone);
    }

}
