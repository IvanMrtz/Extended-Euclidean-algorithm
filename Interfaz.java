
/**
 * Write a description of class Interfaz here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.HashMap;

public class Interfaz extends JFrame
{
    int count = 1;
    
    JTextField aValue, bValue;
    JTextArea result;
    JButton generateButton;
    Container container;
    
    public Interfaz(String title) {
        super(title);
        
        JOptionPane.showMessageDialog(null, "the algorithm assumes that b is different from 0.");
        
        setSize(450, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        container = getContentPane();
        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        
        //Input to a value
        constraints.fill = GridBagConstraints.HORIZONTAL; 
        
        aValue = new JTextField(17);
        constraints.ipady = 15;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        container.add(aValue, constraints);
        
        //Input to b value
        constraints.fill = GridBagConstraints.HORIZONTAL;
        
        bValue = new JTextField(10);
        constraints.ipady = 15;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        container.add(bValue, constraints);
        
        //Result area
        result = new JTextArea("Step by step to find the solution:");
        JScrollPane scroll;
        scroll = new JScrollPane(result);
        result.setEditable(false);
        constraints.ipady = 70;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.gridheight = 2;
        constraints.insets = new Insets(20,0,0,0);
        container.add(scroll, constraints);
        
        //Calculate button
        generateButton = new JButton("Start");
        
        ListenCalculateButton listener = new ListenCalculateButton();
        generateButton.addActionListener(listener);
        
        constraints.ipady = 20;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL; 
        constraints.gridwidth = 2;
        constraints.gridheight = 2;
        constraints.weighty = 1;
        constraints.insets = new Insets(110,0,0,0);
        container.add(generateButton, constraints);
    }
    
    public int[] Euclides_Extended(String a, int iB, String concatenator, int saveB, int[] savePairOfB, int[] savePairOfModule) {
        int iA;
        
        if(concatenator == "") {
            concatenator = Integer.toString(iB);

            iA = Integer.parseInt(a);
            
            savePairOfB[0] = 0;
            savePairOfB[1] = 1;
            
            savePairOfModule[0] = 1;
            savePairOfModule[1] = -1 * (iA - iA%iB)/iB;
        }else {
            iA = saveB;
            
            int[] savedCombinedMultiplied = {savePairOfModule[0] * -1*(iA - iA%iB)/iB, savePairOfModule[1] * -1*(iA - iA%iB)/iB};
            int[] modulePair = {savedCombinedMultiplied[0] + savePairOfB[0], savedCombinedMultiplied[1] + savePairOfB[1]};
            
            savePairOfB = savePairOfModule;
            savePairOfModule = modulePair;
        }
        String module = "(" + a + ")" + "-" + (iA - iA%iB)/iB + "*("+ concatenator + ")";
        result.append("\nStep n°" + count + "  --->  " + module + " = " + iA%iB);
        count++;
        
        if(iA%iB != 0 && iB % (iA%iB) != 0) {
            return Euclides_Extended(concatenator, iA%iB, module, iB, savePairOfB, savePairOfModule);
        }else {
            return savePairOfModule;
        }
    }
    
    public void USABLE_Euclides_Extended(int a, int b) {
        int[] values = Euclides_Extended(Integer.toString(a), b, "", 0, new int[2], new int[2]);
        
        result.append("\nStep n°" + count + "  --->  " + a + "*(" + values[0] + ")" + " + " + b + "*("+ values[1] + ")"  + " = " + (a*values[0] + b*values[1]));
    }
    
    private class ListenCalculateButton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int a = Integer.parseInt(aValue.getText());//only to check if a and b, are valid inputs
                int b = Integer.parseInt(bValue.getText());
                
                count = 1;
                result.setText("Step by step to find the solution:");
                
                USABLE_Euclides_Extended(a, b);
            }catch(Exception err) {
                JOptionPane.showMessageDialog(null, "Put valid input values for 'a' and 'b'");    
            }
            
        }
    }
}
