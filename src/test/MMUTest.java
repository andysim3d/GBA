package test;

import static org.junit.Assert.*;
import hardware.MMU;

import org.junit.Test;

public class MMUTest {

	@Test
	public void test() {
		MMU mm = new MMU();
		mm.test();
		mm.wb(0xAF01, 50);
		mm.wb(0xAF02, 1);
		int res = mm.rw(0xAF01);
		System.out.println(res);
		assertTrue(res == (50 + 1<<8));
	}

}
