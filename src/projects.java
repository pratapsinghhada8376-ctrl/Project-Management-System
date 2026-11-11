import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

class Projects extends JFrame {

    Projects() {

        setTitle("Projects");
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


        // ================= MAIN PANEL =================

        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.setBackground(new Color(245, 245, 245));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25, 25, 25, 25
                )
        );


        // ================= SEARCH PANEL =================

        JPanel searchPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.RIGHT, 10, 5
                )
        );

        searchPanel.setBackground(
                new Color(245, 245, 245)
        );

        JLabel searchLabel = new JLabel(
                "Search Project:"
        );

        searchLabel.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        JTextField searchField = new JTextField();

        searchField.setPreferredSize(
                new Dimension(220, 32)
        );

        JButton searchButton = new JButton(
                "SEARCH"
        );

        searchButton.setPreferredSize(
                new Dimension(100, 32)
        );

        searchButton.setBackground(
                new Color(0, 102, 204)
        );

        searchButton.setForeground(Color.WHITE);

        searchButton.setFont(
                new Font("Arial", Font.BOLD, 12)
        );

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        mainPanel.add(
                searchPanel,
                BorderLayout.NORTH
        );


        // ================= TABLE COLUMNS =================

        String[] columns = {

                "Project Name",
                "Project Manager",
                "Team Name",
                "Members",
                "Start Date",
                "Due Date",
                "Status"
        };


        // ================= PROJECT DATA =================
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        loadProjects(model);

        JTable table = new JTable(model);


        // ================= TABLE =================









        table.setRowHeight(35);

        table.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        table.getTableHeader().setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        table.getTableHeader().setBackground(
                new Color(0, 102, 204)
        );

        table.getTableHeader().setForeground(
                Color.WHITE
        );

        table.setGridColor(Color.GRAY);

        JScrollPane scrollPane =
                new JScrollPane(table);

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );


        // ================= BUTTON PANEL =================

        JPanel buttonPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER,
                        15,
                        15
                )
        );

        buttonPanel.setBackground(Color.WHITE);


        // ================= VIEW BUTTON =================

        JButton view =
                createButton("VIEW");

        view.addActionListener(e -> {

            int selectedRow =
                    table.getSelectedRow();

            // No project selected
            if (selectedRow == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a project first.",
                        "No Project Selected",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }


            // Get values from selected row

            String projectName =
                    table.getValueAt(
                            selectedRow, 0
                    ).toString();

            String projectManager =
                    table.getValueAt(
                            selectedRow, 1
                    ).toString();

            String teamName =
                    table.getValueAt(
                            selectedRow, 2
                    ).toString();

            String numberOfMembers =
                    table.getValueAt(
                            selectedRow, 3
                    ).toString();

            String startDate =
                    table.getValueAt(
                            selectedRow, 4
                    ).toString();

            String dueDate =
                    table.getValueAt(
                            selectedRow, 5
                    ).toString();

            String status =
                    table.getValueAt(
                            selectedRow, 6
                    ).toString();


            // Get extra details from original data
            String teamMembers = "";
            String description = "";


            // Find project from original data
            teamMembers = "";
            description = "";

            String sql = "SELECT team_members, description FROM projects WHERE project_name = ?";

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, projectName);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        teamMembers = rs.getString("team_members");
                        description = rs.getString("description");
                    }
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }



            // Open ViewProject with 9 values

            new ViewProject(
                    projectName,
                    projectManager,
                    teamName,
                    numberOfMembers,
                    startDate,
                    dueDate,
                    status,
                    teamMembers,
                    description
            );

            dispose();
        });


        // ================= EDIT =================

        // ================= EDIT =================

        JButton edit = createButton("EDIT");

        edit.addActionListener(e -> {

            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please select a project first!"
                );
                return;
            }

            // Search ke baad bhi sahi row select ho
            int modelRow = table.convertRowIndexToModel(selectedRow);

            String selectedProjectName =
                    table.getModel().getValueAt(modelRow, 0).toString();

            new EditProject(selectedProjectName);
            dispose();
        });
        // ================= DELETE =================

        JButton delete =
                createButton("DELETE");

        delete.addActionListener(e -> {

            new DeleteProject();

            dispose();
        });


        // ================= NEW PROJECT =================

        JButton newProject =
                createButton("NEW PROJECT");

        newProject.addActionListener(e -> {

            new newproject();

            dispose();
        });


        // ================= BACK =================

        JButton back =
                createButton("BACK");

        back.addActionListener(e -> {

            new Dashboard();

            dispose();
        });


        // Add buttons

        buttonPanel.add(back);

        buttonPanel.add(view);

        buttonPanel.add(edit);

        buttonPanel.add(delete);

        buttonPanel.add(newProject);


        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );


        // ================= SEARCH FUNCTION =================

        searchButton.addActionListener(e -> {
            String text = searchField.getText().trim().toLowerCase();

            DefaultTableModel searchModel =
                    new DefaultTableModel(columns, 0);

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(
                         "SELECT project_name, project_manager, team_name, " +
                                 "members, start_date, due_date, status FROM projects " +
                                 "WHERE LOWER(project_name) LIKE ? " +
                                 "OR LOWER(project_manager) LIKE ? " +
                                 "OR LOWER(team_name) LIKE ?")) {

                String search = "%" + text + "%";

                ps.setString(1, search);
                ps.setString(2, search);
                ps.setString(3, search);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        searchModel.addRow(new Object[]{
                                rs.getString("project_name"),
                                rs.getString("project_manager"),
                                rs.getString("team_name"),
                                rs.getInt("members"),
                                rs.getDate("start_date"),
                                rs.getDate("due_date"),
                                rs.getString("status")
                        });
                    }
                }

                table.setModel(searchModel);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        // ================= ADD MAIN PANEL =================

        add(
                mainPanel,
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

        footer.setPreferredSize(
                new Dimension(0, 35)
        );

        add(
                footer,
                BorderLayout.SOUTH
        );


        setVisible(true);
    }
    void loadProjects(DefaultTableModel model) {
        model.setRowCount(0);

        String sql = "SELECT project_name, project_manager, team_name, " +
                "members, start_date, due_date, status FROM projects";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getString("project_name"),
                        rs.getString("project_manager"),
                        rs.getString("team_name"),
                        rs.getInt("members"),
                        rs.getDate("start_date"),
                        rs.getDate("due_date"),
                        rs.getString("status")
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    this, "Database Error: " + e.getMessage()
            );
        }
    }

    // ================= BUTTON DESIGN =================

    JButton createButton(String text) {

        JButton button =
                new JButton(text);

        button.setPreferredSize(
                new Dimension(130, 40)
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

        return button;
    }


    // ================= MAIN =================

    public static void main(String[] args) {

        new Projects();
    }
}
