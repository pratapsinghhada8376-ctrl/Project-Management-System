import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;

public class DeleteProject extends JFrame {

    DeleteProject() {

        setTitle("Delete Project");
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
        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(new Color(245, 245, 245));


        // ================= DELETE BOX =================
        JPanel box = new JPanel(new GridBagLayout());

        box.setBackground(Color.WHITE);

        box.setBorder(
                BorderFactory.createLineBorder(
                        Color.GRAY, 2
                )
        );

        box.setPreferredSize(
                new Dimension(620, 390)
        );


        GridBagConstraints g = new GridBagConstraints();

        g.insets = new Insets(
                12, 20, 12, 20
        );


        // ================= FONT =================
        Font labelFont = new Font(
                "Arial",
                Font.BOLD,
                15
        );

        Font buttonFont = new Font(
                "Arial",
                Font.BOLD,
                13
        );


        // ================= TITLE =================
        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 2;
        g.anchor = GridBagConstraints.CENTER;

        JLabel title = new JLabel(
                "DELETE PROJECT"
        );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        title.setForeground(
                new Color(0, 102, 204)
        );

        box.add(title, g);


        // ================= PROJECT NAME LABEL =================
        g.gridwidth = 1;
        g.gridy = 1;
        g.gridx = 0;
        g.anchor = GridBagConstraints.EAST;

        JLabel projectLabel = new JLabel(
                "Project Name"
        );

        projectLabel.setFont(labelFont);

        box.add(projectLabel, g);


        // ================= PROJECT NAME FIELD =================
        g.gridx = 1;
        g.anchor = GridBagConstraints.WEST;

        JTextField projectName =
                new JTextField();

        projectName.setPreferredSize(
                new Dimension(260, 38)
        );

        projectName.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        box.add(projectName, g);


        // ================= SEARCH BUTTON =================
        g.gridy = 2;
        g.gridx = 1;

        JButton search = new JButton(
                "SEARCH"
        );

        search.setPreferredSize(
                new Dimension(130, 40)
        );

        search.setBackground(
                new Color(0, 102, 204)
        );

        search.setForeground(Color.WHITE);

        search.setFont(buttonFont);

        box.add(search, g);


        // ================= STATUS LABEL =================
        g.gridy = 3;
        g.gridx = 0;
        g.anchor = GridBagConstraints.EAST;

        JLabel statusLabel = new JLabel(
                "Project Status"
        );

        statusLabel.setFont(labelFont);

        box.add(statusLabel, g);


        // ================= STATUS =================
        g.gridx = 1;
        g.anchor = GridBagConstraints.WEST;

        JLabel status = new JLabel(
                "Not selected"
        );

        status.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        box.add(status, g);


        // ================= DELETE BUTTON =================
        g.gridy = 4;
        g.gridx = 1;

        JButton delete = new JButton(
                "DELETE PROJECT"
        );

        delete.setPreferredSize(
                new Dimension(190, 42)
        );

        delete.setBackground(
                new Color(0, 102, 204)
        );

        delete.setForeground(Color.WHITE);

        delete.setFont(buttonFont);

        box.add(delete, g);


        // ================= BACK BUTTON =================
        g.gridy = 5;
        g.gridx = 1;

        JButton back = new JButton(
                "BACK"
        );

        back.setPreferredSize(
                new Dimension(130, 40)
        );

        back.setBackground(
                new Color(0, 102, 204)
        );

        back.setForeground(Color.WHITE);

        back.setFont(buttonFont);

        box.add(back, g);


        // ================= SEARCH ACTION =================
        // ================= SEARCH ACTION =================
        search.addActionListener(e -> {

            String name = projectName.getText().trim();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this, "Please enter Project Name"
                );
                return;
            }

            String sql = "SELECT status FROM projects WHERE project_name = ?";

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, name);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        status.setText("Found - " + rs.getString("status"));
                    } else {
                        status.setText("Not Found");
                        JOptionPane.showMessageDialog(
                                this, "Project not found in database!"
                        );
                    }
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                        this, "Database Error: " + ex.getMessage()
                );
            }
        });

        // ================= DELETE ACTION =================
        // ================= DELETE ACTION =================
        delete.addActionListener(e -> {

            String name = projectName.getText().trim();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this, "Please enter Project Name"
                );
                return;
            }

            int result = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete Project: " + name + "?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (result != JOptionPane.YES_OPTION) {
                return;
            }

            String sql = "DELETE FROM projects WHERE project_name = ?";

            try (Connection con = DBConnection.getConnection();
                 PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, name);

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    JOptionPane.showMessageDialog(
                            this, "Project deleted successfully!"
                    );

                    projectName.setText("");
                    status.setText("Not selected");
                } else {
                    JOptionPane.showMessageDialog(
                            this, "Project not found!"
                    );
                    status.setText("Not Found");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                        this, "Database Error: " + ex.getMessage()
                );
            }
        });

        // ================= BACK ACTION =================
        back.addActionListener(e -> {

            new Projects();

            dispose();
        });


        center.add(box);

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
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
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


    // ================= MAIN =================
    public static void main(String[] args) {

        new DeleteProject();
    }
}
