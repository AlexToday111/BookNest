package com.booknest.genre;

import com.booknest.common.error.ResourceNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Transactional
    public GenreResponse create(GenreCreateRequest request) {
        return GenreMapper.toResponse(genreRepository.save(new Genre(request.name())));
    }

    @Transactional(readOnly = true)
    public List<GenreResponse> findAll() {
        return genreRepository.findAll().stream().map(GenreMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public Genre getEntity(Long id) {
        return genreRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Genre", id));
    }

    @Transactional(readOnly = true)
    public GenreResponse findById(Long id) {
        return GenreMapper.toResponse(getEntity(id));
    }
}
