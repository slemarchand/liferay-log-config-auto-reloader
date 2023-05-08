package com.slemarchand.log.config.auto.reloader.internal.log4j;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class VersionTest {

	@Test
	public void test() {
		assertTrue(
			!new Version(
				"7.0"
			).greaterThanOrEqualTo(
				_VERSION_7_4_3_41
			));
		assertTrue(
			!new Version(
				"7.0"
			).greaterThanOrEqualTo(
				_VERSION_7_4_3_41
			));
		assertTrue(
			!new Version(
				"7.3.1"
			).greaterThanOrEqualTo(
				_VERSION_7_4_3_41
			));
		assertTrue(
			!new Version(
				"7.4.3.40"
			).greaterThanOrEqualTo(
				_VERSION_7_4_3_41
			));
		assertTrue(
			new Version(
				_VERSION_7_4_3_41
			).greaterThanOrEqualTo(
				_VERSION_7_4_3_41
			));
		assertTrue(
			new Version(
				"7.5.0.0"
			).greaterThanOrEqualTo(
				_VERSION_7_4_3_41
			));
		assertTrue(
			new Version(
				"7.5"
			).greaterThanOrEqualTo(
				_VERSION_7_4_3_41
			));
		assertTrue(
			new Version(
				"7.4.10.0"
			).greaterThanOrEqualTo(
				_VERSION_7_4_3_41
			));
		assertTrue(
			new Version(
				"8.0.0.0"
			).greaterThanOrEqualTo(
				_VERSION_7_4_3_41
			));
		assertTrue(
			new Version(
				"8.0"
			).greaterThanOrEqualTo(
				_VERSION_7_4_3_41
			));
	}

	private static final String _VERSION_7_4_3_41 = "7.4.3.41";

}