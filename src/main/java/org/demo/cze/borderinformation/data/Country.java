package org.demo.cze.borderinformation.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
    /**
     * The ISO 3166-1 alpha-3 code for the country.
     */
    private String cca3;

    /**
     * An array of strings that represent the countries that border the current country.
     */
    private String[] borders;
}
