package redis.lab01_4.a;

import redis.clients.jedis.Jedis;
import java.util.*;
import java.io.*;

public class Autocomplete {
    
    private Jedis jedis;

    public Autocomplete() {
        jedis = new Jedis();
    }

    // follows the ex3 logic
    public List<String> getNames() {
		return jedis.lrange("names_list", 0, -1);
	}

    public static void main(String[] args) {

        Autocomplete autoc = new Autocomplete();

        try {
            // info name jedis
            Scanner file = new Scanner(new File("lab01/src/names.txt"));
            while(file.hasNextLine()){
                String name = file.nextLine();
                autoc.jedis.lpush("names_list", name);
            }

            // user interaction
            System.out.print("Search for ('Enter for quit'): ");
            Scanner sc = new Scanner(System.in);
            String input = sc.next();
            sc.close();

            ArrayList<String> infoautoc = new ArrayList<String>();

            for(String name : autoc.getNames()){
                if (name.startsWith(input)){
                    infoautoc.add(name);
                }
            }
            
            // output
            for(String res: infoautoc){
                System.out.println(res);
            }

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
