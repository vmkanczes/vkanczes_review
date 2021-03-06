package main.java;


// SortDemo.java     
//                                              
// Demonstrates various sorting algorithms on an array generated from a
// text area by StringTokenizer
//
// Assumptions: 
// input is a sequence of n numbers a0, a1, a2 ... 
// n is the length of an array
// a0, a1, ... are the sorting keys of the array entries of type Item
// numbers are separated by white space 

import java.awt.*;
import java.applet.Applet;
import java.util.*;   // for class StringTokenizer
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent;

public class SortDemo extends Applet {

		SortDemoData data = new SortDemoData();
        private TextArea input, output;  
        private Button startBtn;
        private Choice choiceBtn;
        private Panel p1, p2, p3;
        private String helpStr = 
                "Please enter an int value or a string of int numbers \n"
                + "If you only entered one number a randomized \n"
                + "array will be created.  \n"
                + "If you give a string of numbers these will be used for sorting.\n\n"
                + "You can choose the sorting algorithm. \n";
                               
        
        // setup the graphical user interface components
        public void init() {
                setSize(700, 600);
                setLayout(new FlowLayout(FlowLayout.LEFT));
                setFont(new Font("Courier", Font.PLAIN, 18));
        
                p1 = new Panel();
                p2 = new Panel();
                p3 = new Panel();
                
                p1.setLayout(new FlowLayout(FlowLayout.LEFT));
                
                input = new TextArea("63 24 12 53 72 18 44 80 ", 5, 60);
                p1.add(input);          // put input on panel
        
                p2.setLayout(new FlowLayout(FlowLayout.LEFT));
               
                                
                choiceBtn = new Choice();
                choiceBtn.setFont(new Font("Times", Font.PLAIN, 18));
                choiceBtn.addItem("  Bubble Sort  ");
                choiceBtn.addItem("  Selection Sort  ");
                choiceBtn.addItem("  Insertion Sort  ");
                choiceBtn.addItem("  Merge Sort  ");
                choiceBtn.addItem("  Quick Sort  ");
                choiceBtn.addItem("  Heap Sort  ");
                choiceBtn.setSize(2000,500);
                System.out.println(choiceBtn.getSize().toString());
                p2.add(choiceBtn);
            
                startBtn = new Button("  Start  ");
                startBtn.setFont(new Font("Times", Font.PLAIN, 18));	
                startBtn.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                                runDemo();
                        }
                });
                p2.add(startBtn);
                
                p3.setLayout(new FlowLayout(FlowLayout.LEFT));
                output = new TextArea(helpStr, 15, 60);
                p3.add(output);
                                    
                add(p1);
                add(p2);
                add(p3);
        }
        
        private void readArray(){
        	 String inputStr = input.getText();
        	 data.initializeArray(inputStr);
        	 
        }
        
             
        // process user's action on the input text field
        public void displayHelp() {
                output.setText(helpStr); 
        }
        
        public void runDemo() {  
                try {
                        output.setText("");
                        long startTime = System.currentTimeMillis();
                        readArray();              
                        long endTime = System.currentTimeMillis();
                        long runTime = endTime - startTime; 
                        String initTime = new String(
                                "Time to create array: "
                                + Long.toString(runTime) + " ms\n");  
                        output.setText(initTime);  
                        
                        int choice = choiceBtn.getSelectedIndex();
                        
                        StringBuffer outputBuf = data.runAlgo(choice);
                        String choiceStr = choiceBtn.getSelectedItem();
                        
                        endTime = System.currentTimeMillis();
                        runTime = endTime - startTime;        
                            
                        // Construct the sorting time in a String
                        String sortTime = new String(
                                "Time needed for " 
                                + choiceStr + ": "
                                + Long.toString(runTime) + " ms\n");    
                        output.append(sortTime);    
                        
                        
                        output.setText(initTime + sortTime + "\r" 
                        		+ outputBuf.toString()); 
                        		
                // check routine
                
                 } catch (NoSuchElementException e) {
       			 System.out.println("Not enough numbers.");
	       	     } catch (NumberFormatException e) {
	       	    	 System.out.println("Please only use integer values. ");
	       	     } catch (NegativeArraySizeException e) {
	       	    	 System.out.println("Negative Array Size.");
	       	     }      
        }        		
}


