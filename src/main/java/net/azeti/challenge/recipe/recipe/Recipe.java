package net.azeti.challenge.recipe.recipe;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String username;
    @Column(length = 4000)
    private String description;
    @Column(length = 4000)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipe")
    private List<Ingredients> ingredients;
    @Column(length = 8000)
    private String instructions;
    private int servings;
}
