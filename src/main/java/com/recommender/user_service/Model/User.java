package com.recommender.user_service.Model;

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

    @ElementCollection(targetClass = ESize.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "style")
    private List<EStyle> styles;

    @ElementCollection(targetClass = EStyle.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "size")
    private List<ESize> sizes;
}
