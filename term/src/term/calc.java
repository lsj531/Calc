package term;

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import sun.tools.jar.resources.jar;

import java.awt.event.*;

public class calc {
	String str = ""; // 첫번째 숫자 저장 변수
	char cal; // 연산 기호 저장 변수(+,-,*,/ 등)
	String str2; // 두번째 숫자 저장 변수
	JFrame mf;
	JTextField text1 = new JTextField(str, 12); // 숫자 입력 및 계산 결과 출력 창

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
		mf = new JFrame(); // 프레임 생성
		mf.setTitle("계산기"); // 프레임에 제목 추가
		mf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		engr = new engrCalc();
		createMenu();
		p1 = new JPanel(); // p1 패널 생성
		p1.setSize(30, 30);
		text1.setSize(28, 28);
		// 계산기이므로 JTextField의 글자 오른쪽 정렬
		text1.setHorizontalAlignment(JTextField.RIGHT);
		p1.add(text1);

		p2 = new JPanel(); // p2 패널 생성
		p2.setLayout(new GridLayout(4, 4, 4, 4)); // 4행 4열, 버튼 간격은 수직 수평 모두 4

		MyActionListener1 ml1 = new MyActionListener1(); // MyActionListener1 객체 생성
		MyActionListener2 ml2 = new MyActionListener2(); // MyActionListener2 객체 생성
		MyActionListener3 ml3 = new MyActionListener3(); // MyActionListener3 객체 생성

		// 숫자버튼
		// 버튼 객체 만들고, 버튼에 콜백 매소드가 구현된 MyActionListener1 리스너 객체 등록하고,
		// p2 패널에 버튼 추가
		int i;
		for (i = 9; i >= 0; i--) {
			if(i == 6){ // + 버튼
				JButton buttonplus = new JButton("+");
				buttonplus.addActionListener(ml2); // 콜백 매소드가 구현된 MyActionListener2 리스너 객체 등록
				p2.add(buttonplus); // p2 패널에 버튼 추가
			} else if(i==3){ // - 버튼
				JButton buttonminus = new JButton("-");
				buttonminus.addActionListener(ml2); // 콜백 매소드가 구현된 MyActionListener2 리스너 객체 등록
				p2.add(buttonminus); // p2 패널에 버튼 추가
			} else if(i==0){ // * 버튼
				JButton buttonmul = new JButton("*");
				buttonmul.addActionListener(ml2); // 콜백 매소드가 구현된 MyActionListener2 리스너 객체 등록
				p2.add(buttonmul); // p2 패널에 버튼 추가
			}
			JButton button = new JButton(Integer.toString(i));
			button.addActionListener(ml1); // 콜백 매소드가 구현된 MyActionListener1 리스너 객체 등록
			p2.add(button); // p2 패널에 버튼 추가
		}


		// = 버튼
		JButton buttone = new JButton("=");
		buttone.addActionListener(ml3); // 콜백 매소드가 구현된 MyActionListener3 리스너 객체 등록
		p2.add(buttone); // p2 패널에 버튼 추가

		// C(Clear) 버튼
		JButton buttonc = new JButton("C");
		buttonc.addActionListener(ml2);
		p2.add(buttonc);

		// / 버튼
		JButton buttondiv = new JButton("/");
		buttondiv.addActionListener(ml2); // 콜백 매소드가 구현된 MyActionListener2 리스너 객체 등록
		p2.add(buttondiv); // p2 패널에 버튼 추가

		// p3패널 생성 후, p1 패널과 p2 패널을 p3 패널에 등록
		p3 = new JPanel(); // p3 패널 생성
		p3.setLayout(new FlowLayout()); // p3 패널 레이아웃을 FlowLayout()으로 설정
		p3.add(p1); // p3 패널에 p1 패널 등록
		p3.add(p2); // p3 패널에 p2 패널 등록

		mf.getContentPane().add(p3); // p3 패널을 프레임(JFrame)에 등록
		mf.setLocation(250, 250);
		mf.setSize(230, 260); // 프레임 사이즈 설정
		mf.setVisible(true); // 출력
	}

	// 메뉴바 생성 메소드 구현
	void createMenu() {
		// 메뉴바 생성
		JMenuBar mb = new JMenuBar();

		// File 메뉴 생성
		JMenuItem[] fileItem = new JMenuItem[2];
		String[] fileTitle = { "열기", "종료" };
		JMenu fileMenu = new JMenu("File");

		for (int i = 0; i < fileItem.length; i++) {
			fileItem[i] = new JMenuItem(fileTitle[i]);
			fileItem[i].addActionListener(new MenuActionListener());

			fileMenu.add(fileItem[i]);
		}
		mb.add(fileMenu);

		// mode 메뉴 생성
		JMenuItem[] menuItem = new JMenuItem[2];
		String[] itemTitle = { "일반용", "공학용" };
		JMenu modeMenu = new JMenu("Mode");

		for (int i = 0; i < menuItem.length; i++) {
			menuItem[i] = new JMenuItem(itemTitle[i]);
			menuItem[i].addActionListener(new MenuActionListener());

			modeMenu.add(menuItem[i]);
		}
		mb.add(modeMenu);

		// help 메뉴 생성
		JMenu helpMenu = new JMenu("Help");
		mb.add(helpMenu);
		helpMenu.add(new JMenuItem("About"));

		mf.setJMenuBar(mb); // 메뉴바를 프레임에 등록
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

	// 연산 버튼 콜백 매소드 구현
	class MyActionListener2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			str = text1.getText(); // text1에 입력된 내용을 str에 입력
			JButton b = (JButton) e.getSource();
			if (b.getText() == "C") {	// 클리어 버튼 선택
				str = "";
				str2 = "";
				cal = ' ';
				text1.setText(str);
				return;
			}
			str2 = str; // 첫번째 숫자 str2에 저장
			cal = b.getText().charAt(0); // 연산 기호 저장
			str = ""; // 두 번째 숫자 받아야하니 초기화
			text1.setText(str);
		}
	}

	// = 버튼 콜백 매소드 구현
	class MyActionListener3 implements ActionListener {
		public void actionPerformed(ActionEvent e) { // = 버튼 눌렀을 때 호출
			double result;
			if (str2.isEmpty())	// 디폴트 값 0으로 처리
				str2 = "0";
			if (cal == '+') { // 저장된 연산자가 + 라면
				result = Double.parseDouble(str) + Double.parseDouble(str2);// 두 수를 실수로 바꿔서 더하고
				str = Double.toString(result); // 그 결과를 다시 문자로 바꿔서 str에 저장
				text1.setText(str); // text에 출력
			} else if (cal == '-') {
				result = Double.parseDouble(str2) - Double.parseDouble(str); // 두 수를 실수로 바꿔서 빼고
				str = Double.toString(result); // 결과를 문자열로 변환하여 저장
				text1.setText(str); // text에 출력
			} else if (cal == '*') {
				result = Double.parseDouble(str) * Double.parseDouble(str2); // 두 수를 실수로 바꿔서 곱하고
				str = Double.toString(result); // 결과를 문자열로 변환하여 저장
				text1.setText(str); // text에 출력
			} else if (cal == '/') {
				if (str.equals("0")) { // 예외 문구 출력
					text1.setText("0으로 나눌 수 없습니다.");
					return;
				}
				result = Double.parseDouble(str2) / Double.parseDouble(str); // 두 수를 실수로 바꿔서 나누고
				str = Double.toString(result); // 결과를 문자열로 변환하여 저장
				text1.setText(str); // text에 출력
			} else if (cal == '^') {
				result = Math.pow(Double.parseDouble(str2), Double.parseDouble(str)); // 두 수를 실수로 바꿔서 계산하고
				str = Double.toString(result); // 결과를 문자열로 변환하여 저장
				text1.setText(str); // text에 출력
			}
		}
	}

	// 공학용 계산기 구현
	class engrCalc extends JPanel {
		public engrCalc() {
			setLayout(new GridLayout(4, 3, 4, 4));

			MyActionListener4 ml4 = new MyActionListener4();
			
			JButton j1 = new JButton("sin");	// sin 버튼 추가
			j1.addActionListener(ml4);
			add(j1);	// 패널에 버튼 추가

			JButton j2 = new JButton("sinh");	// sinh 버튼 추가
			j2.addActionListener(ml4);
			add(j2);	// 패널에 버튼 추가
			
			JButton j3 = new JButton("cos");	// cos 버튼 추가
			j3.addActionListener(ml4);
			add(j3);	// 패널에 버튼 추가
			
			JButton j4 = new JButton("cosh");	// cosh 버튼 추가
			j4.addActionListener(ml4);
			add(j4);	// 패널에 버튼 추가
			
			JButton j5 = new JButton("tan");	// tan 버튼 추가
			j5.addActionListener(ml4);
			add(j5);	// 패널에 버튼 추가
			
			JButton j6 = new JButton("tanh");	// tanh 버튼 추가
			j6.addActionListener(ml4);
			add(j6);	// 패널에 버튼 추가
			
			JButton j7 = new JButton("exp");	// exp 버튼 추가
			j7.addActionListener(ml4);
			add(j7);	// 패널에 버튼 추가
			
			JButton j8 = new JButton("abs");	// abs 버튼 추가
			j8.addActionListener(ml4);
			add(j8);	// 패널에 버튼 추가
			
			JButton j9 = new JButton("x^y");	// pow 버튼 추가
			j9.addActionListener(ml4);
			add(j9);	// 패널에 버튼 추가
			
			JButton j10 = new JButton("π");		// 파이 버튼 추가
			j10.addActionListener(ml4);
			add(j10);	// 패널에 버튼 추가
			
			JButton j11 = new JButton("n!");	// 펙토리얼 버튼 추가
			j11.addActionListener(ml4);	// 버튼에 MyActionListener4 리스너 콜백 메소드 추가
			add(j11);	// 패널에 버튼 추가

			JButton j12 = new JButton("sqrt");	// sqrt 버튼 추가
			j12.addActionListener(ml4);	// 버튼에 MyActionListener4 리스너 콜백 메소드 추가
			add(j12);	// 패널에 버튼 추가
		}
	}

	// 공학용 연산 콜백 메소드 구현
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
			} else if (btn.getText().equals("π")) { // j10
				str = Double.toString(Math.PI);
				text1.setText(str);
			} else if (btn.getText().equals("n!")) { // j11
				double result;
				if (tmp % 1 != 0) {	// 입력된 수가 실수일 경우
					result = gamma(tmp + 1);
				} else {	// 입력된 수가 정수일 경우
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
		
		// 팩토리얼 메소드 구현
		private double fact(double tmp) {
			if(tmp == 1)
				return 1;
			return tmp * fact(tmp-1);
		}

		// 감마함수 메소드 구현
		private double gamma(double d) {
			double t = (d - 0.5) * Math.log(d + 4.5) - (d + 4.5);
			double ser = 1.0 + 76.18009173 / (d + 0) - 86.50532033 / (d + 1) + 24.01409822 / (d + 2)
					- 1.231739516 / (d + 3) + 0.00120858003 / (d + 4) - 0.00000536382 / (d + 5);
			double result = Math.exp(t + Math.log(ser * Math.sqrt(2 * Math.PI)));
			return result;
		}
	}

	// 메뉴 콜백 메소드 구현
	class MenuActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd.equals("일반용")) { // 일반용 컴포넌트 선택
				p3.remove(engr);
				mf.setSize(230, 260); // 프레임 사이즈 설정
				mf.setVisible(true); // 출력
			} else if (cmd.equals("공학용")) { // 공학용 컴포넌트 선택
				mf.setSize(230, 380); // 프레임 사이즈 설정
				p3.add(engr);	// p3 패널에 engr 패널 추가
				mf.setVisible(true); // 출력
			} else if (cmd.equals("열기")) { // 열기 컴포넌트 선택
				chooser = new JFileChooser();
				filter = new FileNameExtensionFilter("모든 그림파일", "png", "jpg", "gif");
				chooser.setFileFilter(filter);
				int ret = chooser.showOpenDialog(null);
				if (ret == JFileChooser.APPROVE_OPTION) {
					String path = chooser.getSelectedFile().getPath();
					text1.setText(path);
				} else if (ret == JFileChooser.CANCEL_OPTION) {
					chooser.cancelSelection();
				}
			} else if (cmd.equals("종료")) { // 종료 컴포넌트 선택
				System.exit(0);
			}
		}
	}
}
