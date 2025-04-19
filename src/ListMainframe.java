import javax.swing.*;


/**
 * ListMainframe is a GUI application that allows users to add and search for items in a sorted list.
 * It uses a SortedList class to manage the list of items.
 * The GUI consists of input fields, buttons, and a text area to display the list and search results.
 * It also invokes the main method to start the application.
 */
public class ListMainframe extends JFrame
{
    private JTextField inputField;
    private JTextField searchField;
    private JButton addButton;
    private JButton searchButton;
    private JButton exitButton;
    private JTextArea outputArea;

    private SortedList sortedList;
    private int size = 0;

    /**
     * Constructor for ListMainframe.
     * Initializes the GUI components and sets up the layout.
     */
    public ListMainframe()
    {
        setTitle("List Mainframe");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        sortedList = new SortedList(10);

        inputField = new JTextField(20);
        searchField = new JTextField(20);
        addButton = new JButton("Add");
        searchButton = new JButton("Search");
        exitButton = new JButton("Exit");
        outputArea = new JTextArea(20, 35);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Input:"));
        panel.add(inputField);
        panel.add(addButton);
        panel.add(new JLabel("Search:"));
        panel.add(searchField);
        panel.add(searchButton);
        panel.add(exitButton);

        add(panel, "North");
        add(new JScrollPane(outputArea), "Center");

        addButton.addActionListener(e -> addItem());
        searchButton.addActionListener(e -> searchItem(searchField.getText()));
        exitButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    /**
     * Adds an item to the sorted list and updates the output area.
     */
    private void addItem()
    {
        String input = inputField.getText();
        if (!input.isEmpty())
        {
            sortedList.add(input);
            outputArea.append("Added: " + input + "\n");
            String[] currentList = sortedList.getElements();
            for (String item : currentList)
            {
                outputArea.append(item + "\n");
            }
            inputField.setText("");
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Please enter a value to add.");
        }
    }

    /**
     * Searches for an item in the sorted list and updates the output area.
     * @param search The item to search for.
     */
    private void searchItem(String search)
    {
        search = searchField.getText();
        if (!search.isEmpty())
        {
            int index = sortedList.search(search);
            if (index >= 0 && index < sortedList.size() && sortedList.getElements()[index].equals(search))
            {
                outputArea.append("Found: " + search + " at index: " + index + "\n");
            }
            else
            {
                outputArea.append("Not Found: \"" + search + "\". Would be inserted at index: " + index + "\n");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Please enter a value to search.");
        }
    }

    /**
     * Main method to run the ListMainframe application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(ListMainframe::new);
    }

}
