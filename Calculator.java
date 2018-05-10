package calculator;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

import java.util.Stack;

class Calculator extends JFrame implements ActionListener
{
    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 400;
    private static final int FRAME_X = 0;
    private static final int FRAME_Y = 0;

    private JPanel buttonPanel;
    private JPanel inputOutputPanel;
    private JPanel inputOutputPanel1;

    private  JTextArea info;
    private  JTextField tf;
    private JScrollPane scroll;

    private boolean editable = true;

    public Calculator()
    {
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4,4,5,5));
        //for padding
        buttonPanel.setBorder(new EmptyBorder(10,10,10,10));

        inputOutputPanel = new JPanel();
        inputOutputPanel.setLayout(new FlowLayout());
        //for padding
        inputOutputPanel.setBorder(new EmptyBorder(10,10,10,10));
        
        
        inputOutputPanel1 = new JPanel();
        inputOutputPanel1.setLayout(new FlowLayout());
        //for padding
        inputOutputPanel1.setBorder(new EmptyBorder(10,10,10,10));

        setTitle("Calculator using Java");
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setResizable(false);
        setLocation(FRAME_X,FRAME_Y);

        info = new JTextArea(2,20);
        info.setFont(new Font("SansSerif", Font.PLAIN, 16));
        info.setBackground(Color.white);
        info.setBorder(BorderFactory.createLineBorder(Color.black));
        //info.setPreferredSize(new Dimension(270, 35));
        //info.addActionListener(this);
        tf = new JTextField(20);
        tf.setFont(new Font("SansSerif", Font.PLAIN, 16));
        tf.setBackground(Color.white);
        tf.setBorder(BorderFactory.createLineBorder(Color.black));
        scroll=new JScrollPane(info,scroll.VERTICAL_SCROLLBAR_ALWAYS,scroll.HORIZONTAL_SCROLLBAR_ALWAYS);
        inputOutputPanel.add(scroll);
        inputOutputPanel1.add(tf);
        contentPane.add(inputOutputPanel);
        contentPane.add(inputOutputPanel1);

        //buttons in calculator
        String buttons[] = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2","3","-","0","^","r",".", "+","{","}","="};

        //adding buttons for buttons array
        for (String i : buttons)
        {
            JButton button = new JButton(i);
            button.addActionListener(this);
            buttonPanel.add(button);
        }
        
        contentPane.add(buttonPanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent event)
    {
        //source is from button
    	tf.requestFocus();
        if (event.getSource() instanceof JButton)
        {
            JButton clickedButton = (JButton) event.getSource();
            //if = is clicked then result will be displayed
            if(clickedButton.getText().equals("="))
            {
                addOutput();
            }
            //else we will take input
            else
            {
            	
                addInput(clickedButton.getText());
            }
        }
        //source is from JTextField
        else
        {
            addOutput();
        }
    }

    public void addInput(String line)
    {
        //appending pressed keys to TextField
        //editable will be false if result is displayed
        if(editable)
        {
            tf.setText(tf.getText()+line);
        }
        //result is on the screen
        //editable is false
        //no appending
        //we will add the text
        else
        {
            tf.setText(line);
            editable = true;
        }
    }
    public void addOutput()
    {
        double output = 0;
        
        //converting string into expression
        try
        {
        	ExpCreate ob=new ExpCreate();
        	
        	/*String str=info.getText().toString();
        	System.out.println(info.getText().toString());
        	double res=ob.fo(str);
        	info.append("\n"+res);
        	*/
        	String str=tf.getText().toString();
        	double res=ob.fo(str);
        	info.append("\n"+str+"   equals  "+res);
        	tf.setText(""+res);
        	
        }
        catch(Exception e)
        {
        	String str=""+e;
            tf.setText(str);
        }
        editable = false;
    }
    
    //main method
    public static void main (String [] args)
    {
        Calculator calculator = new Calculator();
        String expression=calculator.info.getText(); 
        
        calculator.setVisible(true);
    }
    
       
     
        
}