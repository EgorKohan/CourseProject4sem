package entity;

import java.io.Serializable;
import java.util.Objects;

public class Tractor implements Serializable {
    private final String name;
    private final PropulsionType propType;
    private final DestinationType destType;

    public Tractor(String name, PropulsionType propType, DestinationType destType) {
        this.name = name;
        this.propType = propType;
        this.destType = destType;
    }

    public enum PropulsionType implements Serializable {
        WHEELED, CRAWLER
    }

    public  enum DestinationType  implements Serializable{
        AGRICULTURAL, INDUSTRIAL, ARMY
    }

    public String getName() {
        return name;
    }

    public PropulsionType getPropType() {
        return propType;
    }

    public DestinationType getDestType() {
        return destType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tractor tractor = (Tractor) o;
        return Objects.equals(name, tractor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, propType, destType);
    }

    @Override
    public String toString() {
        return "Tractor{" +
                "name='" + name + '\'' +
                ", propType=" + propType +
                ", destType=" + destType +
                '}';
    }
}