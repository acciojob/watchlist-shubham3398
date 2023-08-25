package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    public void addMovie(Movie movie) {
        movieRepository.addMovie(movie);
    }

    public ResponseEntity addDirector(Director director) {
        return movieRepository.addDirector(director);
    }

    public ResponseEntity addMovieDirectorPair(String movieName, String directorName) {
        return movieRepository.addMovieDirectorPair(movieName, directorName);
    }

    public ResponseEntity getMovieByName(String movieName) {
        return movieRepository.getMovieByName(movieName);
    }
    public ResponseEntity getDirectorByName(String directorName) {
        return movieRepository.getDirectorByName(directorName);
    }

    public ResponseEntity getMoviesByDirectorName(String directorName) {
        List<Movie> movieList = movieRepository.getMoviesByDirectorName(directorName);
        if(movieList.size() > 0){
            List<String> moviesName = new ArrayList<>();
            for (Movie movie : movieList) {
                moviesName.add(movie.getName());
            }
            return new ResponseEntity(moviesName, HttpStatus.FOUND);
        }
        return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity findAllMovies() {
        return movieRepository.findAllMovies();
    }

    public ResponseEntity deleteDirectorByName(String directorName) {
        return movieRepository.deleteDirectorByName(directorName);
    }

    public ResponseEntity deleteAllDirectors() {
        return movieRepository.deleteAllDirectors();
    }
}
