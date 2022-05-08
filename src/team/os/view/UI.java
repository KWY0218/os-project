package team.os.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatDarkLaf;

import team.os.enums.PriorityType;
import team.os.model.CPU;
import team.os.model.Core;
import team.os.model.ECore;
import team.os.model.History;
import team.os.model.PCore;
import team.os.model.Process;
import team.os.scheduling.FCFS;
import team.os.scheduling.HRRN;
import team.os.scheduling.OWN;
import team.os.scheduling.RR;
import team.os.scheduling.SPN;
import team.os.scheduling.SRTN;

@SuppressWarnings("serial")
public class UI extends JFrame {

	private JPanel contentPane;
	private JPanel panel_2;
	private JPanel panel_5;
	private JLabel lblNewLabel;
	private JPanel panel_6;
	private JLabel lblPowerCon;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JButton addProcessButton;
	private JButton resetProcessButton;
	private JButton runSchedulingButton;
	private JPanel panel_1;
	private JLabel lblNewLabel_5;
	private JScrollPane scrollPane;
	private JLabel lblPowerConsumption;
	private JTable table;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JScrollPane scrollPane_1;
	private JTable table_1;
	private JScrollPane scrollPane_2;
	private JTable table_2;
	private JLabel lblCoreSetting;
	private JLabel lblTimeQuantum;
	private JScrollPane scrollPane_3;
	private JList<String> list_1;
	private JRadioButton fcfsRadioButton;
	private JRadioButton rrRadioButton;
	private JRadioButton spnRadioButton;
	private JRadioButton ownRadioButton;
	private JRadioButton srtnRadioButton;
	private JRadioButton hrrnRadioButton;
	private JLabel lblW_2;
	private JTextField textField_2;
	private JLabel lblSeconds;
	private JTextField textField_3;
	private JLabel lblPCore;
	private JTextField textField_4;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JLabel lblECore;
	private JTextField textField_5;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField;
	private JTextField textField_1;
	private JRadioButton PerformanceFirstButton;
	private JRadioButton PowerFirstButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlatDarkLaf.setup();
					UI frame = new UI();
					frame.setTitle("Process Scheduling Simulator (Koreatech OS Team4)");
					frame.setIconImage(Toolkit.getDefaultToolkit().getImage("resource/icon.png"));
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(Color.WHITE, 1));
		panel_2.setBounds(10, 492, 312, 81);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		lblNewLabel_7 = new JLabel("Burst Time (BT) :");
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7.setBounds(0, 46, 111, 20);
		panel_2.add(lblNewLabel_7);

		lblNewLabel_8 = new JLabel("Arrival Time (AT) :");
		lblNewLabel_8.setForeground(Color.WHITE);
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_8.setBounds(0, 12, 111, 20);
		panel_2.add(lblNewLabel_8);

		lblNewLabel_10 = new JLabel("second (s)");
		lblNewLabel_10.setForeground(Color.WHITE);
		lblNewLabel_10.setBounds(236, 12, 64, 20);
		panel_2.add(lblNewLabel_10);

		lblNewLabel_11 = new JLabel("second (s)");
		lblNewLabel_11.setForeground(Color.WHITE);
		lblNewLabel_11.setBounds(236, 46, 64, 20);
		panel_2.add(lblNewLabel_11);

		textField_7 = new JTextField();
		textField_7.setForeground(Color.WHITE);
		textField_7.setText("0");
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setColumns(10);
		textField_7.setBounds(123, 10, 101, 26);
		panel_2.add(textField_7);

		textField_8 = new JTextField();
		textField_8.setForeground(Color.WHITE);
		textField_8.setText("1");
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setColumns(10);
		textField_8.setBounds(123, 44, 101, 26);
		panel_2.add(textField_8);

		lblNewLabel = new JLabel("Scheduling Algorithms");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(334, 10, 242, 20);
		lblNewLabel.setFont(new Font("", Font.BOLD, 14));
		contentPane.add(lblNewLabel);

		panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(Color.WHITE));
		panel_6.setBounds(334, 42, 242, 148);
		contentPane.add(panel_6);
		panel_6.setLayout(null);

		fcfsRadioButton = new JRadioButton("FCFS");
		fcfsRadioButton.setHorizontalAlignment(SwingConstants.LEFT);
		fcfsRadioButton.setForeground(Color.WHITE);
		fcfsRadioButton.setSelected(true);
		fcfsRadioButton.setBounds(15, 19, 89, 24);
		panel_6.add(fcfsRadioButton);

		spnRadioButton = new JRadioButton("SPN");
		spnRadioButton.setForeground(Color.WHITE);
		spnRadioButton.setBounds(15, 62, 89, 24);
		panel_6.add(spnRadioButton);

		ownRadioButton = new JRadioButton("OWN");
		ownRadioButton.setForeground(Color.WHITE);
		ownRadioButton.setBounds(141, 105, 89, 24);
		panel_6.add(ownRadioButton);

		hrrnRadioButton = new JRadioButton("HRRN");
		hrrnRadioButton.setForeground(Color.WHITE);
		hrrnRadioButton.setBounds(15, 105, 89, 24);
		panel_6.add(hrrnRadioButton);

		srtnRadioButton = new JRadioButton("SRTN");
		srtnRadioButton.setBounds(141, 62, 89, 24);
		panel_6.add(srtnRadioButton);
		srtnRadioButton.setHorizontalAlignment(SwingConstants.LEFT);
		srtnRadioButton.setForeground(Color.WHITE);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(fcfsRadioButton);
		buttonGroup.add(spnRadioButton);
		buttonGroup.add(hrrnRadioButton);
		buttonGroup.add(ownRadioButton);
		buttonGroup.add(srtnRadioButton);

		rrRadioButton = new JRadioButton("RR");
		rrRadioButton.setBounds(141, 19, 89, 24);
		panel_6.add(rrRadioButton);
		rrRadioButton.setHorizontalAlignment(SwingConstants.LEFT);
		rrRadioButton.setForeground(Color.WHITE);
		buttonGroup.add(rrRadioButton);

		lblPowerCon = new JLabel("Total Values");
		lblPowerCon.setForeground(Color.WHITE);
		lblPowerCon.setBounds(868, 8, 223, 25);
		lblPowerCon.setFont(new Font("", Font.BOLD, 14));
		contentPane.add(lblPowerCon);

		lblNewLabel_1 = new JLabel("Ready Queue");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(334, 200, 920, 20);
		lblNewLabel_1.setFont(new Font("", Font.BOLD, 14));
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Gantt Chart");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(334, 290, 327, 20);
		lblNewLabel_2.setFont(new Font("", Font.BOLD, 14));
		contentPane.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Process State");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(334, 462, 920, 20);
		lblNewLabel_3.setFont(new Font("", Font.BOLD, 14));
		contentPane.add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel(new ImageIcon("resource/koreatech.png"));
		lblNewLabel_4.setBounds(10, 10, 312, 67);
		contentPane.add(lblNewLabel_4);

		addProcessButton = new JButton("Add");
		addProcessButton.setForeground(Color.WHITE);
		addProcessButton.setBounds(10, 590, 151, 35);
		addProcessButton.setFont(new Font("", Font.BOLD, 14));
		addProcessButton.setBorder(new LineBorder(Color.WHITE, 1));
		addProcessButton.addActionListener(e -> {
			addProcess(textField_7, textField_8);
		});
		contentPane.add(addProcessButton);

		resetProcessButton = new JButton("Reset");
		resetProcessButton.setForeground(Color.WHITE);
		resetProcessButton.setBounds(171, 590, 151, 35);
		resetProcessButton.setFont(new Font("", Font.BOLD, 14));
		resetProcessButton.setBorder(new LineBorder(Color.WHITE, 1));
		resetProcessButton.addActionListener(e -> {
			resetProcess();
		});
		contentPane.add(resetProcessButton);

		runSchedulingButton = new JButton("Run");
		runSchedulingButton.setForeground(Color.WHITE);
		runSchedulingButton.setBounds(10, 635, 312, 35);
		runSchedulingButton.setFont(new Font("", Font.BOLD, 14));
		runSchedulingButton.setBorder(new LineBorder(Color.WHITE, 1));
		runSchedulingButton.addActionListener(e -> {
			runScheduling();
		});
		contentPane.add(runSchedulingButton);

		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.WHITE));
		panel_1.setBounds(588, 42, 268, 81);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		lblPCore = new JLabel("P Core");
		lblPCore.setForeground(Color.WHITE);
		lblPCore.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPCore.setBounds(6, 13, 43, 20);
		panel_1.add(lblPCore);

		textField_4 = new JTextField();
		textField_4.setForeground(Color.WHITE);
		textField_4.setEditable(false);
		textField_4.setText("0");
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setColumns(10);
		textField_4.setBounds(61, 10, 118, 26);
		panel_1.add(textField_4);

		btnNewButton_3 = new JButton("-");
		btnNewButton_3.setForeground(Color.WHITE);
		btnNewButton_3.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton_3.addActionListener(e -> {
			addCoreSize(textField_4, new JTextField[] { textField_4, textField_5 }, -1);
		});
		btnNewButton_3.setBounds(229, 12, 26, 26);
		panel_1.add(btnNewButton_3);

		btnNewButton_4 = new JButton("+");
		btnNewButton_4.setForeground(Color.WHITE);
		btnNewButton_4.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton_4.addActionListener(e -> {
			addCoreSize(textField_4, new JTextField[] { textField_4, textField_5 }, 1);
		});
		btnNewButton_4.setBounds(194, 12, 26, 26);
		panel_1.add(btnNewButton_4);

		lblECore = new JLabel("E Core");
		lblECore.setForeground(Color.WHITE);
		lblECore.setHorizontalAlignment(SwingConstants.RIGHT);
		lblECore.setBounds(6, 46, 43, 20);
		panel_1.add(lblECore);

		textField_5 = new JTextField();
		textField_5.setForeground(Color.WHITE);
		textField_5.setEditable(false);
		textField_5.setText("1");
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setColumns(10);
		textField_5.setBounds(61, 43, 118, 26);
		panel_1.add(textField_5);

		btnNewButton_5 = new JButton("+");
		btnNewButton_5.setForeground(Color.WHITE);
		btnNewButton_5.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton_5.addActionListener(e -> {
			addCoreSize(textField_5, new JTextField[] { textField_4, textField_5 }, 1);
		});
		btnNewButton_5.setBounds(194, 44, 26, 26);
		panel_1.add(btnNewButton_5);

		btnNewButton_6 = new JButton("-");
		btnNewButton_6.setForeground(Color.WHITE);
		btnNewButton_6.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton_6.addActionListener(e -> {
			addCoreSize(textField_5, new JTextField[] { textField_4, textField_5 }, -1);
		});
		btnNewButton_6.setBounds(229, 44, 26, 26);
		panel_1.add(btnNewButton_6);

		lblNewLabel_5 = new JLabel("Time Table");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(10, 89, 312, 20);
		lblNewLabel_5.setFont(new Font("", Font.BOLD, 14));
		contentPane.add(lblNewLabel_5);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 121, 312, 329);
		contentPane.add(scrollPane);
		scrollPane.setBorder(new LineBorder(Color.WHITE, 1));

		table = new JTable();
		table.setForeground(Color.WHITE);
		table.getTableHeader().setForeground(Color.WHITE);
		scrollPane.setViewportView(table);
		((DefaultTableCellRenderer) table.getDefaultRenderer(String.class)).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableModel) table.getModel())
		.setColumnIdentifiers(new String[] { "PID", "Arrival Time", "Burst Time" });

		lblNewLabel_6 = new JLabel("Create Process");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setFont(new Font("", Font.BOLD, 14));
		lblNewLabel_6.setBounds(10, 462, 312, 20);
		contentPane.add(lblNewLabel_6);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBorder(new LineBorder(Color.WHITE, 1));
		scrollPane_1.setBounds(334, 492, 920, 180);
		contentPane.add(scrollPane_1);

		table_1 = new JTable();
		table_1.setForeground(Color.WHITE);
		table_1.getTableHeader().setForeground(Color.WHITE);
		scrollPane_1.setViewportView(table_1);
		((DefaultTableCellRenderer) table_1.getDefaultRenderer(String.class)).setHorizontalAlignment(JLabel.CENTER);
		((DefaultTableModel) table_1.getModel()).setColumnIdentifiers(new String[] { "PID", "Arrival Time",
				"Burst Time", "Waiting Time", "Turnaround Time", "Normalized TT" });

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBorder(new LineBorder(Color.WHITE, 1));
		scrollPane_2.setBounds(334, 322, 920, 128);
		contentPane.add(scrollPane_2);

		table_2 = new JTable();
		table_2.setForeground(Color.WHITE);
		table_2.getTableHeader().setForeground(Color.WHITE);
		scrollPane_2.setViewportView(table_2);
		table_2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		((DefaultTableCellRenderer) table_2.getDefaultRenderer(String.class)).setHorizontalAlignment(JLabel.CENTER);

		lblCoreSetting = new JLabel("Core Setting");
		lblCoreSetting.setForeground(Color.WHITE);
		lblCoreSetting.setBounds(588, 10, 160, 20);
		lblCoreSetting.setFont(new Font("", Font.BOLD, 14));
		contentPane.add(lblCoreSetting);

		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(334, 230, 920, 50);
		scrollPane_3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_3.setBorder(new LineBorder(Color.WHITE, 1));
		contentPane.add(scrollPane_3);

		list_1 = new JList<String>(new DefaultListModel<String>());
		list_1.setForeground(Color.WHITE);
		list_1.setVisibleRowCount(1);
		list_1.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list_1.setFixedCellWidth(40);
		scrollPane_3.setViewportView(list_1);

		panel_5 = new JPanel();
		panel_5.setBounds(868, 42, 386, 81);
		contentPane.add(panel_5);
		panel_5.setBorder(new LineBorder(Color.WHITE));
		panel_5.setLayout(null);

		lblPowerConsumption = new JLabel("Total Power Consumption :");
		lblPowerConsumption.setForeground(Color.WHITE);
		lblPowerConsumption.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPowerConsumption.setBounds(12, 13, 187, 26);
		panel_5.add(lblPowerConsumption);

		lblW_2 = new JLabel("(W)");
		lblW_2.setForeground(Color.WHITE);
		lblW_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblW_2.setBounds(346, 13, 34, 26);
		panel_5.add(lblW_2);

		textField_2 = new JTextField();
		textField_2.setForeground(Color.WHITE);
		textField_2.setText("0");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(211, 13, 123, 26);
		panel_5.add(textField_2);

		JLabel lblTotalBurstTime = new JLabel("Total Burst Time :");
		lblTotalBurstTime.setForeground(Color.WHITE);
		lblTotalBurstTime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalBurstTime.setBounds(34, 46, 165, 26);
		panel_5.add(lblTotalBurstTime);

		textField = new JTextField();
		textField.setForeground(Color.WHITE);
		textField.setText("0");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(211, 46, 123, 26);
		panel_5.add(textField);

		JLabel lblW_2_1 = new JLabel("(S)");
		lblW_2_1.setForeground(Color.WHITE);
		lblW_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblW_2_1.setBounds(346, 51, 34, 26);
		panel_5.add(lblW_2_1);

		JLabel lblNewLabel_2_1 = new JLabel("Real Time = ");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setBounds(694, 291, 82, 20);
		contentPane.add(lblNewLabel_2_1);

		textField_1 = new JTextField();
		textField_1.setForeground(Color.WHITE);
		textField_1.setText("0");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(773, 292, 24, 20);
		contentPane.add(textField_1);
		textField_1.setBorder(null);

		JLabel lblNewLabel_2_1_1 = new JLabel("sec");
		lblNewLabel_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1_1.setBounds(796, 291, 33, 20);
		contentPane.add(lblNewLabel_2_1_1);

		JLabel lblPrioritytype = new JLabel("PriorityType");
		lblPrioritytype.setForeground(Color.WHITE);
		lblPrioritytype.setFont(new Font("Dialog", Font.BOLD, 14));
		lblPrioritytype.setBounds(588, 135, 242, 25);
		contentPane.add(lblPrioritytype);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBorder(new LineBorder(Color.WHITE));
		panel_1_1.setBounds(588, 158, 268, 32);
		contentPane.add(panel_1_1);

		PerformanceFirstButton = new JRadioButton("PerformanceFirst");
		PerformanceFirstButton.setForeground(Color.WHITE);
		PerformanceFirstButton.setSelected(true);
		PerformanceFirstButton.setBounds(8, 6, 119, 23);

		panel_1_1.add(PerformanceFirstButton);

		PowerFirstButton = new JRadioButton("PowerFirst");
		PowerFirstButton.setForeground(Color.WHITE);
		PowerFirstButton.setBounds(141, 6, 118, 23);
		panel_1_1.add(PowerFirstButton);

		ButtonGroup buttonGroup1 = new ButtonGroup();
		buttonGroup1.add(PerformanceFirstButton);
		buttonGroup1.add(PowerFirstButton);

		textField_3 = new JTextField();
		textField_3.setBounds(1072, 161, 89, 26);
		contentPane.add(textField_3);
		textField_3.setForeground(Color.WHITE);
		textField_3.setText("3");
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setColumns(10);

		lblSeconds = new JLabel("second (s)");
		lblSeconds.setBounds(1173, 163, 64, 20);
		contentPane.add(lblSeconds);
		lblSeconds.setForeground(Color.WHITE);

		JLabel lblTimequantum = new JLabel("TimeQuantum");
		lblTimequantum.setForeground(Color.WHITE);
		lblTimequantum.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTimequantum.setBounds(868, 133, 160, 25);
		contentPane.add(lblTimequantum);

		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setBorder(new LineBorder(Color.WHITE));
		panel_1_1_1.setBounds(868, 158, 386, 32);
		contentPane.add(panel_1_1_1);

		lblTimeQuantum = new JLabel("Time Quantum (\u03B4) :");
		lblTimeQuantum.setBounds(12, 6, 123, 20);
		panel_1_1_1.add(lblTimeQuantum);
		lblTimeQuantum.setForeground(Color.WHITE);
		lblTimeQuantum.setHorizontalAlignment(SwingConstants.CENTER);

	}

	/**
	 * @param targetField
	 * @param fields
	 * @param value
	 */

	private void addCoreSize(JTextField targetField, JTextField[] fields, int value) {

		int now = Integer.parseInt(targetField.getText());

		int using = 0;

		for (JTextField field : fields)

			using += Integer.parseInt(field.getText());

		// 타겟 필드의 코어 사용량이 1개 미만이 아니면서, 전체 코어 사용량이 MAX_CORE_SIZE를 초과하지 않을 때
		if (now + value >= 0 && using + value <= CPU.MAX_CORE_SIZE && using + value >= 1)

			targetField.setText(String.valueOf(now + value));

	}

	/**
	 * @param arrivalTimeField
	 * @param burstTimeField
	 */

	private void addProcess(JTextField arrivalTimeField, JTextField burstTimeField) {

		String PID = String.format("P%d", table.getRowCount() + 1);
		int arrivalTime = Integer.parseInt(arrivalTimeField.getText());
		int burstTime = Integer.parseInt(burstTimeField.getText());

		DefaultTableModel model = (DefaultTableModel) table.getModel();

		if (model.getRowCount() < 15)

			model.addRow(new Object[] { PID, arrivalTime, burstTime });

		else

			JOptionPane.showMessageDialog(this, "프로세는 최대 15개까지 가능합니다.");

	}

	/**
	 * Reset Process Table
	 */

	private void resetProcess() {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		DefaultTableModel model1 = (DefaultTableModel) table_1.getModel();
		DefaultTableModel model2 = (DefaultTableModel) table_2.getModel();

		model.setRowCount(0);
		model1.setRowCount(0);
		model2.setRowCount(0);

	}

	/**
	 * @param history
	 * @param time
	 */

	private void drawReadyQueue(History history, int time) {

		Queue<Process> tempProcessList = history.getReadyQueue().get(time);
		DefaultListModel<String> rqModel = (DefaultListModel<String>) list_1.getModel();
		rqModel.clear();

		for (Process process : tempProcessList)

			rqModel.addElement("P" + Integer.toString(process.getpId()));

		SwingUtilities.invokeLater(new Runnable() { public void run() {

			list_1.setModel(rqModel);

		}});

	}

	/**
	 * @param history
	 * @param coreList
	 * @param time
	 */

	private void drawGanttChart(History history, List<Core> coreList, int time) {

		// row number table
		JTable rowTable = new RowNumberTable(table_2, coreList);
		scrollPane_2.setRowHeaderView(rowTable);
		scrollPane_2.setCorner(JScrollPane.UPPER_LEFT_CORNER, rowTable.getTableHeader());

		final boolean[] flag = { true };

		// 간트 차트 배열을 생성한다.
		Object[][] ganttChart = new Object[coreList.size()][time + 1];

		for (int historyIndex = 0; historyIndex < history.getHistory().size(); historyIndex++)

			for (int coreIndex = 0; coreIndex < coreList.size(); coreIndex++)

				for (int t = 0; t < ganttChart[0].length; t++)

					for (Process p : history.getHistory().get(t))

						if (p.getWorkingCoreIndex() == coreIndex)

							ganttChart[coreIndex][t] = String.format("P%d", p.getpId());

		String[] timeHeader = new String[ganttChart[0].length];

		for (int i = 0; i < timeHeader.length; i++) 

			timeHeader[i] = String.format("%d", i + 1);

		SwingUtilities.invokeLater(new Runnable() { public void run() {

			table_2.setModel(new DefaultTableModel(ganttChart, timeHeader));

		}});

		scrollPane_2.getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener() {

			@Override
			public void adjustmentValueChanged(AdjustmentEvent e) {

				if (flag[0])

					e.getAdjustable().setValue(e.getAdjustable().getMaximum());

			}

		});

		scrollPane_2.getHorizontalScrollBar().addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				flag[0] = false;
			}

			@Override
			public void mousePressed(MouseEvent e) {
				flag[0] = false;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				flag[0] = false;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				flag[0] = false;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				flag[0] = false;
			}

		});
	}

	/**
	 * @param processList
	 */

	private void drawProcessStateChart(List<Process> processList) {

		for(Process process : processList)

			SwingUtilities.invokeLater(new Runnable(){ public void run() {

				((DefaultTableModel) table_1.getModel()).addRow(new Object[] {

						String.format("P%d", process.getpId()),
						process.getArrivalTime(),
						process.getBurstTime(),
						process.getWaitingTime(),
						process.getTurnAroundTime(),
						String.format("%.1f", process.getNormalizedTT())

				});

			}});

	}

	/**
	 * @param history
	 * @param time
	 */

	private void drawTotalValues(History history, int time) {

		textField.setText(Integer.toString(history.getTotalBurstTime()));
		textField_1.setText(Integer.toString(time + 1));
		textField_2.setText(String.format("%.2f", history.getTotalPoewrConsumption()));

	}

	/**
	 * Run Scheduling
	 */

	private void runScheduling() {

		// 프로세스 리스트 생성
		List<Process> processList = new ArrayList<Process>();

		for (int rowCount = 0; rowCount < table.getRowCount(); rowCount++)

			processList.add(new Process(Integer.valueOf(table.getValueAt(rowCount, 0).toString().replace("P", "")), (int) (table.getValueAt(rowCount, 1)), (int) (table.getValueAt(rowCount, 2))));

		// 코어 리스트 생성
		List<Core> coreList = new ArrayList<Core>();

		for (int pCoreCount = 0; pCoreCount < Integer.valueOf(textField_4.getText()); pCoreCount++)

			coreList.add(new PCore());

		for (int eCoreCount = 0; eCoreCount < Integer.valueOf(textField_5.getText()); eCoreCount++)

			coreList.add(new ECore());

		// 프로세스를 추가하지 않은 경우
		if (processList.isEmpty()) {

			JOptionPane.showMessageDialog(this, "프로세스를 추가하세요.");
			return;

		}

		// 히스토리 생성
		History history = null;

		if(PowerFirstButton.isSelected())

			CPU.priorityType = PriorityType.POWER_CONSUMPTION;

		else if(PerformanceFirstButton.isSelected())

			CPU.priorityType = PriorityType.POWER;

		// 타임 퀀텀 설정
		int timeQuantum = 3;

		try {

			timeQuantum = Integer.parseInt(textField_3.getText());

			// 필드의 타임퀀텀 0 이하 방지
			if(timeQuantum <= 0)

				throw new Exception();

		} catch (Exception ex) {

			ex.getStackTrace();

			// 3으로 재설정
			textField_3.setText("3");
			textField_3.requestFocus();

			JOptionPane.showMessageDialog(this, "올바른 타임퀀텀 값을 입력하세요.");
			return;

		} finally {

			CPU.timeQuantum = timeQuantum;

		}

		// 스케줄링 시작
		if (fcfsRadioButton.isSelected())

			history = new FCFS().schedule(processList, coreList);

		else if (rrRadioButton.isSelected())

			history = new RR().schedule(processList, coreList);

		else if (spnRadioButton.isSelected())

			history = new SPN().schedule(processList, coreList);

		else if (srtnRadioButton.isSelected())

			history = new SRTN().schedule(processList, coreList);

		else if (hrrnRadioButton.isSelected())

			history = new HRRN().schedule(processList, coreList);

		else if (ownRadioButton.isSelected())

			history = new OWN().schedule(processList, coreList);

		// 스케줄링을 실패한 경우
		if (history == null) {

			JOptionPane.showMessageDialog(this, "스케줄링에 실패했습니다.");
			return;

		}

		// 히스토리 출력 쓰레드 정의
		class drawThread extends Thread {

			History history = null;

			public drawThread(History history) {

				this.history = history;

			}

			public void run() {

				try {

					// 프로세스 상태 차트를 초기화한다.
					while(table_1.getModel().getRowCount() > 0)

						((DefaultTableModel) table_1.getModel()).removeRow(table_1.getModel().getRowCount() - 1);

					for (int t = 0; t < history.getTotalBurstTime(); t++) {

						drawReadyQueue(history, t);
						drawGanttChart(history, coreList, t);
						drawTotalValues(history, t);
						repaint();

						try {

							Thread.sleep(200);

						} catch (Exception ex) {

							ex.printStackTrace();

						}

					}

					drawProcessStateChart(processList);

				} catch(Exception ex) {

					ex.printStackTrace();

				}

				// 스케줄링이 끝나면 버튼을 누를 수 있다.
				addProcessButton.setEnabled(true);
				resetProcessButton.setEnabled(true);
				runSchedulingButton.setEnabled(true);

			}

		}

		new drawThread(history).start();

		// 스케줄링 동안에는 버튼을 누를 수 없다.
		addProcessButton.setEnabled(false);
		resetProcessButton.setEnabled(false);
		runSchedulingButton.setEnabled(false);

	}

}
