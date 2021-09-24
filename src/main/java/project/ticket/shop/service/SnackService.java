package project.ticket.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.ticket.shop.entity.item.Movie;
import project.ticket.shop.entity.item.Snack;
import project.ticket.shop.repository.SnackRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SnackService {

    private final SnackRepository snackRepository;

    /*
     * 스낵 단일 조회
     */
    public Optional<Snack> findSnackOne(Long id){
        Optional<Snack> findSnack = snackRepository.findById(id);
        return findSnack;
    }

    /*
     * 스낵 수정
     */
    @Transactional
    public void updateSnack(Long id, String category){
        Snack snack = snackRepository.findById(id).orElse(null);
        if ( snack != null ){
            snack.setCategory(category);
        }
    }
}
