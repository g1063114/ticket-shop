package project.ticket.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.ticket.shop.entity.item.Item;
import project.ticket.shop.entity.item.Movie;
import project.ticket.shop.repository.MovieRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    /*
     * 영화 단일 검색
     */
    public Optional<Movie> findMovieOne(Long id){
        Optional<Movie> findMovie = movieRepository.findById(id);
        return findMovie;
    }

    /*
     * 영화 수정
     */
    @Transactional
    public void updateMovie(Long id, String genre, int runningTime){
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie != null){
            movie.setGenre(genre);
            movie.setRunningTime(runningTime);
        }
    }
}
