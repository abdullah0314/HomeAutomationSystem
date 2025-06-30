/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smarthomeautomation;

/**
 *
 * @author m2975
 */
import java.util.*;

public class DeviceManager 
{
    private List<Device> devices;

    public DeviceManager() 
    {
        devices=new ArrayList<>();
    }

    public void addDevice(Device device) 
    {
        devices.add(device);
    }

    public List<Device> getDevices() 
    {
        return devices;
    }

    public Device findDeviceById(String id) 
    {
        for(Device d:devices) 
        {
            if(d.getId().equalsIgnoreCase(id)) 
            {
                return d;
            }
        }
        return null;
    }

    public void turnAllOn() 
    {
        for(Device d:devices) 
        {
            d.turnOn();
        }
    }

    public void turnAllOff() 
    {
        for(Device d:devices) 
        {
            d.turnOff();
        }
    }
}

