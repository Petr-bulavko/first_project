package model;

public class Film {
    private int id;
    private String filmName;
    private int filmYear;
    private String filmGenre;
    private String filmCountry;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Film(String filmName, int filmYear, String filmGenre, String filmCountry) {
        this.filmName = filmName;
        this.filmYear = filmYear;
        this.filmGenre = filmGenre;
        this.filmCountry = filmCountry;
    }

    public Film(int id, int filmYear) {
        this.id = id;
        this.filmYear = filmYear;
    }

    public Film(int id, String filmName, int filmYear, String filmGenre, String filmCountry) {
        this.id = id;
        this.filmName = filmName;
        this.filmYear = filmYear;
        this.filmGenre = filmGenre;
        this.filmCountry = filmCountry;
    }

    public Film(int id, String filmName) {
        this.id = id;
        this.filmName = filmName;
    }

    public Film() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public int getFilmYear() {
        return filmYear;
    }

    public void setFilmYear(int filmYear) {
        this.filmYear = filmYear;
    }

    public String getFilmGenre() {
        return filmGenre;
    }

    public void setFilmGenre(String filmGenre) {
        this.filmGenre = filmGenre;
    }

    public String getFilmCountry() {
        return filmCountry;
    }

    public void setFilmCountry(String filmCountry) {
        this.filmCountry = filmCountry;
    }

}
