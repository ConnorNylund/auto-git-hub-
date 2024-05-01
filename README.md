# Challenge2

## Overview
Challenge2 is a Java Swing application designed to streamline the process of creating a Git repository and pushing it to GitHub. It provides a simple user interface for initializing a repository, setting up remote origin, committing files, creating a .gitignore file, and generating a README.md file. Additionally, it interacts with the GitHub API to create a corresponding repository on GitHub with customizable settings such as name, description, and visibility.

## Features
- **Graphical User Interface:** Utilizes Java Swing to provide an intuitive GUI for interacting with Git and GitHub functionalities.
- **Repository Initialization:** Initializes a Git repository locally on the developer's machine.
- **Remote Origin Setup:** Sets up a remote origin for the Git repository, typically linking to a GitHub repository.
- **Commit and Push:** Commits changes and pushes them to the configured remote repository on GitHub.
- **GitHub Integration:** Interacts with the GitHub API to create a repository on GitHub with user-defined settings.
- **.gitignore File Creation:** Generates a .gitignore file to specify files and directories to be ignored by Git.
- **README.md File Creation:** Creates a README.md file for the repository with a customizable title.

## Usage
1. Enter the GitHub repository URL in the designated text field.
2. Click the "Create Repository" button to proceed.
3. Fill in the required details for repository name, description, visibility, user name, and GitHub API access token.
4. Click the "Next" button to create the corresponding repository on GitHub and push the initial commit.
5. A dialog box will display the URL of the created GitHub repository.

## Dependencies
- Java Development Kit (JDK)
- GitSubprocessClient (Dependency for Git operations)
- GitHubApiClient (Dependency for GitHub API interactions)

## Getting Started
1. Clone the repository to your local machine.
2. Open the project in your preferred Java development environment.
3. Ensure that the required dependencies are properly configured.
4. Build and run the Challenge2.java file to launch the application.


