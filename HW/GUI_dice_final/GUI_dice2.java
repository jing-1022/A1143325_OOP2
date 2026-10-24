import java.awt.*;
import javax.swing.*;
import java.util.Random;
import java.awt.event.*;
public class GUI_dice2 extends JFrame implements ActionListener{
    
    //BorderLayout border=new BorderLayout();
    //FlowLayout flow=new FlowLayout();
    //static GUI_dice frm=new GUI_dice();
    //JFrame frm=new JFrame("骰子模擬器");
    // JPanel pne=new JPanel();
    private JLabel lab1 = new JLabel("已擲 0 次，總和 0，平均 0.00", JLabel.CENTER);
    private JLabel lab=new JLabel("目前點數:", JLabel.CENTER);
    private JButton btn=new JButton("擲骰子");  

    private int count = 0;       // 記錄總次數
    private int sum = 0;         // 記錄總和
    private Random ranInt = new Random();
    //public static void main(String arg[]){
    public GUI_dice2(){  
        super("骰子模擬器");
        btn.addActionListener(this);
        lab.setFont(new Font("微軟正黑體", Font.BOLD, 60));
        // lab.setHorizontalTextPosition(JLabel.CENTER); //有圖片才能用
        // lab.setVerticalTextPosition(JLabel.BOTTOM);
        
        this.add(btn, BorderLayout.SOUTH);
        // pne.add(lab);
        this.add(lab, BorderLayout.CENTER);
        this.add(lab1, BorderLayout.NORTH);
  
        this.setSize(400, 320);
        this.setLocationRelativeTo(null); // 開啟時視窗置中
        //frm.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
    public void actionPerformed(ActionEvent e){
        int dicenum = ranInt.nextInt(6) + 1;
        
        count++;
        sum += dicenum;
        double avg = (double)sum / count;

        lab1.setText(String.format("已擲 %d 次，總和 %d，平均 %.2f", count, sum, avg));
        lab.setText(String.valueOf(dicenum));

        if (dicenum == 6){
            lab.setForeground(Color.GREEN);
        } else if (dicenum == 1) {
            lab.setForeground(Color.RED);
        } else {
            lab.setForeground(Color.BLACK);
        }
        
    }
    public static void main(String[] args) {
        GUI_dice2 frm = new GUI_dice2();
        frm.setVisible(true);
    }
}
