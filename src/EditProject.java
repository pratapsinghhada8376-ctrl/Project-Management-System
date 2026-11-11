import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import javax.swing.*;
import java.awt.*;
class EditProject extends JFrame {

    private String originalProjectName;

    EditProject() {
        this("");
    }

    EditProject(String selectedProjectName) {
        originalProjectName = selectedProjectName;

        setTitle("Edit Project");
        setSize(1000, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
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
                520, 90, Image.SCALE_SMOOTH
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
                new Dimension(750, 470)
        );


        // ================= TITLE =================

        JLabel title = new JLabel("EDIT PROJECT");

        title.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        title.setForeground(
                new Color(0, 102, 204)
        );

        GridBagConstraints titleG = new GridBagConstraints();

        titleG.gridx = 0;
        titleG.gridy = 0;
        titleG.gridwidth = 4;

        titleG.insets =
                new Insets(15, 10, 30, 10);

        box.add(title, titleG);


        // ================= COMMON SETTINGS =================

        Font labelFont =
                new Font("Arial", Font.BOLD, 15);

        Font fieldFont =
                new Font("Arial", Font.PLAIN, 14);


        // ================= LEFT LABEL SETTINGS =================

        GridBagConstraints labelLeft =
                new GridBagConstraints();

        labelLeft.anchor = GridBagConstraints.WEST;

        labelLeft.insets =
                new Insets(8, 30, 8, 15);


        // ================= FIELD SETTINGS =================

        GridBagConstraints fieldLeft =
                new GridBagConstraints();

        fieldLeft.fill =
                GridBagConstraints.HORIZONTAL;

        fieldLeft.weightx = 1;

        fieldLeft.insets =
                new Insets(8, 0, 8, 35);


        // ================= RIGHT LABEL SETTINGS =================

        GridBagConstraints labelRight =
                new GridBagConstraints();

        labelRight.anchor =
                GridBagConstraints.WEST;

        labelRight.insets =
                new Insets(8, 20, 8, 15);


        // ================= RIGHT FIELD SETTINGS =================

        GridBagConstraints fieldRight =
                new GridBagConstraints();

        fieldRight.fill =
                GridBagConstraints.HORIZONTAL;

        fieldRight.weightx = 1;

        fieldRight.insets =
                new Insets(8, 0, 8, 30);


        // =====================================================
        // PROJECT NAME
        // =====================================================

        labelLeft.gridx = 0;
        labelLeft.gridy = 1;

        box.add(
                createLabel("Project Name", labelFont),
                labelLeft
        );

        JTextField projectName =
                createField("", fieldFont);

        fieldLeft.gridx = 1;
        fieldLeft.gridy = 1;

        box.add(projectName, fieldLeft);


        // =====================================================
        // START DATE
        // =====================================================

        labelRight.gridx = 2;
        labelRight.gridy = 1;

        box.add(
                createLabel("Start Date", labelFont),
                labelRight
        );

        JTextField startDate = createField("", fieldFont);

        fieldRight.gridx = 3;
        fieldRight.gridy = 1;

        box.add(startDate, fieldRight);


        // =====================================================
        // PROJECT MANAGER
        // =====================================================

        labelLeft.gridx = 0;
        labelLeft.gridy = 2;

        box.add(
                createLabel("Project Manager", labelFont),
                labelLeft
        );

        JTextField projectManager =
                createField("", fieldFont);

        fieldLeft.gridx = 1;
        fieldLeft.gridy = 2;

        box.add(projectManager, fieldLeft);


        // =====================================================
        // DUE DATE
        // =====================================================

        labelRight.gridx = 2;
        labelRight.gridy = 2;

        box.add(
                createLabel("Due Date", labelFont),
                labelRight
        );

        JTextField dueDate =
                createField("", fieldFont);

        fieldRight.gridx = 3;
        fieldRight.gridy = 2;

        box.add(dueDate, fieldRight);


        // =====================================================
        // TEAM NAME
        // =====================================================

        labelLeft.gridx = 0;
        labelLeft.gridy = 3;

        box.add(
                createLabel("Team Name", labelFont),
                labelLeft
        );

        JTextField teamName =
                createField("", fieldFont);

        fieldLeft.gridx = 1;
        fieldLeft.gridy = 3;

        box.add(teamName, fieldLeft);


        // =====================================================
        // STATUS
        // =====================================================

        labelRight.gridx = 2;
        labelRight.gridy = 3;

        box.add(
                createLabel("Status", labelFont),
                labelRight
        );

        String[] statusOptions = {
                "Active",
                "Pending",
                "Completed"
        };

        JComboBox<String> status =
                new JComboBox<>(statusOptions);

        status.setFont(fieldFont);

        status.setPreferredSize(
                new Dimension(200, 35)
        );

        fieldRight.gridx = 3;
        fieldRight.gridy = 3;

        box.add(status, fieldRight);


        // =====================================================
        // NUMBER OF MEMBERS
        // =====================================================

        labelLeft.gridx = 0;
        labelLeft.gridy = 4;

        box.add(
                createLabel("Number of Members", labelFont),
                labelLeft
        );

        JTextField members =
                createField("", fieldFont);

        fieldLeft.gridx = 1;
        fieldLeft.gridy = 4;

        box.add(members, fieldLeft);


        // =====================================================
        // TEAM MEMBERS
        // =====================================================

        labelRight.gridx = 2;
        labelRight.gridy = 4;

        box.add(
                createLabel("Team Members", labelFont),
                labelRight
        );

        JTextField teamMembers =
                createField(
                        "",
                        fieldFont
                );

        fieldRight.gridx = 3;
        fieldRight.gridy = 4;

        box.add(teamMembers, fieldRight);


        // =====================================================
        // DESCRIPTION
        // =====================================================

        labelLeft.gridx = 0;
        labelLeft.gridy = 5;

        box.add(
                createLabel("Description", labelFont),
                labelLeft
        );


        JTextArea description =
                new JTextArea(
                        ""
                );

        description.setFont(fieldFont);

        description.setLineWrap(true);
        description.setWrapStyleWord(true);

        description.setBorder(
                BorderFactory.createLineBorder(Color.GRAY)
        );

        description.setPreferredSize(
                new Dimension(430, 60)
        );


        GridBagConstraints descriptionG =
                new GridBagConstraints();

        descriptionG.gridx = 1;
        descriptionG.gridy = 5;

        descriptionG.gridwidth = 3;

        descriptionG.fill =
                GridBagConstraints.HORIZONTAL;

        descriptionG.insets =
                new Insets(8, 0, 8, 30);

        box.add(description, descriptionG);
// LOAD PROJECT DETAILS FROM DATABASE
        if (!selectedProjectName.isEmpty())  {
            String sql = "SELECT * FROM projects WHERE project_name = ?";

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, selectedProjectName);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        projectName.setText(rs.getString("project_name"));
                        projectManager.setText(rs.getString("project_manager"));
                        teamName.setText(rs.getString("team_name"));
                        members.setText(String.valueOf(rs.getInt("members")));

                        Date start = rs.getDate("start_date");
                        Date due = rs.getDate("due_date");

                        startDate.setText(start == null ? "" : start.toString());
                        dueDate.setText(due == null ? "" : due.toString());

                        status.setSelectedItem(rs.getString("status"));

                        teamMembers.setText(
                                rs.getString("team_members") == null
                                        ? "" : rs.getString("team_members")
                        );

                        description.setText(
                                rs.getString("description") == null
                                        ? "" : rs.getString("description")
                        );
                    }
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }

        // =====================================================
        // BUTTON PANEL
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                25,
                                15
                        )
                );

        buttonPanel.setBackground(Color.WHITE);


        // UPDATE BUTTON

        JButton update =
                createButton("UPDATE PROJECT");

        update.addActionListener(e -> {
            if (projectName.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Project Name is required!");
                return;
            }

            try {
                int memberCount = Integer.parseInt(members.getText().trim());

                String sql = "UPDATE projects SET project_name=?, " +
                        "project_manager=?, team_name=?, members=?, " +
                        "start_date=?, due_date=?, status=?, " +
                        "team_members=?, description=? WHERE project_name=?";

                try (Connection con = DBConnection.getConnection();
                     PreparedStatement ps = con.prepareStatement(sql)) {

                    ps.setString(1, projectName.getText().trim());
                    ps.setString(2, projectManager.getText().trim());
                    ps.setString(3, teamName.getText().trim());
                    ps.setInt(4, memberCount);
                    ps.setDate(5, Date.valueOf(startDate.getText().trim()));
                    ps.setDate(6, Date.valueOf(dueDate.getText().trim()));
                    ps.setString(7, status.getSelectedItem().toString());
                    ps.setString(8, teamMembers.getText().trim());
                    ps.setString(9, description.getText().trim());
                    ps.setString(10, originalProjectName);

                    int rows = ps.executeUpdate();

                    if (rows > 0) {
                        JOptionPane.showMessageDialog(
                                this, "Project Updated Successfully!"
                        );
                        new Projects();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(
                                this, "Project not found!"
                        );
                    }
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Members must be a number!");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(
                        this, "Enter dates in YYYY-MM-DD format."
                );
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage());
            }
        });


        // BACK BUTTON

        JButton back =
                createButton("BACK");

        back.addActionListener(e -> {

            new Projects();
            dispose();

        });


        buttonPanel.add(update);
        buttonPanel.add(back);


        GridBagConstraints buttonG =
                new GridBagConstraints();

        buttonG.gridx = 0;
        buttonG.gridy = 6;

        buttonG.gridwidth = 4;

        buttonG.insets =
                new Insets(10, 10, 10, 10);

        box.add(buttonPanel, buttonG);


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

        footer.setFont(
                new Font("Arial", Font.BOLD, 12)
        );

        footer.setPreferredSize(
                new Dimension(0, 35)
        );

        add(footer, BorderLayout.SOUTH);


        setVisible(true);
    }


    // =====================================================
    // LABEL METHOD
    // =====================================================

    JLabel createLabel(
            String text,
            Font font
    ) {

        JLabel label =
                new JLabel(text);

        label.setFont(font);

        return label;
    }


    // =====================================================
    // TEXT FIELD METHOD
    // =====================================================

    JTextField createField(
            String text,
            Font font
    ) {

        JTextField field =
                new JTextField(text);

        field.setFont(font);

        field.setPreferredSize(
                new Dimension(200, 35)
        );

        return field;
    }


    // =====================================================
    // BUTTON METHOD
    // =====================================================

    JButton createButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setPreferredSize(
                new Dimension(170, 40)
        );

        button.setBackground(
                new Color(0, 102, 204)
        );

        button.setForeground(Color.WHITE);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        button.setFocusPainted(false);

        return button;
    }


    // =====================================================
    // MAIN
    // =====================================================

    public static void main(String[] args) {

        new EditProject();

    }
}