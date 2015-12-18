package com.thinkdevs.sendocs.service;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for get all orders name, usually for first launch application
 *
 * @author Maxim Tikhanovskiy
 */
public class DataBaseService {

    private static String repositoryOrders = "\\\\192.168.0.6\\KDProduction\\";
    private static Path path = Paths.get(repositoryOrders);

    private static List<String> getOrdersList(Path path) {
        List<String> ordersList = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path)) {
            for (Path entry : directoryStream) {
                BasicFileAttributes attributes = Files.readAttributes(entry, BasicFileAttributes.class);
                if (attributes.isDirectory()) {
                    ordersList.add(entry.getFileName().toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    /**
     * Get all orders numbers from names folder from repositoryOrders.
     * if folder name not numeric, then this order will be skip.
     *
     * @return List all orders
     */
    public static List<Integer> getOrdersNumbers() {
        List<Integer> ordersNumbers = new ArrayList<>();
        for (String number : getOrdersList(path)) {
            try {
                int num = Integer.parseInt(number);
                ordersNumbers.add(num);
            } catch (NumberFormatException ignore) {
            }
        }
        return ordersNumbers;
    }

}
