//7th
import java.util.Scanner;
public class CaesarCipher {
    static String encrypt(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                ch = (char) ((ch - base + key) % 26 + base);
            }
            result.append(ch);
        }
        return result.toString();
    }
    static String decrypt(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                ch = (char) ((ch - base - key + 26) % 26 + base);
            }
            result.append(ch);
        }
        return result.toString();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.nextLine();
        System.out.print("Enter key (shift): ");
        int key = sc.nextInt();
        String encrypted = encrypt(text, key);
        System.out.println("Encrypted text: " + encrypted);
        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted text: " + decrypted);
        sc.close();
    }
}