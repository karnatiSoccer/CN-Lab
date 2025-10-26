import java.util.*;

public class CRC {
    static String xor(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < b.length(); i++)
            result.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        return result.toString();
    }

    static String mod2div(String dividend, String divisor) {
        int pick = divisor.length();
        String tmp = dividend.substring(0, pick);
        // System.out.println("first : " + tmp);

        while (pick < dividend.length()) {
            if (tmp.charAt(0) == '1'){
                // System.out.println("before 1:" + tmp);
                tmp = xor(divisor, tmp) + dividend.charAt(pick);
                // System.out.println("after 1:" + tmp);
            }
            else{
                System.out.println("before 0:" + tmp);
                tmp = xor("0".repeat(pick), tmp) + dividend.charAt(pick);
                // System.out.println("after 0:" + tmp);
            }
            pick++;
        }

        if (tmp.charAt(0) == '1')
            tmp = xor(divisor, tmp);
        else
            tmp = xor("0".repeat(pick), tmp);

        // System.out.println(tmp);
        return tmp;
    }

    static String encodeData(String data, String key) {
        int keylen = key.length();
        String appendedData = data + "0".repeat(keylen - 1);
        System.out.println("Total : " + appendedData);
        String remainder = mod2div(appendedData, key);
        return data + remainder;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter binary data: ");
        String data = sc.nextLine();

        String crc12 = "1100000001111";      // CRC-12 (x^12 + x^11 + x^3 + x^2 + x + 1)
        String crc16 = "11000000000000101";  // CRC-16 (x^16 + x^15 + x^2 + 1)
        String crcCCITT = "10001000000100001"; // CRC-CCITT (x^16 + x^12 + x^5 + 1)

        System.out.println("\nCRC-12 Codeword:     " + encodeData(data, crc12));
        System.out.println("CRC-16 Codeword:     " + encodeData(data, crc16));
        System.out.println("CRC-CCITT Codeword:  " + encodeData(data, crcCCITT));
    }
}
