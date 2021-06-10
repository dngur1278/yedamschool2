package com.yedam.lambda;

interface Run {
	void run();
}

class RunCls implements Run {
	@Override
	public void run() {
		System.out.println("달립니다");
	}
}

public class FunctionaEx1 {
	public static void main(String[] args) {
		RunCls cls = new RunCls();
		cls.run();
		// 익명 클래스 생성
		// 람다표현식(Consumer 타입)
		Run r = () -> System.out.println("빨리 달립니다");
		r.run();
	}
}
