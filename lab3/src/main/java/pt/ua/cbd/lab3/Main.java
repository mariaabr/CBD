package pt.ua.cbd.lab3;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

public class Main {
    public static void main(String[] args) {
        try (CqlSession session = CqlSession.builder().build()) {                                  // (1)
            ResultSet rs = session.execute("select release_version from system.local");              // (2)
            Row row = rs.one();
            System.out.println(row.getString("release_version"));                                    // (3)
            
            session.execute("use video_app;");
            
            System.out.println("------- alinea a) -------");
            alinea_a(session);
            System.out.println();
            System.out.println("------- alinea b) -------");
            alinea_b(session);
        }
    }

    public static void alinea_a(CqlSession session) {
        
        // insert data into users
        System.out.println("insert into users");
        // session.execute("delete from users where username = 'filsons'");
        session.execute("insert into users(username, name, email, ts) values ('filsons', 'Rafaela', 'filsons@ua.pt', toTimestamp(now()));");

        // insert data into videos
        System.out.println("insert into videos");
        // session.execute("delete from videos where id = 17");
        // session.execute("delete from videos_by_author where author = 'benny'");
        session.execute("insert into videos(id, author, video_name, description, tags, ts) values (17, 'benny', 'Os novos conceitos de hidráulica', 'bioengenharia não é pera doce', {'university', 'study'}, toTimestamp(now()));");
        session.execute("insert into videos_by_author(id, author, video_name, description, tags, ts) values (17, 'benny', 'Os novos conceitos de hidráulica', 'bioengenharia não é pera doce', {'university', 'study'}, toTimestamp(now()));");
        // session.execute("insert into videos_by_author(id, author, video_name, description, tags, ts) values (12, 'benny', 'World Padel Tour Show', 'o que acontece por detras dos jogos', {'sports', 'lifestyle'}, toTimestamp(now()))");
        
        // insert data into events
        System.out.println("insert into events");
        // session.execute("delete from events where video_id = 17 and username = 'freixinho'");
        // session.execute("delete from events where video_id = 17 and username = 'figas'");
        session.execute("insert into events(video_id, username, type, ts, moment) values (17, 'freixinho', 'play', toTimestamp(now()), 0);");
        session.execute("insert into events(video_id, username, type, ts, moment) values (17, 'figas', 'play', toTimestamp(now()), 0);");

        // update data in users
        System.out.println("update luisinha's name to 'Capucho'");
        session.execute("update users set name = 'Capucho' where username = 'luisinha';");
        
        // update data in followers
        System.out.println("update followers set for video 3");
        session.execute("update followers set users_follow = {'guisoscas', 'andra', 'filsons'} where video_id = 3;");
    
        // search users
        System.out.println("search user with username = 'filsons'");
        for(Row row: session.execute("select * from users where username = 'filsons';")){
            System.out.printf("{'username': %s, 'name': %s, 'email': %s, 'ts': %s}%n", row.getString("username"), row.getString("name"), row.getString("email"), row.getInstant("ts"));
        }

        // search videos by user
        System.out.println("search videos with author = 'benny'");
        for(Row row: session.execute("select * from videos_by_author where author = 'benny';")){
            System.out.printf("{'id': %s, 'author': %s, 'video_name': %s, 'description': %s, 'tags': %s, 'ts': %s}%n", String.valueOf(row.getInt("id")), row.getString("author"), row.getString("video_name"), row.getString("description"), row.getSet("tags", String.class), row.getInstant("ts"));
        }

        // search events
        System.out.println("search for all events");
        for(Row row: session.execute("select * from events;")){
            System.out.printf("{'video_id': %s, 'username': %s, 'type': %s, 'ts': %s, 'moment': %s}%n", String.valueOf(row.getInt("video_id")), row.getString("username"), row.getString("type"), row.getInstant("ts"), String.valueOf(row.getInt("moment")));
        }

        // search users' name
        System.out.println("search user's name with username = 'luisinha'");
        for(Row row: session.execute("select name from users where username = 'luisinha';")){
            System.out.printf("{'name': %s}%n", row.getString("name"));
        }

        // search followers
        System.out.println("search followers of video 3");
        for(Row row: session.execute("select * from followers where video_id = 3;")){
            System.out.printf("{'video_id': %s, 'user_follow': %s}%n", String.valueOf(row.getInt("video_id")), row.getSet("users_follow", String.class));
        }
    }

    public static void alinea_b(CqlSession session) {

        // query 1
        System.out.println("// 1. Os ultimos 3 comentarios introduzidos para um video;");
        for(Row row: session.execute("select * from comments_by_video where video_id = 4 order by ts desc limit 3;")){
            System.out.printf("{'id': %s, 'video_id': %s, 'author': %s, 'comment': %s, 'ts': %s}%n", String.valueOf(row.getInt("id")), String.valueOf(row.getInt("video_id")), row.getString("author"), row.getString("comment"), row.getInstant("ts"));
        }

        // query 2
        System.out.println("// 2. Lista das tags de determinado video;");
        for(Row row: session.execute("select * from videos where id = 15;")){
            System.out.printf("{'id': %s, 'tags': %s}%n", String.valueOf(row.getInt("id")), row.getSet("tags", String.class));
        }

        // query 4
        System.out.println("// 4. Os ultimos 5 eventos de determinado video realizados por um utilizador;");
        for(Row row: session.execute("select * from events where username = 'figas' and video_id = 5 order by ts desc limit 5;")){
            System.out.printf("{'video_id': %s, 'username': %s, 'type': %s, 'ts': %s, 'moment': %s}%n", String.valueOf(row.getInt("video_id")), row.getString("username"), row.getString("type"), row.getInstant("ts"), String.valueOf(row.getInt("moment")));
        }

        // query 10
        System.out.println("// 10. Uma query que retorne todos os videos e que mostre claramente a forma pela qual estao ordenados;");
        for(Row row: session.execute("select * from videos;")){
            System.out.printf("{'id': %s, 'author': %s, 'video_name': %s, 'description': %s, 'tags': %s, 'ts': %s}%n", String.valueOf(row.getInt("id")), row.getString("author"), row.getString("video_name"), row.getString("description"), row.getSet("tags", String.class), row.getInstant("ts"));
        } 
    }
}