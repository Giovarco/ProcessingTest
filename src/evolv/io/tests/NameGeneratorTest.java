package evolv.io.tests;

import org.junit.Assert;
import org.junit.Test;

import evolv.io.NameGenerator;

public class NameGeneratorTest {
	@Test
	public void testNewName() {
		for (int i = 0; i < 1_000; i++) {
			String name = NameGenerator.newName();
			char first = name.charAt(0);
			Assert.assertTrue(Character.isUpperCase(first));
			for (int j = 1; j < name.length(); j++) {
				Assert.assertTrue(Character.isLowerCase(name.charAt(j)));
			}
		}
	}

	@Test
	public void testMutatedNames() {
		for (int i = 0; i < 1_000; i++) {
			String name = NameGenerator.newName();
			String mutatedName = NameGenerator.mutateName(name);
			char first = mutatedName.charAt(0);
			Assert.assertTrue(Character.isUpperCase(first));
			for (int j = 1; j < mutatedName.length(); j++) {
				Assert.assertTrue(Character.isLowerCase(mutatedName.charAt(j)));
			}
		}
	}
}
