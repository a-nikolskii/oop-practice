package practice1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author a.nikolskii
 */
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Keypad extends JPanel{
    
    private final ActionListener actionListener;
    
    public Keypad(ActionListener actionListener){
        this.actionListener = actionListener;
        initComponents();
    }
    
    private final String[][] keyMap = {
        {"M+","M-","%","C"},
        {"7","8","9","/"},
        {"4","5","6","x"},
        {"1","2","3","-"},
        {"0",".","=","+"}
    };
    
    private void initComponents(){
        this.setLayout(new GridLayout(keyMap.length,keyMap.length));
        
        for(String[] line : keyMap){
            for(String caption : line){
                JButton btn = new JButton(caption);
                btn.addActionListener(actionListener);
                this.add(btn);
            }
        }
    }   
}