package org.example.streamCollectors;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.example.StringTools.log;

public class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Laptop", 1200.0),
                new Order("Smartphone", 800.0),
                new Order("Laptop", 1500.0),
                new Order("Tablet", 500.0),
                new Order("Smartphone", 900.0)
        );

        log("1) Создайте список заказов с разными продуктами и их стоимостями:");
        orders.forEach(System.out::println);
        log("");

        log("2) Группируйте заказы по продуктам:");
        log(orders.stream().collect(Collectors.groupingBy(Order::getProduct)) + "\n");

        log("3) Для каждого продукта найдите общую стоимость всех заказов:");
        log(orders.stream().collect(Collectors.groupingBy(Order::getProduct,
                Collectors.summingDouble(Order::getCost))) + "\n");

        log("4) Отсортируйте продукты по убыванию общей стоимости:");
        log(orders.stream().collect(Collectors.groupingBy(Order::getProduct, Collectors.summingDouble(Order::getCost)))
                .entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .toList() + "\n");

        log("5) Выберите три самых дорогих продукта:");
        List<Order> threeMaxCostOrders = orders.stream()
                .sorted(Comparator.comparingDouble(Order::getCost).reversed())
                .limit(3)
                .toList();
        log(threeMaxCostOrders + "\n");

        log("6) Выведите результат: список трех самых дорогих продуктов и их общая стоимость:");
        log(threeMaxCostOrders + " Общая стоимость: " + threeMaxCostOrders.stream().mapToDouble(Order::getCost).sum());
    }
}