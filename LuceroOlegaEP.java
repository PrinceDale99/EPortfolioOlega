import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LuceroOlegaEP extends JFrame {
    private JPanel homePanel, aboutUsPanel, ourWorksPanel, ourKnowledgePanel, contactUsPanel;
    private JTextArea ourWorksArea, ourKnowledgeArea;
    private JButton editWorksButton, lockWorksButton, editKnowledgeButton, lockKnowledgeButton;
    private boolean isWorksEditable = false, isKnowledgeEditable = false;
    private final String WORKS_FILE = "works_content.txt";
    private final String KNOWLEDGE_FILE = "knowledge_content.txt";
    private final String SETTINGS_FILE = "settings.txt";

    public LuceroOlegaEP() {
        setTitle("LuceroOlegaEPortfolio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        // Create navigation panel
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new GridLayout(6, 1));
        navPanel.setBackground(new Color(128, 0, 0)); // Maroon
        add(navPanel, BorderLayout.WEST);

        // Create buttons for navigation
        String[] pages = {"Home", "About Us", "Our Works", "Our Knowledge", "Contact Us"};
        CardLayout cardLayout = new CardLayout();
        JPanel contentPanel = new JPanel(cardLayout);
        add(contentPanel, BorderLayout.CENTER);

        for (String page : pages) {
            JButton button = new JButton(page);
            button.setForeground(Color.WHITE);
            button.setBackground(new Color(128, 0, 0)); // Maroon
            button.addActionListener(e -> cardLayout.show(contentPanel, page));
            navPanel.add(button);
        }

        // Home Panel
        homePanel = new JPanel();
homePanel.setLayout(new BorderLayout()); // Using BorderLayout for better text positioning

// Create a header for the Home Page
JLabel homeHeader = new JLabel("HOME PAGE", JLabel.CENTER);
homeHeader.setFont(new Font("Arial", Font.BOLD, 28)); // Large, bold font for the header
homeHeader.setForeground(new Color(128, 0, 0)); // Maroon color for header

// Create a text area to introduce the page and explain the features
JTextArea homeText = new JTextArea();
homeText.setText(
    "Welcome to the Home Page of LuceroOlegaEP!\n\n" +
    "This page gives you an overview of what each section in this portfolio does.\n" +
    "Here’s a brief introduction to the sections you'll navigate through:\n\n" +
    "1. About Us: Learn about the developers and the process of creating this application.\n" +
    "2. Our Works: This page allows you to add, edit, and lock your works. It remembers any " +
    "edits you make, even if the app is closed.\n" +
    "3. Our Knowledge: Similar to Our Works, this section stores your knowledge and allows " +
    "you to edit and save it permanently.\n" +
    "4. Contact Us: Reach out to us for more information or collaboration.\n\n" +
    "Advanced Features:\n" +
    "- Persistent data: The application remembers your work and knowledge edits.\n" +
    "- Scroll through content on each page for better navigation.\n\n" +
    "How to Use:\n" +
    "- Navigate through the pages using the sidebar on the left.\n" +
    "- On 'Our Works' and 'Our Knowledge', use 'Edit' to change the content, and 'Lock' to save it.\n" +
    "Enjoy exploring this application!"
);
homeText.setFont(new Font("Arial", Font.PLAIN, 16)); // Readable font for content
homeText.setForeground(Color.BLACK); // Text color
homeText.setBackground(new Color(245, 222, 179)); // Light background color for better readability
homeText.setEditable(false); // Make the text area read-only
homeText.setWrapStyleWord(true); // Wrap words correctly
homeText.setLineWrap(true); // Enable line wrap to avoid horizontal scrolling
homeText.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around the text

// Use a JScrollPane to make the content scrollable if necessary
JScrollPane scrollPane = new JScrollPane(homeText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

// Add header and content to the panel
homePanel.add(homeHeader, BorderLayout.NORTH);
homePanel.add(scrollPane, BorderLayout.CENTER);

// Add the homePanel to the contentPanel
contentPanel.add(homePanel, "Home");

        // About Us Panel
        // About Us Panel
aboutUsPanel = new JPanel();
aboutUsPanel.setLayout(new BoxLayout(aboutUsPanel, BoxLayout.Y_AXIS)); // Vertical layout
aboutUsPanel.setBackground(new Color(245, 222, 179)); // Same color as Home page

// Add John Olega's section
JPanel johnPanel = new JPanel();
johnPanel.setLayout(new BoxLayout(johnPanel, BoxLayout.X_AXIS)); // Horizontal layout
johnPanel.setBackground(new Color(245, 222, 179)); 

// Image placeholder for John (use actual image if available)
ImageIcon johnImage = new ImageIcon("olega.jpeg"); // Placeholder, replace with actual image path
JLabel johnLabel = new JLabel(johnImage);
Image johnResized = johnImage.getImage().getScaledInstance(150, -1, Image.SCALE_SMOOTH); // Adjust image size
johnLabel.setIcon(new ImageIcon(johnResized));
johnPanel.add(johnLabel);

// Text description for John
JPanel johnTextPanel = new JPanel();
johnTextPanel.setLayout(new BoxLayout(johnTextPanel, BoxLayout.Y_AXIS));
johnTextPanel.setBackground(new Color(245, 222, 179));

JLabel johnName = new JLabel("John Peter E. Olega");
johnName.setFont(new Font("Arial", Font.BOLD, 24));
johnName.setForeground(new Color(128, 0, 0)); // Maroon

JLabel johnDescription = new JLabel("<html>John Peter E. Olega is an ICT student at Taguig National High School "
    + "with a strong passion for creativity and the arts. In his free time, he can often be found drawing, "
    + "whether for fun or as part of his side business, where he takes commissions to create custom pieces for clients. "
    + "His artistic skills have allowed him to blend his hobby with a small entrepreneurial venture. Alongside his love for drawing, "
    + "John is a dedicated movie enthusiast, enjoying films across all genres. He frequently critiques and analyzes movies, sharing his reviews on Letterboxd for others to read. "
    + "A lifelong fan of comic books, John has been immersed in the worlds of Marvel and DC since he was five years old, "
    + "continually expanding his collection and knowledge of their iconic stories and characters.</html>");
johnDescription.setFont(new Font("Poppins", Font.BOLD, 16));
johnDescription.setForeground(Color.BLACK);

johnTextPanel.add(johnName);
johnTextPanel.add(johnDescription);

johnPanel.add(Box.createHorizontalStrut(20)); // Add space between image and text
johnPanel.add(johnTextPanel);

// Add John's panel to About Us
aboutUsPanel.add(johnPanel);
aboutUsPanel.add(Box.createVerticalStrut(30)); // Space between the two sections

// Add Lawrence Lucero's section
JPanel lawrencePanel = new JPanel();
lawrencePanel.setLayout(new BoxLayout(lawrencePanel, BoxLayout.X_AXIS)); // Horizontal layout
lawrencePanel.setBackground(new Color(245, 222, 179)); 

// Image placeholder for Lawrence (use actual image if available)
ImageIcon lawrenceImage = new ImageIcon("lucero.jpeg"); // Placeholder, replace with actual image path
JLabel lawrenceLabel = new JLabel(lawrenceImage);
Image lawrenceResized = lawrenceImage.getImage().getScaledInstance(150, -1, Image.SCALE_SMOOTH); // Adjust image size
lawrenceLabel.setIcon(new ImageIcon(lawrenceResized));
lawrencePanel.add(lawrenceLabel);

// Text description for Lawrence
JPanel lawrenceTextPanel = new JPanel();
lawrenceTextPanel.setLayout(new BoxLayout(lawrenceTextPanel, BoxLayout.Y_AXIS));
lawrenceTextPanel.setBackground(new Color(245, 222, 179));

JLabel lawrenceName = new JLabel("Lawrence A. Lucero");
lawrenceName.setFont(new Font("Arial", Font.BOLD, 24));
lawrenceName.setForeground(new Color(128, 0, 0)); // Maroon

JLabel lawrenceDescription = new JLabel("<html>Lawrence A. Lucero is a Grade 12 ICT student at Taguig National High School. "
    + "While he considers himself an average student, his skills in math stand out, making it one of his strongest subjects. "
    + "In his free time, Lawrence enjoys playing video games, particularly Roblox, Call of Duty Mobile (CODM), and Mobile Legends, "
    + "where he finds fun and relaxation. His balance between academics and gaming reflects his ability to stay focused on school while enjoying his favorite hobbies.</html>");
lawrenceDescription.setFont(new Font("Poppins", Font.BOLD, 16));
lawrenceDescription.setForeground(Color.BLACK);

lawrenceTextPanel.add(lawrenceName);
lawrenceTextPanel.add(lawrenceDescription);

lawrencePanel.add(Box.createHorizontalStrut(20)); // Add space between image and text
lawrencePanel.add(lawrenceTextPanel);

// Add Lawrence's panel to About Us
aboutUsPanel.add(lawrencePanel);

// Add the About Us panel to the content panel
contentPanel.add(aboutUsPanel, "About Us");
// Our Works Panel
ourWorksPanel = new JPanel(new BorderLayout());
ourWorksPanel.setBackground(new Color(0x800000)); // Maroon background color

// Panel to hold the works
JPanel worksDisplayPanel = new JPanel();
worksDisplayPanel.setLayout(new BoxLayout(worksDisplayPanel, BoxLayout.Y_AXIS));
JScrollPane worksScrollPane = new JScrollPane(worksDisplayPanel);
worksScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding to scroll pane
ourWorksPanel.add(worksScrollPane, BorderLayout.CENTER);

// Button to add new projects
JPanel worksButtonPanel = new JPanel();
worksButtonPanel.setBackground(new Color(0x800000)); // Maroon background for button panel

JButton addProjectButton = new JButton("Add");
addProjectButton.addActionListener(e -> showAddProjectDialog(worksDisplayPanel));
worksButtonPanel.add(addProjectButton);

ourWorksPanel.add(worksButtonPanel, BorderLayout.SOUTH);
contentPanel.add(ourWorksPanel, "Our Works");

// Our Knowledge Panel
ourKnowledgePanel = new JPanel(new BorderLayout());
ourKnowledgePanel.setBackground(new Color(0x800000)); // Maroon background color
ourKnowledgeArea = new JTextArea(20, 40);
ourKnowledgeArea.setLineWrap(true);
ourKnowledgeArea.setWrapStyleWord(true);
ourKnowledgeArea.setFont(new Font("Poppins", Font.PLAIN, 14)); // Set font to Poppins

JScrollPane knowledgeScrollPane = new JScrollPane(ourKnowledgeArea);
knowledgeScrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding to scroll pane
ourKnowledgePanel.add(knowledgeScrollPane, BorderLayout.CENTER);

JPanel knowledgeButtonPanel = new JPanel();
knowledgeButtonPanel.setBackground(new Color(0x800000)); // Maroon background for button panel

editKnowledgeButton = new JButton("Edit");
lockKnowledgeButton = new JButton("Lock");
editKnowledgeButton.addActionListener(e -> setKnowledgeEditable(true));
lockKnowledgeButton.addActionListener(e -> setKnowledgeEditable(false));
knowledgeButtonPanel.add(editKnowledgeButton);
knowledgeButtonPanel.add(lockKnowledgeButton);

ourKnowledgePanel.add(knowledgeButtonPanel, BorderLayout.SOUTH);
contentPanel.add(ourKnowledgePanel, "Our Knowledge");

// Load previous settings and content
loadSettings();
loadContent();

setVisible(true);
}

// Method to show the dialog for adding a new project
private void showAddProjectDialog(JPanel worksDisplayPanel) {
    JDialog dialog = new JDialog(this, "Enter Your Project", true);
    dialog.setLayout(new GridLayout(3, 1));

    // Project name input
    JTextField projectNameField = new JTextField();
    dialog.add(new JLabel("Project Name (Header):"));
    dialog.add(projectNameField);

    // Java code input
    JTextArea codeArea = new JTextArea(10, 30);
    codeArea.setLineWrap(true);
    codeArea.setWrapStyleWord(true);
    dialog.add(new JScrollPane(codeArea));

    // Button to add the project
    JButton doneButton = new JButton("Done");
    doneButton.addActionListener(e -> {
        String projectName = projectNameField.getText();
        String javaCode = codeArea.getText();

        if (!projectName.isEmpty() && !javaCode.isEmpty()) {
            addProjectToDisplay(worksDisplayPanel, projectName, javaCode);
            dialog.dispose(); // Close the dialog
            saveContent(); // Save the content
        } else {
            JOptionPane.showMessageDialog(dialog, "Please fill in both fields.");
        }
    });
    
    dialog.add(doneButton);
    dialog.pack();
    dialog.setLocationRelativeTo(this);
    dialog.setVisible(true);
}

// Method to add a project to the display panel
private void addProjectToDisplay(JPanel worksDisplayPanel, String projectName, String javaCode) {
    JPanel projectPanel = new JPanel();
    projectPanel.setLayout(new BorderLayout());

    // Add project name as a header
    JLabel projectHeader = new JLabel("<html><h3 style='color:#800000;'>" + projectName + "</h3></html>");
    projectPanel.add(projectHeader, BorderLayout.NORTH);

    // Add the code area
    JTextArea codeDisplayArea = new JTextArea(javaCode);
    codeDisplayArea.setFont(new Font("Poppins", Font.PLAIN, 14));
    codeDisplayArea.setEditable(false); // Make the code area non-editable
    projectPanel.add(new JScrollPane(codeDisplayArea), BorderLayout.CENTER);

    // Button to run the code
    JButton runCodeButton = new JButton("Run Code");
    runCodeButton.addActionListener(e -> runJavaCode(javaCode));
    projectPanel.add(runCodeButton, BorderLayout.SOUTH);

    // Add the project panel to the display panel
    worksDisplayPanel.add(projectPanel);
    worksDisplayPanel.revalidate(); // Refresh the display panel
    worksDisplayPanel.repaint(); // Repaint the panel
}

// Load previous content from file
private void loadContent() {
    try {
        if (Files.exists(Paths.get(WORKS_FILE))) {
            // Load each project line by line
            List<String> lines = Files.readAllLines(Paths.get(WORKS_FILE));
            for (String line : lines) {
                String[] parts = line.split(";", 2); // Split on first semicolon
                if (parts.length == 2) {
                    String projectName = parts[0];
                    String javaCode = parts[1];
                    addProjectToDisplay(worksDisplayPanel, projectName, javaCode);
                }
            }
        }
        if (Files.exists(Paths.get(KNOWLEDGE_FILE))) {
            ourKnowledgeArea.setText(Files.readString(Paths.get(KNOWLEDGE_FILE)));
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// Save content to file
private void saveContent() {
    try (PrintWriter writer = new PrintWriter(new FileWriter(WORKS_FILE))) {
        for (Component component : worksDisplayPanel.getComponents()) {
            if (component instanceof JPanel) {
                JPanel projectPanel = (JPanel) component;
                JLabel headerLabel = (JLabel) projectPanel.getComponent(0);
                JTextArea codeArea = (JTextArea) ((JScrollPane) projectPanel.getComponent(1)).getViewport().getView();
                writer.println(headerLabel.getText() + ";" + codeArea.getText());
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// Load previous settings from file
private void loadSettings() {
    try {
        if (Files.exists(Paths.get(SETTINGS_FILE))) {
            for (String line : Files.readAllLines(Paths.get(SETTINGS_FILE))) {
                if (line.startsWith("works_editable=")) {
                    isWorksEditable = Boolean.parseBoolean(line.split("=")[1]);
                    ourWorksArea.setEditable(isWorksEditable);
                } else if (line.startsWith("knowledge_editable=")) {
                    isKnowledgeEditable = Boolean.parseBoolean(line.split("=")[1]);
                    ourKnowledgeArea.setEditable(isKnowledgeEditable);
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// Save settings to file
private void saveSettings() {
    try (PrintWriter writer = new PrintWriter(new FileWriter(SETTINGS_FILE))) {
        writer.println(isWorksEditable ? "works_editable=true" : "works_editable=false");
        writer.println(isKnowledgeEditable ? "knowledge_editable=true" : "knowledge_editable=false");
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// Set Our Knowledge container editable or not
private void setKnowledgeEditable(boolean editable) {
    isKnowledgeEditable = editable;
    ourKnowledgeArea.setEditable(editable);
    saveSettings();
    saveContent();
}

    public static void main(String[] args) {
        new LuceroOlegaEP();
    }
}