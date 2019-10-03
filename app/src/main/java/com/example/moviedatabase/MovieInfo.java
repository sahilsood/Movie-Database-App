package com.example.moviedatabase;

import java.io.Serializable;
import java.util.Comparator;

public class MovieInfo implements Serializable {
    String name, desc, genre, imdb;
    int year, rating;

    public MovieInfo(String name, String desc, String genre, int year, int rating, String imdb) {
        this.name = name;
        this.desc = desc;
        this.genre = genre;
        this.year = year;
        this.rating = rating;
        this.imdb = imdb;
    }

    @Override
    public String toString() {
        return "MovieInfo{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", imdb=" + imdb +
                '}';
    }

    public static Comparator<MovieInfo> yearSort = new Comparator<MovieInfo>() {

        public int compare(MovieInfo y1, MovieInfo y2) {

            int year1 = y1.year;
            int year2 = y2.year;

            /*For ascending order*/
            return year1-year2;

            /*For descending order*/
            //year2-year1;
        }};

    public static Comparator<MovieInfo> ratingSort = new Comparator<MovieInfo>() {

        public int compare(MovieInfo r1, MovieInfo r2) {

            int rating1 = r1.year;
            int rating2 = r2.year;

            /*For ascending order*/
            return rating1-rating2;

            /*For descending order*/
            //rating2-rating1;
        }};
}
