import java.awt.event.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import git.tools.client.GitSubprocessClient;
import github.tools.client.GitHubApiClient;
import github.tools.responseObjects.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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

        // Create text field
        JTextField repoPathTextField = new JTextField("enter the GitHub reposetory URL",16);
        repoPathTextField.setSize(200, 30); // Set the size of the text field
        repoPathTextField.setLocation(50, 50); // Set the location of the text field within the panel
        mainPanel.add(repoPathTextField);

        // create button
        JButton fortextfield = new JButton("Create Repository");
        fortextfield.setSize(150, 50);
        fortextfield.setLocation(250, 50);

        mainPanel.add(fortextfield);
        // add mainPanel to JFrame
        frame.setContentPane(mainPanel);
            // when button is clicked, run the code inside the actionPerformed method
            fortextfield.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("You clicked the button!");
                    helloLabel.setText("Goodbye!!");
                    String repoPath = repoPathTextField.getText();
                    //must enter an atual file for the Text Field
                    GitSubprocessClient gitSubprocessClient = new GitSubprocessClient(repoPath);
                    String gitInit = gitSubprocessClient.gitInit();
                    System.out.println(gitInit);
                    String gitRemoteAdd = gitSubprocessClient.gitRemoteAdd("origin", repoPath);
                    System.out.println(gitRemoteAdd);

                    //GitIgnore method call 
                    gitIgnore(repoPath);

                    // Add READ.me file 

                    // Create an initial commit in the Git repo

                    // Create a GitHub repo mirroring the Git repo on the developer’s computer
                    // The GitHub repo’s name, description and visibility (private or public) should be able to be set by the user

                }
            });

        // show JFrame
        frame.setVisible(true);
    }

    //Creating git.ignore file method
    private static void gitIgnore(String projectPath) {
        try {
            //Common Files to Ignore
            String ignoreContent = 
            "*.class\n"+
            ".idea/\n"+
            "*.log\n"+
            "*.ctxt\n"+
            "*.jar\n"+
            "*.war\n"+
            "*.nar\n"+
            "*.ear\n"+
            "*.zip\n"+
            "*.tar.gz\n"+
            "*.rar\n";

            FileWriter ignoreWriter = new FileWriter(projectPath + File.separator + ".gitignore");
            ignoreWriter.write(ignoreContent);
            ignoreWriter.close();
            System.out.println("The .gitignore file was created.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("There was a problem creating the .gitignore file.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
