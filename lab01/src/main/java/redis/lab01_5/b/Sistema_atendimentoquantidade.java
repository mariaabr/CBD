package redis.lab01_5.b;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;
import java.util.*;
import java.util.Map.Entry;
import java.io.*;

public class Sistema_atendimentoquantidade {
    
    private static Jedis jedis;
    private static int limit;
    private static int timeslot; // in seconds

    public Sistema_atendimentoquantidade(int limit, int timeslot){ // constructor with limit of products and timeslot
        
        jedis = new Jedis();
        this.limit = limit; // limit of products
        this.timeslot = timeslot;
    }

    public static void registOrder(String username, String product, Integer quantity){
        // Transaction t = jedis.multi(); // starts a transaction block

        try{
            jedis.select(0);
            
            jedis.expire(username, Sistema_atendimentoquantidade.timeslot); // set a timeout on key

            // gets the number of products already ordered
            String productatual = jedis.hget(username, "products");
            int products = (productatual != null) ? Integer.parseInt(productatual) : 0;

            if (products < limit) { // verifiy that the number of products already ordered is less than the limit
                jedis.hincrBy(username, "products", quantity);
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

        Sistema_atendimentoquantidade atendimento = new Sistema_atendimentoquantidade(5, 20); // comuns a todo o sistema, testar com 5 e 20 para ser mais rápido
        
        Map<String, Integer> products_user1 = new HashMap<>();
        Map<String, Integer> products_user2 = new HashMap<>();

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
        products_user1.put("batatas", 3);
        products_user1.put("café", 1);
        products_user1.put("carne picada", 1);
        products_user1.put("gel de banho", 1);
        products_user1.put("chocolate", 1);

        // list order - user2
        products_user2.put("bolachas", 2);
        products_user2.put("compal de maçã", 1);
        products_user2.put("salmão", 1);
        products_user2.put("creme de corpo", 1);
        products_user2.put("arroz", 1);
        products_user2.put("chávena de chá", 2);

        // solicitar order - user1

        System.out.println("Utilizador 1");
        for(Entry<String, Integer> entry1 : products_user1.entrySet()){
            String product1 = entry1.getKey();
            Integer quantidade1 = entry1.getValue();
            registOrder(user1, product1, quantidade1);
        }

        // solicitar order - user2

        System.out.println();
        System.out.println("Utilizador 2");
        for(Entry<String, Integer> entry2 : products_user2.entrySet()){
            String product2 = entry2.getKey();
            Integer quantidade2 = entry2.getValue();
            registOrder(user2, product2, quantidade2);
        }

        jedis.del(user1);
        jedis.del(user2);
    }
}

