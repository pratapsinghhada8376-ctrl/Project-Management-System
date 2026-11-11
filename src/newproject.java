import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;

public class newproject extends JFrame {

    public newproject() {

        setTitle("New Project");
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
                500, 90, Image.SCALE_SMOOTH
        );

        header.setIcon(new ImageIcon(img));

        add(header, BorderLayout.NORTH);


        // ================= CENTER =================

        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(new Color(245, 245, 245));


        // ================= BACK ARROW =================

        JButton backBtn = new JButton("←");

        backBtn.setFont(new Font("Arial", Font.BOLD, 30));
        backBtn.setForeground(new Color(0, 102, 204));

        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setFocusPainted(false);
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        backBtn.addActionListener(e -> {
            new Dashboard();
            dispose();
        });


        JPanel topLeft = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 15, 5)
        );

        topLeft.setBackground(new Color(245, 245, 245));
        topLeft.add(backBtn);

        center.add(topLeft, BorderLayout.NORTH);


        // ================= BOX CENTER =================

        JPanel boxCenter = new JPanel(new GridBagLayout());
        boxCenter.setBackground(new Color(245, 245, 245));


        // ================= MAIN WHITE BOX =================

        JPanel box = new JPanel(new GridBagLayout());

        box.setBackground(Color.WHITE);

        box.setBorder(
                BorderFactory.createLineBorder(Color.GRAY, 2)
        );

        box.setPreferredSize(
                new Dimension(900, 500)
        );


        Font labelFont = new Font(
                "Arial",
                Font.BOLD,
                16
        );


        // ================= GRID CONSTRAINTS =================

        GridBagConstraints g = new GridBagConstraints();

        g.insets = new Insets(15, 20, 15, 20);
        g.fill = GridBagConstraints.HORIZONTAL;
        g.anchor = GridBagConstraints.NORTH;
        g.weighty = 0;


        // =================================================
        // PROJECT NAME
        // =================================================

        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 1;
        g.weightx = 0;

        box.add(
                label("Project Name", labelFont),
                g
        );

        g.gridx = 1;
        g.weightx = 1;

        JTextField projectName = field();
        box.add(projectName, g);


        // =================================================
        // PROJECT MANAGER
        // =================================================

        g.gridx = 0;
        g.gridy = 1;
        g.weightx = 0;

        box.add(
                label("Project Manager", labelFont),
                g
        );

        g.gridx = 1;
        g.weightx = 1;

        JTextField projectManager = field();
        box.add(projectManager, g);


        // =================================================
        // TEAM NAME
        // =================================================

        g.gridx = 0;
        g.gridy = 2;
        g.weightx = 0;

        box.add(
                label("Team Name", labelFont),
                g
        );

        g.gridx = 1;
        g.weightx = 1;

        JTextField teamName = field();
        box.add(teamName, g);


        // =================================================
        // NUMBER OF MEMBERS
        // =================================================

        g.gridx = 0;
        g.gridy = 3;
        g.weightx = 0;

        box.add(
                label("Number of Members", labelFont),
                g
        );

        g.gridx = 1;
        g.weightx = 1;

        JTextField numberMembers = field();
        box.add(numberMembers, g);


        // =================================================
        // START DATE
        // =================================================

        g.gridx = 2;
        g.gridy = 0;
        g.weightx = 0;

        box.add(
                label("Start Date", labelFont),
                g
        );

        g.gridx = 3;
        g.weightx = 1;

        JTextField startDate = field();
        box.add(startDate, g);


        // =================================================
        // DUE DATE
        // =================================================

        g.gridx = 2;
        g.gridy = 1;
        g.weightx = 0;

        box.add(
                label("Due Date", labelFont),
                g
        );

        g.gridx = 3;
        g.weightx = 1;

        JTextField dueDate = field();
        box.add(dueDate, g);


        // =================================================
        // TEAM MEMBERS
        // =================================================

        g.gridx = 2;
        g.gridy = 2;
        g.weightx = 0;

        box.add(
                label("Team Members", labelFont),
                g
        );

        g.gridx = 3;
        g.weightx = 1;

        JTextArea members = new JTextArea(3, 15);

        members.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        members.setLineWrap(true);
        members.setWrapStyleWord(true);

        members.setBorder(
                BorderFactory.createLineBorder(Color.GRAY)
        );

        JScrollPane memberScroll =
                new JScrollPane(members);

        box.add(memberScroll, g);


        // =================================================
        // ADD MEMBERS BUTTON
        // =================================================


        // =================================================
        // DESCRIPTION
        // =================================================

        g.gridx = 0;
        g.gridy = 4;
        g.gridwidth = 1;
        g.weightx = 0;

        box.add(
                label("Description", labelFont),
                g
        );


        g.gridx = 1;
        g.gridy = 4;
        g.gridwidth = 2;
        g.weightx = 1;

        JTextArea description =
                new JTextArea(4, 25);

        description.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        description.setLineWrap(true);
        description.setWrapStyleWord(true);

        description.setBorder(
                BorderFactory.createLineBorder(Color.GRAY)
        );

        JScrollPane descriptionScroll =
                new JScrollPane(description);

        box.add(descriptionScroll, g);


        // =================================================
        // CREATE PROJECT BUTTON
        // =================================================

        g.gridx = 1;
        g.gridy = 5;
        g.gridwidth = 1;
        g.weightx = 0;

        JButton create =
                new JButton("CREATE PROJECT");

        create.setPreferredSize(
                new Dimension(200, 42)
        );

        create.setBackground(
                new Color(0, 102, 204)
        );

        create.setForeground(Color.WHITE);

        create.setFont(
                new Font("Arial", Font.BOLD, 15)
        );

        box.add(create, g);

        create.addActionListener(e -> {
            String sql = "INSERT INTO projects "
                    + "(project_name, project_manager, team_name, members, "
                    + "start_date, due_date, status, team_members, description) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            if (projectName.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter Project Name!");
                return;
            }

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, projectName.getText().trim());
                ps.setString(2, projectManager.getText().trim());
                ps.setString(3, teamName.getText().trim());
                ps.setInt(4, Integer.parseInt(numberMembers.getText().trim()));
                ps.setDate(5, java.sql.Date.valueOf(startDate.getText().trim()));
                ps.setDate(6, java.sql.Date.valueOf(dueDate.getText().trim()));
                ps.setString(7, "Pending");
                ps.setString(8, members.getText().trim());
                ps.setString(9, description.getText().trim());

                ps.executeUpdate();

                JOptionPane.showMessageDialog(this,
                        "Project Saved Successfully!");

                new Projects();
                dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Number of Members must be a number!");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this,
                        "Enter dates in YYYY-MM-DD format, e.g. 2026-10-20");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,
                        "Database Error: " + ex.getMessage());
            }
        });


        // ================= ADD BOX =================

        boxCenter.add(box);

        center.add(
                boxCenter,
                BorderLayout.CENTER
        );

        add(
                center,
                BorderLayout.CENTER
        );


        // ================= FOOTER =================

        JLabel footer = new JLabel(
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

        add(
                footer,
                BorderLayout.SOUTH
        );


        setVisible(true);
    }


    // ================= LABEL METHOD =================

    JLabel label(String text, Font font) {

        JLabel l = new JLabel(text);
        l.setFont(font);

        return l;
    }


    // ================= TEXT FIELD METHOD =================

    JTextField field() {

        JTextField t = new JTextField();

        t.setPreferredSize(
                new Dimension(220, 38)
        );

        t.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        return t;
    }


    // ================= MAIN =================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> new newproject()
        );
    }
}