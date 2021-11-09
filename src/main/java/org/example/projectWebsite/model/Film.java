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
    private Long filmId;
    private String filmName;
    private String filmYear;
    private String filmGenre;
    private String filmCountry;
    private String description;
    private String img;
    private String linkMovie;
}
