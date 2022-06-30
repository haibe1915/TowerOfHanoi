/**
 * The main frame establishing the GUI.
 * @author EFE ACER
 * @version 1.0
 */
package TowerOfHanoi;
import java.util.LinkedList;
import java.math.*;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.Timer;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

public class MainFrame extends JFrame {    
    //Constants
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 720;
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
    private JLabel diskNumberLabel,MH;
    public JComboBox<Integer> diskNumberSelection;
    private JComponent mainComponent;
    private Rods rods;
    private Timer timer;
    private LinkedList<Integer> movesToSolve;
    private JTextArea txt ;
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
        
        txt = new JTextArea("Stack Overflow\n"
        		+ "About\n"
        		+ "Products\n"
        		+ "For Teams\n"
        		+ "Search…\n"
        		+ "Home\n"
        		+ "PUBLIC\n"
        		+ "Questions\n"
        		+ "Tags\n"
        		+ "Users\n"
        		+ "Companies\n"
        		+ "COLLECTIVES\n"
        		+ "Explore Collectives\n"
        		+ "TEAMS\n"
        		+ "Stack Overflow for Teams – Start collaborating and sharing organizational knowledge. \n"
        		+ "Dynamically refresh JTextArea as processing occurs?\n"
        		+ "Asked 13 years, 3 months ago\n"
        		+ "Modified 9 years, 1 month ago\n"
        		+ "Viewed 53k times\n"
        		+ "24\n"
        		+ "\n"
        		+ "12\n"
        		+ "I am trying to create a very simple Swing UI that logs information onto the screen via a JTextArea as processing occurs in the background. When the user clicks a button, I want each call to:\n"
        		+ "\n"
        		+ "textArea.append(someString + \"\\n\");\n"
        		+ "to immediately show up in the UI.\n"
        		+ "\n"
        		+ "At the moment, the JTextArea does not show all log information until the processing has completed after clicking the button. How can I get it to refresh dynamically?\n"
        		+ "\n"
        		+ "java\n"
        		+ "multithreading\n"
        		+ "swing\n"
        		+ "Share\n"
        		+ "Follow\n"
        		+ "edited Mar 10, 2009 at 16:00\n"
        		+ "user avatar\n"
        		+ "Michael Myers♦\n"
        		+ "184k4545 gold badges285285 silver badges291291 bronze badges\n"
        		+ "asked Mar 10, 2009 at 8:23\n"
        		+ "user avatar\n"
        		+ "digiarnie\n"
        		+ "21.3k2929 gold badges7777 silver badges126126 bronze badges\n"
        		+ "Add a comment\n"
        		+ "6 Answers\n"
        		+ "Sorted by:\n"
        		+ "\n"
        		+ "Highest score (default)\n"
        		+ "47\n"
        		+ "Try this:\n"
        		+ "\n"
        		+ "jTextArea.update(jTextArea.getGraphics());\n"
        		+ "Share\n"
        		+ "Follow\n"
        		+ "edited Jan 5, 2011 at 7:13\n"
        		+ "user avatar\n"
        		+ "Michael Mrozek\n"
        		+ "162k2828 gold badges165165 silver badges171171 bronze badges\n"
        		+ "answered Oct 17, 2009 at 8:32\n"
        		+ "user avatar\n"
        		+ "petr\n"
        		+ "47944 silver badges22 bronze badges\n"
        		+ "it is perfect. Thank you :) – \n"
        		+ "olyanren\n"
        		+ " Apr 29, 2013 at 9:09\n"
        		+ "1\n"
        		+ "It works and is very easy to implement. That should be the best answer. – \n"
        		+ "Nautilus\n"
        		+ " Oct 5, 2015 at 9:30\n"
        		+ "This should be the answer. – \n"
        		+ "Gideon\n"
        		+ " Jan 30, 2019 at 8:41\n"
        		+ "Add a comment\n"
        		+ "24\n"
        		+ "I ran into the same issue with my application. I had a \"Run\" button my application that performed some actions and outputted the results to a JTextArea. I had to call the method from a Thread. Here is what I did.\n"
        		+ "\n"
        		+ "I have several radio buttons of actions that can be done, and then one \"Run\" button to execute that particular action. I have an action called Validate. So when I check that radio button and click the \"Run\" button, it calls the method validate(). So I first placed this method into an inner class that implemented Runnable\n"
        		+ "\n"
        		+ "class ValidateThread implements Runnable {\n"
        		+ "    public void run() {\n"
        		+ "        validate();\n"
        		+ "    }\n"
        		+ "}\n"
        		+ "I then called this thread in the ActionListener of the \"Run\" button as so\n"
        		+ "\n"
        		+ "runButton.addActionListener(new ActionListener() {\n"
        		+ "    public void actionPerformed(ActionEvent ae) {\n"
        		+ "        // Some code checked on some radio buttons\n"
        		+ "        if(radioButton.isSelected()) {\n"
        		+ "            if(radioButton.getText().equals(\"VALIDATE\")) {\n"
        		+ "               Runnable runnable = new ValidateThread();\n"
        		+ "               Thread thread = new Thread(runnable);\n"
        		+ "               thread.start();\n"
        		+ "            }\n"
        		+ "        }\n"
        		+ "    }\n"
        		+ "\n"
        		+ "});\n"
        		+ "Voila! The output is now sent to the JTextArea.\n"
        		+ "\n"
        		+ "Now, you will notice that the JTextArea will not scroll down with the text. So you need to set the caret position like\n"
        		+ "\n"
        		+ "textArea.setCaretPosition(textArea.getText().length() - 1);\n"
        		+ "Now when the data is added to the JTextArea, it will always scroll down.\n"
        		+ "\n"
        		+ "Share\n"
        		+ "Follow\n"
        		+ "answered Mar 10, 2009 at 12:57\n"
        		+ "user avatar\n"
        		+ "Ascalonian\n"
        		+ "13.8k1818 gold badges7070 silver badges103103 bronze badges\n"
        		+ "3\n"
        		+ "Why not use EventQueue.invokeLater(runnable)? – \n"
        		+ "Jonas Eicher\n"
        		+ " May 29, 2013 at 13:35\n"
        		+ "1\n"
        		+ "I am not sure that was available - your comment was 4 years after mine lol – \n"
        		+ "Ascalonian\n"
        		+ " Jun 12, 2017 at 13:16\n"
        		+ "Add a comment\n"
        		+ "4\n"
        		+ "As others have said this requires multithreading. Have a look at Concurrency in Swing.\n"
        		+ "\n"
        		+ "One solution would be to implement the processing using a SwingWorker. The doInBackground method would implement the processing and you would invoke the publish method with the String to be appended as the argument. Your SwingWorker would then override the process method to take a String argument and append it to the text area.\n"
        		+ "\n"
        		+ "Share\n"
        		+ "Follow\n"
        		+ "answered Mar 10, 2009 at 9:30\n"
        		+ "user avatar\n"
        		+ "Mark\n"
        		+ "28.2k77 gold badges5858 silver badges9090 bronze badges\n"
        		+ "Add a comment\n"
        		+ "1\n"
        		+ "I'm sorry for replying to this question that was posted 4 years ago, but I have another solution that worked for me. I just used pointers to update the JTextAreaas such:\n"
        		+ "\n"
        		+ "//JScrollPane variable pane initialized with JTextArea area\n"
        		+ "//We will update area with new text\n"
        		+ "JTextArea temp = (JTextArea) pane.getViewPort().getView();\n"
        		+ "//new text to add\n"
        		+ "JTextArea c = new JTextArea();\n"
        		+ "c.append(\"text \\n)\";\n"
        		+ "//update through pointers\n"
        		+ "temp = c;\n"
        		+ "pane.validate();\n"
        		+ "Share\n"
        		+ "Follow\n"
        		+ "answered May 29, 2013 at 13:31\n"
        		+ "user avatar\n"
        		+ "collielimabean\n"
        		+ "9722 silver badges99 bronze badges\n"
        		+ "Add a comment\n"
        		+ "0\n"
        		+ "You'll need multithreading for this. Ready to take the plunge?\n"
        		+ "\n"
        		+ "Share\n"
        		+ "Follow\n"
        		+ "answered Mar 10, 2009 at 8:24\n"
        		+ "user avatar\n"
        		+ "Spencer Ruport\n"
        		+ "34.6k1111 gold badges8383 silver badges145145 bronze badges\n"
        		+ "I've got my hard hat on. So yes. – \n"
        		+ "digiarnie\n"
        		+ " Mar 10, 2009 at 8:31\n"
        		+ "Add a comment\n"
        		+ "0\n"
        		+ "If it's multithreading you need try this! http://www.devarticles.com/c/a/Java/Multithreading-in-Java/\n"
        		+ "\n"
        		+ "Share\n"
        		+ "Follow\n"
        		+ "answered Mar 10, 2009 at 8:38\n"
        		+ "user avatar\n"
        		+ "binarycreations\n"
        		+ "3,07166 gold badges2929 silver badges3838 bronze badges\n"
        		+ "Add a comment\n"
        		+ "Highly active question. Earn 10 reputation (not counting the association bonus) in order to answer this question. The reputation requirement helps protect this question from spam and non-answer activity.\n"
        		+ "Not the answer you're looking for? Browse other questions tagged java multithreading swing or ask your own question.\n"
        		+ "The Overflow Blog\n"
        		+ "GitHub Copilot is here. But what’s the price? (Ep. 457)\n"
        		+ "Skilling up to architect: What you need to land high-paying IT roles\n"
        		+ "Featured on Meta\n"
        		+ "Testing new traffic management tool\n"
        		+ "Duplicated votes are being cleaned up\n"
        		+ "Trending: A new answer sorting option\n"
        		+ "Ask Wizard Test Results and Next Steps\n"
        		+ "Should we burninate the [hyphen] tag?\n"
        		+ "Updated button styling for vote arrows: currently in A/B testing\n"
        		+ "Linked\n"
        		+ "0\n"
        		+ "Java Update Components Dynamically\n"
        		+ "0\n"
        		+ "Java dont updating textarea inside a loop\n"
        		+ "1\n"
        		+ "How to Display Output in Text Area in java\n"
        		+ "1\n"
        		+ "Reference Parent UI element in Java\n"
        		+ "0\n"
        		+ "JTextArea is not updating\n"
        		+ "0\n"
        		+ "append text in jtextarea continuosuly?\n"
        		+ "Related\n"
        		+ "6\n"
        		+ "Swing JTextArea multithreading problem - InterruptedException\n"
        		+ "4\n"
        		+ "Swing components freezing until one component completes its job\n"
        		+ "26458\n"
        		+ "Why is processing a sorted array faster than processing an unsorted array?\n"
        		+ "0\n"
        		+ "How to make button click block in Java Swing?\n"
        		+ "0\n"
        		+ "Java jButton and jTextArea\n"
        		+ "0\n"
        		+ "How to implement a delay in ActionListener in Swings?\n"
        		+ "9\n"
        		+ "Busy loop in other thread delays EDT processing\n"
        		+ "Hot Network Questions\n"
        		+ "Fibonacci polynomials\n"
        		+ "What did Putin do to ensure that he doesn't get overthrown by a military coup or to reduce the likelihood of it?\n"
        		+ "Hook arrow versus Tail arrow\n"
        		+ "Simplification of complex number factors out an extra i\n"
        		+ "When would one carry a sword on a shoulder with grip upwards?\n"
        		+ "Ordering polygons from north to south in QGIS\n"
        		+ "When do I switch from \"Dear Dr. X\" to \"Dear Firstname\" when exchanging emails with a professor that I never seen before?\n"
        		+ "Decipher a squashed sequence\n"
        		+ "Why would a nation require immigrants to fulfil a decade of military service to gain citizenship?\n"
        		+ "Is it possible to connect one voltage reference to multiple ADCs?\n"
        		+ "Does Material Design 3 recommend an incorrect icon style?\n"
        		+ "Is it possible for an Orion drive-based spaceship to experience smooth artificial gravity\n"
        		+ "What happens if I don’t have a prerequisite class for a grad school admissions but am in the process of taking them?\n"
        		+ "How to write completeness of wavefunctions without bra ket notation?\n"
        		+ "Can all LEGO Loco characters be recreated in real life?\n"
        		+ "Can a story have a monster called the \"Venomancer\"?\n"
        		+ "What happens when the opinion of the Court misrepresents the facts of the case?\n"
        		+ "Recurrence relation for matrices\n"
        		+ "Inability to review a conference paper\n"
        		+ "Reverse string with identical spaces as in original String using Java\n"
        		+ "How are laws requiring gun buyers to be 21+ constitutional?\n"
        		+ "Will this mains disconnect circuit work?\n"
        		+ "Is 3-phase power in any way better than split-phase power in a residential setting?\n"
        		+ "Is it secure that Linux have default users?\n"
        		+ " Question feed\n"
        		+ "\n"
        		+ "STACK OVERFLOW\n"
        		+ "Questions\n"
        		+ "Help\n"
        		+ "PRODUCTS\n"
        		+ "Teams\n"
        		+ "Advertising\n"
        		+ "Collectives\n"
        		+ "Talent\n"
        		+ "COMPANY\n"
        		+ "About\n"
        		+ "Press\n"
        		+ "Work Here\n"
        		+ "Legal\n"
        		+ "Privacy Policy\n"
        		+ "Terms of Service\n"
        		+ "Contact Us\n"
        		+ "Cookie Settings\n"
        		+ "Cookie Policy\n"
        		+ "STACK EXCHANGE NETWORK\n"
        		+ "Technology\n"
        		+ "Culture & recreation\n"
        		+ "Life & arts\n"
        		+ "Science\n"
        		+ "Professional\n"
        		+ "Business\n"
        		+ "API\n"
        		+ "Data\n"
        		+ "Blog\n"
        		+ "Facebook\n"
        		+ "Twitter\n"
        		+ "LinkedIn\n"
        		+ "Instagram\n"
        		+ "Site design / logo © 2022 Stack Exchange Inc; user contributions licensed under cc by-sa. rev 2022.6.29.42482\n"
        		+ "\n"
        		+ " ",8,25);
        txt.setPreferredSize(new Dimension(100,100));
        JScrollPane scrollPane= new JScrollPane(txt,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        txt.setLineWrap(true);
        panel = new JPanel();
        
        mainComponent = new MainComponent(rods);
        mainComponent.setPreferredSize(new Dimension(RODS_WIDTH, RODS_HEIGHT));
        
        panel.add(mainComponent);
        
        diskNumberLabel = new JLabel(DISK_NUMBER_LABEL);
        MH=new JLabel("Moves History: ");
        diskNumberSelection = new JComboBox<Integer>(new Integer[] {1, 2, 3, 4, 5, 6, 7,8,9,10,11});
        diskNumberSelection.setSelectedItem(4);
        diskNumberSelection.addActionListener(new DiskNumberChoiceListener());
        
        rods.solveTowersOfHanoi(rods.getRodArray()[INITIAL_ROD].getDisksOnTop().size(), INITIAL_ROD, 1, 2);
        rods.initializeRods((int) diskNumberSelection.getSelectedItem(), INITIAL_ROD);
        
       
        panel.add(diskNumberLabel);
        panel.add(diskNumberSelection);
        panel.add(nextButton);
        panel.add(animateButton);
        panel.add(MH);
        panel.add(txt);
        panel.add(scrollPane);
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

                rods.moveDisk(movesToSolve.removeFirst(), movesToSolve.removeFirst());
                ((MainComponent) mainComponent).update();
                
            }
            txt.append(rods.ntc);
            
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
            } else {
                timer.stop();
            }
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
           
            rods = new Rods((int) diskNumberSelection.getSelectedItem(), INITIAL_ROD);
            ((MainComponent) mainComponent).updateRods(rods);
            rods.solveTowersOfHanoi(rods.getRodArray()[INITIAL_ROD].getDisksOnTop().size(), INITIAL_ROD, 1, 2);
            rods.initializeRods((int) diskNumberSelection.getSelectedItem(), INITIAL_ROD);
        }
    } 
    public int getDisk() {
    	return (int)diskNumberSelection.getSelectedItem();
    }
 
}
