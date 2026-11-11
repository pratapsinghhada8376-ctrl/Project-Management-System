import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {

    Login() {
        setTitle("Project Management System");
        setSize(800, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // HEADER
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setPreferredSize(new Dimension(0, 90));

        ImageIcon icon = new ImageIcon(
                getClass().getResource("poornima.jpeg")
        );

        Image img = icon.getImage().getScaledInstance(
                520, 90, Image.SCALE_SMOOTH
        );

        header.add(new JLabel(new ImageIcon(img)));
        add(header, BorderLayout.NORTH);


        // CENTER
        JPanel center = new JPanel();
        center.setBackground(new Color(245, 245, 245));
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        // Project Name
        JLabel title = new JLabel("PROJECT MANAGEMENT SYSTEM");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(new Color(0, 54, 204));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        center.add(Box.createVerticalStrut(30));
        center.add(title);
        center.add(Box.createVerticalStrut(40));


        // LOGIN BOX
        JPanel box = new JPanel(new GridBagLayout());
        box.setBackground(Color.WHITE);
        box.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        box.setPreferredSize(new Dimension(500, 280));
        box.setMaximumSize(new Dimension(500, 280));


        // Username
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(10, 5, 10, 5);

// Username
        g.gridx = 0;
        g.gridy = 0;
        g.anchor = GridBagConstraints.EAST;

        box.add(new JLabel("Username"), g);

        g.gridx = 1;
        g.anchor = GridBagConstraints.WEST;

        JTextField username = new JTextField();
        username.setPreferredSize(new Dimension(240, 40));
        box.add(username, g);


// Password
        g.gridx = 0;
        g.gridy = 1;
        g.anchor = GridBagConstraints.EAST;

        box.add(new JLabel("Password"), g);

        g.gridx = 1;
        g.anchor = GridBagConstraints.WEST;

        JPasswordField password = new JPasswordField();
        password.setPreferredSize(new Dimension(240, 40));
        box.add(password, g);


// Login
        g.gridx = 1;
        g.gridy = 2;

        JButton login = new JButton("LOGIN");
        login.setPreferredSize(new Dimension(180, 45));
        login.setBackground(new Color(0, 102, 204));
        login.setForeground(Color.WHITE);

        box.add(login, g);






        login.addActionListener(e -> {

            String u = username.getText().trim();
            String p = new String(password.getPassword());

            String sql = "SELECT password FROM users WHERE username = ?";

            try (java.sql.Connection con = DBConnection.getConnection();
                 java.sql.PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, u);

                try (java.sql.ResultSet rs = ps.executeQuery()) {

                    if (rs.next() && rs.getString("password").equals(p)) {
                        new Dashboard();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(
                                this, "Invalid Username or Password"
                        );
                    }
                }

            } catch (java.sql.SQLException ex) {
                JOptionPane.showMessageDialog(
                        this, "Database Error: " + ex.getMessage()
                );
            }
        });
        ;
// Forgot Password
        g.gridy = 3;

        JButton forgot = new JButton("Forgot Password?");
        forgot.setForeground(new Color(0, 102, 204));
        forgot.setBorderPainted(false);
        forgot.setContentAreaFilled(false);

        forgot.addActionListener(e -> {
            new chandepassword();
            dispose();
        });

        box.add(forgot, g);

        center.add(box);
        add(center, BorderLayout.CENTER);




        // FOOTER
        JLabel footer = new JLabel(
                "Developed By : Pratap Singh Hada",
                SwingConstants.CENTER
        );

        footer.setForeground(Color.WHITE);
        footer.setBackground(new Color(50, 50, 50));
        footer.setOpaque(true);
        footer.setPreferredSize(new Dimension(0, 35));

        add(footer, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }
}