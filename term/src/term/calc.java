package term;

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import sun.tools.jar.resources.jar;

import java.awt.event.*;

public class calc {
	String str = ""; // ù��° ���� ���� ����
	char cal; // ���� ��ȣ ���� ����(+,-,*,/ ��)
	String str2; // �ι�° ���� ���� ����
	JFrame mf;
	JTextField text1 = new JTextField(str, 12); // ���� �Է� �� ��� ��� ��� â

	JPanel engr;
	JPanel p1;
	JPanel p2;
	JPanel p3;

	JFileChooser chooser;
	FileNameExtensionFilter filter;

	public static void main(String[] args) {
		calc test = new calc();
		test.gui();
	}

	public void gui() {
		mf = new JFrame(); // ������ ����
		mf.setTitle("����"); // �����ӿ� ���� �߰�
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		engr = new engrCalc();
		createMenu();
		p1 = new JPanel(); // p1 �г� ����
		p1.setSize(30, 30);
		text1.setSize(28, 28);
		// �����̹Ƿ� JTextField�� ���� ������ ����
		text1.setHorizontalAlignment(JTextField.RIGHT);
		p1.add(text1);

		p2 = new JPanel(); // p2 �г� ����
		p2.setLayout(new GridLayout(4, 4, 4, 4)); // 4�� 4��, ��ư ������ ���� ���� ��� 4

		MyActionListener1 ml1 = new MyActionListener1(); // MyActionListener1 ��ü ����
		MyActionListener2 ml2 = new MyActionListener2(); // MyActionListener2 ��ü ����
		MyActionListener3 ml3 = new MyActionListener3(); // MyActionListener3 ��ü ����

		// ���ڹ�ư
		// ��ư ��ü �����, ��ư�� �ݹ� �żҵ尡 ������ MyActionListener1 ������ ��ü ����ϰ�,
		// p2 �гο� ��ư �߰�
		int i;
		for (i = 9; i >= 0; i--) {
			if(i == 6){ // + ��ư
				JButton buttonplus = new JButton("+");
				buttonplus.addActionListener(ml2); // �ݹ� �żҵ尡 ������ MyActionListener2 ������ ��ü ���
				p2.add(buttonplus); // p2 �гο� ��ư �߰�
			} else if(i==3){ // - ��ư
				JButton buttonminus = new JButton("-");
				buttonminus.addActionListener(ml2); // �ݹ� �żҵ尡 ������ MyActionListener2 ������ ��ü ���
				p2.add(buttonminus); // p2 �гο� ��ư �߰�
			} else if(i==0){ // * ��ư
				JButton buttonmul = new JButton("*");
				buttonmul.addActionListener(ml2); // �ݹ� �żҵ尡 ������ MyActionListener2 ������ ��ü ���
				p2.add(buttonmul); // p2 �гο� ��ư �߰�
			}
			JButton button = new JButton(Integer.toString(i));
			button.addActionListener(ml1); // �ݹ� �żҵ尡 ������ MyActionListener1 ������ ��ü ���
			p2.add(button); // p2 �гο� ��ư �߰�
		}


		// = ��ư
		JButton buttone = new JButton("=");
		buttone.addActionListener(ml3); // �ݹ� �żҵ尡 ������ MyActionListener3 ������ ��ü ���
		p2.add(buttone); // p2 �гο� ��ư �߰�

		// C(Clear) ��ư
		JButton buttonc = new JButton("C");
		buttonc.addActionListener(ml2);
		p2.add(buttonc);

		// / ��ư
		JButton buttondiv = new JButton("/");
		buttondiv.addActionListener(ml2); // �ݹ� �żҵ尡 ������ MyActionListener2 ������ ��ü ���
		p2.add(buttondiv); // p2 �гο� ��ư �߰�

		// p3�г� ���� ��, p1 �гΰ� p2 �г��� p3 �гο� ���
		p3 = new JPanel(); // p3 �г� ����
		p3.setLayout(new FlowLayout()); // p3 �г� ���̾ƿ��� FlowLayout()���� ����
		p3.add(p1); // p3 �гο� p1 �г� ���
		p3.add(p2); // p3 �гο� p2 �г� ���

		mf.getContentPane().add(p3); // p3 �г��� ������(JFrame)�� ���
		mf.setLocation(250, 250);
		mf.setSize(230, 260); // ������ ������ ����
		mf.setVisible(true); // ���
	}

	// �޴��� ���� �޼ҵ� ����
	void createMenu() {
		// �޴��� ����
		JMenuBar mb = new JMenuBar();

		// File �޴� ����
		JMenuItem[] fileItem = new JMenuItem[2];
		String[] fileTitle = { "����", "����" };
		JMenu fileMenu = new JMenu("File");

		for (int i = 0; i < fileItem.length; i++) {
			fileItem[i] = new JMenuItem(fileTitle[i]);
			fileItem[i].addActionListener(new MenuActionListener());

			fileMenu.add(fileItem[i]);
		}
		mb.add(fileMenu);

		// mode �޴� ����
		JMenuItem[] menuItem = new JMenuItem[2];
		String[] itemTitle = { "�Ϲݿ�", "���п�" };
		JMenu modeMenu = new JMenu("Mode");

		for (int i = 0; i < menuItem.length; i++) {
			menuItem[i] = new JMenuItem(itemTitle[i]);
			menuItem[i].addActionListener(new MenuActionListener());

			modeMenu.add(menuItem[i]);
		}
		mb.add(modeMenu);

		// help �޴� ����
		JMenu helpMenu = new JMenu("Help");
		mb.add(helpMenu);
		helpMenu.add(new JMenuItem("About"));

		mf.setJMenuBar(mb); // �޴��ٸ� �����ӿ� ���
	}

	// ���� ��ư(0 ~ 9) ���� �ݹ� �żҵ� ����
	class MyActionListener1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource(); // �׼� �̺�Ʈ�� �߻��� ��ü�� ���۷��� ��ȯ
			switch (b.getText()) { // �� ��ü�� ����� ���ڰ�
			case "1": // 1 �̶��
				str = str + "1"; // ���� ���ڿ� �ڿ� �߰�
				text1.setText(str); // text1 ��ü�� ǥ��
				break;
			case "2":
				str = str + "2";
				text1.setText(str);
				break;
			case "3":
				str = str + "3";
				text1.setText(str);
				break;
			case "4":
				str = str + "4";
				text1.setText(str);
				break;
			case "5":
				str = str + "5";
				text1.setText(str);
				break;
			case "6":
				str = str + "6";
				text1.setText(str);
				break;
			case "7":
				str = str + "7";
				text1.setText(str);
				break;
			case "8":
				str = str + "8";
				text1.setText(str);
				break;
			case "9":
				str = str + "9";
				text1.setText(str);
				break;
			case "0":
				str = str + "0";
				text1.setText(str);
				break;
			}
		}
	}

	// ���� ��ư �ݹ� �żҵ� ����
	class MyActionListener2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			str = text1.getText(); // text1�� �Էµ� ������ str�� �Է�
			JButton b = (JButton) e.getSource();
			if (b.getText() == "C") {	// Ŭ���� ��ư ����
				str = "";
				str2 = "";
				cal = ' ';
				text1.setText(str);
				return;
			}
			str2 = str; // ù��° ���� str2�� ����
			cal = b.getText().charAt(0); // ���� ��ȣ ����
			str = ""; // �� ��° ���� �޾ƾ��ϴ� �ʱ�ȭ
			text1.setText(str);
		}
	}

	// = ��ư �ݹ� �żҵ� ����
	class MyActionListener3 implements ActionListener {
		public void actionPerformed(ActionEvent e) { // = ��ư ������ �� ȣ��
			double result;
			if (str2.isEmpty())	// ����Ʈ �� 0���� ó��
				str2 = "0";
			if (cal == '+') { // ����� �����ڰ� + ���
				result = Double.parseDouble(str) + Double.parseDouble(str2);// �� ���� �Ǽ��� �ٲ㼭 ���ϰ�
				str = Double.toString(result); // �� ����� �ٽ� ���ڷ� �ٲ㼭 str�� ����
				text1.setText(str); // text�� ���
			} else if (cal == '-') {
				result = Double.parseDouble(str2) - Double.parseDouble(str); // �� ���� �Ǽ��� �ٲ㼭 ����
				str = Double.toString(result); // ����� ���ڿ��� ��ȯ�Ͽ� ����
				text1.setText(str); // text�� ���
			} else if (cal == '*') {
				result = Double.parseDouble(str) * Double.parseDouble(str2); // �� ���� �Ǽ��� �ٲ㼭 ���ϰ�
				str = Double.toString(result); // ����� ���ڿ��� ��ȯ�Ͽ� ����
				text1.setText(str); // text�� ���
			} else if (cal == '/') {
				if (str.equals("0")) { // ���� ���� ���
					text1.setText("0���� ���� �� �����ϴ�.");
					return;
				}
				result = Double.parseDouble(str2) / Double.parseDouble(str); // �� ���� �Ǽ��� �ٲ㼭 ������
				str = Double.toString(result); // ����� ���ڿ��� ��ȯ�Ͽ� ����
				text1.setText(str); // text�� ���
			} else if (cal == '^') {
				result = Math.pow(Double.parseDouble(str2), Double.parseDouble(str)); // �� ���� �Ǽ��� �ٲ㼭 ����ϰ�
				str = Double.toString(result); // ����� ���ڿ��� ��ȯ�Ͽ� ����
				text1.setText(str); // text�� ���
			}
		}
	}

	// ���п� ���� ����
	class engrCalc extends JPanel {
		public engrCalc() {
			setLayout(new GridLayout(4, 3, 4, 4));

			MyActionListener4 ml4 = new MyActionListener4();
			
			JButton j1 = new JButton("sin");	// sin ��ư �߰�
			j1.addActionListener(ml4);
			add(j1);	// �гο� ��ư �߰�

			JButton j2 = new JButton("sinh");	// sinh ��ư �߰�
			j2.addActionListener(ml4);
			add(j2);	// �гο� ��ư �߰�
			
			JButton j3 = new JButton("cos");	// cos ��ư �߰�
			j3.addActionListener(ml4);
			add(j3);	// �гο� ��ư �߰�
			
			JButton j4 = new JButton("cosh");	// cosh ��ư �߰�
			j4.addActionListener(ml4);
			add(j4);	// �гο� ��ư �߰�
			
			JButton j5 = new JButton("tan");	// tan ��ư �߰�
			j5.addActionListener(ml4);
			add(j5);	// �гο� ��ư �߰�
			
			JButton j6 = new JButton("tanh");	// tanh ��ư �߰�
			j6.addActionListener(ml4);
			add(j6);	// �гο� ��ư �߰�
			
			JButton j7 = new JButton("exp");	// exp ��ư �߰�
			j7.addActionListener(ml4);
			add(j7);	// �гο� ��ư �߰�
			
			JButton j8 = new JButton("abs");	// abs ��ư �߰�
			j8.addActionListener(ml4);
			add(j8);	// �гο� ��ư �߰�
			
			JButton j9 = new JButton("x^y");	// pow ��ư �߰�
			j9.addActionListener(ml4);
			add(j9);	// �гο� ��ư �߰�
			
			JButton j10 = new JButton("��");		// ���� ��ư �߰�
			j10.addActionListener(ml4);
			add(j10);	// �гο� ��ư �߰�
			
			JButton j11 = new JButton("n!");	// ���丮�� ��ư �߰�
			j11.addActionListener(ml4);	// ��ư�� MyActionListener4 ������ �ݹ� �޼ҵ� �߰�
			add(j11);	// �гο� ��ư �߰�

			JButton j12 = new JButton("sqrt");	// sqrt ��ư �߰�
			j12.addActionListener(ml4);	// ��ư�� MyActionListener4 ������ �ݹ� �޼ҵ� �߰�
			add(j12);	// �гο� ��ư �߰�
		}
	}

	// ���п� ���� �ݹ� �޼ҵ� ����
	class MyActionListener4 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			str = text1.getText();
			double tmp = 0.0;
			if(!str.equals(""))
				tmp = Double.parseDouble(str);
			if (btn.getText().equals("exp")) { // j7

			} else if (btn.getText().equals("sin")) { // j1
				tmp = Math.sin(tmp);
				text1.setText(Double.toString(tmp));
			} else if (btn.getText().equals("sinh")) { // j2
				tmp = Math.sinh(tmp);
				text1.setText(Double.toString(tmp));
			} else if (btn.getText().equals("cos")) { // j3
				tmp = Math.cos(tmp);
				text1.setText(Double.toString(tmp));
			} else if (btn.getText().equals("cosh")) { // j4
				tmp = Math.cosh(tmp);
				text1.setText(Double.toString(tmp));
			} else if (btn.getText().equals("tan")) { // j5
				tmp = Math.tan(tmp);
				text1.setText(Double.toString(tmp));
			} else if (btn.getText().equals("tanh")) { // j6
				tmp = Math.tanh(tmp);
				text1.setText(Double.toString(tmp));
			} else if (btn.getText().equals("abs")) { // j8
				tmp = Math.abs(tmp);
				text1.setText(Double.toString(tmp));
			} else if (btn.getText().equals("x^y")) { // j9
				cal = '^';
				str2 = str;
				str = "";
			} else if (btn.getText().equals("��")) { // j10
				str = Double.toString(Math.PI);
				text1.setText(str);
			} else if (btn.getText().equals("n!")) { // j11
				double result;
				if (tmp % 1 != 0) {	// �Էµ� ���� �Ǽ��� ���
					result = gamma(tmp + 1);
				} else {	// �Էµ� ���� ������ ���
					result = fact(tmp);
				}
				text1.setText(Double.toString(result));
				str2 = Double.toString(result);
				str = "";
			} else if (btn.getText().equals("sqrt")) { // j10
				tmp = Math.sqrt(tmp);
				str = Double.toString(tmp);
				text1.setText(str);
			}
		}
		
		// ���丮�� �޼ҵ� ����
		private double fact(double tmp) {
			if(tmp == 1)
				return 1;
			return tmp * fact(tmp-1);
		}

		// �����Լ� �޼ҵ� ����
		private double gamma(double d) {
			double t = (d - 0.5) * Math.log(d + 4.5) - (d + 4.5);
			double ser = 1.0 + 76.18009173 / (d + 0) - 86.50532033 / (d + 1) + 24.01409822 / (d + 2)
					- 1.231739516 / (d + 3) + 0.00120858003 / (d + 4) - 0.00000536382 / (d + 5);
			double result = Math.exp(t + Math.log(ser * Math.sqrt(2 * Math.PI)));
			return result;
		}
	}

	// �޴� �ݹ� �޼ҵ� ����
	class MenuActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd.equals("�Ϲݿ�")) { // �Ϲݿ� ������Ʈ ����
				p3.remove(engr);
				mf.setSize(230, 260); // ������ ������ ����
				mf.setVisible(true); // ���
			} else if (cmd.equals("���п�")) { // ���п� ������Ʈ ����
				mf.setSize(230, 380); // ������ ������ ����
				p3.add(engr);	// p3 �гο� engr �г� �߰�
				mf.setVisible(true); // ���
			} else if (cmd.equals("����")) { // ���� ������Ʈ ����
				chooser = new JFileChooser();
				filter = new FileNameExtensionFilter("��� �׸�����", "png", "jpg", "gif");
				chooser.setFileFilter(filter);
				int ret = chooser.showOpenDialog(null);
				if (ret == JFileChooser.APPROVE_OPTION) {
					String path = chooser.getSelectedFile().getPath();
					text1.setText(path);
				} else if (ret == JFileChooser.CANCEL_OPTION) {
					chooser.cancelSelection();
				}
			} else if (cmd.equals("����")) { // ���� ������Ʈ ����
				System.exit(0);
			}
		}
	}
}
