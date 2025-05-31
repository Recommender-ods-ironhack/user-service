package com.recommender.user_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection(targetClass = EStyle.class)
    @CollectionTable(
            name = "user_styles",
            joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "style"})
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "style")
    private List<EStyle> styles;

    @ElementCollection(targetClass = ESize.class)
    @CollectionTable(
            name = "user_sizes",
            joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "size"})
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "size")
    private List<ESize> sizes;
}
