package com.cbd.ex4;

import org.bson.Document;
import org.bson.conversions.Bson;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.DistinctIterable;
import com.mongodb.client.FindIterable;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.BsonField;
import com.mongodb.client.model.Updates;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.DistinctIterable;
import java.time.*;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.io.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Aggregates.group;

public class SistemaAtendimento {
    
    private MongoClient mongoClient;
    private static MongoDatabase database;
    private static int limit;

    public SistemaAtendimento(int limit){ // constructor with limit of products and timeslot
        
        mongoClient = MongoClients.create();
        database = mongoClient.getDatabase("test");
        this.limit = limit; // limit of products
    }

    public static void checkProductLifetime(){  
        // System.out.println("check do tempo de vida");

        MongoCollection<Document> collection = database.getCollection("orders");
        
        FindIterable<Document> iterable = collection.find();
        List<Document> docs = new ArrayList<>();

        for (Document doc : iterable) {
            docs.add(doc);
        }

        for (Document doc : docs) {
                String username = doc.get("username", String.class);
                List<Document> products = (List<Document>) doc.get("products", List.class);
                Integer count = doc.get("count", Integer.class);

            for (Document productDoc : products) {
                Long lifetime = (Long) productDoc.get("lifetime");
                String product = (String) productDoc.get("product");
                
                long lifetimeplusseconds = lifetime + 30000;
                // System.out.println(lifetimeplusseconds);
                // System.out.println(System.currentTimeMillis());

                if (lifetimeplusseconds < System.currentTimeMillis()) { // Se o tempo de vida do produto expirou
                    
                    System.out.println("o meu tempo de vida acabou " + product);

                    // System.out.println("doc antes delete: " + products);
                    // collection.deleteOne(eq("products.product", product));
                    // System.out.println("doc depois delete: " + collection.find(eq("products")).toString());

                    // System.out.println("doc antes delete: " + products);

                    // identifica o documento especifico
                    Bson filter = Filters.eq("username", username);

                    // $pull para remover o produto da lista de produtos
                    Bson update = Updates.pull("products", new Document("product", product));
                    Bson updatecount = Updates.set("count", count - 1);

                    // Execute a exclusão no banco de dados
                    collection.updateOne(filter, update);
                    collection.updateOne(filter, updatecount);

                    // String product = doc.getString("products.product");
                    // System.out.println("o meu tempo de vida acabou");
                }
            }
        }
    }

    public static void registOrder(String username, String product, Integer timestamp){

        MongoCollection<Document> collection = database.getCollection("orders");
        Document doc = collection.find(eq("username", username)).first();

        if (doc == null) {
            doc = new Document("username", username)
                .append("products", Arrays.asList(new Document("product", product).append("lifetime", System.currentTimeMillis())))
                .append("count", 1);
            collection.insertOne(doc);
            // System.out.println(doc);


            // List<Document> products = (List<Document>) doc.get("products", List.class);
            // for (Document productDoc : products) {
            //     if (productDoc.get("product") == product){
            //         Long lifetime = (Long) productDoc.get("lifetime");
            //         // System.out.println("Lifetime do product " + product + ": " + new Date(lifetime));
            //     }
            // }

            System.out.println("Pedido atendido com sucesso!"); // success message
        } else {
            List<Document> products = (List<Document>) doc.get("products");
            int count = (int) doc.get("count");
            // System.out.println(count);
            if (count < limit) { // verify that the number of products already ordered is less than the limit
                products.add(new Document("product", product).append("lifetime", System.currentTimeMillis()));
                count++;
                doc.put("products", products);
                doc.put("count", count);
                collection.replaceOne(Filters.eq("username", username), doc);
                System.out.println("Pedido atendido com sucesso!"); // success message
            } else {
                System.out.println("Pedimos desculpa mas o seu pedido foi rejeitado, excedeu o número máximo de produtos por unidade de tempo."); // error message
            }
        }
    }

    public static void deleteOrder(String username) {
        MongoCollection<Document> collection = database.getCollection("orders");
        collection.deleteOne(Filters.eq("username", username));
    }

    public static void main(String[] args){

        SistemaAtendimento atendimento = new SistemaAtendimento(5); // comuns a todo o sistema

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

        // list order - user1
        products_user1.put("batata", 30);
        products_user1.put("café", 30);
        products_user1.put("carne picada", 30);
        products_user1.put("gel de banho", 30);
        products_user1.put("chocolate", 30);

        // list order - user2
        products_user2.put("bolachas", 30);
        products_user2.put("compal de maçã", 30);
        products_user2.put("salmão", 30);
        products_user2.put("creme de corpo", 30);
        products_user2.put("arroz", 30);
        products_user2.put("chávena de chá", 30);
        products_user2.put("chávena de café", 30);

        // solicitar order - user1

        System.out.println("Utilizador 1");
        for(Map.Entry<String, Integer> order1 : products_user1.entrySet()){
            String product1 = order1.getKey();
            Integer timestamp1 = order1.getValue();
            registOrder(user1, product1, timestamp1);
            try {
                Thread.sleep(5000); // Pausa por 5 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            checkProductLifetime();
        }

        // solicitar order - user2

        System.out.println();
        System.out.println("Utilizador 2");
        for(Map.Entry<String, Integer> order2 : products_user2.entrySet()){
            String product2 = order2.getKey();
            Integer timestamp2 = order2.getValue();
            registOrder(user2, product2, timestamp2);
            try {
                Thread.sleep(5000); // Pausa por 5 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            checkProductLifetime();
        }

        deleteOrder(user1);
        deleteOrder(user2);
        username1.close();
        username2.close();
    }
}