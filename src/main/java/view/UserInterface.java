package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserInterface extends JFrame {

        private JTextField polynomial1Text;
        private JTextField polynomial2Text;
        private JTextField polynomialText;
        private JTextField result2Text;
        private JTextField divisionRemainderText;
        private JTextField result1Text;

        private JButton addButton;
        private JButton subtractButton;
        private JButton divideButton;
        private JButton multiplyButton;
        private JButton derivativeButton;
        private JButton integrateButton;


    /**
     * set the aspect of the JFrame
     */
    public UserInterface(){

    this.setTitle("Polynomial Calculator");
    this.setSize(600, 600);
    this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    this.setResizable(false);
    this.setLocationRelativeTo(null);

    JPanel panel = new JPanel();
    panel.setBackground(new Color(218,189,232));
    panel.setLayout(null);
    initializePanel(panel);
    this.setContentPane(panel);
    this.setVisible(true);

}


    /**
     * method to initialize all the components in the panel, set their sizes, positions and color
     * @param panel
     */
    public void initializePanel(JPanel panel){
     JLabel polynomial1Label;
     JLabel polynomial2Label;
     JLabel polynomialLabel;
     JLabel result2Label;
     JLabel divisionRemainderLabel;
     JLabel result1Label;
     JLabel operationForTwo;
     JLabel operationForOne;

    operationForTwo = new JLabel("Operations on 2 polynomials");
    operationForTwo.setFont(new Font("Lucida Console", Font.PLAIN, 16));
    operationForTwo.setBounds(170,10,400,40);
    panel.add(operationForTwo);
    polynomial1Label = new JLabel("Polynomial 1:");
    polynomial1Label.setBounds(10, 80, 80, 30);
    panel.add(polynomial1Label);
    polynomial2Label = new JLabel("Polynomial 2:");
    polynomial2Label.setBounds(10, 120, 80, 30);
    panel.add(polynomial2Label);
    result2Label = new JLabel("Result:");
    result2Label.setBounds(10, 160, 80, 30);
    panel.add(result2Label);

    divisionRemainderLabel = new JLabel("Remainder:");
    divisionRemainderLabel.setBounds(10, 200, 80, 30);
    panel.add(divisionRemainderLabel);

    polynomial1Text = new JTextField();
    polynomial1Text.setBounds(100,80,200,30);
    panel.add(polynomial1Text);

    polynomial2Text = new JTextField();
    polynomial2Text.setBounds(100,120,200,30);
    panel.add(polynomial2Text);

    result2Text = new JTextField();
    result2Text.setBounds(100,160,200,30);
    panel.add(result2Text);

    divisionRemainderText= new JTextField();
    divisionRemainderText.setBounds(100,200,200,30);
    panel.add(divisionRemainderText);

    addButton = new JButton("+");
    addButton.setBounds(390,80,55,55);
    addButton.setBackground(new Color(233,203,240));
    panel.add(addButton);
    subtractButton = new JButton("-");
    subtractButton.setBounds(445,80,55,55);
    subtractButton.setBackground(new Color(233,203,240));
    panel.add(subtractButton);
    divideButton= new JButton(":");
    divideButton.setBounds(390,135,55,55);
    divideButton.setBackground(new Color(233,203,240));
    panel.add(divideButton);
    multiplyButton = new JButton("x");
    multiplyButton.setBounds(445,135,55,55);
    multiplyButton.setBackground(new Color(233,203,240));
    panel.add(multiplyButton);

    operationForOne = new JLabel("Operations on 1 polynomial");
    operationForOne.setFont(new Font("Lucida Console", Font.PLAIN, 16));
    operationForOne.setBounds(170, 280, 400,40);
    panel.add(operationForOne);

    polynomialLabel = new JLabel("Polynomial:");
    polynomialLabel.setBounds(10, 350, 200,30);
    panel.add(polynomialLabel);

    polynomialText = new JTextField();
    polynomialText.setBounds(100,350,200,30);
    panel.add(polynomialText);

    result1Label = new JLabel("Result:");
    result1Label.setBounds(10, 390, 80, 30);
    panel.add(result1Label);

    result1Text = new JTextField();
    result1Text.setBounds(100,390,200,30);
    panel.add(result1Text);

    derivativeButton = new JButton("()'");
    derivativeButton.setBounds(390,350,55,55);
    derivativeButton.setBackground(new Color(233,203,240));
    panel.add(derivativeButton);

    integrateButton = new JButton("\t∫");
    integrateButton.setBounds(445,350,55,55);
    integrateButton.setBackground(new Color(233,203,240));
    panel.add(integrateButton);

    JLabel information = new JLabel("** please introduce polynomials of variable 'x', with degrees in decreasing order ** ");
    information.setBounds(10,500,500,50);
    panel.add(information);
}


    /**
     * methods for adding the action listeners
     * will be used in the controller
     * @param actionListener
     */
    public void addButtonActionListener(final ActionListener actionListener) {
        addButton.addActionListener(actionListener);
    }

    public void subtractButtonActionListener(final ActionListener actionListener) {
        subtractButton.addActionListener(actionListener);
    }

    public void multiplyButtonActionListener(final ActionListener actionListener) {
        multiplyButton.addActionListener(actionListener);
    }

    public void divideButtonActionListener(final ActionListener actionListener) {
        divideButton.addActionListener(actionListener);
    }

    public void derivativeButtonActionListener(final ActionListener actionListener) {
        derivativeButton.addActionListener(actionListener);
    }

    public void integrateButtonActionListener(final ActionListener actionListener) {
        integrateButton.addActionListener(actionListener);
    }

    public void setResult1(String result) {

    result1Text.setText(result);
    }
    public void setResult2(String result) {

    result2Text.setText(result);
    }

    public void setDivisionRemainderText(String result){

        divisionRemainderText.setText(result);
    }

    public String getPolynomial1() {

        return polynomial1Text.getText();
    }
    public String getPolynomial2() {
        return polynomial2Text.getText();
    }
    public String getPolynomial() {
        return polynomialText.getText();
    }

    /**
     * method to display the errors as messages to the user when the validation of the input fails
     * @param exception
     */
    public void displayErrorMessage(Exception exception) {
        if (exception != null) {
            String message = exception.getMessage();
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
