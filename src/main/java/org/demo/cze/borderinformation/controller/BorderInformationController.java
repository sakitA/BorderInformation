package org.demo.cze.borderinformation.controller;

import org.demo.cze.borderinformation.service.BorderInformationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BorderInformationController {

    /**
     * Inject the {@link BorderInformationService}.
     */
    private final BorderInformationService borderInformationService;

    public BorderInformationController(BorderInformationService borderInformationService) {
        this.borderInformationService = borderInformationService;
    }


    @GetMapping("/routing/{origin}/{destination}")
    public ResponseEntity<List<String>> routing(@PathVariable String origin, @PathVariable String destination) {
        // Find the route.
        List<String> routes = borderInformationService.getBorderInfo(origin, destination);

        // Return a bad request response if no route exists as per the requirement, otherwise return the discovered route.
        return routes.isEmpty() ? ResponseEntity.badRequest().build() : ResponseEntity.ok(routes);
    }
}
