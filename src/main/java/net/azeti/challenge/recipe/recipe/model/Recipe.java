package net.azeti.challenge.recipe.recipe.model;

import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotEmpty;

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
    @NotEmpty
    private String title;
    private String username;
    @Column(length = 4000)
    private String description;
    @NotEmpty
    @Column(length = 4000)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "recipe")
    private List<Ingredients> ingredients;
    @NotEmpty
    @Column(length = 8000)
    private String instructions;
    private int servings;
}
