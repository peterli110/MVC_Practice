/*
 * Student Info: Name=Xinkai Li, ID=16271
 * Subject: CS532B_HW1_Fall_2016
 * Author: PeterLi
 * Filename: HW2.java
 * Date and Time: 2016-9-28 23:32:05
 * Project Name: HW1
 */
package hw1;

/**
 *
 * @author PeterLi
 */
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Field;

class CarModel {
    
    private double length = 200.0;
    private double height = 80.0;
    private double wheelSize = 50.0;
    private Color bodyColor = Color.blue;
    private Color wheelColor = Color.yellow;

    private ArrayList<ActionListener> actionListenerList = new ArrayList<>();

    public void addActionListener(ActionListener l) {
        actionListenerList.add(l);
    }

    public void removeActionListener(ActionListener l) {

        actionListenerList.remove(l);

    }

    private void processEvent(ActionEvent e) {

        for (ActionListener listener : actionListenerList) {

            listener.actionPerformed(e);

        }

    }

    public double getLength() {

        return length;

    }

    public void setLength(double length) {

        this.length = length;

        processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "length"));

    }
    
    public double getHeight() {

        return height;

    }

    public void setHeight(double height) {

        this.height = height;

        processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "height"));

    }
    
    public double getWheelSize() {

        return wheelSize;

    }

    public void setWheelSize(double wheelSize) {

        this.wheelSize = wheelSize;

        processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "wheelSize"));

    }

    public Color getBodyColor() {

        return bodyColor;

    }

    public void setBodyColor(Color bodyColor) {

        this.bodyColor = bodyColor;

        processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "bodyColor"));

    }
    
    public Color getWheelColor() {

        return wheelColor;

    }

    public void setWheelColor(Color wheelColor) {

        this.wheelColor = wheelColor;

        processEvent(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "wheelColor"));

    }

}

class CarController extends JFrame {
    
    CarModel model;

    JTextField carLength = new JTextField("200.0");
    
    JTextField carHeight = new JTextField("80.0");
    
    JTextField carWheelSize = new JTextField("50.0");

    JComboBox carBodyColor = new JComboBox(new String[]{"red", "green","yellow","blue","white","black"});
    
    JComboBox carWheelColor = new JComboBox(new String[]{"red", "green","yellow","blue","white","black"});
    
    JButton button = new JButton("Set");

    public CarController(CarModel model) {

        this.model = model;

        this.setTitle("Car Controller");

        this.setLayout(new GridLayout(6, 2));

        this.getContentPane().add(new JLabel("Length"));

        this.getContentPane().add(carLength);
        
        this.getContentPane().add(new JLabel("Height"));

        this.getContentPane().add(carHeight);
        
        this.getContentPane().add(new JLabel("Wheel Size"));

        this.getContentPane().add(carWheelSize);

        this.getContentPane().add(new JLabel("Body Color"));

        this.getContentPane().add(carBodyColor);
        
        this.getContentPane().add(new JLabel("Wheel Color"));

        this.getContentPane().add(carWheelColor);
        
        this.getContentPane().add(button);

        carLength.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                double length = Double.parseDouble(carLength.getText());

                model.setLength(length);

            }

        });
        
        carHeight.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                double height = Double.parseDouble(carHeight.getText());

                model.setHeight(height);

            }

        });
        
        carWheelSize.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                double wheelSize = Double.parseDouble(carWheelSize.getText());

                model.setWheelSize(wheelSize);

            }

        });

        carBodyColor.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Color bodyColor;
                try {
                    Field field = Class.forName("java.awt.Color").getField(carBodyColor.getSelectedItem().toString());
                    bodyColor = (Color)field.get(null);
                } catch (Exception a) {
                    bodyColor = null;
                }

                model.setBodyColor(bodyColor);;

            }

        });
        carWheelColor.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Color wheelColor;
                try {
                    Field field = Class.forName("java.awt.Color").getField(carWheelColor.getSelectedItem().toString());
                    wheelColor = (Color)field.get(null);
                } catch (Exception a) {
                    wheelColor = null;
                }

                model.setWheelColor(wheelColor);;

            }

        });
                
        button.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent event) {
                
                double length = Double.parseDouble(carLength.getText());

                model.setLength(length);
                
                double height = Double.parseDouble(carHeight.getText());

                model.setHeight(height);
                
                double wheelSize = Double.parseDouble(carWheelSize.getText());

                model.setWheelSize(wheelSize);
                
                Color bodyColor;
                try {
                    Field field = Class.forName("java.awt.Color").getField(carBodyColor.getSelectedItem().toString());
                    bodyColor = (Color)field.get(null);
                } catch (Exception a) {
                    bodyColor = null;
                }

                model.setBodyColor(bodyColor);;
                
                Color wheelColor;
                try {
                    Field field = Class.forName("java.awt.Color").getField(carWheelColor.getSelectedItem().toString());
                    wheelColor = (Color)field.get(null);
                } catch (Exception a) {
                    wheelColor = null;
                }

                model.setWheelColor(wheelColor);;
            }  
        });

        this.setSize(300, 300);

        this.setVisible(true);

    }

}

class CarView extends JFrame implements ActionListener {

    JPanel panel;

    CarModel model;

    public CarView(CarModel model) {

        this.model = model;

        model.addActionListener(this);

        this.setTitle("Car View");

        this.panel = new JPanel() {

            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                int xCenter = getWidth() / 2;

                int yCenter = getHeight() / 2;

                int length = (int) model.getLength();
                
                int height = (int) model.getHeight();
                
                int wheelSize = (int) model.getWheelSize();
                
                Color bodyColor = model.getBodyColor();
                
                Color wheelColor = model.getWheelColor();
                
                g.setColor(bodyColor);
                
                g.fillRect(xCenter - length / 2, yCenter - height / 2, length, height);
                
                g.setColor(wheelColor);
                
                g.fillOval(xCenter - length / 2 + length / 8, yCenter + height / 2 - wheelSize / 2, wheelSize, wheelSize);
                
                g.fillOval(xCenter + length / 8, yCenter + height / 2 - wheelSize / 2, wheelSize, wheelSize);


            }

        };

        this.getContentPane().add(panel);

        this.setSize(600, 300);

        this.setVisible(true);

    }

    public void actionPerformed(ActionEvent event) {

        this.panel.repaint();

    }

}

public class HW1 extends JFrame {

    JButton btnController = new JButton("Controller");

    JButton btnView = new JButton("View");

    CarModel model = new CarModel();

    public HW1() {

        this.setLayout(new GridLayout(2, 1));

        this.getContentPane().add(btnController);

        this.getContentPane().add(btnView);

        btnController.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                CarController controller = new CarController(model);

            }

        });

        btnView.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                CarView view = new CarView(model);

            }

        });

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(300, 300);

        this.setVisible(true);

    }

    public static void main(String[] args) {

        HW1 test=new HW1();

    }

}
