package com.s06.score;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ScoreMain {
	private BufferedReader br;
	private ScoreDAO dao;

	public ScoreMain() {
		dao = new ScoreDAO();
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			callMenu();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 자원정리
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
				}
		}
	}

	public void callMenu() throws IOException {
		while (true) {
			System.out.print("1.입력,2.목록,3.상세정보,4.수정,5.삭제,6.종료:");
			try {
				int no = Integer.parseInt(br.readLine());
				if (no == 1) {// 입력
					System.out.println("성적을 입력합니다.");
					System.out.print("이름:");
					String name = br.readLine();
					System.out.print("국어:");
					int korean = Integer.parseInt(br.readLine());
					System.out.print("영어:");
					int english = Integer.parseInt(br.readLine());
					System.out.print("수학:");
					int math = Integer.parseInt(br.readLine());

					ScoreVO vo = new ScoreVO();
					vo.setName(name);
					vo.setKorean(korean);
					vo.setEnglish(english);
					vo.setMath(math);

					dao.insertInfo(vo);

				} else if (no == 2) {// 목록
					dao.selectInfo();
				} else if (no == 3) {// 상세정보
					System.out.print("번호:");
					int num = Integer.parseInt(br.readLine());
					dao.selectDetailInfo(num);
				} else if (no == 4) {// 수정
					System.out.print("수정할 성적 번호 : ");
					int num = Integer.parseInt(br.readLine());
					System.out.print("이름 : ");
					String name = br.readLine();
					System.out.print("국어:");
					int korean = Integer.parseInt(br.readLine());
					System.out.print("영어:");
					int english = Integer.parseInt(br.readLine());
					System.out.print("수학:");
					int math = Integer.parseInt(br.readLine());

					ScoreVO vo = new ScoreVO();
					vo.setNum(num);
					vo.setName(name);
					vo.setKorean(korean);
					vo.setEnglish(english);
					vo.setMath(math);
					dao.updateInfo(vo);
				} else if (no == 5) {// 삭제
					System.out.print("번호:");
					int num = Integer.parseInt(br.readLine());
					dao.deleteInfo(num);
				} else if (no == 6) {// 종료
					System.out.println("프로그램 종료!!");
					break;
				} else {
					System.out.println("잘못 입력했습니다.");
				}
			} catch (NumberFormatException e) {
				System.out.println("숫자만 입력 가능!!");
			}
		}
	}

	public static void main(String[] args) {
		new ScoreMain();
	}
}
