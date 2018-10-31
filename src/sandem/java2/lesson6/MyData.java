package sandem.java2.lesson6;

import java.util.Objects;

public class MyData {

    private final int id;
    private final String name;

    public MyData(int id) {
        this.id = id;
        this.name = "It's element with id = " + id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "MyData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyData data = (MyData) o;
        return id == data.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
