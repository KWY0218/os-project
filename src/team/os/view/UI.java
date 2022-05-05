package team.os.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatDarkLaf;

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
	private JLabel lblPCores_2;
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
	private JLabel lblPCore_1;
	private JTextField textField_5;
	private JButton btnNewButton_5;
	private JButton btnNewButton_6;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JTextField textField_7;
	private JTextField textField_8;

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
		panel_2.setBorder(new LineBorder(Color.GRAY));
		panel_2.setBounds(10, 494, 312, 102);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		lblNewLabel_7 = new JLabel("Burst Time (BT) :");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7.setBounds(6, 70, 111, 20);
		panel_2.add(lblNewLabel_7);

		lblNewLabel_8 = new JLabel("Arrival Time (AT) :");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_8.setBounds(6, 38, 111, 20);
		panel_2.add(lblNewLabel_8);

		lblNewLabel_10 = new JLabel("second (s)");
		lblNewLabel_10.setBounds(242, 38, 64, 20);
		panel_2.add(lblNewLabel_10);

		lblNewLabel_11 = new JLabel("second (s)");
		lblNewLabel_11.setBounds(242, 70, 64, 20);
		panel_2.add(lblNewLabel_11);

		textField_7 = new JTextField();
		textField_7.setText("0");
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setColumns(10);
		textField_7.setBounds(129, 38, 101, 26);
		panel_2.add(textField_7);

		textField_8 = new JTextField();
		textField_8.setText("1");
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setColumns(10);
		textField_8.setBounds(129, 70, 101, 26);
		panel_2.add(textField_8);

		panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(Color.GRAY));
		panel_5.setBounds(915, 42, 339, 142);
		contentPane.add(panel_5);
		panel_5.setLayout(null);

		lblPCores_2 = new JLabel("Total Power Consumption :");
		lblPCores_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPCores_2.setBounds(6, 70, 192, 20);
		panel_5.add(lblPCores_2);

		lblW_2 = new JLabel("W");
		lblW_2.setBounds(313, 70, 20, 20);
		panel_5.add(lblW_2);

		textField_2 = new JTextField();
		textField_2.setText("0");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(210, 70, 91, 26);
		panel_5.add(textField_2);

		lblNewLabel = new JLabel("Scheduling Algorithms");
		lblNewLabel.setBounds(334, 10, 327, 20);
		contentPane.add(lblNewLabel);

		panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(Color.GRAY));
		panel_6.setBounds(334, 42, 327, 142);
		contentPane.add(panel_6);
		panel_6.setLayout(null);

		lblTimeQuantum = new JLabel("Time Quantum (\u03B4) :");
		lblTimeQuantum.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTimeQuantum.setBounds(6, 114, 123, 20);
		panel_6.add(lblTimeQuantum);

		fcfsRadioButton = new JRadioButton("FCFS");
		fcfsRadioButton.setSelected(true);
		fcfsRadioButton.setBounds(6, 6, 135, 24);
		panel_6.add(fcfsRadioButton);

		rrRadioButton = new JRadioButton("RR");
		rrRadioButton.setBounds(6, 42, 135, 24);
		panel_6.add(rrRadioButton);

		spnRadioButton = new JRadioButton("SPN");
		spnRadioButton.setBounds(6, 78, 135, 24);
		panel_6.add(spnRadioButton);

		ownRadioButton = new JRadioButton("Own");
		ownRadioButton.setBounds(153, 78, 135, 24);
		panel_6.add(ownRadioButton);

		srtnRadioButton = new JRadioButton("SRTN");
		srtnRadioButton.setBounds(153, 6, 135, 24);
		panel_6.add(srtnRadioButton);

		hrrnRadioButton = new JRadioButton("HRRN");
		hrrnRadioButton.setBounds(153, 42, 135, 24);
		panel_6.add(hrrnRadioButton);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(fcfsRadioButton);
		buttonGroup.add(rrRadioButton);
		buttonGroup.add(spnRadioButton);
		buttonGroup.add(srtnRadioButton);
		buttonGroup.add(hrrnRadioButton);
		buttonGroup.add(ownRadioButton);

		lblSeconds = new JLabel("second (s)");
		lblSeconds.setBounds(257, 114, 64, 20);
		panel_6.add(lblSeconds);

		textField_3 = new JTextField();
		textField_3.setText("0");
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setColumns(10);
		textField_3.setBounds(141, 111, 104, 26);
		panel_6.add(textField_3);

		lblPowerCon = new JLabel("Power Consumption");
		lblPowerCon.setBounds(915, 10, 339, 20);
		contentPane.add(lblPowerCon);

		lblNewLabel_1 = new JLabel("Ready Queue");
		lblNewLabel_1.setBounds(334, 196, 920, 20);
		contentPane.add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Gantt Chart");
		lblNewLabel_2.setBounds(334, 290, 920, 20);
		contentPane.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Process State");
		lblNewLabel_3.setBounds(334, 462, 920, 20);
		contentPane.add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel(new ImageIcon("resource/koreatech.png"));
		lblNewLabel_4.setBounds(10, 10, 312, 67);
		contentPane.add(lblNewLabel_4);

		addProcessButton = new JButton("Add");
		addProcessButton.setBounds(10, 608, 151, 26);
		addProcessButton.addActionListener(e -> {
			addProcess(textField_7, textField_8);
		});
		contentPane.add(addProcessButton);

		resetProcessButton = new JButton("Reset");
		resetProcessButton.setBounds(171, 608, 151, 26);
		resetProcessButton.addActionListener(e -> {
			resetProcess();
		});
		contentPane.add(resetProcessButton);

		runSchedulingButton = new JButton("Run");
		runSchedulingButton.setBounds(10, 646, 312, 26);
		runSchedulingButton.addActionListener(e -> {
			runScheduling();
		});
		contentPane.add(runSchedulingButton);

		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.GRAY));
		panel_1.setBounds(673, 42, 230, 142);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		lblPCore = new JLabel("P Core");
		lblPCore.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPCore.setBounds(6, 6, 43, 20);
		panel_1.add(lblPCore);

		textField_4 = new JTextField();
		textField_4.setText("0");
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(61, 6, 97, 26);
		panel_1.add(textField_4);

		btnNewButton_3 = new JButton("-");
		btnNewButton_3.addActionListener(e -> {
			addCoreSize(textField_4, new JTextField[] {textField_4, textField_5}, -1);
		});
		btnNewButton_3.setBounds(198, 6, 26, 26);
		panel_1.add(btnNewButton_3);

		btnNewButton_4 = new JButton("+");
		btnNewButton_4.addActionListener(e -> {
			addCoreSize(textField_4, new JTextField[] {textField_4, textField_5}, 1);
		});
		btnNewButton_4.setBounds(170, 6, 26, 26);
		panel_1.add(btnNewButton_4);

		lblPCore_1 = new JLabel("E Core");
		lblPCore_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPCore_1.setBounds(6, 38, 43, 20);
		panel_1.add(lblPCore_1);

		textField_5 = new JTextField();
		textField_5.setText("1");
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(61, 38, 97, 26);
		panel_1.add(textField_5);

		btnNewButton_5 = new JButton("+");
		btnNewButton_5.addActionListener(e -> {
			addCoreSize(textField_5, new JTextField[] {textField_4, textField_5}, 1);
		});
		btnNewButton_5.setBounds(170, 38, 26, 26);
		panel_1.add(btnNewButton_5);

		btnNewButton_6 = new JButton("-");
		btnNewButton_6.addActionListener(e -> {
			addCoreSize(textField_5, new JTextField[] {textField_4, textField_5}, -1);
		});
		btnNewButton_6.setBounds(198, 38, 26, 26);
		panel_1.add(btnNewButton_6);

		lblNewLabel_5 = new JLabel("Time Table");
		lblNewLabel_5.setBounds(10, 89, 312, 20);
		contentPane.add(lblNewLabel_5);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 121, 312, 329);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		lblNewLabel_6 = new JLabel("Create Process");
		lblNewLabel_6.setBounds(10, 462, 312, 20);
		contentPane.add(lblNewLabel_6);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(334, 494, 920, 178);
		contentPane.add(scrollPane_1);

		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_2.setBounds(334, 322, 920, 128);
		contentPane.add(scrollPane_2);

		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);

		lblCoreSetting = new JLabel("Core Setting");
		lblCoreSetting.setBounds(673, 10, 230, 20);
		contentPane.add(lblCoreSetting);

		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(334, 228, 920, 50);
		scrollPane_3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane_3);

		list_1 = new JList<String>(new DefaultListModel<String>());
		list_1.setVisibleRowCount(1);
		list_1.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list_1.setFixedCellWidth(40);
		scrollPane_3.setViewportView(list_1);

	}

	/**
	 * @param targetField
	 * @param fields
	 * @param value
	 */

	private void addCoreSize(JTextField targetField, JTextField[] fields, int value) {

		int now = Integer.parseInt(targetField.getText());

		int using = 0;

		for(JTextField field : fields)

			using += Integer.parseInt(field.getText());

		// 타겟 필드의 코어 사용량이 1개 미만이 아니면서, 전체 코어 사용량이 MAX_CORE_SIZE를 초과하지 않을 때
		if(now + value >= 0 && using + value <= CPU.MAX_CORE_SIZE && using + value >= 1)

			targetField.setText(String.valueOf(now + value));

	}

	/**
	 * @param pidField
	 * @param arrivalTimeField
	 * @param burstTimeField
	 */

	private void addProcess(JTextField arrivalTimeField, JTextField burstTimeField) {

		String PID = String.format("P%d", table.getRowCount() + 1);
		int arrivalTime = Integer.parseInt(arrivalTimeField.getText());
		int burstTime = Integer.parseInt(burstTimeField.getText());

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setColumnIdentifiers(new String[] {"PID", "Arrival Time", "Burst Time"});
		model.addRow(new Object[] {PID, arrivalTime, burstTime});

	}

	/**
	 * Reset Process Table
	 */

	private void resetProcess() {

		DefaultTableModel model = (DefaultTableModel) table.getModel();

		while(model.getRowCount() > 0)

			model.removeRow(model.getRowCount() - 1);

	}

	/**
	 * @param history
	 * @param time
	 */

	private void organizeReadyQueue(History history, int time) {

		/*DefaultTableModel pModel = (DefaultTableModel) table.getModel();

		List<Process> processList = new ArrayList<Process>();

		for(int row = 0; row < pModel.getRowCount(); row++)

			processList.add(new Process((String) pModel.getValueAt(row, 0), (int) pModel.getValueAt(row, 1), (int) pModel.getValueAt(row, 2)));

		// 프로세스 목록을 Arrival Time순으로 정리한다. -> 추후 table의 데이터로만 계산하기로 수정할 것.
		processList.sort(Comparator.naturalOrder());

		// Ready Queue에 입력한다.
		DefaultListModel<String> rqModel = (DefaultListModel<String>) list_1.getModel();

		rqModel.clear();

		for(Process process : processList)

			rqModel.addElement(process.getPID());*/

	}

	/**
	 * @param history
	 * @param time
	 */

	private void drawGanttChart(History history, List<Core> coreList, int time) {

		// 간트 차트 배열을 생성한다.
		Object[][] ganttChart = new Object[coreList.size()][time + 1];

		System.out.println("-- Gantt");

		System.out.print("       ");

		for(int historyIndex = 0; historyIndex < history.getHistory().size(); historyIndex++)

			System.out.printf("%4d ", historyIndex);

		System.out.println();

		for(int coreIndex = 0; coreIndex < coreList.size(); coreIndex++) {

			System.out.printf("Core%2d", coreIndex + 1);

			for(int t = 0; t < time; t++) {

				for(List<Process> pl : history.getHistory()) {

					boolean worked = false;

					for(Process p : pl)

						if(p.getWorkingCoreIndex() == coreIndex) {

							System.out.printf("%5d", p.getpId());

							ganttChart[coreIndex][t] = String.format("P%d", p.getpId());

							worked = true;

						}

					if(!worked)

						System.out.printf("     ");

				}

			}

			System.out.println();

		}

		table_2.setModel(new DefaultTableModel(ganttChart, new Object[history.getTotalBurstTime()]));

	}

	/**
	 * @param processList
	 * @param time
	 */

	private void drawProcessStateChart(List<Process> processList, int time) {

		System.out.println("-- Process State");

		for(Process process : processList)

			System.out.printf("P%02d\t%2d\t%2d\t%2d\t%2d\t%4.1f\n", process.getpId(), process.getArrivalTime(), process.getBurstTime(), process.getWaitingTime(), process.getTurnAroundTime(), process.getNormalizedTT());

	}

	/**
	 * @param history
	 * @param time
	 */

	private void drawTotalValues(History history, int time) {

		System.out.println("-- Total");
		System.out.printf("History.getTotalBurstTime(): %d\n", history.getTotalBurstTime());
		System.out.printf("History.getTotalPowerConsumption(): %.1f\n", history.getTotalPoewrConsumption());

	}

	/**
	 * Run Scheduling
	 */

	private void runScheduling() {

		// temp
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setColumnIdentifiers(new String[] {"PID", "Arrival Time", "Burst Time"});
		model.addRow(new Object[] {"P1", 0, 3});
		model.addRow(new Object[] {"P2", 1, 7});
		model.addRow(new Object[] {"P3", 3, 2});
		model.addRow(new Object[] {"P4", 5, 5});
		model.addRow(new Object[] {"P5", 6, 3});

		// 프로세스 리스트 생성
		List<Process> processList = new ArrayList<Process>();

		for(int rowCount = 0; rowCount < table.getRowCount(); rowCount++)

			processList.add(new Process(Integer.valueOf(table.getValueAt(rowCount, 0).toString().replace("P", "")), (int) table.getValueAt(rowCount, 1), (int) table.getValueAt(rowCount, 2)));

		// 코어 리스트 생성
		List<Core> coreList = new ArrayList<Core>();

		for(int pCoreCount = 0; pCoreCount < Integer.valueOf(textField_4.getText()); pCoreCount++)

			coreList.add(new PCore());

		for(int eCoreCount = 0; eCoreCount < Integer.valueOf(textField_5.getText()); eCoreCount++)

			coreList.add(new ECore());

		// 프로세스를 추가하지 않은 경우
		if(processList.isEmpty()) {

			JOptionPane.showMessageDialog(this , "프로세스를 추가하세요.");
			return;

		}

		// 히스토리 생성
		History history = null;

		if(fcfsRadioButton.isSelected())

			history = new FCFS().schedule(processList, coreList);

		else if(rrRadioButton.isSelected())

			history = new RR().schedule(processList, coreList);

		else if(spnRadioButton.isSelected())

			history = new SPN().schedule(processList, coreList);

		else if(srtnRadioButton.isSelected())

			history = new SRTN().schedule(processList, coreList);

		else if(hrrnRadioButton.isSelected())

			history = new HRRN().schedule(processList, coreList);

		else if(ownRadioButton.isSelected())

			history = new OWN().schedule(processList, coreList);

		// 스케줄링을 실패한 경우
		if(history == null) {

			JOptionPane.showMessageDialog(this, "스케줄링에 실패했습니다.");
			return;

		}

		// 데이터 출력
		System.out.println("-- Core");

		for(Core core : coreList)

			System.out.printf("Core %d\t%s\n", coreList.indexOf(core) + 1, core.getClass().getSimpleName());

		// 히스토리 출력 쓰레드 정의
		class drawThread extends Thread {

			History history = null;

			public drawThread(History history) {

				this.history = history;

			}

			public void run() {

				for(int t = 0; t < history.getTotalBurstTime(); t++) {

					drawGanttChart(history, coreList, t);
					drawProcessStateChart(processList, t);
					drawTotalValues(history, t);

					repaint();

					try { Thread.sleep(100); } catch(Exception ex) { ex.printStackTrace(); }			

				}

			}

		}
		
		new drawThread(history).start();

	}

}
