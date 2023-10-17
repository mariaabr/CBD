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
import com.mongodb.client.DistinctIterable;

import java.util.*;
import java.io.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Aggregates.group;

public class Methods {

    public static MongoCollection<Document> collection;
    public static void main(String[] args){
        String uri = "mongodb://127.0.0.1:27017/?directConnection=true&serverSelectionTimeoutMS=2000&appName=mongosh+2.0.1";

        try (MongoClient mongoClient = MongoClients.create(uri)) {

            MongoDatabase database = mongoClient.getDatabase("cbd");
            collection = database.getCollection("restaurants");

            System.out.println("Alínea d)");

            System.out.print("Número de localidades distintas: ");
            int count = countLocalidades();
            System.out.println(count);

            System.out.println("Número de restaurantes por localidade:");
            Map<String, Integer> restaurants_localidade = countRestByLocalidade();

            for(Map.Entry<String, Integer> entry: restaurants_localidade.entrySet()) {
                System.out.println("-> " + entry.getKey() + " - " + entry.getValue());
            }

            System.out.println("Nome dos restaurantes que contenham 'Park' no nome:");
            List<String> restaurants = getRestWithNameCloserTo("Park");

            for(String restaurant: restaurants){
                System.out.println("-> " + restaurant);
            }

        } catch (MongoException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    // methods

    public static int countLocalidades(){ // numero de localidades distintas

        int count = 0;

        DistinctIterable<String> distinct_cities = collection.distinct("localidade", String.class);

        for (String city: distinct_cities){
            // System.out.println(city);
            count++;
        }

        return count;
    }

    public static Map<String, Integer> countRestByLocalidade(){ // numero de restaurantes por localidade

        Map<String, Integer> restaurants_localidade = new HashMap();
        
        AggregateIterable<Document> docs = collection.aggregate(Arrays.asList(Aggregates.group("$localidade", Accumulators.sum("restaurants", 1))));

        for (Document doc: docs){
            // System.out.println(doc);
            restaurants_localidade.put(doc.getString("_id"), doc.getInteger("restaurants"));
        }

        return restaurants_localidade;
    }

    public static List<String> getRestWithNameCloserTo(String name){  // nome de restaurantes contendo 'name' do nome

        List<String> restaurants = new ArrayList<>();
        
        String regex = ".*" + name + ".*";
        FindIterable<Document> docs = collection.find(regex("nome", regex));

        for(Document doc : docs){
            restaurants.add(doc.getString("nome"));
        }

        return restaurants;
    }


}
