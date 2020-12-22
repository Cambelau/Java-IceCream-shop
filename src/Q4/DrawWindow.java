/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Q4;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Owner
 */
public class DrawWindow extends JFrame implements ActionListener {
    
    private JTextField xTextField;
    private JTextField yTextField;
    private JTextField sizeTextField;
    private JButton changeButton;
    public static final int WIDTH = 720;
    public static final int HEIGHT = 490;
    private int xCenter = 250;
    private int yCenter = 200;
    private int size = 100;
    private int forme = 1;
    private Color color = Color.BLACK;
    
    public DrawWindow(){
        super();
        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JMenu shapeMenu = new JMenu("Shape");
        JMenuItem triangleMenuItem = new JMenuItem("Triangle");
        triangleMenuItem.addActionListener(this);
        shapeMenu.add(triangleMenuItem);
        JMenuItem squareMenuItem = new JMenuItem("Square");
        squareMenuItem.addActionListener(this);
        shapeMenu.add(squareMenuItem);
        JMenuItem circleMenuItem = new JMenuItem("Circle");
        circleMenuItem.addActionListener(this);
        shapeMenu.add(circleMenuItem);
        JMenu colorMenu = new JMenu("Color");
        JMenuItem blackMenuItem = new JMenuItem("Black");
        blackMenuItem.addActionListener(this);
        colorMenu.add(blackMenuItem);
        JMenuItem cyanMenuItem = new JMenuItem("Cyan");
        cyanMenuItem.addActionListener(this);
        colorMenu.add(cyanMenuItem);
        JMenuItem magentaMenuItem = new JMenuItem("Magenta");
        magentaMenuItem.addActionListener(this);
        colorMenu.add(magentaMenuItem);
        JMenuItem yellowMenuItem = new JMenuItem("Yellow");
        yellowMenuItem.addActionListener(this);
        colorMenu.add(yellowMenuItem);
        changeButton = new JButton("Change");
        changeButton.addActionListener(this);
        
        JMenuBar mBar = new JMenuBar();
        mBar.add(shapeMenu);
        mBar.add(colorMenu);
        mBar.add(changeButton);
        setJMenuBar(mBar);
        
        xTextField = new JTextField("   ");
        yTextField = new JTextField("   ");
        sizeTextField = new JTextField("   ");
        JLabel xLabel = new JLabel("Center X");
        JLabel yLabel = new JLabel("Center Y");
        JLabel sizeLabel = new JLabel("Size");
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(3, 2, 0,0));
        controlPanel.add(xLabel);
        controlPanel.add(xTextField);
        controlPanel.add(yLabel);
        controlPanel.add(yTextField);
        controlPanel.add(sizeLabel);
        controlPanel.add(sizeTextField);
        getContentPane().add(controlPanel, BorderLayout.EAST);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Circle")) {
            forme = 1;
        } else if (e.getActionCommand().equals("Square")) {
            forme = 0;
        } else if (e.getActionCommand().equals("Triangle")) {
            forme = 2;
        } else if (e.getActionCommand().equals("Black")) {
            color = Color.BLACK;
        } else if (e.getActionCommand().equals("Cyan")) {
            color = Color.CYAN;
        } else if (e.getActionCommand().equals("Magenta")) {
            color = Color.MAGENTA;
        } else if (e.getActionCommand().equals("Yellow")) {
            color = Color.YELLOW;
        }else if (e.getActionCommand().equals("Change")) {
            try{
                String xString = xTextField.getText().trim();
                String yString = yTextField.getText().trim();
                String sizeString = sizeTextField.getText().trim();
                xCenter = Integer.parseInt(xString);
                yCenter = Integer.parseInt(yString);
                size = Integer.parseInt(sizeString);
            } catch (Exception ex){
            }
        } else
            System.out.println("Error.");
        
        repaint();
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        int leftX = xCenter + size/2;
        int rightX = xCenter - size/2;
        int topY = yCenter + size/2;
        int bottomY = yCenter - size/2;
        g.setColor(color);
        switch (forme) {
            case 0:                g.fillRect(leftX, topY, size, size);
                break;
            case 1:                g.fillOval(leftX, topY, size, size);
                break;
            case 2:                int [] xdata = {xCenter, rightX, leftX, xCenter};
                                        int [] ydata = {topY, bottomY, bottomY, topY};
                                        g.fillPolygon(xdata, ydata, 4);
                break;
        }
        
    }
    
}
