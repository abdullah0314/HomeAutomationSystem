/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package smarthomeautomation;

/**
 *
 * @author m2975
 */
/*
 Device Class
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

/*
 Fan Class
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

/*
 Light Class
 */
public class Light extends Device 
{
    public Light(String id, String name) 
    {
        super(id, name);
    }
}

/*
 Thermostat Class
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

/*
 Device Manager Class
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

/*
 Main Class
 */
import javax.swing.*;
import java.awt.*;

public class SmartHomeGUI 
{
    private DeviceManager manager;
    private JFrame frame;
    private JTextArea outputArea;

    public SmartHomeGUI() 
    {
        manager=new DeviceManager();

        frame=new JFrame("Home Automation System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel=new JPanel(new BorderLayout());
        outputArea=new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane=new JScrollPane(outputArea);  
        JPanel buttonsPanel=new JPanel(new GridLayout(4, 2, 10, 10));

        String[] btnLabels={"Add Device", "Turn On Device", "Turn Off Device",
                              "Set Fan Speed", "Set Thermostat Temp", "Turn All On", "Turn All Off"};

        JButton[] buttons=new JButton[btnLabels.length];

        for(int i=0; i<btnLabels.length; i++) 
        {
            buttons[i]=new JButton(btnLabels[i]);
            buttonsPanel.add(buttons[i]);
        }

        buttons[0].addActionListener(e->addDevice());
        buttons[1].addActionListener(e->toggleDevice(true));
        buttons[2].addActionListener(e->toggleDevice(false));
        buttons[3].addActionListener(e->setFanSpeed());
        buttons[4].addActionListener(e->setTemperature());
        buttons[5].addActionListener(e->{ manager.turnAllOn(); listDevices(); });
        buttons[6].addActionListener(e->{ manager.turnAllOff(); listDevices(); });

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(buttonsPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void addDevice() 
    {
        try 
        {
            String[] options={"Light", "Fan", "Thermostat"};
            String type=(String) JOptionPane.showInputDialog(frame, "Select device type:", "Add Device",
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if(type == null) 
            {
                return;
            }

            String id=JOptionPane.showInputDialog("Enter Device ID:");
            String name=JOptionPane.showInputDialog("Enter Device Name:");

            switch (type.toLowerCase()) 
            {
                case "light"->manager.addDevice(new Light(id, name));
                case "fan"->manager.addDevice(new Fan(id, name));
                case "thermostat"->manager.addDevice(new Thermostat(id, name));
            }

            listDevices();
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(frame, "Error adding device: " + e.getMessage());
        }
    }

    private void listDevices() 
    {
        outputArea.setText("");
        for(Device d:manager.getDevices()) 
        {
            outputArea.append(d.getDetails() + "\n");
        }
    }

    private void toggleDevice(boolean on) 
    {
        try 
        {
            String id=JOptionPane.showInputDialog("Enter Device ID:");
            Device d=manager.findDeviceById(id);
            if(d!=null) 
            {
                if (on) d.turnOn();
                else d.turnOff();
                listDevices();
            } 
            else 
            {
                JOptionPane.showMessageDialog(frame, "Device not found.");
            }
        } catch(Exception e) 
        {
            JOptionPane.showMessageDialog(frame, "Error toggling device: " + e.getMessage());
        }
    }

    private void setFanSpeed() 
    {
        try 
        {
            String id=JOptionPane.showInputDialog("Enter Fan ID:");
            Device d=manager.findDeviceById(id);
            if(d instanceof Fan fan) 
            {
                int speed=Integer.parseInt(JOptionPane.showInputDialog("Enter speed (1-5):"));
                fan.setSpeed(speed);
                listDevices();
            } 
            else 
            {
                JOptionPane.showMessageDialog(frame, "Fan not found or invalid ID.");
            }
        } 
        catch(NumberFormatException e) 
        {
            JOptionPane.showMessageDialog(frame, "Invalid speed entered.");
        } 
        catch(Exception e) 
        {
            JOptionPane.showMessageDialog(frame, "Error setting fan speed: " + e.getMessage());
        }
    }

    private void setTemperature() 
    {
        try 
        {
            String id=JOptionPane.showInputDialog("Enter Thermostat ID:");
            Device d=manager.findDeviceById(id);
            if(d instanceof Thermostat thermostat) 
            {
                int temp=Integer.parseInt(JOptionPane.showInputDialog("Enter temperature:"));
                thermostat.setTemperature(temp);
                listDevices();
            } 
            else 
            {
                JOptionPane.showMessageDialog(frame, "Thermostat not found or invalid ID.");
            }
        } 
        catch (NumberFormatException e) 
        {
            JOptionPane.showMessageDialog(frame, "Invalid temperature entered.");
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(frame, "Error setting temperature: " + e.getMessage());
        }
    }

    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(SmartHomeGUI::new);
    }
}
