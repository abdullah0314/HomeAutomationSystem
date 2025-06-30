/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smarthomeautomation;

/**
 *
 * @author m2975
 */
public class Fan extends Device 
{
    private int speed;

    public Fan(String id, String name) 
    {
        super(id, name);
        this.speed=1;
    }

    public void setSpeed(int speed) 
    {
        if(speed>=1 && speed<=5) 
        {
            this.speed=speed;
        }
    }

    public int getSpeed() 
    {
        return speed;
    }

    @Override
    public String getDetails() 
    {
        return super.getDetails()+", Speed: "+speed;
    }
}
