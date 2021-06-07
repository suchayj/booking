package com.maersk.booking.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Table("available")
public class Available {

    @Id
    @PrimaryKey
    private Integer id;

    @Column("size")
    private Integer size;

    @Column("type")
    private String type;

    @Column("available_space")
    private Integer availableSpace;

}
