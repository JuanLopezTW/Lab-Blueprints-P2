CREATE TABLE IF NOT EXISTS blueprints (
                                          id BIGSERIAL PRIMARY KEY,
                                          author VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    CONSTRAINT uq_author_name UNIQUE (author, name)
    );

CREATE TABLE IF NOT EXISTS blueprint_points (
                                                blueprint_id BIGINT NOT NULL REFERENCES blueprints(id) ON DELETE CASCADE,
    point_order INTEGER NOT NULL,
    x INTEGER NOT NULL,
    y INTEGER NOT NULL,
    PRIMARY KEY (blueprint_id, point_order)
    );

INSERT INTO blueprints (author, name) VALUES
                                          ('john', 'house'),
                                          ('john', 'garage'),
                                          ('jane', 'garden');

INSERT INTO blueprint_points (blueprint_id, point_order, x, y)
SELECT id, 0, 0, 0 FROM blueprints WHERE author = 'john' AND name = 'house'
UNION ALL SELECT id, 1, 10, 0  FROM blueprints WHERE author = 'john' AND name = 'house'
UNION ALL SELECT id, 2, 10, 10 FROM blueprints WHERE author = 'john' AND name = 'house'
UNION ALL SELECT id, 3, 0, 10  FROM blueprints WHERE author = 'john' AND name = 'house'
UNION ALL SELECT id, 0, 5, 5   FROM blueprints WHERE author = 'john' AND name = 'garage'
UNION ALL SELECT id, 1, 15, 5  FROM blueprints WHERE author = 'john' AND name = 'garage'
UNION ALL SELECT id, 2, 15, 15 FROM blueprints WHERE author = 'john' AND name = 'garage'
UNION ALL SELECT id, 0, 2, 2   FROM blueprints WHERE author = 'jane' AND name = 'garden'
UNION ALL SELECT id, 1, 3, 4   FROM blueprints WHERE author = 'jane' AND name = 'garden'
UNION ALL SELECT id, 2, 6, 7   FROM blueprints WHERE author = 'jane' AND name = 'garden';