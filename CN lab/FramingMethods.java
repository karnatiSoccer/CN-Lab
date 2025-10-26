import java.util.*;

public class FramingMethods {
    // Character Count Framing
    static String charCountFrame(String data) {
        String[] frames = data.split(" ");
        StringBuilder result = new StringBuilder();
        for (String f : frames)
            result.append(f.length()).append(f);
        return result.toString();
    }

    // Character Stuffing
    static String charStuff(String data) {
        String FLAG = "FLAG", ESC = "ESC";
        StringBuilder result = new StringBuilder(FLAG);
        for (String f : data.split(" ")) {
            result.append(" ");
            if (f.contains(FLAG)) f = f.replace(FLAG, ESC + FLAG);
            if (f.contains(ESC)) f = f.replace(ESC, ESC + ESC);
            result.append(f).append(" ");
        }
        result.append(FLAG);
        return result.toString();
    }

    static String charDestuff(String data) {
        String FLAG = "FLAG", ESC = "ESC";
        data = data.replaceFirst(FLAG, "").replaceFirst(FLAG + "$", "").trim();
        data = data.replace(ESC + FLAG, FLAG).replace(ESC + ESC, ESC);
        return data;
    }

    // Bit Stuffing
    static String bitStuff(String bits) {
        String FLAG = "01111110";
        StringBuilder result = new StringBuilder(FLAG);
        int count = 0;
        for (char b : bits.toCharArray()) {
            result.append(b);
            if (b == '1') {
                count++;
                if (count == 5) {
                    result.append('0');
                    count = 0;
                }
            } else count = 0;
        }
        result.append(FLAG);
        return result.toString();
    }

    static String bitDestuff(String bits) {
        String FLAG = "01111110";
        bits = bits.replace(FLAG, "");
        StringBuilder res = new StringBuilder();
        int count = 0;
        for (int i = 0; i < bits.length(); i++) {
            char b = bits.charAt(i);
            if (b == '1') {
                count++;
                res.append(b);
                if (count == 5 && i + 1 < bits.length() && bits.charAt(i + 1) == '0')
                    i++;
            } else {
                res.append(b);
                count = 0;
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text data: ");
        String data = sc.nextLine();

        System.out.println("\nCharacter Count Framing: " + charCountFrame(data));

        String stuffed = charStuff(data);
        System.out.println("Character Stuffing: " + stuffed);
        System.out.println("Destuffed: " + charDestuff(stuffed));

        System.out.print("\nEnter bit string: ");
        String bits = sc.nextLine();
        String bstuff = bitStuff(bits);
        System.out.println("Bit Stuffing: " + bstuff);
        System.out.println("Destuffed: " + bitDestuff(bstuff));
    }
}
