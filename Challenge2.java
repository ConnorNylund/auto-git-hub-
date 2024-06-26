import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import git.tools.client.GitSubprocessClient;
import github.tools.client.GitHubApiClient;
import github.tools.client.RequestParams;
import github.tools.responseObjects.CreateRepoResponse;

public class Challenge2 extends JFrame implements ActionListener {
    private static GitSubprocessClient gitSubprocessClient;
    public static void main(String[] args) {

        // create JFrame
        JFrame frame = new JFrame("Swing Practice");
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // make it so when JFrame is closed via the x
                                                                       // button, the entire program stops
        frame.setLocationRelativeTo(null); // make JFrame open center screen
        frame.setResizable(false);

        // create main panel (canvas)
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null); // no layout so you can put things wherever you want
        mainPanel.setBackground(Color.blue);

        // create label
        JLabel helloLabel = new JLabel("Hello!!!");
        helloLabel.setSize(100, 100);
        helloLabel.setLocation(350, 150);
        helloLabel.setForeground(Color.white);
        mainPanel.add(helloLabel);

        // Create text field
        JTextField repoPathTextField = new JTextField("enter the GitHub repository URL", 16);
        repoPathTextField.setSize(200, 30); // Set the size of the text field
        repoPathTextField.setLocation(50, 50); // Set the location of the text field within the panel
        mainPanel.add(repoPathTextField);

        // create button
        JButton fortextfield = new JButton("Create Repository");
        fortextfield.setSize(150, 50);
        fortextfield.setLocation(250, 50);
        mainPanel.add(fortextfield);

        //Creates next buttons
        JButton nextButton = new JButton("next");
        nextButton.setSize(150, 50);
        nextButton.setLocation(250, 110);
        nextButton.setVisible(false);
        mainPanel.add(nextButton);

        // Creates text fields for repo name, description, visibility, user name, and git API Token
        JTextField repoName = new JTextField("enter the repo name", 16);
        repoName.setSize(200, 30); // Set the size of the text field
        repoName.setLocation(50, 110); // Set the location of the text field within the panel
        repoName.setVisible(false);
        mainPanel.add(repoName);

        JTextField repoDescription = new JTextField("enter the repo description", 16);
        repoDescription.setSize(200, 30); // Set the size of the text field
        repoDescription.setLocation(50, 170); // Set the location of the text field within the panel
        repoDescription.setVisible(false);
        mainPanel.add(repoDescription);

        JTextField repoVisibility = new JTextField("enter the repo visibility(public/private)", 16);
        repoVisibility.setSize(200, 30); // Set the size of the text field
        repoVisibility.setLocation(50, 230); // Set the location of the text field within the panel
        repoVisibility.setVisible(false);
        mainPanel.add(repoVisibility);

        JTextField userName = new JTextField("enter your user name", 16);
        userName.setSize(200, 30); // Set the size of the text field
        userName.setLocation(50, 290); // Set the location of the text field within the panel
        userName.setVisible(false);
        mainPanel.add(userName);

        JTextField APIToken = new JTextField("enter your GitHub API access token", 16);
        APIToken.setSize(200, 30); // Set the size of the text field
        APIToken.setLocation(50, 350); // Set the location of the text field within the panel
        APIToken.setVisible(false);
        mainPanel.add(APIToken);

        // add mainPanel to JFrame
        frame.setContentPane(mainPanel);
        // when button is clicked, run the code inside the actionPerformed method
        fortextfield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextButton.setVisible(true);
                repoName.setVisible(true);
                repoDescription.setVisible(true);
                repoVisibility.setVisible(true);
                userName.setVisible(true);
                APIToken.setVisible(true);
                System.out.println("You clicked the button!");
                helloLabel.setText("Goodbye!!");
                String repoPath = repoPathTextField.getText();
                // must enter an actual file for the Text Field
                 gitSubprocessClient = new GitSubprocessClient(repoPath);
                String gitInit = gitSubprocessClient.gitInit();
                System.out.println(gitInit);
                String gitRemoteAdd = gitSubprocessClient.gitRemoteAdd("origin", repoPath);
                System.out.println(gitRemoteAdd);
                String gitAddAll = gitSubprocessClient.gitAddAll();
                System.out.println(gitAddAll);
                String commitMessage = "Inital commit";
                String commit = gitSubprocessClient.gitCommit(commitMessage);
                System.out.println(commit);



                // GitIgnore method call
                gitIgnore(repoPath);

                // Add READ.me file
                String projectName = repoName.getText();
                createReadMe(repoPath, projectName);
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a GitHub repo mirroring the Git repo on the developer’s computer
                // The GitHub repo’s name, description and visibility (private or public) should
                // be able to be set by the user

                GitHubApiClient gitHubApiClient = new GitHubApiClient(userName.getText(), APIToken.getText());
                RequestParams requestParams = new RequestParams();
                requestParams.addParam("name", repoName.getText());
                requestParams.addParam("description", repoDescription.getText());
                if (repoVisibility.getText().equals("private")) {
                    requestParams.addParam("private", true);
                } else {
                    requestParams.addParam("private", false);
                }
                //Creates the repo based on the name, description, and visibility
                CreateRepoResponse createRepo = gitHubApiClient.createRepo(requestParams);
                System.out.println(createRepo);

                  // Extracting repository URL from the response
        String repoUrl = createRepo.getUrl();
        System.out.println("Repository URL: " + repoUrl);

        // Commit and Push to GitHub
        String commitMessage = "Initial commit";
            gitSubprocessClient.gitAddFile(".");
            gitSubprocessClient.gitCommit(commitMessage);
            gitSubprocessClient.gitPush("master");
            System.out.println("Initial commit pushed to GitHub successfully.");

        // Display the URL to the user
        // Here you can use any method to display the URL to the user, such as JOptionPane
        JOptionPane.showMessageDialog(frame, "Your GitHub repository has been created.\nURL: " + repoUrl);
            }
        });

        // show JFrame
        frame.setVisible(true);
    }

    // Creating git.ignore file method
    private static void gitIgnore(String projectPath) {
        try {
            // Common Files to Ignore
            String ignoreContent = "*.class\n" +
                    ".idea/\n" +
                    "*.log\n" +
                    "*.ctxt\n" +
                    "*.jar\n" +
                    "*.war\n" +
                    "*.nar\n" +
                    "*.ear\n" +
                    "*.zip\n" +
                    "*.tar.gz\n" +
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

    //Method to create README.md file
    private static void createReadMe(String projectPath, String projectName) {
        try {
            FileWriter readmeWriter = new FileWriter(projectPath + File.separator + "README.md");
            readmeWriter.write("# " + projectName + "\n\n");
            readmeWriter.close();
            System.out.println("The README.md file was created.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("There was a problem creating the README.md file.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
