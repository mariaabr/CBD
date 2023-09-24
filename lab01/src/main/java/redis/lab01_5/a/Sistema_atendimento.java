package redis.lab01_5.a;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import java.util.*;
import java.io.*;

public class Sistema_atendimento {
    
    private static Jedis jedis;
    private static int limit;
    private static int timeslot; // in seconds

    public Sistema_atendimento(int limit, int timeslot){ // constructor with limit of products and timeslot
        
        jedis = new Jedis();
        this.limit = limit; // limit of products
        this.timeslot = timeslot;
    }

    public static void registOrder(String username, String product){
        // Transaction t = jedis.multi(); // starts a transaction block

        try{
            jedis.select(0);
            
            jedis.expire(username, Sistema_atendimento.timeslot); // set a timeout on key

            // gets the number of products already ordered
            String productatual = jedis.hget(username, "products");
            int products = (productatual != null) ? Integer.parseInt(productatual) : 0;

            if (products < limit) { // verifiy that the number of products already ordered is less than the limit
                jedis.hincrBy(username, "products", 1);
                jedis.hset(username, "product" + products, product);
                // t.exec(); // execute transaction on redis
                System.out.println("Pedido atendido com sucesso!"); // success message
            } else {
                // t.discard(); // discard the transaction, reached limit
                System.out.println("Pedimos desculpa mas o seu pedido foi rejeitado, excedeu o numéro máximo de produtos por unidade de tempo."); // error message
            }
        } finally {
            jedis.close();
        }
    }

    public static void main(String[] args){

        Sistema_atendimento atendimento = new Sistema_atendimento(5, 20); // comuns a todo o sistema
        
        ArrayList<String> products_user1 = new ArrayList<String>();
        ArrayList<String> products_user2 = new ArrayList<String>();

        System.out.println("Registo de pedidos");
        System.out.println("------------------");

        System.out.print("Utilizador1: ");
        Scanner username1 = new Scanner(System.in);
        String user1 = username1.nextLine();

        System.out.print("Utilizador2: ");
        Scanner username2 = new Scanner(System.in);
        String user2 = username2.nextLine();
        username2.close();

        // list order - user1
        products_user1.add("batata");
        products_user1.add("café");
        products_user1.add("carne picada");
        products_user1.add("gel de banho");
        products_user1.add("chocolate");

        // list order - user2
        products_user2.add("bolachas");
        products_user2.add("compal de maçã");
        products_user2.add("salmão");
        products_user2.add("creme de corpo");
        products_user2.add("arroz");
        products_user2.add("chávena de chá");

        // solicitar order - user1

        System.out.println("Utilizador 1");
        for(String product1 : products_user1){
            registOrder(user1, product1);
        }

        // solicitar order - user2

        System.out.println();
        System.out.println("Utilizador 2");
        for(String product2 : products_user2){
            registOrder(user2, product2);
        }

        jedis.del(user1);
        jedis.del(user2);
    }
}
