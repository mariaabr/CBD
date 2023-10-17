package com.cbd.ex3;

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

import java.util.*;
import java.io.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Aggregates.group;

public class Java_ex2 {

    public static void main(String[] args){

        String uri = "mongodb://127.0.0.1:27017/?directConnection=true&serverSelectionTimeoutMS=2000&appName=mongosh+2.0.1";

        try (MongoClient mongoClient = MongoClients.create(uri)) {

            MongoDatabase database = mongoClient.getDatabase("cbd");
            MongoCollection<Document> collection = database.getCollection("restaurants");
            
            System.out.println("Alínea c)");

            System.out.println("Query 3");
            execute_query3(collection);

            System.out.println("Query 9");
            execute_query9(collection);

            System.out.println("Query 11");
            execute_query11(collection);

            System.out.println("Query 20");
            execute_query20(collection);

            System.out.println("Query 23");
            execute_query23(collection);

        } catch (MongoException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public static void execute_query3(MongoCollection<Document> collection){

        // 3. Apresente os campos restaurant_id, nome, localidade e código postal (zipcode), mas exclua o campo _id de todos os documentos da coleção.
        
        FindIterable<Document> docs = collection.find().projection(Projections.fields(Projections.include("restaurant_id", "nome", "localidade", "address.zipcode"), Projections.exclude("_id")));

        for (Document doc : docs) {
            if (doc != null) {
                System.out.println(doc.toJson());
            } else {
                System.out.println("No matching documents found.");
            }
        }
    }

    public static void execute_query9(MongoCollection<Document> collection){
        
        // 9. Indique os restaurantes que não têm gastronomia "American", tiveram uma (ou mais) pontuação superior a 70 e estão numa latitude inferior a -65.
    
        FindIterable<Document> docs = collection.find(and(ne("gastronomia","American"), gt("grades.score", 70), lt("address.coord.0", -65))).projection(Projections.include("restaurant_id", "nome", "gastronomia", "grades.score", "address.coord"));

        for (Document doc : docs) {
            if (doc != null) {
                System.out.println(doc.toJson());
            } else {
                System.out.println("No matching documents found.");
            }
        }
    }

    public static void execute_query11(MongoCollection<Document> collection){
        
        // 11. Liste o nome, a localidade e a gastronomia dos restaurantes que pertencem ao Bronx e cuja gastronomia é do tipo "American" ou "Chinese".

        FindIterable<Document> docs = collection.find(and(eq("localidade","Bronx"), or(eq("gastronomia", "American"), eq("gastronomia", "Chinese")))).projection(Projections.include("restaurant_id", "nome", "gastronomia", "localidade"));

        for (Document doc : docs) {
            if (doc != null) {
                System.out.println(doc.toJson());
            } else {
                System.out.println("No matching documents found.");
            }
        }
    }
    
    public static void execute_query20(MongoCollection<Document> collection){
        
        // 20. Apresente o nome e número de avaliações (numGrades) dos 3 restaurante com mais avaliações.
            
        AggregateIterable<Document> docs = collection.aggregate(Arrays.asList(
            Aggregates.project(
                Projections.fields( 
                    Projections.include("nome"),
                    Projections.computed("numGrades", 
                        new Document("$cond", Arrays.asList(
                            new Document("$isArray", "$grades"),
                            new Document("$size", "$grades"), 0 ))))),
            Aggregates.sort(Sorts.descending("numGrades")),
            Aggregates.limit(3)));

        for (Document doc : docs) {
            if (doc != null) {
                System.out.println(doc.toJson());
            } else {
                System.out.println("No matching documents found.");
            }
        }
    }

    public static void execute_query23(MongoCollection<Document> collection){
        
        // 23. Indique os restaurantes que têm gastronomia "Portuguese", o somatório de score é superior a 50 e estão numa latitude inferior a -60.

        AggregateIterable<Document> docs = collection.aggregate(Arrays.asList(
            Aggregates.match(Filters.and(eq("gastronomia", "Portuguese"), lt("address.coord.0", -60))),
            Aggregates.unwind("$grades"),
            Aggregates.group("$nome", Accumulators.sum("sumScore", "$grades.score")),
            Aggregates.match(Filters.gt("sumScore", 50)),
            Aggregates.project(Projections.fields(Projections.include("nome"), Projections.include("gastronomia"), Projections.include("sumScore"), Projections.include("address.coord")))));

        for (Document doc : docs) {
            if (doc != null) {
                System.out.println(doc.toJson());
            } else {
                System.out.println("No matching documents found.");
            }
        }
    }
}
