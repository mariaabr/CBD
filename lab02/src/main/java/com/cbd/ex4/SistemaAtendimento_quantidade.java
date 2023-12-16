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

public class SistemaAtendimento_quantidade {
    
    private MongoClient mongoClient;
    private static MongoDatabase database;
    private static int limit;

    public SistemaAtendimento_quantidade(int limit){ // constructor with limit of products and timeslot
        
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
                Integer quantidade = (Integer) productDoc.get("quantidade");
                // Date lifetimedate = new Date(lifetime);
                
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
                    Bson updatecount = Updates.set("count", count - quantidade);

                    // Execute a exclusão no banco de dados
                    collection.updateOne(filter, update);
                    collection.updateOne(filter, updatecount);

                    // System.out.println("doc antes delete: " + update);

                    // doc.put("count", doc.getInteger("count") - quantidade);
                    // System.out.println("update " + doc);
                }
            }
        }
    }

    public static void registOrder(String username, String product, Integer quantidade, Integer timestamp){

        MongoCollection<Document> collection = database.getCollection("orders");
        Document doc = collection.find(eq("username", username)).first();

        if (doc == null) {
            doc = new Document("username", username)
                .append("products", Arrays.asList(new Document("product", product).append("quantidade", quantidade).append("lifetime", System.currentTimeMillis())))
                .append("count", quantidade);
            collection.insertOne(doc);
            System.out.println("inicio " + doc);

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

            if (count < limit && count + quantidade <= limit) { // verify that the number of products already ordered is less than the limit
                products.add(new Document("product", product).append("quantidade", quantidade).append("lifetime", System.currentTimeMillis()));
                count = count + quantidade;
                doc.put("products", products);
                doc.put("count", count);
                collection.replaceOne(Filters.eq("username", username), doc);
                System.out.println("a seguir " + doc);
                System.out.println();
                System.out.println("Pedido atendido com sucesso!"); // success message
            } else {
                System.out.println("a seguir nao regist" + doc);
                System.out.println();
                System.out.println("Pedimos desculpa mas o seu pedido foi rejeitado, excedeu o número máximo de produtos por unidade de tempo."); // error message
            }
        }
    }

    public static void deleteOrder(String username) {
        MongoCollection<Document> collection = database.getCollection("orders");
        collection.deleteOne(Filters.eq("username", username));
    }

    public static void main(String[] args){

        SistemaAtendimento_quantidade atendimento = new SistemaAtendimento_quantidade(5); // comuns a todo o sistema

        Map<Map<String, Integer>, Integer> products_user1 = new HashMap<>();
        Map<Map<String, Integer>, Integer> products_user2 = new HashMap<>();

        System.out.println("Registo de pedidos");
        System.out.println("------------------");

        System.out.print("Utilizador1: ");
        Scanner username1 = new Scanner(System.in);
        String user1 = username1.nextLine();

        System.out.print("Utilizador2: ");
        Scanner username2 = new Scanner(System.in);
        String user2 = username2.nextLine();

        // list order - user1
        Map<String, Integer> prod11 = new HashMap<>();
        prod11.put("batatas", 3);
        products_user1.put(prod11, 30);
        Map<String, Integer> prod12 = new HashMap<>();
        prod12.put("café", 1);
        products_user1.put(prod12, 30);
        Map<String, Integer> prod13 = new HashMap<>();
        prod13.put("carne picada", 1);
        products_user1.put(prod13, 30);
        Map<String, Integer> prod14 = new HashMap<>();
        prod14.put("gel de banho", 1);
        products_user1.put(prod14, 30);
        Map<String, Integer> prod15 = new HashMap<>();
        prod15.put("chocolate", 1);
        products_user1.put(prod15, 30);

        // list order - user2
        Map<String, Integer> prod21 = new HashMap<>();
        prod21.put("bolachas", 2);
        products_user2.put(prod21, 30);
        Map<String, Integer> prod22 = new HashMap<>();
        prod22.put("compal de maçã", 1);
        products_user2.put(prod22, 30);
        Map<String, Integer> prod23 = new HashMap<>();
        prod23.put("salmão", 1);
        products_user2.put(prod23, 30);
        Map<String, Integer> prod24 = new HashMap<>();
        prod24.put("creme de corpo", 1);
        products_user2.put(prod24, 30);
        Map<String, Integer> prod25 = new HashMap<>();
        prod25.put("arroz", 1);
        products_user2.put(prod25, 30);
        Map<String, Integer> prod26 = new HashMap<>();
        prod26.put("chávena de chá", 1);
        products_user2.put(prod26, 30);
        Map<String, Integer> prod27 = new HashMap<>();
        prod27.put("chávena de café", 2);
        products_user2.put(prod27, 30);
        Map<String, Integer> prod28 = new HashMap<>();
        prod28.put("leite", 1);
        products_user2.put(prod28, 30);
        Map<String, Integer> prod29 = new HashMap<>();
        prod29.put("pêras", 2);
        products_user2.put(prod29, 30);

        // solicitar order - user1

        System.out.println("Utilizador 1");

        String product1 = "";
        Integer quantidade1 = 0;
        
        for(Map.Entry<Map<String, Integer>, Integer> order1 : products_user1.entrySet()){
            Map<String, Integer> product1_map = order1.getKey();
            Integer timestamp1 = order1.getValue();

            for(Map.Entry<String, Integer> product1_entry : product1_map.entrySet()){
                product1 = product1_entry.getKey();
                quantidade1 = product1_entry.getValue();
            }
            
            registOrder(user1, product1, quantidade1, timestamp1);
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

        String product2 = "";
        Integer quantidade2 = 0;

        for(Map.Entry<Map<String, Integer>, Integer> order2 : products_user2.entrySet()){
            Map<String, Integer> product2_map = order2.getKey();
            Integer timestamp2 = order2.getValue();

            for(Map.Entry<String, Integer> product2_entry : product2_map.entrySet()){
                product2 = product2_entry.getKey();
                quantidade2 = product2_entry.getValue();
            }
            
            checkProductLifetime();
            System.out.println("try regist " + product2);
            registOrder(user2, product2, quantidade2, timestamp2);

            try {
                Thread.sleep(5000); // Pausa por 5 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        deleteOrder(user1);
        deleteOrder(user2);
        username1.close();
        username2.close();
    }
}