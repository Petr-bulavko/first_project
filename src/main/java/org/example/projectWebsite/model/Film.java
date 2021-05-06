package org.example.projectWebsite.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class Film implements Entity{
    private int id;
    private String filmName;
    private int filmYear;
    private String filmGenre;
    private String filmCountry;
    private String description;
}
