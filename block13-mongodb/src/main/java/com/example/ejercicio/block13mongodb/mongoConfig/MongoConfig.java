package com.example.ejercicio.block13mongodb.mongoConfig;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

//@Configuration
//public class MongoConfig {
//    @Bean
//    public MongoTemplate mongoTemplate() {
//        SimpleMongoClientDatabaseFactory dbFactory = new SimpleMongoClientDatabaseFactory("mongodb+srv://emilioparjona:emilioparjona@emilio.glj7trg.mongodb.net/");
//        MappingMongoConverter converter = new MappingMongoConverter(dbFactory, new MongoMappingContext());
//        converter.afterPropertiesSet();
//        return new MongoTemplate(dbFactory, converter);
//    }
//}


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
