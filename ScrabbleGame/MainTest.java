import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @org.junit.jupiter.api.Test
    void main() throws IOException {
        String input = "d";
        String expectedOutput = "------ Scrabble Game------\r";
        expectedOutput += "Would you like to _l_oad a board or use the _d_efault board?\r";
        expectedOutput += "Please enter your choice (l/d):\r";
        expectedOutput = "a   b  c   d  e   f  g   h  i   j  k   l  m   n  o\r";
        expectedOutput += "1 {3}  .  .  (2)  .  .  .  {3}  .  .  .  (2)  .  .  {3}  \r";
        expectedOutput += "2 .  {2}  .  .  .  (3)  .  .  .  (3)  .  .  .  {2}  .  \r";
        expectedOutput += "3 .  .  {2}  .  .  .  (2)  .  (2)  .  .  .  {2}  .  .   \r";
        expectedOutput += "4 (2)  .  .  {2}  .  .  .  (2)  .  .  .  {2}  .  .  (2)  \r";
        expectedOutput += " .  .  .  .  {2}  .  .  .  .  .  {2}  .  .  .  .  \r";
        expectedOutput += " .  (3)  .  .  .  (3)  .  .  .  (3)  .  .  .  (3)  .  \r";
        expectedOutput += ".  .  (2)  .  .  .  (2)  .  (2)  .  .  .  (2)  .  .  \r";
        expectedOutput += "{3}  .  .  (2)  .  .  .  {2}  .  .  .  (2)  .  .  {3}  \r";
        expectedOutput += " .  .  (2)  .  .  .  (2)  .  (2)  .  .  .  (2)  .  .  ";
        expectedOutput += " .  (3)  .  .  .  (3)  .  .  .  (3)  .  .  .  (3)  .  \r";
        expectedOutput += ".  .  .  .  {2}  .  .  .  .  .  {2}  .  .  .  .  \r";
        expectedOutput += "(2)  .  .  {2}  .  .  .  (2)  .  .  .  {2}  .  .  (2)  \r";
        expectedOutput += " .  .  {2}  .  .  .  (2)  .  (2)  .  .  .  {2}  .  .  \r";
        expectedOutput += ".  {2}  .  .  .  (3)  .  .  .  (3)  .  .  .  {2}  .  \r";
        expectedOutput += " {3}  .  .  (2)  .  .  .  {3}  .  .  .  (2)  .  .  {3}   \r";
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        Main.main(null);
        assertEquals(expectedOutput, outContent.toString());
    }

    @org.junit.jupiter.api.Test
    void t2() throws IOException {
        String input = "d";
        String expectedOutput = "------ Scrabble Game------\r";
        expectedOutput += "Would you like to _l_oad a board or use the _d_efault board?\r";
        expectedOutput += "Please enter your choice (l/d):\r";
        expectedOutput = "a   b  c   d  e   f  g   h  i   j  k   l  m   n  o\r";
        expectedOutput += "1 {3}  .  .  (2)  .  .  .  {3}  .  .  .  (2)  .  .  {3}  \r";
        expectedOutput += "2 .  {2}  .  .  .  (3)  .  .  .  (3)  .  .  .  {2}  .  \r";
        expectedOutput += "3 .  .  {2}  .  .  .  (2)  .  (2)  .  .  .  {2}  .  .   \r";
        expectedOutput += "4 (2)  .  .  {2}  .  .  .  (2)  .  .  .  {2}  .  .  (2)  \r";
        expectedOutput += " .  .  .  .  {2}  .  .  .  .  .  {2}  .  .  .  .  \r";
        expectedOutput += " .  (3)  .  .  .  (3)  .  .  .  (3)  .  .  .  (3)  .  \r";
        expectedOutput += ".  .  (2)  .  .  .  (2)  .  (2)  .  .  .  (2)  .  .  \r";
        expectedOutput += "{3}  .  .  (2)  .  .  .  {2}  .  .  .  (2)  .  .  {3}  \r";
        expectedOutput += " .  .  (2)  .  .  .  (2)  .  (2)  .  .  .  (2)  .  .  ";
        expectedOutput += " .  (3)  .  .  .  (3)  .  .  .  (3)  .  .  .  (3)  .  \r";
        expectedOutput += ".  .  .  .  {2}  .  .  .  .  .  {2}  .  .  .  .  \r";
        expectedOutput += "(2)  .  .  {2}  .  .  .  (2)  .  .  .  {2}  .  .  (2)  \r";
        expectedOutput += " .  .  {2}  .  .  .  (2)  .  (2)  .  .  .  {2}  .  .  \r";
        expectedOutput += ".  {2}  .  .  .  (3)  .  .  .  (3)  .  .  .  {2}  .  \r";
        expectedOutput += " {3}  .  .  (2)  .  .  .  {3}  .  .  .  (2)  .  .  {3}   \r";
        expectedOutput += "Human player score:0\r";
        expectedOutput += "Computer player score: 0\r";
        expectedOutput+="It's your turn! Your tiles:\r";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Main.main(null);
        assertEquals(expectedOutput, outContent.toString());
    }

}