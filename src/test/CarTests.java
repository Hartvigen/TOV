import com.company.CarAlarmSystem;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarTests {
    CarAlarmSystem CAS = new CarAlarmSystem();

    private void tickx(int x){
        for(int i = 0; i < x; i++){
            CAS.tick();
        }
    }

    @Test
    public void openDoor(){
        CAS.open();
        assertTrue(CAS.opened());
        assertFalse(CAS.closed());
    }

    @Test
    public void closeDoor(){
        CAS.close();
        assertTrue(CAS.closed());
        assertFalse(CAS.opened());
    }
    
    @Test
    public void lockDoor(){
        CAS.lock();
        assertTrue(CAS.locked());
        assertFalse(CAS.unlocked());
    }
    
    public void unlockDoor(){
        CAS.unlock();
        assertTrue(CAS.unlocked());
        assertFalse(CAS.locked());
    }

    @Test
    public void systemTurnOn(){
        CAS.lock();
        assertFalse(CAS.armed());
        tickx(2);
        assertFalse(CAS.armed());
        CAS.tick();
        assertTrue(CAS.armed());
        assertFalse((CAS.flash()));
        assertFalse(CAS.sound());

    }
    
    @Test
    public void flashAndSound(){
        CAS.lock();
        tickx(3);
        CAS.open();
        assertTrue(CAS.flash());
        assertTrue(CAS.sound());
        CAS.unlock();
        assertFalse(CAS.flash());
        assertFalse(CAS.sound());
        assertFalse(CAS.armed());
    }
    
    @Test
    public void legitCarOpening(){
        CAS.lock();
        tickx(30);
        CAS.unlock();
        assertFalse(CAS.armed());
        CAS.open();
        assertFalse(CAS.flash());
        assertFalse(CAS.sound());
        CAS.close();
        CAS.lock();
        tickx(5);
        assertTrue(CAS.armed());
    }
    
    //we test that the sound and flashing stops after the designated time and doesn't just keep going
    @Test
    public void flashAndSoundStops()
    {
        CAS.lock();
        tickx(5);
        CAS.open();
        assertTrue(CAS.flash());
        assertTrue(CAS.sound());
        tickx(4);
        assertTrue(CAS.flash());
        assertFalse(CAS.sound());
        tickx(27);
        assertFalse(CAS.flash());
        assertFalse(CAS.sound());
    }
}
