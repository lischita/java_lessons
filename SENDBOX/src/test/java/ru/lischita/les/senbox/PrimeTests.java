package ru.lischita.les.senbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PrimeTests {
  @Test
  public void testPrimes(){
    Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));

  }
}
