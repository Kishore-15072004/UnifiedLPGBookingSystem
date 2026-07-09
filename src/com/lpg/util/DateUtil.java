package com.lpg.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtil {

	public static String getCurrentDate() {

		return LocalDate.now().toString();
	}

	public static String getCurrentDateTime() {

		return LocalDateTime.now().toString();
	}
}