/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smarthomeautomation;

/**
 *
 * @author m2975
 */
public class Thermostat extends Device 
{
    private int temperature;

    public Thermostat(String id, String name) 
    {
        super(id, name);
        this.temperature=22;
    }

    public void setTemperature(int temperature) 
    {
        this.temperature=temperature;
    }

    public int getTemperature() 
    {
        return temperature;
    }

    @Override
    public String getDetails() 
    {
        return super.getDetails() + ", Temp: " + temperature + "°C";
    }
}

