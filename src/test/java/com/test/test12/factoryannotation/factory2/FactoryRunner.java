package com.test.test12.factoryannotation.factory2;

import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class FactoryRunner {
	
	@Test

	@Factory
	public Object[] runTests() {

		return new Object[] { new Factory1Test(), new Factory2Test() };
	}
}
