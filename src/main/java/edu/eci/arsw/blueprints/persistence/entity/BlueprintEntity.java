package edu.eci.arsw.blueprints.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "blueprints", uniqueConstraints = @UniqueConstraint(columnNames = {"author", "name"}))
public class BlueprintEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "blueprint_points", joinColumns = @JoinColumn(name = "blueprint_id"))
    @OrderColumn(name = "point_order")
    private List<PointEmbeddable> points = new ArrayList<>();

    public BlueprintEntity(String author, String name, List<PointEmbeddable> points) {
        this.author = author;
        this.name = name;
        if (points != null) this.points = new ArrayList<>(points);
    }

    public void addPoint(PointEmbeddable p) {
        points.add(p);
    }
}
