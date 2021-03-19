package testqueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import queue_singlelinkedlist.FifoQueue;

class TestAppendFifoQueue {
	private FifoQueue<Integer> q1, q2;
	
	@BeforeEach
	public void setUp() throws Exception {
		q1 = new FifoQueue<Integer>();
		q2 = new FifoQueue<Integer>();
	}
	
	@AfterEach
	public void tearDown() throws Exception {
		q1 = q2 = null;
	}
	
	@Test
	public void testEmptyToEmpty() {
		q1.append(q2);
		assertEquals(null, q1.peek(), "The head of q1 is not null");
		assertEquals(0, q1.size(), "The size of q1 is not zero");
		assertEquals(null, q2.peek(), "The head of q2 is not null");
		assertEquals(0, q2.size(), "The size of q2 is not zero");
	}
	
	@Test
	public void testEmptyToNonEmpty() {
		int n = 10;
		
		for (int i = 0; i < n; i++) {
			q1.offer(i);
		}
		
		q1.append(q2);
		assertEquals(n, q1.size(), "The size of q1 is not n");
		
		for (int i = 0; i < n; i++) {
			assertEquals(i, (int) q1.poll(), "The order is incorrect");
		}
		
		assertEquals(null, q2.peek(), "The head of q2 is not null");
		assertEquals(0, q2.size(), "The size of q2 is not zero");
	}
	
	@Test
	public void testNonEmptyToEmpty() {
		int n = 10;
		
		for (int i = 0; i < n; i++) {
			q2.offer(i);
		}
		
		q1.append(q2);
		assertEquals(n, q1.size(), "The size of q1 is not n");
		
		for (int i = 0; i < n; i++) {
			assertEquals(i, (int) q1.poll(), "The order is incorrect");
		}
		
		assertEquals(null, q2.peek(), "The head of q2 is not null");
		assertEquals(0, q2.size(), "The size of q2 is not zero");
	}
	
	@Test
	public void testNonEmptyToNonEmpty() {
		int n = 10;
		
		for (int i = 0; i < n; i++) {
			if (i < n/2) {
				q1.offer(i);
			} else {
				q2.offer(i);
			}
		}
		
		q1.append(q2);
		assertEquals(n, q1.size(), "The size of q1 is not n");
		
		for (int i = 0; i < n; i++) {
			assertEquals(i, (int) q1.poll(), "The order is incorrect");
		}
		
		assertEquals(null, q2.peek(), "The head of q2 is not null");
		assertEquals(0, q2.size(), "The size of q2 is not zero");
	}
	
	@Test
	public void testSelfToSelf() {
		try {
			q1.append(q1);
			fail("Should raise an IllegalArgumentException");
		} catch (IllegalArgumentException e) { 
			
		}
	}
}