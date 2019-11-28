/******************************************************************************
 *  Marlon Jacques | 23147013
 *
 *  _Example commands for running this file_
 *  Compilation:  javac -Xlint LyricCount.java
 *  Execution:    java LyricCount
 *
 *  Example code counts the frequency of words used in a song using a HashMap.
 *
 ******************************************************************************/



import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LyricCount {
    public static void main(String []args) throws IOException {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter the name of the song.\n");
        String song = s.nextLine();
        System.out.println();

        System.out.print("What is the name of the artist?\n");
        String artist = s.nextLine();
        System.out.println();

        System.out.println("Enter a filename (must be .txt). " +
                "Don't worry, the extension is already covered for you.: "); //To make it dynamic by user input file name
        String fileName = s.nextLine();
        System.out.println();

        System.out.println("Here is the word count for every word in your song!\n\n" +
                "Song: " + song +  "\nArtist: " + artist + "\n");
        lyricCount(System.getProperty("user.dir"),fileName+".txt", song, artist);

    }

    private static void lyricCount(String directory, String fileName, String song, String artist) throws IOException {
// Declare the HashMap
        HashMap<String, Integer> freq = new HashMap();
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
// Puts together the string that the FileReader will refer to.
        String fileNameDir = directory+ "\\src\\" + fileName;

        try {
            FileReader reader = new FileReader(fileNameDir);
            BufferedReader br = new BufferedReader(reader);

// The BufferedReader reads the lines

            String verse;
            while ((verse = br.readLine()) != null) {
// First removes all non-letter characters, then to lowercase, then splits the input,
// \\s+ - matches sequence of one or more whitespace characters.
                String[] lyrics = verse.replaceAll("[^a-zA-Z ]", "")
                        .toLowerCase().split("\\s+");


// for loop goes through every word
                for (int i = 0; i < lyrics.length; i++) {
// Case if the HashMap already contains the key.
// If so, just increments the value

                    if (freq.containsKey(lyrics[i])) {
                        int n = freq.get(lyrics[i]);
                        freq.put(lyrics[i], ++n);
                    }
// Otherwise, puts the word into the HashMap
                    else {
                        freq.put(lyrics[i], 1);
                    }
                }
            }

            System.out.printf("%-10s%53s", "Word", "Count \n");
            System.out.printf("---------------------------------------------------------------------\n");

            writer.write("Here is the word count for every word in your song!\n\n" +
                    "Song: " + song +  "\nArtist: " + artist + "\n\n");
            writer.write(String.format("%-10s%53s", "Word", "Count \n"));
            writer.write("---------------------------------------------------------------------\n");

               for (Map.Entry entry : freq.entrySet()) {
                   System.out.printf("%-10s%50s", entry.getKey(), entry.getValue() + "\n");
                   writer.write(String.format("%-10s%50s", entry.getKey(), entry.getValue() + "\n"));
               }

               writer.close();

// Catching the file not found error
// and any other errors
        }
        catch (FileNotFoundException e) {
            System.err.println("Error 001: File cannot be found.");
        }
        catch (NullPointerException e) {
            System.err.println("Error 002: The file submitted is empty.");
        }
        catch (Exception e) {
            System.err.print(e);
        }
    }
}