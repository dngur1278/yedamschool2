package com.yedam.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.yedam.Person;
import com.yedam.PersonDAO;

public class IteratorExample {
	public static void main(String[] args) {

		// asList() -> 괄호 안의 값을 list에 넣어준다
		List<String> list = Arrays.asList("Hong", "Park", "Choi");
		// 반복된 요소를 지정 사용하기 위한 반복자
		Iterator<String> iter = list.iterator();
		while (iter.hasNext()) { // iter의 가져올 값이 있는지 물어본 while문
			String value = iter.next(); // next로 iter의 요소를 가져옴
			System.out.println(value.toUpperCase().length());
		}

		// stream사용
		Stream<String> stream = list.stream();
		stream.forEach(new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.println(t);
			}
		});

		// sample 요소를 database에서 갖고 오도록 한다
		PersonDAO dao = new PersonDAO();
		ArrayList<Person> persons = dao.getPersonList();
		Stream<Person> pStream = persons.stream();
		long cnt = pStream//
				.filter((t) -> t.getAge() > 27)		// return 값이 true인 요소만 통과
				.filter((t) -> t.getName().startsWith("김")) //
				//.forEach((t) -> System.out.println(t.toString()))
				.count();
		System.out.println("최종 건수 처리:" + cnt);
	}
}
