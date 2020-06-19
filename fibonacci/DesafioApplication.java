package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

	public static List<Integer> fibonacci() {

		int num = 0;
		List<Integer> seqfibo = new ArrayList<>();

		while (num <= 350) {
			if (seqfibo.size() != 0) {
				seqfibo.add(num);
				num += seqfibo.get(seqfibo.size() - 2);
			} else {
				seqfibo.add(num);
				num = 1;
			}

		}

		seqfibo.add(num);

		return seqfibo;
	}

	public static Boolean isFibonacci(Integer a) {
		return fibonacci().contains(a);
	}
}