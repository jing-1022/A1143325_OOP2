import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    public LoginFrame() {
        setTitle("登入");
        setSize(300, 200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 讓視窗置中顯示

        // 帳號標籤與輸入框
        JLabel l1 = new JLabel("帳號:");
        l1.setBounds(30, 25, 50, 25);
        JTextField t1 = new JTextField();
        t1.setBounds(90, 25, 150, 25);

        // 密碼標籤與輸入框（密碼建議改用 JPasswordField）
        JLabel l2 = new JLabel("密碼:");
        l2.setBounds(30, 65, 50, 25);
        JPasswordField t2 = new JPasswordField();
        t2.setBounds(90, 65, 150, 25);

        // 登入按鈕
        JButton btn = new JButton("登入");
        btn.setBounds(100, 110, 80, 30);

        // 加入元件至視窗
        add(l1);
        add(t1);
        add(l2);
        add(t2);
        add(btn);

        // 按鈕事件監聽
        btn.addActionListener(e -> {
            String account = t1.getText();
            String password = new String(t2.getPassword());

            // Java 字串比對必須使用 .equals()
            if ("admin".equals(account) && "1234".equals(password)) {
                System.out.println("登入成功");
                JOptionPane.showMessageDialog(this, "登入成功！");
            } else {
                System.out.println("帳號或密碼錯誤");
                JOptionPane.showMessageDialog(this, "帳號或密碼錯誤！", "錯誤", JOptionPane.ERROR_MESSAGE);
            }
        });

        // 所有元件加入後再顯示視窗
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}
