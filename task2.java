import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Word Counting Program");
        System.out.println("---------------------");
        System.out.println("1. Enter text");
        System.out.println("2. Provide a file");
        System.out.print("Select an option (1/2): ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        
        String text = "";
        
        switch (choice) {
            case 1:
                System.out.print("Enter the text: ");
                text = scanner.nextLine();
                break;
            case 2:
                System.out.print("Enter the file path: ");
                String filePath = scanner.nextLine();
                try {
                    text = readFile(filePath);
                } catch (IOException e) {
                    System.out.println("Error reading file: " + e.getMessage());
                    return;
                }
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
        
        String[] words = text.split("[\\s\\p{Punct}]+");
        int wordCount = words.length;
        
        System.out.println("Total word count: " + wordCount);
        
        // Enhancements:
        // 7. Ignoring common words or stop words
        // 8. Providing statistics like the number of unique words or the frequency of each word
        
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            word = word.toLowerCase();
            if (!isCommonWord(word)) {
                wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            }
        }
        
        System.out.println("Unique word count: " + wordFrequency.size());
        System.out.println("Word frequency:");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    
    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        
        reader.close();
        return content.toString();
    }
    
    private static boolean isCommonWord(String word) {
        // Replace with your list of common words or stop words
        String[] commonWords = {"the", "and", "is", "of", "in", "to", "a", "that", "it", "with"};
        return Arrays.asList(commonWords).contains(word);
    }
}
