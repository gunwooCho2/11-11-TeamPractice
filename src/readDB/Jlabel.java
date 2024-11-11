package readDB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Jlabel extends JFrame {
    
    // 버튼 패널
    private JPanel buttonPanel;
    
    // 뷰 패널
    private JPanel viewPanel;

    // 생성자
    public Jlabel() {
        // 기본 프레임 설정
        setTitle("Swing Example");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // 화면 중앙에 위치

        // 레이아웃 설정
        setLayout(new BorderLayout());

        // 버튼 패널 생성 및 추가
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));  // 세로로 버튼 배치
        createButtonPanel();  // 버튼 패널에 버튼 추가
        add(buttonPanel, BorderLayout.WEST);  // 왼쪽에 버튼 패널 배치

        // 뷰 패널 생성 및 추가
        viewPanel = new JPanel();
        viewPanel.setLayout(new BorderLayout());
        add(viewPanel, BorderLayout.CENTER);  // 오른쪽에 뷰 패널 배치

        // 뷰 패널에 테이블 추가 (기본값으로 3행 3열로 설정)
        createViewPanel(3, 3);
    }

    // 버튼 패널 생성 메서드
    private void createButtonPanel() {
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");
        JButton button3 = new JButton("Button 3");

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
    }

    // 뷰 패널에 테이블을 생성하는 메서드 (행과 열의 수를 인자로 받음)
    private void createViewPanel(int rows, int cols) {
        // "Shall" JLabel 생성
        JLabel shallLabel = new JLabel("Shall", SwingConstants.CENTER);
        viewPanel.add(shallLabel, BorderLayout.NORTH);  // 북쪽에 배치

        // 테이블을 생성할 데이터 및 열 이름 설정
        String[] columnNames = new String[cols];
        for (int i = 0; i < cols; i++) {
            columnNames[i] = "Column " + (i + 1);  // "Column 1", "Column 2" 등
        }

        // 테이블 데이터 (행 수만큼 배열을 생성)
        Object[][] data = new Object[rows][cols];
        
        // DefaultTableModel을 사용하여 데이터 모델 생성
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);

        // 테이블을 뷰 패널에 추가 (중앙에 배치)
        JScrollPane scrollPane = new JScrollPane(table);
        viewPanel.add(scrollPane, BorderLayout.CENTER);
    }

    // 메인 메서드
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	Jlabel frame = new Jlabel();
            frame.setVisible(true);
        });
    }
}
