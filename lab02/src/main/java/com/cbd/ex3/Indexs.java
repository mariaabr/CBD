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
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Indexes;

import java.util.*;
import java.io.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Aggregates.group;

public class Indexs {
    
    public static void main( String[] args ) {

        String uri = "mongodb://127.0.0.1:27017/?directConnection=true&serverSelectionTimeoutMS=2000&appName=mongosh+2.0.1";

        try (MongoClient mongoClient = MongoClients.create(uri)) {

            MongoDatabase database = mongoClient.getDatabase("cbd");
            MongoCollection<Document> collection = database.getCollection("restaurants");
            
            System.out.println("Alínea b)");

            // find sem indexs
            System.out.println("Find sem indexes");

            long start_time = System.currentTimeMillis();

            FindIterable<Document> docs = collection.find(and(eq("gastronomia", "Italian"), eq("localidade", "Bronx"))).projection(Projections.include("gastronomia", "localidade"));

            for (Document doc : docs) {
                if (doc != null) {
                    System.out.println(doc.toJson());
                } else {
                    System.out.println("No matching documents found.");
                }
            }

            long end_time = System.currentTimeMillis();
            long time_sem_indexs = end_time - start_time;

            // indexs

            collection.createIndex(Indexes.ascending("gastronomia")); // indice gastronomia

            collection.createIndex(Indexes.ascending("localidade")); // indice localidade

            collection.createIndex(Indexes.text("nome")); // indice texto nome

            // find com idexs
            System.out.println("Find com indexes");

            long start_time_index = System.currentTimeMillis();

            FindIterable<Document> docsindexs = collection.find(and(eq("gastronomia", "Italian"), eq("localidade", "Manhattan"))).projection(Projections.include("gastronomia", "localidade"));

            for (Document docindex : docsindexs) {
                if (docindex != null) {
                    System.out.println(docindex.toJson());
                } else {
                    System.out.println("No matching documents found.");
                }
            }

            long end_time_index = System.currentTimeMillis();
            long time_indexs = end_time_index - start_time_index;

            System.out.println("Comparação de resultados:");

            System.out.printf("Tempo de pesquisa sem índices %d\n", time_sem_indexs);
            System.out.printf("Tempo de pesquisa com índices %d\n", time_indexs);

        } catch (MongoException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}