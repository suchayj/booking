package com.maersk.booking.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;

@Configuration
@EnableReactiveCassandraRepositories
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Value("${spring.data.cassandra.keyspaceName}")
    private String keySpace;

    protected String getKeyspaceName() {
        return keySpace;
    }

    @Value("${spring.data.cassandra.contactPoints}")
    private String contactPoints;

    @Override
    protected String getContactPoints() {
        return contactPoints;
    }

    @Value("${spring.data.cassandra.port}")
    private Integer port;

    @Override
    protected int getPort() {
        return port;
    }

    @Value("${spring.data.cassandra.schemaAction}")
    private String schemaAction;

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.valueOf(schemaAction);
    }

}
