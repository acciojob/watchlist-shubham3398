package com.driver;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {
    HashMap<String, Movie> movieDb = new HashMap<>();
    HashMap<String, Director> directorDb = new HashMap<>();
    HashMap<String, List<Movie>> directorMoviePairDb = new HashMap<>();

    public void addMovie(Movie movie) {
        movieDb.put(movie.getName(), movie);
    }

    public ResponseEntity addDirector(Director director) {
        directorDb.put(director.getName(), director);
        return new ResponseEntity("success", HttpStatus.CREATED);
    }

    public ResponseEntity addMovieDirectorPair(String movieName, String directorName) {
        if(movieDb.containsKey(movieName) && directorDb.containsKey(directorName)){
            if(directorMoviePairDb.containsKey(directorName)) {
                List<Movie> movieList = directorMoviePairDb.get(directorName);
                movieList.add(movieDb.get(movieName));
            }
            else{
                List<Movie> newMovieList = new ArrayList<>();
                newMovieList.add(movieDb.get(movieName));
                directorMoviePairDb.put(directorName, newMovieList);
            }
            return new ResponseEntity("success", HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity("failed", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity getMovieByName(String movieName) {
        if(movieDb.containsKey(movieName)){
            return new ResponseEntity(movieDb.get(movieName), HttpStatus.FOUND);
        }
        return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity getDirectorByName(String directorName) {
        if(directorDb.containsKey(directorName)){
            return new ResponseEntity(directorDb.get(directorName), HttpStatus.FOUND);
        }
        return new ResponseEntity("Not Found", HttpStatus.NOT_FOUND);
    }

    public List<Movie> getMoviesByDirectorName(String directorName) {
        if(directorMoviePairDb.containsKey(directorName)){
            return directorMoviePairDb.get(directorName);

        }
        return new ArrayList<>();
    }

    public ResponseEntity findAllMovies() {
        List<String> allMoviesName = new ArrayList<>();

        for(String name : movieDb.keySet()){
            allMoviesName.add(movieDb.get(name).getName());
        }
        return new ResponseEntity(allMoviesName, HttpStatus.FOUND);
    }

    public ResponseEntity deleteDirectorByName(String directorName) {
        if(directorMoviePairDb.containsKey(directorName)){
            List<Movie> allMoviesOfDirector = directorMoviePairDb.get(directorName);
            for(Movie movie : allMoviesOfDirector){
                movieDb.remove(movie.getName());
            }
        }
        if(directorDb.containsKey(directorName)){
            directorDb.remove(directorName);
            return new ResponseEntity("Director and his all songs Deleted Successfully", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity("Director is not exist", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity deleteAllDirectors() {
        if(!directorDb.isEmpty()){
            for(String directorName : directorDb.keySet()){
                if(directorMoviePairDb.containsKey(directorName)){
                    List<Movie> allMoviesOfDirector = directorMoviePairDb.get(directorName);
                    for(Movie movie : allMoviesOfDirector){
                        movieDb.remove(movie.getName());
                    }
                }
                directorDb.remove(directorName);
            }
            return new ResponseEntity("All Directors and all movies of them is Deleted", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity("No Director is present", HttpStatus.BAD_REQUEST);
    }
}
