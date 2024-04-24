import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import git.tools.client.GitSubprocessClient;
import github.tools.client.GitHubApiClient;
import github.tools.responseObjects.*;


public class Challeng2 extends JFrame implements ActionListener {

    
    public static void main(String[] args) { 
        
            // create JFrame
            JFrame frame = new JFrame("Swing Practice");
            frame.setSize(1000, 800);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // make it so when JFrame is closed via the x button, the entire program stops
            frame.setLocationRelativeTo(null); // make JFrame open center screen
            frame.setResizable(false);
            
            // Create Challeng2 class
            //Challeng2 te = new Challeng2();

            // create main panel (canvas)
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(null); // no layout so you can put things wherever you want
            mainPanel.setBackground(Color.blue);

            // create label
            JLabel helloLabel = new JLabel("Hello!!!");
            helloLabel.setSize(100, 100);
            helloLabel.setLocation(150, 150);
            helloLabel.setForeground(Color.white);
            mainPanel.add(helloLabel);
            JLabel l = new JLabel("nothing entered");
            l.setSize(100, 100);
            l.setLocation(200, 200);
            l.setForeground(Color.white);
            mainPanel.add(l);

            // Create text field
            JTextField t = new JTextField("enter the GitHub reposetory URL",16);
            t.setSize(200, 30); // Set the size of the text field
            t.setLocation(50, 50); // Set the location of the text field within the panel
            mainPanel.add(t);

            // create button
            JButton submitButton = new JButton("Submit");
            submitButton.setSize(100, 50);
            submitButton.setLocation(50, 200);
            JButton fortextfield = new JButton("submit");
            fortextfield.setSize(100, 50);
            fortextfield.setLocation(250, 50);

            // when button is clicked, run the code inside the actionPerformed method
            submitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("You clicked the button!");
                    GitSubprocessClient gitSubprocessClient = new GitSubprocessClient(repoPath);
                    String gitInit = gitSubprocessClient.gitInit();
                    String gitRemoteAdd = gitSubprocessClient.gitRemoteAdd("origin", "https://github.com/CSC109/GitSubprocessClient.git");
                    helloLabel.setText("Goodbye!!");
                }
            });
            fortextfield.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("You clicked the button!");
                    String textFieldValue = repoPath.getText();

                    //Contine from hereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
                    helloLabel.setText("Goodbye!!");
                }
            });
            mainPanel.add(submitButton);
            mainPanel.add(fortextfield);
            // add mainPanel to JFrame
            frame.setContentPane(mainPanel);

            // show JFrame
            frame.setVisible(true);
        }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
    }
