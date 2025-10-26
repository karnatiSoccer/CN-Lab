import java.util.*;

public class GoBackNSimulation {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter total number of frames to send: ");
        int totalFrames = sc.nextInt();

        System.out.print("Enter window size: ");
        int windowSize = sc.nextInt();

        Random rand = new Random();
        int base = 0; 
        int nextSeq = 0; 

        System.out.println("\n--- Starting Go-Back-N Simulation ---");

        while (base < totalFrames) {
            // Send frames in the window
            while (nextSeq < base + windowSize && nextSeq < totalFrames) {
                System.out.println("Sender: Sending Frame " + nextSeq);
                nextSeq++;
            }

            // Simulate ACKs and random packet loss
            for (int i = base; i < nextSeq; i++) {
                boolean lost = rand.nextInt(10) < 5; // 50% chance frame lost
                if (!lost) {
                    System.out.println("Receiver: ACK received for Frame " + i);
                    base++;
                } else {
                    System.out.println("Receiver: Frame " + i + " lost! Go-Back-N triggered.");
                    nextSeq = base; // retransmit all frames from base
                    break;
                }
            }

            System.out.println("-------------------------------");
        }

        System.out.println("All frames sent and acknowledged successfully!");
    }
}
