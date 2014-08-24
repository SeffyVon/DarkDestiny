package com.darkdensity.test;

import static org.junit.Assert.assertEquals;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import com.darkdensity.maprender.Map;

public class JUnitTest {
	
	@Test
	public void MapTest() {
		Map map = new Map("BigMap");
		assertEquals(4864, map.getMapWidthPx());
		assertEquals(3840, map.getMapHeightPx());
	}

}
