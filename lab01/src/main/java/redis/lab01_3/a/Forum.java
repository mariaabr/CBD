package redis.lab01_3.a;
import redis.clients.jedis.Jedis;

public class Forum {
    public static void main( String[] args )
    {
        Jedis jedis = new Jedis();
        System.out.println( jedis.ping() );
        System.out.println( jedis.info() );
        jedis.close();
    }
}

