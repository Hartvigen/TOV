import com.company.CarAlarmSystem;
import org.junit.Test;

import static org.junit.Assert.*;

public class test6 {
    CarAlarmSystem CAS = new CarAlarmSystem();
    @Test
    public void tests(){

assertTrue(CAS.closed());
assertFalse(CAS.locked());

CAS.lock();

assertTrue(CAS.locked());
assertTrue(CAS.closed());
assertFalse(CAS.unlocked());
assertFalse(CAS.opened());

CAS.open();

assertTrue(CAS.opened());
assertTrue(CAS.locked());

    }
}