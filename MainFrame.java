/**
 * The main frame establishing the GUI.
 * @author EFE ACER
 * @version 1.0
 */
package TowerOfHanoi;
import java.util.LinkedList;
import java.math.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class MainFrame extends JFrame {    
    //Constants
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 660;
    private static final int RODS_WIDTH = 550;
    private static final int RODS_HEIGHT = 400;
    private static final int INITIAL_NUMBER_OF_DISKS = 4;
    private static final int INITIAL_ROD = 0;
    private static final String TITLE = "Towers of Hanoi ";
    private static final String DISK_NUMBER_LABEL = "Select the number of disks:";
    private static final int DELAY = 1000;
    
    //Instance Variables
    private JPanel panel;
    private JButton nextButton;
    private JButton animateButton;
    private JLabel diskNumberLabel,MH,N;
    public JComboBox<Integer> diskNumberSelection;
    public JComboBox<String> history;
    private JComponent mainComponent;
    private Rods rods;
    private Timer timer;
    public LinkedList<Integer> movesToSolve;
    private JTextArea txt ;
    
    private int cnt=1;
    /**
     * Constructor of the main frame.
     */
    public MainFrame() {
        rods = new Rods(INITIAL_NUMBER_OF_DISKS, INITIAL_ROD);
        createComponents();
        setTitle(TITLE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);
    }
    
    /**
     * Creates the smaller GUI elements and adds them to the frame.
     */
    static void towerOfHanoi(int n, String from_rod, String to_rod, String helper_rod)
    {
        if (n == 1)
        {
            System.out.println("Take disk 1 from rod " +  from_rod + " to rod " + to_rod);
            return;
        }
        towerOfHanoi(n-1, from_rod, helper_rod, to_rod);
        System.out.println("Take disk " + n + " from rod " +  from_rod + " to rod " + to_rod);
        towerOfHanoi(n-1, helper_rod, to_rod, from_rod);
    }
    private void createComponents() {
        timer = new Timer(DELAY, new AnimationListener());
        nextButton = new JButton("Next");
        animateButton = new JButton("Animate");
        nextButton.addActionListener(new NextButtonListener());
        animateButton.addActionListener(new AnimateButtonListener());
        
        txt = new JTextArea("Number of disk: 4\n",10,25);
        txt.setPreferredSize(new Dimension(100,100));
        //JScrollPane scrollPane= new JScrollPane(txt,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        txt.setLineWrap(true);
        panel = new JPanel();
        panel.setLayout(new GridLayout(3,1));
        
        
        
        mainComponent = new MainComponent(rods);
        
        mainComponent.setPreferredSize(new Dimension(RODS_WIDTH, RODS_HEIGHT));
        
        
        
        panel.add(mainComponent);
        
        diskNumberLabel = new JLabel(DISK_NUMBER_LABEL);
        MH=new JLabel("Moves History: ");
        N=new JLabel("                 ");
        diskNumberSelection = new JComboBox<Integer>(new Integer[] {1, 2, 3, 4, 5, 6, 7,8,9,10,11});
        diskNumberSelection.setSelectedItem(4);
        diskNumberSelection.addActionListener(new DiskNumberChoiceListener());
        
      
        rods.solveTowersOfHanoi(rods.getRodArray()[INITIAL_ROD].getDisksOnTop().size(), INITIAL_ROD, 1, 2);
        rods.initializeRods((int) diskNumberSelection.getSelectedItem(), INITIAL_ROD);
        FlowLayout fl=new FlowLayout();
        fl.setHgap(10);
        fl.setAlignment(FlowLayout.CENTER);
        panel.setLayout(fl);
        panel.add(diskNumberLabel);
        panel.add(diskNumberSelection);
        panel.add(nextButton);
        panel.add(animateButton);
        //panel.add(MH);
        panel.add(txt);
       // panel.add(scrollPane);
        add(panel);
       
        
    }
    
    /**
     * Inner class for the ActionListener used by the next button.
     */
    private class NextButtonListener implements ActionListener {
        /**
         * Performs a move to solve the Towers of Hanoi Problem.
         * @param event The action event.
         */
        @Override
        public void actionPerformed(ActionEvent event) {
        	
            timer.stop();
            movesToSolve = rods.getMovesToSolve();
            
            if (!movesToSolve.isEmpty()) {
            	movesToSolve.get(0);
                rods.moveDisk(movesToSolve.removeFirst(), movesToSolve.removeFirst());
                	((MainComponent) mainComponent).update();
                	
            }
            else return;
            if(cnt%10==0) {
            	txt.setText("");
            	cnt=0;
            }	
            txt.append(rods.ntc);
            cnt++;
            
        }
    }
    
    /**
     * Inner class for the ActionListener used by the animate button.
     */
    private class AnimateButtonListener implements ActionListener {
        /**
         * Triggers the animation process.
         * @param event The action event.
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            timer.start();
        }
    }
    
    /**
     * Inner class for the ActionListener used by the timer.
     */
    private class AnimationListener implements ActionListener {
        /**
         * Animates the disk in a way to solve the Towers of Hanoi problem.
         * @param event The action event.
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            movesToSolve = rods.getMovesToSolve();
            if (!movesToSolve.isEmpty()) {
                rods.moveDisk(movesToSolve.removeFirst(), movesToSolve.removeFirst());
                ((MainComponent) mainComponent).update();
            } 
            else return;
            if(cnt%10==0) {
            	txt.setText("");
            	cnt=0;
            }	
            txt.append(rods.ntc);
            cnt++;
        }
    } 
    
    /**
     * Inner class for the ActionListener used by the combo box.
     */
    private class DiskNumberChoiceListener implements ActionListener {
        /**
         * Changes the disk number according to the selected number on the combo box.
         * @param event The action event.
         */
        @Override
        public void actionPerformed(ActionEvent event) {
            timer.stop();
            timer = new Timer(DELAY, new AnimationListener());
            cnt=1;
            rods = new Rods((int) diskNumberSelection.getSelectedItem(), INITIAL_ROD);
            ((MainComponent) mainComponent).updateRods(rods);
            txt.setText("Number of disk: "+rods.getRodArray()[INITIAL_ROD].getDisksOnTop().size()+"\n");
            rods.solveTowersOfHanoi(rods.getRodArray()[INITIAL_ROD].getDisksOnTop().size(), INITIAL_ROD, 1, 2);
            rods.initializeRods((int) diskNumberSelection.getSelectedItem(), INITIAL_ROD);
        }
    } 
    public int getDisk() {
    	return (int)diskNumberSelection.getSelectedItem();
    }
 
}
