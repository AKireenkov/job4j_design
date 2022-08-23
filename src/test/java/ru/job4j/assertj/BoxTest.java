package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withinPercentage;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .containsIgnoringCase("sp");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube")
                .endsWith("be");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(0, 0);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object")
                .isNotEmpty()
                .isNotBlank();
    }

    @Test
    void isThisCubeArea() {
        Box box = new Box(8, 2);
        double area = box.getArea();
        assertThat(area).isGreaterThan(5.26d)
                .isCloseTo(24.0d, Percentage.withPercentage(0.01d));
    }

    @Test
    void isThisTetrahedronArea() {
        Box box = new Box(4, 5);
        double area = box.getArea();
        assertThat(area).isLessThan(100.0005d)
                .isCloseTo(43.0d, withinPercentage(1.5d));
    }
}