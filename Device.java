/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smarthomeautomation;

/**
 *
 * @author m2975
 */
public abstract class Device 
{
    protected String id;
    protected String name;
    protected boolean isOn;

    public Device(String id, String name) 
    {
        this.id = id;
        this.name = name;
        this.isOn = false;
    }

    public void turnOn() 
    {
        isOn=true;
    }

    public void turnOff() 
    {
        isOn = false;
    }

    public String getDetails() 
    {
        return String.format("ID: %s, Name: %s, Status: %s", id, name, isOn ? "ON" : "OFF");
    }

    public String getId() 
    {
        return id;
    }

    public boolean isOn() 
    {
        return isOn;
    }

    public String getName() 
    {
        return name;
    }
}

