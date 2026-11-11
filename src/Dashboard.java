import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    public Dashboard() {

        setTitle("Project Management System - Dashboard");
        setSize(900,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //================ HEADER =================
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setPreferredSize(new Dimension(0, 90));

        ImageIcon icon = new ImageIcon(
                getClass().getResource("poornima.jpeg")
        );

        Image img = icon.getImage().getScaledInstance(
                500, 90, Image.SCALE_SMOOTH
        );

        header.add(new JLabel(new ImageIcon(img)));
        add(header, BorderLayout.NORTH);


        // CENTER
        JPanel center = new JPanel();
        center.setBackground(new Color(245, 245, 245));
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        // Project Name
        JLabel title = new JLabel("PROJECT MANAGEMENT SYSTEM");
        title.setFont(new Font("Arial", Font.BOLD, 50));
        title.setForeground(new Color(0, 102, 204));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        center.add(Box.createVerticalStrut(30));
        center.add(title);
        center.add(Box.createVerticalStrut(40));


        //================ CENTER =================

        center = new JPanel();
        center.setBackground(new Color(245,245,245));
        center.setLayout(new GridBagLayout());

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400,350));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY,2));
        panel.setLayout(null);

        JLabel heading = new JLabel("DASHBOARD");
        heading.setBounds(0,20,400,40);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setFont(new Font("Arial",Font.BOLD,28));
        panel.add(heading);

        JButton projectBtn = new JButton("PROJECTS");
        projectBtn.setBounds(100,90,200,45);
        projectBtn.setBackground(new Color(0,102,204));
        projectBtn.setForeground(Color.WHITE);
        projectBtn.setFocusPainted(false);
        panel.add(projectBtn);
        projectBtn.addActionListener(e -> {
            new Projects();
            dispose();
        });

        JButton newProjectBtn = new JButton("NEW PROJECT");
        newProjectBtn.setBounds(100,160,200,45);
        newProjectBtn.setBackground(new Color(0,102,204));
        newProjectBtn.setForeground(Color.WHITE);
        newProjectBtn.setFocusPainted(false);
        panel.add(newProjectBtn);
        newProjectBtn.addActionListener(e -> {
            new newproject();
            dispose();
        });

        JButton resetBtn = new JButton("RESET PASSWORD");
        resetBtn.setBounds(100,230,200,45);
        resetBtn.setBackground(new Color(0,102,204));
        resetBtn.setForeground(Color.WHITE);
        resetBtn.setFocusPainted(false);
        panel.add(resetBtn);
        
        resetBtn.addActionListener(e -> {

            new chandepassword();   // Reset Password page open hoga
            dispose();             // Dashboard page close ho jayega

        });
        ;

        center.add(panel);

        add(center,BorderLayout.CENTER);

        //================ FOOTER =================

        JPanel footer = new JPanel();
        footer.setBackground(new Color(60,60,60));
        footer.setPreferredSize(new Dimension(900,30));

        JLabel footerLabel = new JLabel("Developed By : Pratap Singh Hada");
        footerLabel.setForeground(Color.WHITE);
        footerLabel.setFont(new Font("Arial",Font.BOLD,14));

        footer.add(footerLabel);

        add(footer,BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Dashboard());
    }
}
