import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ch2_units extends JFrame implements ActionListener, ItemListener {
    private JComboBox<String> categoryComboBox;
    private JComboBox<String> sourceUnitComboBox;
    private JComboBox<String> targetUnitComboBox;
    private JTextField sourceTextField;
    private JTextField targetTextField;
    private JButton convertButton;

    // 各類型對應的單位選項
    private final String[] lengthUnits = {"公尺", "公分", "英吋", "英尺"};
    private final String[] weightUnits = {"公斤", "公克", "磅", "盎司"};
    private final String[] tempUnits = {"攝氏", "華氏", "克氏"};

    public ch2_units() {
        setTitle("單位換算器");
        setSize(480, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // 主視窗使用 BorderLayout 規格
        setLayout(new BorderLayout());

        // --- NORTH：JComboBox 選擇換算類型 ---
        JPanel northPanel = new JPanel(); // 預設就是 FlowLayout
        northPanel.add(new JLabel("換算類型："));
        categoryComboBox = new JComboBox<>(new String[]{"長度", "重量", "溫度"});
        northPanel.add(categoryComboBox);
        add(northPanel, BorderLayout.NORTH);

        // --- CENTER：兩列輸入與單位選擇（改用 FlowLayout 加上兩個子面板來達成上下兩列）---
        JPanel centerPanel = new JPanel(); 
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));

        // 第一列面板
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        sourceTextField = new JTextField("1", 10);
        sourceUnitComboBox = new JComboBox<>(lengthUnits);
        row1.add(new JLabel("輸入數值："));
        row1.add(sourceTextField);
        row1.add(sourceUnitComboBox);

        // 第二列面板
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        targetTextField = new JTextField(10);
        targetTextField.setEditable(false);
        targetUnitComboBox = new JComboBox<>(lengthUnits);
        row2.add(new JLabel("換算結果："));
        row2.add(targetTextField);
        row2.add(targetUnitComboBox);

        // 將兩列加入到中間面板中
        centerPanel.add(row1);
        centerPanel.add(row2);
        add(centerPanel, BorderLayout.CENTER);

        // --- SOUTH：「換算」按鈕 ---
        JPanel southPanel = new JPanel();
        convertButton = new JButton("換算");
        southPanel.add(convertButton);
        add(southPanel, BorderLayout.SOUTH);

        // 註冊事件傾聽者 (課本標準寫法)
        categoryComboBox.addItemListener(this);
        convertButton.addActionListener(this);
    }

    // 1. 下拉選單切換類型時，動態更新上下單位的選項
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String selectedCategory = (String) categoryComboBox.getSelectedItem();
            sourceUnitComboBox.removeAllItems();
            targetUnitComboBox.removeAllItems();

            String[] units;
            if ("長度".equals(selectedCategory)) {
                units = lengthUnits;
            } else if ("重量".equals(selectedCategory)) {
                units = weightUnits;
            } else {
                units = tempUnits;
            }

            for (String unit : units) {
                sourceUnitComboBox.addItem(unit);
                targetUnitComboBox.addItem(unit);
            }
        }
    }

    // 2. 點擊「換算」按鈕時的計算邏輯
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double inputValue = Double.parseDouble(sourceTextField.getText());
            String category = (String) categoryComboBox.getSelectedItem();
            String sourceUnit = (String) sourceUnitComboBox.getSelectedItem();
            String targetUnit = (String) targetUnitComboBox.getSelectedItem();
            double result = 0.0;

            if ("長度".equals(category)) {
                result = convertLength(inputValue, sourceUnit, targetUnit);
            } else if ("重量".equals(category)) {
                result = convertWeight(inputValue, sourceUnit, targetUnit);
            } else if ("溫度".equals(category)) {
                result = convertTemp(inputValue, sourceUnit, targetUnit);
            }

            targetTextField.setText(String.format("%.4f", result));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "請輸入有效的數字！", "格式錯誤", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 長度換算 (基準：公尺)
    private double convertLength(double val, String from, String to) {
        double valInMeters = val;
        if ("公分".equals(from)) valInMeters = val / 100.0;
        else if ("英吋".equals(from)) valInMeters = val * 0.0254;
        else if ("英尺".equals(from)) valInMeters = val * 0.3048;

        double result = valInMeters;
        if ("公分".equals(to)) result = valInMeters * 100.0;
        else if ("英吋".equals(to)) result = valInMeters / 0.0254;
        else if ("英尺".equals(to)) result = valInMeters / 0.3048;
        return result;
    }

    // 重量換算 (基準：公斤)
    private double convertWeight(double val, String from, String to) {
        double valInKg = val;
        if ("公克".equals(from)) valInKg = val / 1000.0;
        else if ("磅".equals(from)) valInKg = val * 0.45359237;
        else if ("盎司".equals(from)) valInKg = val * 0.02834952;

        double result = valInKg;
        if ("公克".equals(to)) result = valInKg * 1000.0;
        else if ("磅".equals(to)) result = valInKg / 0.45359237;
        else if ("盎司".equals(to)) result = valInKg / 0.02834952;
        return result;
    }

    // 溫度換算
    private double convertTemp(double val, String from, String to) {
        if (from.equals(to)) return val;
        double celsius = val;
        if ("華氏".equals(from)) celsius = (val - 32) * 5.0 / 9.0;
        else if ("克氏".equals(from)) celsius = val - 273.15;

        if ("攝氏".equals(to)) return celsius;
        else if ("華氏".equals(to)) return celsius * 9.0 / 5.0 + 32;
        else if ("克氏".equals(to)) return celsius + 273.15;
        return celsius;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ch2_unit().setVisible(true);
        });
    }
}
