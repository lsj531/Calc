package term;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class calc {
	String str = ""; // 첫번째 숫자 저장 변수
	char cal; // 연산 기호 저장 변수(+,-,*,/ 등)
	String str2; // 두번째 숫자 저장 변수

	JTextField text1 = new JTextField(str, 12); // 숫자 입력 및 계산 결과 출력 창

	public static void main(String[] args) {
		calc test = new calc();
		test.gui();
	}

	public void gui() {
		JFrame mf = new JFrame(); // 프레임 생성
		mf.setTitle("계산기"); // 프레임에 제목 추가
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel p1 = new JPanel(); // p1 패널 생성
		// 계산기이므로 JTextField의 글자 오른쪽 정렬
		text1.setHorizontalAlignment(JTextField.RIGHT);
		p1.add(text1);

		JPanel p2 = new JPanel(); // p2 패널 생성
		p2.setLayout(new GridLayout(4, 4, 4, 4)); // 4행 4열, 버튼 간격은 수직 수평 모두 4

		MyActionListener1 ml1 = new MyActionListener1(); // MyActionListener1 객체 생성

		// 숫자버튼
		// 버튼 객체 만들고, 버튼에 콜백 매소드가 구현된 MyActionListener1 리스너 객체 등록하고,
		// p2 패널에 버튼 추가
		int i;
		for (i = 9; i >=0; i--) {
			JButton button = new JButton(Integer.toString(i));
			button.addActionListener(ml1); // 콜백 매소드가 구현된 MyActionListener1 리스너 객체 등록
			p2.add(button); // p2 패널에 버튼 추가
		}

		// + 버튼
		JButton buttonplus = new JButton("+");
		MyActionListener2 ml2 = new MyActionListener2(); // MyActionListener2 객체 생성
		buttonplus.addActionListener(ml2); // 콜백 매소드가 구현된 MyActionListener2 리스너 객체 등록
		p2.add(buttonplus); // p2 패널에 버튼 추가

		// - 버튼
		JButton buttonminus = new JButton("-");
		buttonminus.addActionListener(ml2); // 콜백 매소드가 구현된 MyActionListener2 리스너 객체 등록
		p2.add(buttonminus); // p2 패널에 버튼 추가

		// * 버튼
		JButton buttonmul = new JButton("*");
		buttonmul.addActionListener(ml2); // 콜백 매소드가 구현된 MyActionListener2 리스너 객체 등록
		p2.add(buttonmul); // p2 패널에 버튼 추가

		// / 버튼
		JButton buttondiv = new JButton("/");
		buttondiv.addActionListener(ml2); // 콜백 매소드가 구현된 MyActionListener2 리스너 객체 등록
		p2.add(buttondiv); // p2 패널에 버튼 추가

		// = 버튼
		JButton buttone = new JButton("=");
		MyActionListener3 ml3 = new MyActionListener3(); // MyActionListener3 객체 생성
		buttone.addActionListener(ml3); // 콜백 매소드가 구현된 MyActionListener3 리스너 객체 등록
		p2.add(buttone); // p2 패널에 버튼 추가

		// p3패널 생성 후, p1 패널과 p2 패널을 p3 패널에 등록
		JPanel p3 = new JPanel(); // p3 패널 생성
		p3.setLayout(new FlowLayout()); // p3 패널 레이아웃을 FlowLayout()으로 설정
		p3.add(p1); // p3 패널에 p1 패널 등록
		p3.add(p2); // p3 패널에 p2 패널 등록

		mf.getContentPane().add(p3); // p3 패널을 프레임(JFrame)에 등록

		mf.setSize(230, 325); // 프레임 사이즈 설정
		mf.setVisible(true); // 출력
	}

	// 숫자 버튼(0 ~ 9) 공통 콜백 매소드 구현
	class MyActionListener1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource(); // 액션 이벤트가 발생한 객체의 레퍼런스 반환
			switch (b.getText()) { // 그 객체에 저장된 문자가
			case "1": // 1 이라면
				str = str + "1"; // 현재 문자열 뒤에 추가
				text1.setText(str); // text1 객체에 표시
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

	// + 버튼 콜백 매소드 구현
	class MyActionListener2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if (b.getText() == "+"){
				str2 = str; // 첫번째 숫자 str2에 저장
				cal = '+'; // 더하기 연산 저장
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
			str = ""; // 두 번째 숫자 받아야하니 초기화
			text1.setText(str);
		}
	}

	// = 버튼 콜백 매소드 구현
	class MyActionListener3 implements ActionListener {
		public void actionPerformed(ActionEvent e) { // = 버튼 눌렀을 때 호출
			int result;
			if (cal == '+') { // 저장된 연산자가 + 라면
				result = Integer.parseInt(str) + Integer.parseInt(str2);// 두 수를 정수로 바꿔서 더하고
				str = Integer.toString(result); // 그 결과를 다시 문자로 바꿔서 str에 저장
				text1.setText(str); //  text에 출력
			} else if (cal == '-') {
				result = Integer.parseInt(str) - Integer.parseInt(str2);
				// 바꿔서 더하고
				text1.setText(Integer.toString(result));
				str = "";
			} else if (cal == '*') {
				result = Integer.parseInt(str) * Integer.parseInt(str2);
				// 바꿔서 더하고
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
