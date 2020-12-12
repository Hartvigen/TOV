import com.company.CarAlarmSystem;
import org.junit.Test;

import static org.junit.Assert.*;

public class test2 {
    CarAlarmSystem CAS = new CarAlarmSystem();
    @Test
    public void tests(){

assertTrue(CAS.closed());
assertFalse(CAS.locked());

CAS.open();

assertTrue(CAS.opened());
assertTrue(CAS.unlocked());

CAS.close();

assertTrue(CAS.closed());
assertFalse(CAS.locked());

    }
}