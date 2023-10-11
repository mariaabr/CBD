package redis.lab01_4.b;

import redis.clients.jedis.Jedis;
import java.util.*;
import java.util.Map.*;
import java.io.*;

public class Autocompletepopularidade {
    
    private Jedis jedis;

    public Autocompletepopularidade() {
        jedis = new Jedis();
    }

    // follows the ex3 logic
    public Map<String, String> getNames() {
		return jedis.hgetAll("names_popularity_list");
	}

    // order map by popularity
    public static Map<String, String> ordermapa(Map<String, String> map){
        List<Entry<String, String>> entries = new LinkedList<>(map.entrySet()); // entries' list
        
        // entries.sort(Entry.comparingByValue());
        Collections.sort(entries, new Comparator<Entry<String, String>>(){ // sort by popularity

            @Override
            public int compare(Entry<String, String> entry1, Entry<String, String> entry2){
                Integer intentry1 = Integer.parseInt(entry1.getValue());
                Integer intentry2 = Integer.parseInt(entry2.getValue());

                return intentry2.compareTo(intentry1); // compare do maior para o menor
            }
        });

        // create the new ordered map
        Map<String, String> orderedmapa = new LinkedHashMap<>();

        for(Entry<String, String> entry : entries){
            orderedmapa.put(entry.getKey(), entry.getValue());
        }

        return orderedmapa;

    }

    public static void main(String[] args) {

        Autocompletepopularidade autocp = new Autocompletepopularidade();

        try {

            Map<String, String> mapa = new HashMap<>();
            // info name,popularity (k,v) jedis
            Scanner file = new Scanner(new File("lab01/src/nomes-pt-2021.csv"));
            while(file.hasNextLine()){
                String line = file.nextLine();
                String[] line_split = line.split(";"); // split da linha, name como key e popularity como value
                String name = line_split[0];
                String popularity = line_split[1];

                mapa.put(name, popularity);
            }
            
            // for(Map.Entry<String, String> entry : mapa.entrySet()) {
            //     System.out.println(entry);
            // }

            autocp.jedis.hmset("names_popularity_list", mapa); // mapa not ordered

            // user interaction
            System.out.print("Search for ('Enter for quit'): ");
            Scanner sc = new Scanner(System.in);
            String input = sc.next();
            sc.close();

            Map<String, String> infoautoc = new HashMap<>();
            
            for(Entry<String, String> entry : autocp.getNames().entrySet()){
                String name = entry.getKey();
                String popularity = entry.getValue();
                if (name.toLowerCase().startsWith(input)){
                    infoautoc.put(name, popularity);
                }
            }
            
            // mapa order by popularity
            // Map<String, String> mapaautocp = autocp.getNames();
            Map<String, String> orderedmapa = ordermapa(infoautoc); // sort map of autocp
            
            // for(Entry<String, String> entry : orderedmapa.entrySet()) {
            //     if(Integer.parseInt(entry.getValue()) > 450)
            //         System.out.println(entry);
            // }

            // output
            for(Entry<String, String> res: orderedmapa.entrySet()){
                System.out.println(res);
            }

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
