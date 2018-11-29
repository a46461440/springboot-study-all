package com.zxc.normal.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author Zhou RunMing
 * @Date 2018-11-27
 */
public class Test {

    public static void main(String[] args) {
        Car car1 = Car.create(Car::new);
        Car car2 = Car.create(Car::new);
        List<Car> list = Arrays.asList(car1, car2);
        list.sort(Car::compareCar);
        list.forEach(e->System.out.println(e.hashCode()));
    }

    public static class Car {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static int compareCar(Car car1, Car car2) {
            return car2.hashCode() - car1.hashCode();
        }

        public static Car create( final Supplier< Car > supplier ) {
            return supplier.get();
        }

        public static void collide( final Car car ) {
            System.out.println( "Collided " + car.toString() );
        }

        public void follow( final Car another ) {
            System.out.println( "Following the " + another.toString() );
        }

        public void repair() {
            System.out.println( "Repaired " + this.toString() );
        }
    }

    @org.junit.Test
    public void testStream() {
        Car car1 = new Car();
        car1.setName("zxc");
        Car car2 = new Car();
        car2.setName("zxm");
        Collection<Car> list = Arrays.asList(car1, car2);
        list.stream()
                .filter(e -> e.getName().equals("zxc"))
        .forEach(e -> e.setName("ok"));
        System.out.println(car1.getName());
        System.out.println(car2.getName());
    }

}
