package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {
    @Autowired
    MovieService movieService;
    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        movieService.addMovie(movie);
        return new ResponseEntity("success", HttpStatus.CREATED);
    }

    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        return movieService.addDirector(director);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movieName") String movieName,@RequestParam("directorName") String directorName){
        return movieService.addMovieDirectorPair(movieName, directorName);
    }

    @GetMapping("/get-movie-by-name/{movieName}")
    public ResponseEntity getMovieByName(@PathVariable("movieName") String movieName){
        return movieService.getMovieByName(movieName);
    }

    @GetMapping("/get-director-by-name/{directorName}")
    public ResponseEntity getDirectorByName(@PathVariable("directorName") String name){
        return movieService.getDirectorByName(name);
    }

    @GetMapping("/get-movies-by-director-name/{name}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable("name") String directorName){
        return movieService.getMoviesByDirectorName(directorName);
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity findAllMovies(){
        return movieService.findAllMovies();
    }
    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("name") String directorName){
        return movieService.deleteDirectorByName(directorName);
    }
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        return movieService.deleteAllDirectors();
    }
}
