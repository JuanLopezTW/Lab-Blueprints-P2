package edu.eci.arsw.blueprints.persistence.mapper;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.entity.BlueprintEntity;
import edu.eci.arsw.blueprints.persistence.entity.PointEmbeddable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BlueprintMapper {

    public BlueprintEntity toEntity(Blueprint bp) {
        List<PointEmbeddable> points = bp.getPoints().stream()
                .map(p -> new PointEmbeddable(p.x(), p.y()))
                .collect(Collectors.toList());
        return new BlueprintEntity(bp.getAuthor(), bp.getName(), points);
    }

    public Blueprint toDomain(BlueprintEntity entity) {
        List<Point> points = entity.getPoints().stream()
                .map(p -> new Point(p.getX(), p.getY()))
                .collect(Collectors.toList());
        return new Blueprint(entity.getAuthor(), entity.getName(), points);
    }
}
