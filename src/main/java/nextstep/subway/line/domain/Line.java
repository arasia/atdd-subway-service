package nextstep.subway.line.domain;

import nextstep.subway.BaseEntity;
import nextstep.subway.station.domain.Station;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Line extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private String color;

    @Column
    private int additionalPrice;

    @Embedded
    private final Sections sections = new Sections();

    public Line() {
    }

    private Line(String name, String color, int additionalPrice) {
        this.name = name;
        this.color = color;
        this.additionalPrice = additionalPrice;
    }

    public static Line of(String name, String color) {
        return new Line(name, color, 0);
    }

    public static Line of(String name, String color, int additionalPrice) {
        return new Line(name, color, additionalPrice);
    }

    public static Line of(String name, String color, Station upStation, Station downStation, int distance) {
        Line line = new Line(name, color, 0);
        line.addSection(upStation, downStation, distance);

        return line;
    }

    public static Line of(String name, String color, int additionalPrice, Station upStation, Station downStation, int distance) {
        Line line = new Line(name, color, additionalPrice);
        line.addSection(upStation, downStation, distance);

        return line;
    }

    public void update(Line line) {
        this.name = line.getName();
        this.color = line.getColor();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getAdditionalPrice() {
        return additionalPrice;
    }

    public List<Station> getOrderedStations() {
        return sections.getOrderedStations();
    }

    public void addSection(Station upStation, Station downStation, int distance) {
        sections.addSection(this, upStation, downStation, distance);
    }

    public void removeStation(Station station) {
        sections.removeStation(this, station);
    }
}
