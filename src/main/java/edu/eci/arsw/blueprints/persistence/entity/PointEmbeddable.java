package edu.eci.arsw.blueprints.persistence.entity;

import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PointEmbeddable {

    private int x;
    private int y;
}