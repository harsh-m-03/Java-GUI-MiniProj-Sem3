package Password;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class password {
    public String generate() {
        // special char, number, lowerchase, upperchase
        String pass = ""; // introduce pass as empty
        pass += (char) randomChar(97, 122);
        pass = pass.toUpperCase();
        pass += (char) randomChar(97, 122);
        char temp1 = (char) randomChar(58, 64);
        char temp2 = (char) randomChar(33, 47);
        pass += new Random().nextBoolean() ? (char) temp1 : (char) temp2;
        int i = 5;
        while (i-- > 0)
            pass += String.valueOf((int) randomChar(0, 9));
        pass += String.valueOf((char) randomChar(97, 122)).toUpperCase();
        pass += (char) randomChar(97, 122);

        return shuffleString(pass);
    }

    public String generate(int length, int upper, int lower, int special, int numbers) {
        String pass = "";
        while (upper-- > 0)
            pass += String.valueOf((char) randomChar(97, 122)).toUpperCase();
        while (lower-- > 0)
            pass += String.valueOf((char) randomChar(97, 122));
        while (numbers-- > 0)
            pass += String.valueOf((int) randomChar(0, 9));
        while (special-- > 0) {
            char temp1 = (char) randomChar(58, 64);
            char temp2 = (char) randomChar(33, 47);
            pass += new Random().nextBoolean() ? (char) temp1 : (char) temp2;
        }

        return shuffleString(pass);
    }

    public int randomChar(int min, int max) { // selecting random number betweeen max and min
        int x = (int) (Math.random() * (max - min + 1) + min);
        return x;
    }

    public String shuffleString(String pass) {
        List<String> strList = Arrays.asList(pass.split(""));
        System.out.println("pass->" + strList);
        Collections.shuffle(strList);
        System.out.println("pass->" + strList);
        pass = "";
        for (String s : strList)
            pass += s;
        return pass;
    }

}