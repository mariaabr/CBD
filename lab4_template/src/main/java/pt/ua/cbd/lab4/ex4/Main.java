package pt.ua.cbd.lab4.ex4;

import java.io.*;
import java.util.*;

import org.neo4j.driver.*;
import org.neo4j.driver.Record;

public class Main {
    private static final String bd_file = "lab4_template/src/main/java/pt/ua/cbd/lab4/ex4/futebol_21.csv";
    private static final String output_file = "lab4_template/resources/CBD_L44_107658_output.txt";

    public static void main(String[] args) throws Exception {
        // connection information
        String uri = "neo4j://localhost:7687"; // server URL
        String user = ""; // username
        String password = ""; // password

        // initialize driver
        Driver driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));

        Session session = driver.session();

        // Runtime runtime = Runtime.getRuntime();
        // long totalMemory = runtime.totalMemory();
        // long freeMemory = runtime.freeMemory();
        // long usedMemory = totalMemory - freeMemory;

        // System.out.println("Total Memory: " + totalMemory);
        // System.out.println("Free Memory: " + freeMemory);
        // System.out.println("Used Memory: " + usedMemory);

        // reset db
        System.out.println("Initializing FIFA 21 graph database...");
        session.run("match (n) detach delete (n)"); // delete database
        System.out.println("limpa");

        // read file .csv
        try {
            File file = new File(bd_file);
            if (!file.exists()) {
                System.err.println("File not found: " + bd_file);
                System.exit(1);
            } else {
                System.out.println("hello");
            }

            readFile(file, session); // read file and create the database with data

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        // execute 10 querys
        executequeries(session);
    }

    public static void readFile(File file, Session session) throws IOException {

        // data info
        String name, nameid, club, clubid, nationality, position, positionid, age, matches, starts, mins, goals,
                assists, passesAttempted,
                percPassesCompleted, penaltyGoals, penaltyAttempted, xG, xA, yellowCards, redCards;

        String playerquery = "";

        String countryquery = "";
        String clubquery = "";
        String positionquery = "";

        String fromquery = "";
        String plays_forquery = "";
        String plays_inquery = "";

        int count = 0;

        List<String> countries = new ArrayList<>();
        List<String> clubs = new ArrayList<>();
        List<String> positions = new ArrayList<>();

        // read lines one by one
        Scanner scanner = new Scanner(file);

        // read file lines
        while (scanner.hasNextLine()) {

            String line = scanner.nextLine();
            // System.out.println(line);

            // create a node and a relation for each line
            if (count != 0) {
                String[] info = line.split(",");
                name = info[0];
                nameid = name.toLowerCase().replace(" ", "");
                // System.out.println("nameid: " + nameid);
                club = info[1];
                clubid = name.toLowerCase().replace(" ", "");
                nationality = info[2];
                position = info[3].replace("'", " ").replace(",", " ");
                positionid = position.replace(" ", "");
                // System.out.println("position: " + position);
                age = info[4];
                matches = info[5];
                starts = info[6];
                mins = info[7];
                goals = info[8];
                assists = info[9];
                passesAttempted = info[10];
                percPassesCompleted = info[11];
                penaltyGoals = info[12];
                penaltyAttempted = info[13];
                xG = info[14];
                xA = info[15];
                yellowCards = info[16];
                redCards = info[17];

                // create player, country, club and position nodes
                playerquery = "CREATE (pl" + nameid
                        + ":Player {name: $name, age: $age, matches: $matches, " +
                        "starts: $starts, mins: $mins, goals: $goals, assists: $assists, passesAttempted: $passesAttempted, "
                        +
                        "percPassesCompleted: $percPassesCompleted, penaltyGoals: $penaltyGoals, penaltyAttempted: $penaltyAttempted, "
                        +
                        "xG: $xG, xA: $xA, yellowCards: $yellowCards, redCards: $redCards })";

                Value paramsplayer = Values.parameters("name", name, "age", age, "matches", matches, "starts", starts,
                        "mins", mins,
                        "goals", goals,
                        "assists", assists, "passesAttempted", passesAttempted, "percPassesCompleted",
                        percPassesCompleted,
                        "penaltyGoals", penaltyGoals, "penaltyAttempted", penaltyAttempted, "xG", xG, "xA", xA,
                        "yellowCards", yellowCards, "redCards", redCards);

                session.run(playerquery, paramsplayer);

                // verify if it was already created
                if (!countries.contains(nationality)) {
                    countryquery = "CREATE (ct" + nationality.toLowerCase() + ":Country {name: $nationality})";
                    Value paramscountry = Values.parameters("nationality", nationality);
                    session.run(countryquery, paramscountry);

                    countries.add(nationality);
                }

                // System.out.println("criei a country");

                if (!clubs.contains(club)) {
                    clubquery = "CREATE (cl" + clubid + ":Club {club: $club})";
                    Value paramsclub = Values.parameters("club", club);
                    session.run(clubquery, paramsclub);

                    clubs.add(club);
                }
                // System.out.println("criei o clube");

                if (!countries.contains(position)) {
                    positionquery = "CREATE (pt" + positionid + ":Position {position: $position})";
                    Value paramsposition = Values.parameters("position", position);
                    session.run(positionquery, paramsposition);

                    positions.add(position);
                }

                // System.out.println("criei a posição");

                // create from, plays_for and plays_in relations
                fromquery = "MATCH (pl:Player {name: $name}), (ct:Country {name: $nationality}) " +
                        "CREATE (pl)-[:FROM]->(ct)";
                Value paramsfrom = Values.parameters("name", name, "nationality", nationality);
                session.run(fromquery, paramsfrom);
                // System.out.println("from done");

                plays_forquery = "MATCH (pl:Player {name: $name}), (cl:Club {club: $club}) " +
                        "CREATE (pl)-[:PLAYS_FOR]->(cl)";
                Value paramsplaysfor = Values.parameters("name", name, "club", club);
                session.run(plays_forquery, paramsplaysfor);
                // System.out.println("plays for done");

                plays_inquery = "MATCH (pl:Player {name: $name}), (pt:Position {position: $position}) " +
                        "CREATE (pl)-[:PLAYS_IN]->(pt)";
                Value paramsplaysin = Values.parameters("name", name, "position", position);
                session.run(plays_inquery, paramsplaysin);
                // System.out.println("plays in done");
            }

            count++;
        }

        System.out.println("count: " + count + " nodes");
        // close scanner
        scanner.close();
    }

    public static void executequeries(Session session) throws Exception {

        List<String> queriesquestion = new ArrayList<>();
        List<String> queriescode = new ArrayList<>();

        String question1 = "//1. Apresente os nomes de todos os jogadores de nacionalidade inglesa, 'England', tendo em conta que Country é uma entidade.";
        String query1 = "match (player)-[:FROM]->(country) " +
                        "where country.name = 'ENG' " +
                        "return player.name as Player, country.name as Nationality";

        queriesquestion.add(question1);
        queriescode.add(query1);

        String question2 = "//2. Apresentar os jogadores do 'Manchester United' ordenados de melhor para pior em termos de número de golos marcados.";
        String query2 = "match (player)-[:PLAYS_FOR]->(club) " +
                        "where club.club  = 'Manchester United' " +
                        "return player.name as Player, club.club as Club, player.goals as Goals " +
                        "order by Goals desc";

       queriesquestion.add(question2);
        queriescode.add(query2);

        String question3 = "//3. Apresentar os 3 jogadores mais velhos.";
        String query3 = "match (player:Player) " + 
                        "return player.name as Player, player.age as Age " +
                        "order by Age desc " +
                        "limit 3";

        queriesquestion.add(question3);
        queriescode.add(query3);

        String question4 = "//4. Apresente todos os pares de jogadores que jogam no mesmo clube.";
        String query4 = "match (player1)-[:PLAYS_FOR]->(club)<-[:PLAYS_FOR]-(player2) " +
                        "where id(player1) < id(player2) " +
                        "return player1.name as player1, player2.name as player2, club.club as club";

        queriesquestion.add(question4);
        queriescode.add(query4);

        String question5 = "//5. Apresente todos os pares de jogadores que jogam no mesmo clube e cujo segundo jogador é de nacionalidade francesa, 'FRA'.";
        String query5 = "match (player1)-[:PLAYS_FOR]->(club)<-[:PLAYS_FOR]-(player2)-[:FROM]->(country) " + 
                        "where id(player1) < id(player2) and country.name = 'FRA' " +
                        "return player1.name as player1, club.club as club, player2.name as player2, country.name as nationality_player2";

        queriesquestion.add(question5);
        queriescode.add(query5);

        String question6 = "//6. Determine a idade média da equipa do clube 'Juventus'.";
        String query6 = "match (player)-[:PLAYS_FOR]->(club) " +
                        "where club.club = 'Chelsea' " +
                        "with club, toInteger(player.age) as age " +
                        "return club.club as club, round(avg(age) * 1.0, 2) as averageAge";

        queriesquestion.add(question6);
        queriescode.add(query6);

       String question7 = "//7. Apresente os guarda-redes do clube 'Leicester City'.";
        String query7 = "match (player)-[:PLAYS_FOR]->(club) " +
                        "match (player)-[:PLAYS_IN]->(position) " +
                        "where club.club = 'Leicester City' and position.position = 'GK' " +
                        "return player.name as player, club.club as club";

        queriesquestion.add(question7);
        queriescode.add(query7);

        String question8 = "//8. Apresentar os jogadores que jogam numa posição diferente de guarda-redes e ordenados por golos marcados de melhor para pior.";
        String query8 = "match (player)-[:PLAYS_IN]->(position) " +
                        "where position.position <> 'GK' " +
                        "return player.name as player, position.position as position, player.goals as goals " +
                        "order by goals desc";

        queriesquestion.add(question8);
        queriescode.add(query8);

        String question9 = "//9. Apresente o jogador mais novo português e em que clube é que joga.";
        String query9 = "match (player)-[:FROM]->(country) " +
                        "match (player)-[:PLAYS_FOR]->(club) " +
                        "where country.name = 'POR' " +
                        "return player.name as player, country.name as nationality, player.age as age, club.club as club " +
                        "order by age " +
                        "limit 1";

        queriesquestion.add(question9);
        queriescode.add(query9);

        String question10 = "//10. Apresente o top 10 de jogadores com mais cartões vermelhos e na posição em que jogam.";
        String query10 = "match (player)-[:PLAYS_IN]->(position) " +
                "return player.name as player, position.position as position, player.redCards as redCards " +
                "order by redCards desc " +
                "limit 10";

        queriesquestion.add(question10);
        queriescode.add(query10);

        writeOutput(queriesquestion, queriescode, session);
        // driver.close();
    }

    public static void writeOutput(List<String> questions, List<String> queries, Session session) throws Exception {

        try {
            PrintWriter pw = new PrintWriter(new File(output_file));

            int i = 0;
            for (String query : queries) {
                System.out.println(questions.get(i));
                pw.println(questions.get(i));
                Result result = session.run(query);
                // for (Result result : results) {
                // System.out.println(result);
                // pw.println(result);
                // }
                List<Record> records = result.list();
                for (Record record : records) {
                    System.out.println(record);
                    pw.println(record);
                }
                System.out.println();
                pw.println();
                i++;
            }

            Thread.sleep(3000); // 3 segundos

            pw.close();
            System.out.println("file written");
        } catch (IOException e) {
            System.out.println("Erro a escrever no ficheiro output! ");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
