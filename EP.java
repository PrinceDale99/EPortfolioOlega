import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.Diagnostic;
import java.util.Arrays;

public class EP extends JFrame {
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JTextArea knowledgeArea;
    private JPanel worksPanel;
    private JButton editButton, lockButton, addCodeButton;
    private boolean isEditable = false;
    private final String WORKS_FILE = "works.dat";
    private final String KNOWLEDGE_FILE = "knowledge.dat";

    public EP() {
        setTitle("LuceroOlegaEPortfolio");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Sidebar Menu
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(5, 1));
        menuPanel.setBackground(new Color(63, 20, 20));

        String[] buttons = {"Home", "About Us", "Our Works", "Our Knowledge", "Contact Us"};
        for (String btn : buttons) {
            JButton button = new JButton(btn);
            button.setFocusPainted(false);
            button.setFont(new Font("Arial", Font.BOLD, 16));
            button.setBackground(new Color(139, 0, 0));
            button.setForeground(Color.WHITE);
            button.addActionListener(e -> cardLayout.show(contentPanel, btn));
            menuPanel.add(button);
        }

        add(menuPanel, BorderLayout.WEST);

        // Content Panel with CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        add(contentPanel, BorderLayout.CENTER);

        // Pages
        contentPanel.add(createHomePage(), "Home");
        contentPanel.add(createAboutPage(), "About Us");
        contentPanel.add(createWorksPage(), "Our Works");
        contentPanel.add(createKnowledgePage(), "Our Knowledge");
        contentPanel.add(createContactPage(), "Contact Us");

        setVisible(true);
    }

    private JScrollPane createHomePage() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Stack components vertically
    panel.setBackground(new Color(244, 236, 223)); // Light background color

    // Welcome Header
    JLabel welcomeLabel = new JLabel("Welcome to Lucero & Olega's E-Portfolio");
    welcomeLabel.setFont(new Font("Poppins", Font.BOLD, 36));
    welcomeLabel.setForeground(new Color(128, 0, 0)); // Maroon color
    welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(welcomeLabel);
    panel.add(Box.createVerticalStrut(20)); // Spacing

    // Introduction Text
    JLabel introLabel = new JLabel("<html>This ePortfolio is designed to showcase various<br>"
            + "projects and knowledge snippets. It allows users to<br>"
            + "add Java code snippets, edit knowledge content,<br>"
            + "and run code directly within the application.</html>");
    introLabel.setFont(new Font("Poppins", Font.PLAIN, 18));
    introLabel.setForeground(Color.BLACK);
    introLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    introLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
    panel.add(introLabel);
    panel.add(Box.createVerticalStrut(30)); // Spacing

    // What is an ePortfolio
    JLabel whatIsEportfolioLabel = new JLabel("<html><strong>What is an ePortfolio?</strong><br>"
            + "An ePortfolio is a digital collection of artifacts that<br>"
            + "demonstrate an individual's skills, experiences,<br>"
            + "and achievements. It serves as an interactive platform<br>"
            + "for showcasing work, reflecting on learning,<br>"
            + "and providing evidence of competencies. ePortfolios<br>"
            + "can be utilized for educational purposes,<br>"
            + "professional development, and career advancement.</html>");
    whatIsEportfolioLabel.setFont(new Font("Poppins", Font.PLAIN, 18));
    whatIsEportfolioLabel.setForeground(Color.BLACK);
    whatIsEportfolioLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    whatIsEportfolioLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding
    panel.add(whatIsEportfolioLabel);
    panel.add(Box.createVerticalStrut(30)); // Spacing

    // Section Features
    String[] sectionTitles = {"Home", "About Us", "Our Works", "Our Knowledge", "Contact Us"};
    String[] sectionDescriptions = {
        "The Home section provides a welcoming<br>"
        + "introduction to the portfolio.",
        "The About Us section shares information<br>"
        + "about the developers.",
        "The Our Works section allows users to<br>"
        + "add and view their Java code snippets.",
        "The Our Knowledge section offers a space<br>"
        + "for users to manage and edit their<br>"
        + "knowledge content.",
        "The Contact Us section provides information<br>"
        + "on how to get in touch."
    };

    for (int i = 0; i < sectionTitles.length; i++) {
        JLabel sectionHeader = new JLabel(sectionTitles[i]);
        sectionHeader.setFont(new Font("Poppins", Font.BOLD, 24));
        sectionHeader.setForeground(new Color(128, 0, 0)); // Maroon color
        sectionHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(sectionHeader);

        JLabel sectionDescription = new JLabel("<html>" + sectionDescriptions[i] + "</html>");
        sectionDescription.setFont(new Font("Poppins", Font.PLAIN, 16));
        sectionDescription.setForeground(Color.BLACK);
        sectionDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
        sectionDescription.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20)); // Padding
        panel.add(sectionDescription);

        panel.add(Box.createVerticalStrut(20)); // Spacing
    }

    // Wrap the main panel in a JScrollPane
    JScrollPane scrollPane = new JScrollPane(panel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Always show the vertical scrollbar
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Disable horizontal scrolling
    scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Optional: Remove border from the scroll pane

    return scrollPane; // Return the scroll pane instead of the panel
}

    private JScrollPane createAboutPage() {
    JPanel aboutUsPanel = new JPanel();
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
    johnName.setFont(new Font("Poppins", Font.BOLD, 24)); // Use Poppins font
    johnName.setForeground(new Color(128, 0, 0)); // Maroon

    JLabel johnDescription = new JLabel("<html>John Peter E. Olega is an ICT student at Taguig National High<br>"
        + "School with a strong passion for creativity and the arts.<br>"
        + "In his free time, he can often be found drawing,<br>"
        + "whether for fun or as part of his side business,<br>"
        + "where he takes commissions to create custom pieces<br>"
        + "for clients. His artistic skills have allowed him to<br>"
        + "blend his hobby with a small entrepreneurial venture.<br>"
        + "Alongside his love for drawing, John is a dedicated<br>"
        + "movie enthusiast, enjoying films across all genres.<br>"
        + "He frequently critiques and analyzes movies,<br>"
        + "sharing his reviews on Letterboxd for others to read.<br>"
        + "A lifelong fan of comic books, John has been<br>"
        + "immersed in the worlds of Marvel and DC since he<br>"
        + "was five years old, continually expanding his<br>"
        + "collection and knowledge of their iconic stories<br>"
        + "and characters.</html>");

johnDescription.setFont(new Font("Poppins", Font.PLAIN, 16)); // Use Poppins font
johnDescription.setForeground(Color.BLACK);
johnDescription.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Padding

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
lawrenceName.setFont(new Font("Poppins", Font.BOLD, 24)); // Use Poppins font
lawrenceName.setForeground(new Color(128, 0, 0)); // Maroon

JLabel lawrenceDescription = new JLabel("<html>Lawrence A. Lucero is a Grade 12 ICT student at<br>"
        + "Taguig National High<br>"
        + "School. While he considers himself an average<br>"
        + "student, his skills in math stand out,<br>"
        + "making it one of his strongest subjects. In his<br>"
        + "free time, Lawrence enjoys playing video games,<br>"
        + "particularly Roblox, Call of Duty Mobile (CODM),<br>"
        + "and Mobile Legends, where he finds fun and<br>"
        + "relaxation. His balance between academics and<br>"
        + "gaming reflects his ability to stay focused on<br>"
        + "school while enjoying his favorite hobbies.</html>");
lawrenceDescription.setFont(new Font("Poppins", Font.PLAIN, 16)); // Use Poppins font
lawrenceDescription.setForeground(Color.BLACK);
lawrenceDescription.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Padding

    lawrenceTextPanel.add(lawrenceName);
    lawrenceTextPanel.add(lawrenceDescription);

    lawrencePanel.add(Box.createHorizontalStrut(20)); // Add space between image and text
    lawrencePanel.add(lawrenceTextPanel);

    // Add Lawrence's panel to About Us
    aboutUsPanel.add(lawrencePanel);

    // Wrap the main panel in a JScrollPane
    JScrollPane scrollPane = new JScrollPane(aboutUsPanel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Always show the vertical scrollbar
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Disable horizontal scrolling

    // Optional: Remove border from the scroll pane
    scrollPane.setBorder(BorderFactory.createEmptyBorder());

    // Return the scroll pane
    return scrollPane;
}

    private JPanel createWorksPage() {
    JPanel panel = new JPanel(new BorderLayout());

    // Initialize worksPanel to hold the added codes
    worksPanel = new JPanel();
    worksPanel.setLayout(new BoxLayout(worksPanel, BoxLayout.Y_AXIS)); // Stack components vertically

    // Wrap worksPanel in a JScrollPane to allow scrolling
    JScrollPane scrollPane = new JScrollPane(worksPanel);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Always show the vertical scrollbar

    panel.add(scrollPane, BorderLayout.CENTER); // Add the scroll pane to the center of the panel

    addCodeButton = new JButton("Add Code");
    addCodeButton.setFont(new Font("Arial", Font.BOLD, 16));
    addCodeButton.setBackground(new Color(139, 0, 0));
    addCodeButton.setForeground(Color.WHITE);
    addCodeButton.addActionListener(e -> showAddCodeDialog());

    panel.add(addCodeButton, BorderLayout.SOUTH); // Add the button at the bottom
    panel.setBackground(new Color(244, 236, 223));

    loadWorks(); // Load previously saved works
    return panel;
}

    private JPanel createKnowledgePage() {
        JPanel panel = new JPanel(new BorderLayout());
        knowledgeArea = new JTextArea();
        knowledgeArea.setEditable(false);
        panel.add(new JScrollPane(knowledgeArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        lockButton = new JButton("Lock");
        lockButton.setFont(new Font("Arial", Font.BOLD, 16));
        lockButton.setBackground(new Color(139, 0, 0));
        lockButton.setForeground(Color.WHITE);
        lockButton.addActionListener(e -> lockContent());

        editButton = new JButton("Edit");
        editButton.setFont(new Font("Arial", Font.BOLD, 16));
        editButton.setBackground(new Color(139, 0, 0));
        editButton.setForeground(Color.WHITE);
        editButton.addActionListener(e -> editContent());

        buttonPanel.add(lockButton);
        buttonPanel.add(editButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        panel.setBackground(new Color(244, 236, 223));
        loadKnowledge();
        return panel;
    }

    private JPanel createContactPage() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Vertical layout
    panel.setBackground(new Color(139, 0, 0)); // Maroon color scheme
    panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding

    // Title - Contact Us
    JLabel titleLabel = new JLabel("Contact Us");
    titleLabel.setFont(new Font("Poppins", Font.BOLD, 36));
    titleLabel.setForeground(Color.WHITE);
    titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center alignment
    panel.add(titleLabel);
    panel.add(Box.createRigidArea(new Dimension(0, 20))); // Add space between components

    // First Contact - John Peter Olega
    panel.add(createContactSection("John Peter Olega", "John Peter Olega",
            "https://www.facebook.com/johnpeter.olega.5?mibextid=ZbWKwL", "@jpolega_06",
            "https://www.instagram.com/jpolega_06?igsh=MWIxemxqcHZ6dzZxbQ=="));

    // Space between contacts
    panel.add(Box.createRigidArea(new Dimension(0, 20)));

    // Second Contact - Lawrence Lucero
    panel.add(createContactSection("Lawrence Lucero", "Lawrence Lucero",
            "https://www.facebook.com/profile.php?id=100072534756475&mibextid=ZbWKwL", "@lo.ririnsd",
            "https://www.instagram.com/lo.ririnsd?igsh=Ynptb3lyOWQzMnZ4"));

    return panel;
}

private JPanel createContactSection(String name, String facebookName, String facebookUrl, String instagramHandle, String instagramUrl) {
    JPanel contactPanel = new JPanel();
    contactPanel.setLayout(new BoxLayout(contactPanel, BoxLayout.Y_AXIS));
    contactPanel.setBackground(new Color(139, 0, 0)); // Match the background
    contactPanel.setForeground(Color.WHITE);

    // Name Header (H2)
    JLabel nameLabel = new JLabel(name);
    nameLabel.setFont(new Font("Poppins", Font.BOLD, 24));
    nameLabel.setForeground(Color.WHITE);
    nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    contactPanel.add(nameLabel);

    // Facebook Info
    contactPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Space
    contactPanel.add(createLinkButton("Facebook: " + facebookName, facebookUrl));

    // Instagram Info
    contactPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Space
    contactPanel.add(createLinkButton("Instagram: " + instagramHandle, instagramUrl));

    return contactPanel;
}

private JPanel createLinkButton(String label, String url) {
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel.setBackground(new Color(139, 0, 0)); // Maroon background
    
    JLabel linkLabel = new JLabel(label);
    linkLabel.setFont(new Font("Poppins", Font.PLAIN, 18));
    linkLabel.setForeground(Color.WHITE);
    panel.add(linkLabel);

    JButton linkButton = new JButton("Open");
    linkButton.setFont(new Font("Arial", Font.BOLD, 14));
    linkButton.setBackground(new Color(244, 236, 223)); // Light color for contrast
    linkButton.setForeground(Color.BLACK);
    linkButton.addActionListener(e -> {
        try {
            Desktop.getDesktop().browse(new java.net.URI(url));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    });
    panel.add(linkButton);

    return panel;
}

    private void showAddCodeDialog() {
    JDialog dialog = new JDialog(this, "Add Your Code", true);
    dialog.setLayout(new GridLayout(4, 2)); // Adjusted to accommodate the Cancel button
    JTextField codeNameField = new JTextField();
    JTextArea codeArea = new JTextArea();
    
    dialog.add(new JLabel("Project Name:"));
    dialog.add(codeNameField);
    dialog.add(new JLabel("Java Code:"));
    dialog.add(new JScrollPane(codeArea));

    JButton addCodeButton = new JButton("Add Code");
    addCodeButton.setFont(new Font("Arial", Font.BOLD, 16));
    addCodeButton.setBackground(new Color(139, 0, 0));
    addCodeButton.setForeground(Color.WHITE);
    addCodeButton.addActionListener(e -> {
        String codeName = codeNameField.getText();
        String code = codeArea.getText();
        if (!codeName.isEmpty() && !code.isEmpty()) {
            addCode(codeName, code);
            dialog.dispose();
        } else {
            JOptionPane.showMessageDialog(dialog, "Please enter both project name and code.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    JButton cancelButton = new JButton("Cancel");
    cancelButton.setFont(new Font("Arial", Font.BOLD, 16));
    cancelButton.setBackground(new Color(139, 0, 0));
    cancelButton.setForeground(Color.WHITE);
    cancelButton.addActionListener(e -> dialog.dispose()); // Close the dialog when Cancel is clicked

    dialog.add(addCodeButton);
    dialog.add(cancelButton);
    dialog.setSize(400, 300);
    dialog.setVisible(true);
}
   

    private void addCode(String codeName, String code) {
    // Create a label to display the code name
    JLabel codeLabel = new JLabel(codeName);

    // Create a text area to display the actual code
    JTextArea codeTextArea = new JTextArea(code);
    codeTextArea.setEditable(false); // Make it read-only
    codeTextArea.setLineWrap(true);
    codeTextArea.setWrapStyleWord(true);
    codeTextArea.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Optional: Add a border for better visibility

    // Create a button to run the code
    JButton runButton = new JButton("Run");
    runButton.setFont(new Font("Arial", Font.BOLD, 16));
    runButton.setBackground(new Color(139, 0, 0));
    runButton.setForeground(Color.WHITE);
    runButton.addActionListener(e -> runCode(code)); // Run the provided code when the button is clicked

    // Create a panel to hold the label, text area, and buttons
    JPanel codePanel = new JPanel(new BorderLayout()); // Define codePanel here
    codePanel.add(codeLabel, BorderLayout.NORTH); // Add the label to the north
    codePanel.add(new JScrollPane(codeTextArea), BorderLayout.CENTER); // Add the text area to the center

    // Create a button to delete the code
    JButton deleteButton = new JButton("Delete");
    deleteButton.setFont(new Font("Arial", Font.BOLD, 16));
    deleteButton.setBackground(new Color(255, 69, 0)); // Red color for delete
    deleteButton.setForeground(Color.WHITE);
    deleteButton.addActionListener(e -> {
        worksPanel.remove(codePanel); // Remove the panel from worksPanel
        worksPanel.revalidate();
        worksPanel.repaint();
        deleteCode(codeName); // Call deleteCode method to remove it from the file
    });

    // Create a panel for the buttons
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    buttonPanel.add(runButton);
    buttonPanel.add(deleteButton);

    codePanel.add(buttonPanel, BorderLayout.SOUTH); // Add the buttons to the south

    // Add the code panel to the works panel
    worksPanel.add(codePanel);
    worksPanel.revalidate(); // Refresh the works panel
    worksPanel.repaint(); // Repaint to show the new entry

    // Save the code along with the name in works.dat
    saveWorks(codeName, code); // Call saveWorks to store the new code entry
}


private void deleteCode(String codeName) {
    List<String> works = new ArrayList<>();

    // Load the existing works from the file
    File file = new File(WORKS_FILE);
    if (file.exists() && file.length() != 0) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            works = (List<String>) ois.readObject(); // Load previous works
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Remove the work with the matching codeName
    works.removeIf(work -> work.split(":")[0].equals(codeName)); // Remove the code with the matching name

    // Save the updated works list back to the file
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(WORKS_FILE))) {
        oos.writeObject(works); // Write the updated list of works
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    private void runCode(String code) {
    SwingWorker<Void, String> worker = new SwingWorker<>() {
        @Override
        protected Void doInBackground() throws Exception {
            String className = "DynamicClass";
            String fullCode = "import java.util.Scanner;\n" +
                              "public class " + className + " {\n" +
                              "    public static void main(String[] args) {\n" +
                              "        Scanner scanner = new Scanner(System.in);\n" +
                              "        " + code + "\n" +
                              "        scanner.close();\n" +
                              "    }\n" +
                              "}";

            boolean needsScannerInput = code.contains("Scanner") || code.contains("nextInt") || code.contains("nextLine");
            String userInput = null;

            if (needsScannerInput) {
                userInput = JOptionPane.showInputDialog("Please enter values (comma separated):");
                if (userInput == null) {
                    JOptionPane.showMessageDialog(null, "Input was cancelled.", "Cancelled", JOptionPane.INFORMATION_MESSAGE);
                    return null;
                }
            }

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
            StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);

            try (PrintWriter out = new PrintWriter(className + ".java")) {
                out.println(fullCode);
            }

            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnostics, null, null,
                    fileManager.getJavaFileObjectsFromStrings(Arrays.asList(className + ".java")));
            boolean success = task.call();

            StringBuilder output = new StringBuilder();

            if (success) {
                ProcessBuilder pb = new ProcessBuilder("java", className);
                pb.redirectErrorStream(true);
                Process process = pb.start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));

                String line;
                String[] inputs = userInput != null ? userInput.split(",") : new String[0];
                int inputIndex = 0;

                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                    publish(line);

                    if (line.contains("Enter a number:") || line.contains("Please enter a value:")) {
                        if (inputIndex < inputs.length) {
                            writer.write(inputs[inputIndex].trim() + "\n");
                            writer.flush();
                            inputIndex++;
                        } else {
                            writer.write("\n"); // Send an empty line if no more inputs are available
                            writer.flush();
                        }
                    }
                }
                process.waitFor();
            } else {
                StringBuilder errorMsg = new StringBuilder("Compilation failed:\n");
                for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {
                    errorMsg.append(diagnostic).append("\n");
                }
                output.append(errorMsg.toString());
            }
            fileManager.close();

            showOutputDialog(output.toString());
            return null;
        }

        @Override
        protected void process(List<String> chunks) {
            for (String chunk : chunks) {
            }
        }

        @Override
        protected void done() {
        }
    };
    worker.execute();
}

    private void showOutputDialog(String output) {
    JOptionPane.showMessageDialog(this, output, "Output", JOptionPane.INFORMATION_MESSAGE);
}

    private void lockContent() {
    knowledgeArea.setEditable(false);
    lockButton.setEnabled(false);
    editButton.setEnabled(true);
    saveKnowledge(); // Save knowledge when locking
}

    private void editContent() {
        knowledgeArea.setEditable(true);
        lockButton.setEnabled(true);
        editButton.setEnabled(false);
    }

@SuppressWarnings("unchecked")
private void loadWorks() {
    File file = new File(WORKS_FILE);
    
    // Step 1: Check if the file exists and is not empty
    if (!file.exists() || file.length() == 0) {
        System.out.println("No works file found or the file is empty.");
        return; // Exit early if the file doesn't exist or is empty
    }
    
    // Step 2: Load the previously saved works from the file
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
        List<String> works = (List<String>) ois.readObject(); // Read the saved list of works
        for (String work : works) {
            System.out.println("Processing work entry: " + work);
            if (work == null || work.isEmpty()) {
                System.err.println("Empty or null entry found in works file.");
                continue;
            }

            // Step 3: Split the entry into code name and actual code
            String[] parts = work.split(":", 2); // Split into two parts: code name and code content
            if (parts.length == 2) {
                addCode(parts[0], parts[1]); // Add the saved code to the panel (code name and code)
            } else {
                System.err.println("Invalid work format: " + work);
            }
        }
    } catch (FileNotFoundException e) {
        System.err.println("File not found: " + e.getMessage());
    } catch (EOFException e) {
        System.err.println("Reached end of file unexpectedly: " + e.getMessage());
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }
}

    private void saveWorks(String codeName, String code) {
    List<String> works = new ArrayList<>();

    // Step 1: Load the existing works from the file (if it exists)
    File file = new File(WORKS_FILE);
    if (file.exists() && file.length() != 0) { // Check if the file exists and is not empty
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            works = (List<String>) ois.readObject(); // Load previous works
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Step 2: Add or update the new code in the list
    boolean codeExists = false; // Flag to check if the code is already in the list

    for (int i = 0; i < works.size(); i++) {
        String work = works.get(i);
        String[] parts = work.split(":", 2); // Split the work into code name and code
        if (parts.length == 2 && parts[0].equals(codeName)) { // If the code name matches
            works.set(i, codeName + ":" + code); // Update the existing code
            codeExists = true; // Set flag to true
            break;
        }
    }

    // Step 3: If the code doesn't exist, add it to the list
    if (!codeExists) {
        works.add(codeName + ":" + code); // Add the new code
    }

    // Step 4: Save the updated list of works back to the file
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(WORKS_FILE))) {
        oos.writeObject(works); // Write the updated list of works
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    private void loadKnowledge() {
        try (BufferedReader br = new BufferedReader(new FileReader(KNOWLEDGE_FILE))) {
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
            knowledgeArea.setText(content.toString());
        } catch (FileNotFoundException e) {
            // File doesn't exist yet, ignore
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveKnowledge() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(KNOWLEDGE_FILE))) {
            bw.write(knowledgeArea.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EP::new);
    }
}