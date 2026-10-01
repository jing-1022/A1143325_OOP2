import java.awt.*;
import javax.swing.*;
import java.util.Random;
import java.awt.event.*;
public class GUI_dice extends JFrame implements ActionListener{
    
    public static void main(String arg[]){
        //BorderLayout border=new BorderLayout();
        //FlowLayout flow=new FlowLayout();
        GUI_dice frm=new GUI_dice();
        //JFrame frm=new JFrame("骰子模擬器");
        // JPanel pne=new JPanel();
        JLabel lab=new JLabel("目前點數:", JLabel.CENTER);
        JButton btn=new JButton("擲骰子");
        
        btn.addActionListener(frm);
        lab.setFont(new Font("微軟正黑體", Font.BOLD, 60));
        // lab.setHorizontalTextPosition(JLabel.CENTER); //有圖片才能用
        // lab.setVerticalTextPosition(JLabel.BOTTOM);
        
        // frm.setSize(400, 320);
        frm.add(btn, BorderLayout.SOUTH);
        // pne.add(lab);
        frm.add(lab);
        frm.setSize(400, 320);
        frm.setVisible(true);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void actionPerformed(ActionEvent e){

    }
}
