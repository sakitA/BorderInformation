package org.demo.cze.borderinformation.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.demo.cze.borderinformation.data.Country;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BorderInformationService {
    private Map<String, Set<String>> borderInformation;

    public BorderInformationService() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // reads the countries.json file from the resources folder and converts it into an array of Country objects.
            Country[] countries = objectMapper.readValue(this.getClass().getResource("/countries.json"), Country[].class);

            // filters out countries without neighbors, and creates a map where each country's code is the key and its
            // neighbors' codes are stored in a set. Set will help fast lookup.
            this.borderInformation = Arrays.stream(countries)
                    .filter( country -> country.getBorders().length > 0)
                    .collect(Collectors.toMap(Country::getCca3, country -> new HashSet<>(Arrays.asList(country.getBorders()))));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Find route information between the origin and destination cities.
     * @param origin the country code of the origin country.
     * @param destination the country code of the destination country.
     * @return a list representing a route between the two cities
     */
    public List<String> getBorderInfo(String origin, String destination) {
        // Handle case-insensitive concerns.
        origin = origin.toUpperCase();
        destination = destination.toUpperCase();

        // Regarding the task description, it lacks clarity about the expected outcome when the origin and destination
        // cities are the same. In this context, I am assuming that the result should be the origin city itself,
        // implying that there is no need to exit the country.
        if (origin.equals(destination)) {
            return Collections.singletonList(origin);
        }

        // If either the origin or destination city lacks neighboring cities, it indicates that there is no route
        // between the two cities.
        if (!this.borderInformation.containsKey(origin) || !this.borderInformation.containsKey(destination)) {
            return Collections.emptyList();
        }

        // A breadth-first search algorithm (BFS) to find a route between the cities using their border connections.
        Queue<String> queue = new LinkedList<>();
        queue.offer(destination);

        // Keeping track of city-to-neighbor paths.
        Map<String, String> routes = new HashMap<>();

        // Saving processed cities to avoid redundant processing.
        Set<String> seen = new HashSet<>();
        seen.add(destination);

        while (!queue.isEmpty()) {
            String currentCity = queue.poll(); // Retrieve the first city from the queue.

            for (String neighbor : this.borderInformation.get(currentCity)) {
                // If the city hasn't been processed, add it to the queue.
                if (!seen.contains(neighbor)) {
                    seen.add(neighbor);

                    // Enqueue for processing.
                    queue.offer(neighbor);

                    routes.put(neighbor, currentCity);

                    // If found, construct the route and return the result.
                    if (neighbor.equals(origin)) {
                        return this.buildRoute(routes, neighbor);
                    }
                }
            }
        }

        return Collections.emptyList();
    }

    /**
     * Constructs a route based on a given map of routes and a starting point (neighbor).
     * @param routes a map where keys are cities, and values are their corresponding parent cities.
     * @param neighbor the starting point for building the route.
     * @return A list representing the route from origin to destination.
     */
    private List<String> buildRoute(Map<String, String> routes, String neighbor) {
        List<String> reversedRoute = new ArrayList<>();
        while (neighbor != null) {
            reversedRoute.add(neighbor);
            neighbor = routes.get(neighbor);
        }
        return reversedRoute;
    }
}
