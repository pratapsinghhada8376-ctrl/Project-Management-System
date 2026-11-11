import javax.swing.*;
import java.awt.*;

class chandepassword extends JFrame {

    public chandepassword() {

        setTitle("Reset Password");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        // ================= HEADER =================

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setPreferredSize(new Dimension(0, 90));

        ImageIcon icon = new ImageIcon(
                getClass().getResource("poornima.jpeg")
        );

        Image img = icon.getImage().getScaledInstance(
                500, 90, Image.SCALE_SMOOTH
        );

        JLabel headerImage = new JLabel(
                new ImageIcon(img)
        );

        headerImage.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        header.add(
                headerImage,
                BorderLayout.CENTER
        );

        add(header, BorderLayout.NORTH);


        // ================= CENTER =================

        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(
                new Color(245, 245, 245)
        );


        // ================= BACK ARROW =================

        JButton backBtn = new JButton("←");

        backBtn.setFont(
                new Font("Arial", Font.BOLD, 30)
        );

        backBtn.setForeground(
                new Color(0, 102, 204)
        );

        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setFocusPainted(false);

        backBtn.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );


        // Back to Dashboard

        backBtn.addActionListener(e -> {
            new Login();
            dispose();
        });




        // Arrow Panel
        JPanel arrowPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.LEFT,
                        15,
                        5
                )
        );

        arrowPanel.setBackground(
                new Color(245, 245, 245)
        );

        arrowPanel.add(backBtn);

        center.add(
                arrowPanel,
                BorderLayout.NORTH
        );


        // ================= RESET BOX =================

        JPanel centerBox =
                new JPanel(new GridBagLayout());

        centerBox.setBackground(
                new Color(245, 245, 245)
        );


        JPanel panel = new JPanel(null);

        panel.setPreferredSize(
                new Dimension(420, 380)
        );

        panel.setBackground(Color.WHITE);

        panel.setBorder(
                BorderFactory.createLineBorder(
                        Color.LIGHT_GRAY,
                        2
                )
        );


        // ================= HEADING =================

        JLabel heading =
                new JLabel("RESET PASSWORD");

        heading.setBounds(
                0, 20, 420, 35
        );

        heading.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        heading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        panel.add(heading);


        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(50, 65, 200, 25);
        userLabel.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(50, 90, 320, 35);
        panel.add(userField);

        // ================= CURRENT PASSWORD =================

        JLabel oldPass =
                new JLabel("Current Password");

        oldPass.setBounds(
                50, 90, 200, 25
        );

        oldPass.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        panel.add(oldPass);


        JPasswordField current =
                new JPasswordField();

        current.setBounds(
                50, 115, 320, 35
        );

        panel.add(current);


        // ================= NEW PASSWORD =================

        JLabel newPass =
                new JLabel("New Password");

        newPass.setBounds(
                50, 165, 200, 25
        );

        newPass.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        panel.add(newPass);


        JPasswordField newPassword =
                new JPasswordField();

        newPassword.setBounds(
                50, 190, 320, 35
        );

        panel.add(newPassword);


        // ================= CONFIRM PASSWORD =================

        JLabel confirmPass =
                new JLabel("Confirm Password");

        confirmPass.setBounds(
                50, 240, 200, 25
        );

        confirmPass.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        panel.add(confirmPass);


        JPasswordField confirm =
                new JPasswordField();

        confirm.setBounds(
                50, 265, 320, 35
        );

        panel.add(confirm);


        // ================= CHANGE PASSWORD BUTTON =================

        JButton changeBtn =
                new JButton("CHANGE PASSWORD");

        changeBtn.setBounds(
                100, 320, 220, 40
        );

        changeBtn.setBackground(
                new Color(0, 102, 204)
        );

        changeBtn.setForeground(Color.WHITE);

        changeBtn.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        changeBtn.setFocusPainted(false);

        panel.add(changeBtn);

        changeBtn.addActionListener(e -> {

            String user = userField.getText().trim();
            String oldP = new String(current.getPassword());
            String newP = new String(newPassword.getPassword());
            String confirmP = new String(confirm.getPassword());

            if (user.isEmpty() || oldP.isEmpty()
                    || newP.isEmpty() || confirmP.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this, "Please fill all fields!"
                );
                return;
            }

            if (!newP.equals(confirmP)) {
                JOptionPane.showMessageDialog(
                        this, "New passwords do not match!"
                );
                return;
            }

            if (oldP.equals(newP)) {
                JOptionPane.showMessageDialog(
                        this, "New password must be different!"
                );
                return;
            }

            String checkSql =
                    "SELECT username FROM users WHERE username = ? AND password = ?";

            String updateSql =
                    "UPDATE users SET password = ? WHERE username = ?";

            try (java.sql.Connection con = DBConnection.getConnection();
                 java.sql.PreparedStatement check =
                         con.prepareStatement(checkSql)) {

                check.setString(1, user);
                check.setString(2, oldP);

                try (java.sql.ResultSet rs = check.executeQuery()) {

                    if (!rs.next()) {
                        JOptionPane.showMessageDialog(
                                this, "Invalid username or current password!"
                        );
                        return;
                    }
                }

                try (java.sql.PreparedStatement update =
                             con.prepareStatement(updateSql)) {

                    update.setString(1, newP);
                    update.setString(2, user);

                    if (update.executeUpdate() == 1) {
                        JOptionPane.showMessageDialog(
                                this, "Password Changed Successfully!"
                        );

                        new Login();
                        dispose();
                    }
                }

            } catch (java.sql.SQLException ex) {
                JOptionPane.showMessageDialog(
                        this, "Database Error: " + ex.getMessage()
                );
                ex.printStackTrace();
            }
        });


        // Add panel
        centerBox.add(panel);

        center.add(
                centerBox,
                BorderLayout.CENTER
        );

        add(
                center,
                BorderLayout.CENTER
        );


        // ================= FOOTER =================

        JPanel footer = new JPanel();

        footer.setPreferredSize(
                new Dimension(900, 30)
        );

        footer.setBackground(
                new Color(60, 60, 60)
        );


        JLabel footerLabel =
                new JLabel(
                        "Developed By : Pratap Singh Hada"
                );

        footerLabel.setForeground(
                Color.WHITE
        );

        footerLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        footer.add(footerLabel);

        add(
                footer,
                BorderLayout.SOUTH
        );


        // ================= CHANGE PASSWORD ACTION =================




        setVisible(true);
    }


    // ================= MAIN =================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> new chandepassword()
        );

    }
}