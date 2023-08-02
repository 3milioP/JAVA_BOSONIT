package com.ejercicio.backendFront.backendFront.Ticket.mongoConfig;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MongoConfig {
    private final ApplicationContext context;

    public MongoConfig(ApplicationContext context) {
        this.context = context;
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        MongoDatabaseFactory factory = new SimpleMongoClientDatabaseFactory(mongoClient(), databaseName());
        return new MongoTemplate(factory);
    }

    @Bean
    public MongoClient mongoClient() {
        String connectionString = "mongodb+srv://emilioparjona:emilioparjona@emilio.glj7trg.mongodb.net/emilio?retryWrites=true&w=majority";
        ConnectionString connString = new ConnectionString(connectionString);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .build();
        return MongoClients.create(settings);
    }

    @Bean
    public String databaseName() {
        MongoProperties properties = new MongoProperties();
        String databaseName = properties.getMongoClientDatabase();
        return databaseName != null ? databaseName : "emilio"; // Cambia "emilio" por el nombre de tu base de datos
    }
}
