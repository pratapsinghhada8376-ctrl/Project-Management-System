import javax.swing.*;
import java.awt.*;

class ViewProject extends JFrame {

    ViewProject(
            String projectName,
            String projectManager,
            String teamName,
            String members,
            String startDate,
            String dueDate,
            String status,
            String teamMembers,
            String description) {

        setTitle("View Project");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ================= HEADER =================

        JLabel header = new JLabel();
        header.setHorizontalAlignment(SwingConstants.CENTER);
        header.setBackground(Color.WHITE);
        header.setOpaque(true);

        ImageIcon icon = new ImageIcon(
                getClass().getResource("poornima.jpeg")
        );

        Image img = icon.getImage().getScaledInstance(
                500, 90, Image.SCALE_SMOOTH
        );

        header.setIcon(new ImageIcon(img));

        add(header, BorderLayout.NORTH);


        // ================= MAIN BACKGROUND =================

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(245, 245, 245));


        // ================= WHITE BOX =================

        JPanel box = new JPanel(new GridBagLayout());
        box.setBackground(Color.WHITE);

        box.setBorder(
                BorderFactory.createLineBorder(Color.GRAY, 2)
        );

        box.setPreferredSize(
                new Dimension(750, 430)
        );


        GridBagConstraints g = new GridBagConstraints();

        g.insets = new Insets(10, 15, 10, 15);
        g.anchor = GridBagConstraints.WEST;


        Font labelFont =
                new Font("Arial", Font.BOLD, 14);

        Font valueFont =
                new Font("Arial", Font.PLAIN, 14);


        // ================= TITLE =================

        JLabel title = new JLabel("PROJECT DETAILS");

        title.setFont(
                new Font("Arial", Font.BOLD, 26)
        );

        title.setForeground(
                new Color(0, 102, 204)
        );

        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 4;
        g.anchor = GridBagConstraints.CENTER;
        g.insets = new Insets(15, 10, 25, 10);

        box.add(title, g);


        // Reset

        g.gridwidth = 1;
        g.anchor = GridBagConstraints.WEST;
        g.insets = new Insets(9, 15, 9, 15);


        // ================= LEFT SIDE =================

        // Project Name

        addLabelValue(
                box, g,
                "Project Name",
                projectName,
                0, 1,
                labelFont,
                valueFont
        );


        // Project Manager

        addLabelValue(
                box, g,
                "Project Manager",
                projectManager,
                0, 2,
                labelFont,
                valueFont
        );


        // Team Name

        addLabelValue(
                box, g,
                "Team Name",
                teamName,
                0, 3,
                labelFont,
                valueFont
        );


        // Number of Members

        addLabelValue(
                box, g,
                "Number of Members",
                members,
                0, 4,
                labelFont,
                valueFont
        );


        // ================= RIGHT SIDE =================

        // Start Date

        addLabelValue(
                box, g,
                "Start Date",
                startDate,
                2, 1,
                labelFont,
                valueFont
        );


        // Due Date

        addLabelValue(
                box, g,
                "Due Date",
                dueDate,
                2, 2,
                labelFont,
                valueFont
        );


        // Status

        addLabelValue(
                box, g,
                "Status",
                status,
                2, 3,
                labelFont,
                valueFont
        );


        // Team Members

        addLabelValue(
                box, g,
                "Team Members",
                teamMembers,
                2, 4,
                labelFont,
                valueFont
        );


        // ================= DESCRIPTION =================

        JLabel descLabel =
                new JLabel("Description");

        descLabel.setFont(labelFont);

        g.gridx = 0;
        g.gridy = 5;
        g.gridwidth = 1;

        box.add(descLabel, g);


        JTextArea descArea =
                new JTextArea(description);

        descArea.setFont(valueFont);
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descArea.setEditable(false);

        descArea.setBorder(
                BorderFactory.createLineBorder(Color.GRAY)
        );

        JScrollPane descScroll =
                new JScrollPane(descArea);

        descScroll.setPreferredSize(
                new Dimension(560, 65)
        );

        g.gridx = 1;
        g.gridy = 5;
        g.gridwidth = 3;
        g.fill = GridBagConstraints.HORIZONTAL;

        box.add(descScroll, g);


        // ================= BACK BUTTON =================

        JButton back =
                new JButton("BACK");

        back.setPreferredSize(
                new Dimension(560, 40)
        );

        back.setBackground(
                new Color(0, 102, 204)
        );

        back.setForeground(Color.WHITE);

        back.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        back.setFocusPainted(false);


        back.addActionListener(e -> {

            new Projects();

            dispose();

        });


        g.gridx = 1;
        g.gridy = 6;
        g.gridwidth = 3;
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(10, 15, 15, 15);

        box.add(back, g);


        // ================= ADD BOX =================

        mainPanel.add(box);

        add(mainPanel, BorderLayout.CENTER);


        // ================= FOOTER =================

        JLabel footer =
                new JLabel(
                        "Developed By : Pratap Singh Hada",
                        SwingConstants.CENTER
                );

        footer.setForeground(Color.WHITE);

        footer.setBackground(
                new Color(50, 50, 50)
        );

        footer.setOpaque(true);

        footer.setPreferredSize(
                new Dimension(0, 35)
        );

        add(footer, BorderLayout.SOUTH);


        setVisible(true);
    }


    // =================================================
    // LABEL + VALUE METHOD
    // =================================================

    void addLabelValue(
            JPanel panel,
            GridBagConstraints g,
            String labelText,
            String valueText,
            int column,
            int row,
            Font labelFont,
            Font valueFont) {


        JLabel label =
                new JLabel(labelText);

        label.setFont(labelFont);


        JLabel value =
                new JLabel(valueText);

        value.setFont(valueFont);


        g.gridx = column;
        g.gridy = row;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.NONE;

        panel.add(label, g);


        g.gridx = column + 1;
        g.gridy = row;
        g.gridwidth = 1;
        g.fill = GridBagConstraints.HORIZONTAL;

        panel.add(value, g);
    }


    // =================================================
    // MAIN
    // =================================================

    public static void main(String[] args) {

        new ViewProject(
                "Website Development",
                "Rahul",
                "Team A",
                "5",
                "01/09/2026",
                "15/09/2026",
                "Active",
                "Rahul, Amit, Ravi, Mohit, Suresh",
                "Website development project for college management."
        );
    }
}