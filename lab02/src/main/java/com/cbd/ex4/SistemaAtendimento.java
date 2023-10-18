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
import java.io.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Aggregates.group;

public class SistemaAtendimento {
    
    private MongoClient mongoClient;
    private static MongoDatabase database;
    private static int limit;
    private static int timeslot; // in seconds

    public SistemaAtendimento(int limit, int timeslot){ // constructor with limit of products and timeslot
        
        mongoClient = MongoClients.create();
        database = mongoClient.getDatabase("test");
        this.limit = limit; // limit of products
        this.timeslot = timeslot;
    }

    public static void registOrder(String username, String product){

        MongoCollection<Document> collection = database.getCollection("orders");
        Document doc = collection.find(eq("username", username)).first();

        if (doc == null) {
            doc = new Document("username", username)
                .append("products", Arrays.asList(product))
                .append("count", 1)
                .append("timestamp", LocalDateTime.now()); // add a timestamp field
            collection.insertOne(doc);
            System.out.println(product);
            System.out.println("Pedido atendido com sucesso!"); // success message
        } else {
            Date date = doc.getDate("timestamp");
            LocalDateTime timestamp = Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
            if (LocalDateTime.now().minusSeconds(timeslot).isBefore(timestamp)) {
                List<String> products = (List<String>) doc.get("products");
                int count = (int) doc.get("count");
    
                if (count < limit) { // verify that the number of products already ordered is less than the limit
                    products.add(product);
                    count++;
                    doc.put("products", products);
                    doc.put("count", count);
                    collection.replaceOne(Filters.eq("username", username), doc);
                    System.out.println("Pedido atendido com sucesso!"); // success message
                } else {
                    System.out.println("Pedimos desculpa mas o seu pedido foi rejeitado, excedeu o número máximo de produtos por unidade de tempo."); // error message
                }
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

        SistemaAtendimento atendimento = new SistemaAtendimento(5, 20); // comuns a todo o sistema

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

        deleteOrder(user1);
        deleteOrder(user2);
        username1.close();
        username2.close();
    }
}

