import java.security.SecureRandom;
import javax.crypto.spec.IvParameterSpec;

public class PRNG_1_Req14 {
  public static void main(String[] args) {
    
    // Generate a secure random seed.
    SecureRandom secureRandomSeed = new SecureRandom();
    byte[] seed = new byte[8]; // Can be 8 bytes in length.
    secureRandomSeed.nextBytes(seed);

    // Create another SecureRandom instance using the generated seed.
    SecureRandom random = new SecureRandom(seed);

    // Initialize an array to hold the IV.
    byte[] iv = new byte[16]; // Assuming we are using AES, IV should be 16 bytes.

    // Fill the array with random bytes.
    random.nextBytes(iv);

    // Create an IvParameterSpec object with the generated IV.
    IvParameterSpec ivSpec = new IvParameterSpec(iv);

    // Print out the seed and the IV for debugging.
    System.out.println("Seed: ");
    for (byte b : seed) {
      System.out.print(b + " ");
    }

    System.out.println("\nInitialization Vector (IV): ");
    for (byte b : iv) {
      System.out.print(b + " ");
    }
  }
}