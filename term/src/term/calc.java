package term;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class calc {
	String str = ""; // ù��° ���� ���� ����
	char cal; // ���� ��ȣ ���� ����(+,-,*,/ ��)
	String str2; // �ι�° ���� ���� ����

	JTextField text1 = new JTextField(str, 12); // ���� �Է� �� ��� ��� ��� â

	public static void main(String[] args) {
		calc test = new calc();
		test.gui();
	}

	public void gui() {
		JFrame mf = new JFrame(); // ������ ����
		mf.setTitle("����"); // �����ӿ� ���� �߰�
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel p1 = new JPanel(); // p1 �г� ����
		// �����̹Ƿ� JTextField�� ���� ������ ����
		text1.setHorizontalAlignment(JTextField.RIGHT);
		p1.add(text1);

		JPanel p2 = new JPanel(); // p2 �г� ����
		p2.setLayout(new GridLayout(4, 4, 4, 4)); // 4�� 4��, ��ư ������ ���� ���� ��� 4

		MyActionListener1 ml1 = new MyActionListener1(); // MyActionListener1 ��ü ����

		// ���ڹ�ư
		// ��ư ��ü �����, ��ư�� �ݹ� �żҵ尡 ������ MyActionListener1 ������ ��ü ����ϰ�,
		// p2 �гο� ��ư �߰�
		int i;
		for (i = 9; i >=0; i--) {
			JButton button = new JButton(Integer.toString(i));
			button.addActionListener(ml1); // �ݹ� �żҵ尡 ������ MyActionListener1 ������ ��ü ���
			p2.add(button); // p2 �гο� ��ư �߰�
		}

		// + ��ư
		JButton buttonplus = new JButton("+");
		MyActionListener2 ml2 = new MyActionListener2(); // MyActionListener2 ��ü ����
		buttonplus.addActionListener(ml2); // �ݹ� �żҵ尡 ������ MyActionListener2 ������ ��ü ���
		p2.add(buttonplus); // p2 �гο� ��ư �߰�

		// - ��ư
		JButton buttonminus = new JButton("-");
		buttonminus.addActionListener(ml2); // �ݹ� �żҵ尡 ������ MyActionListener2 ������ ��ü ���
		p2.add(buttonminus); // p2 �гο� ��ư �߰�

		// * ��ư
		JButton buttonmul = new JButton("*");
		buttonmul.addActionListener(ml2); // �ݹ� �żҵ尡 ������ MyActionListener2 ������ ��ü ���
		p2.add(buttonmul); // p2 �гο� ��ư �߰�

		// / ��ư
		JButton buttondiv = new JButton("/");
		buttondiv.addActionListener(ml2); // �ݹ� �żҵ尡 ������ MyActionListener2 ������ ��ü ���
		p2.add(buttondiv); // p2 �гο� ��ư �߰�

		// = ��ư
		JButton buttone = new JButton("=");
		MyActionListener3 ml3 = new MyActionListener3(); // MyActionListener3 ��ü ����
		buttone.addActionListener(ml3); // �ݹ� �żҵ尡 ������ MyActionListener3 ������ ��ü ���
		p2.add(buttone); // p2 �гο� ��ư �߰�

		// p3�г� ���� ��, p1 �гΰ� p2 �г��� p3 �гο� ���
		JPanel p3 = new JPanel(); // p3 �г� ����
		p3.setLayout(new FlowLayout()); // p3 �г� ���̾ƿ��� FlowLayout()���� ����
		p3.add(p1); // p3 �гο� p1 �г� ���
		p3.add(p2); // p3 �гο� p2 �г� ���

		mf.getContentPane().add(p3); // p3 �г��� ������(JFrame)�� ���

		mf.setSize(230, 325); // ������ ������ ����
		mf.setVisible(true); // ���
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

	// + ��ư �ݹ� �żҵ� ����
	class MyActionListener2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if (b.getText() == "+"){
				str2 = str; // ù��° ���� str2�� ����
				cal = '+'; // ���ϱ� ���� ����
			} else if (b.getText() == "-"){
				str2 = str;
				cal = '-';
			} else if (b.getText() == "*"){
				str2 = str;
				cal = '*';
			} else if (b.getText() == "/"){
				str2 = str;
				cal = '/';
			}
			str = ""; // �� ��° ���� �޾ƾ��ϴ� �ʱ�ȭ
			text1.setText(str);
		}
	}

	// = ��ư �ݹ� �żҵ� ����
	class MyActionListener3 implements ActionListener {
		public void actionPerformed(ActionEvent e) { // = ��ư ������ �� ȣ��
			int result;
			if (cal == '+') { // ����� �����ڰ� + ���
				result = Integer.parseInt(str) + Integer.parseInt(str2);// �� ���� ������ �ٲ㼭 ���ϰ�
				str = Integer.toString(result); // �� ����� �ٽ� ���ڷ� �ٲ㼭 str�� ����
				text1.setText(str); //  text�� ���
			} else if (cal == '-') {
				result = Integer.parseInt(str) - Integer.parseInt(str2);
				// �ٲ㼭 ���ϰ�
				text1.setText(Integer.toString(result));
				str = "";
			} else if (cal == '*') {
				result = Integer.parseInt(str) * Integer.parseInt(str2);
				// �ٲ㼭 ���ϰ�
				text1.setText(Integer.toString(result));
				str = "";
			} else if (cal == '/') {
				result = Integer.parseInt(str) / Integer.parseInt(str2);
				text1.setText(Integer.toString(result)); 
				str = "";
			}
		}
	}
}
