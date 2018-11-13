package com.pinux.service.impl;

import static org.junit.Assert.fail;

import com.pinux.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pinux.dto.AppointExecution;
import com.pinux.service.BookService;

public class BookServiceImplTest extends BaseTest {

	@Autowired
	private BookService bookService;

	@Test
	public void testAppoint() throws Exception {
		long bookId = 1001;
		long studentId = 12345678910L;
		AppointExecution execution = bookService.appoint(bookId, studentId);
		System.out.println(execution);
	}

}
