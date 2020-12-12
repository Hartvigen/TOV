package com.company;

public class CarAlarmSystem implements ICarAlarmSystem
{
    private boolean _open = false;
    private boolean _armed = false;
    private boolean _locked = false;
    private boolean _flash = false;
    private boolean _sound = false;
    
    private int ARM_DUR = 2;
    private int SOUND_DUR = 3;
    private int FLASH_DUR = 30;
    
    private int armCounter = 0;
    private int soundCounter = 0;
    private int flashCounter = 0;
    
    @Override
    public boolean opened()
    {
        return _open;
    }
    
    @Override
    public boolean closed()
    {
        return !_open;
    }
    
    @Override
    public boolean locked()
    {
        return _locked;
    }
    
    @Override
    public boolean unlocked()
    {
        return !_locked;
    }
    
    @Override
    public boolean flash()
    {
        return _flash;
    }
    
    @Override
    public boolean sound()
    {
        return _sound;
    }
    
    @Override
    public boolean armed()
    {
        return _armed;
    }
    
    @Override
    public void lock()
    {
        _locked = true;
    }
    
    @Override
    public void unlock()
    {
        //CAS turns off when the car is unlocked
        _locked = false;
        _armed = false;
        _sound = false;
        _flash = false;
    
        //counters reset
        armCounter = 0;
        flashCounter = 0;
        soundCounter = 0;
    }
    
    @Override
    public void close()
    {
        _open = false;
    }
    
    @Override
    public void open()
    {
        _open = true;
        if(armed())
        {
            _sound = true;
            _flash = true;
        }
    }
    
    @Override
    public void tick()
    {
        //if 2 seconds have passe since the car was closed and locked the system is armed
        if (locked() && closed())
        {
            if (armCounter == ARM_DUR)
            {
                _armed = true;
            }
            armCounter++;
        }
        
        //sound keeps playing while sound is turned on and sound duration has not been reached
        if(sound() && soundCounter < SOUND_DUR)
        {
            soundCounter++;
        }
        
        //once 3 seconds have passed we turn off the sound
        else if(sound() && soundCounter == SOUND_DUR)
            _sound = false;
        
        //car continues flashing while the flash is turned on and the duration which we want to keep flashing has not transpired
        if(flash() && flashCounter < FLASH_DUR)
        {
            flashCounter++;
        }
        
        //once 30 seconds have passed we turn off the flash
        else if(flash() && flashCounter == FLASH_DUR)
            _flash = false;
        
    }
}
