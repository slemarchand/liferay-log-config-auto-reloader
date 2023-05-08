package com.slemarchand.log.config.auto.reloader.internal.log4j;

import java.util.Arrays;

public class Version implements Comparable<Version>  {
	
	private int[] _parts;
	
	public Version(String version) {
		this(Arrays.stream(version.split("\\.")).mapToInt(Integer::parseInt).toArray());
	}
	
	public Version(int[] parts) {
		_parts = Arrays.copyOf(parts, 5);
	}
	
	public int[] getParts() {
		return _parts;
	}
	
	public boolean greaterThanOrEqualTo(Version otherVersion) {
		
		int compare = compareTo(otherVersion);
		
		return compare >= 0;
	}
	
	public boolean greaterThanOrEqualTo(String otherVersion) {
		
		return greaterThanOrEqualTo(new Version(otherVersion));
	}

	@Override
	public int compareTo(Version otherVersion) {
		
		int result = 0;
		
		int[] _otherParts = otherVersion.getParts();
		
		for (int i = 0; i < _parts.length && result == 0; i++) {
			result = Integer.compare(_parts[i], _otherParts[i]);
		}
		
		return result;
	}
}
