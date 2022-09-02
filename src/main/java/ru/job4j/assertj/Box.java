package ru.job4j.assertj;

/**
 * Класс реализует создание объекта, и подсчет его площади.
 *
 * @author Andrey Kireenkov
 * @version 1.0
 * @since 23.08.2022
 */
public class Box {
    public static final String UNKNOWN = "Unknown object";
    private int vertex;
    private final int edge;
    private String type = "";

    public Box(int vertex, int edge) {
        this.vertex = vertex;
        this.edge = edge;
        init();
    }

    /**
     * метод определяет объект (сфера, тетраэдр, куб), на основе количества вершин и ребер
     */
    private void init() {
        type = switch (vertex) {
            case 0 -> "Sphere";
            case 4 -> "Tetrahedron";
            case 8 -> "Cube";
            default -> UNKNOWN;
        };
        if (UNKNOWN.equals(type)) {
            vertex = -1;
        }
        if (edge <= 0) {
            vertex = -1;
            type = UNKNOWN;
        }
    }

    /**
     * @return возвращает название объекта
     */
    public String whatsThis() {
        return this.type;
    }

    public int getNumberOfVertices() {
        return this.vertex;
    }

    /**
     * метод работает с количеством вершин
     *
     * @return возвращает true если количество вершин =-1, т.е. тип объекта не определен
     */
    public boolean isExist() {
        return this.vertex != -1;
    }

    /**
     * метод подсчитывает площадь объекта
     *
     * @return в зависимости от типа объекта - сфера, тетраэдр, куб, возвращает его площадь
     */
    public double getArea() {
        double a = edge;
        return switch (vertex) {
            case 0 -> 4 * Math.PI * (a * a);
            case 4 -> Math.sqrt(3) * (a * a);
            case 8 -> 6 * (a * a);
            default -> 0;
        };
    }
}
