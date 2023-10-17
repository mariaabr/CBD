package com.cbd.ex3;

import static com.mongodb.client.model.Filters.eq;

import org.bson.Document;

import com.mongodb.MongoException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BsonField;
import com.mongodb.client.model.Updates;
import com.mongodb.client.model.Projections;

import java.util.*;
import java.io.*;

public class Application { // alinea a)
    public static void main( String[] args ) {

        String uri = "mongodb://127.0.0.1:27017/?directConnection=true&serverSelectionTimeoutMS=2000&appName=mongosh+2.0.1";

        try (MongoClient mongoClient = MongoClients.create(uri)) {

            MongoDatabase database = mongoClient.getDatabase("cbd");
            MongoCollection<Document> collection = database.getCollection("restaurants");
            
            System.out.println("Alínea a)");
            
            // menu

            showMenu(collection);

        } catch (MongoException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public static void insertion(MongoCollection<Document> collection){

        System.out.println("INSERTION");

        Document doc = new Document("address", new Document("building", 1436)
                                                                .append("coord", Arrays.asList(41.0631172, -8.5735388))
                                                                .append("rua", "Avenida Dr. Moreira Sousa")
                                                                .append("zipcode", "4415-381"))
                        .append("localidade", "Pedroso")
                        .append("gastronomia", "Portuguese")
                        .append("grades", new Document("date", new Date (15102023))
                                                            .append("garde", "A")
                                                            .append("score", 237))
                        .append("nome", "Hemingway Bar Restaurante")
                        .append("restaurant_id", "27330190");


        collection.insertOne(doc);
    }

    public static void update(MongoCollection<Document> collection){

        System.out.println("UPDATE");

        // acabar

        collection.updateOne(eq("nome", "Hemingway Bar Restaurante"), Updates.set("localidade", "Carvalhos"));

    }

    public static void find(MongoCollection<Document> collection){

        System.out.println("SEARCH");

        FindIterable<Document> docs = collection.find(eq("gastronomia", "Portuguese"));

        for (Document doc : docs) {
            if (doc != null) {
                System.out.println(doc.toJson());
            } else {
                System.out.println("No matching documents found.");
            }
        }
    }

    public static void showMenu(MongoCollection<Document> collection){

        System.out.println("-----Menu-----");
        System.out.println("1. Insert data");
        System.out.println("2. Update data");
        System.out.println("3. Find gastronomia 'Portuguese'");
        System.out.println("4. Exit");

        System.out.print("option: ");
        Scanner sc = new Scanner(System.in);
        String op = sc.next();

        switch(op){

            case "1":
                insertion(collection);
                break;
            case "2":
                update(collection);
                break;
            case "3":
                find(collection);
                break;
            case "4":
                System.out.println("exit");
                System.exit(0);
            default:
                System.out.println("The option is not valid");
        }
        sc.close();
    }
}
