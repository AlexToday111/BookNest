package com.booknest.author;

import com.booknest.common.error.ResourceNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public AuthorResponse create(AuthorCreateRequest request) {
        return AuthorMapper.toResponse(authorRepository.save(new Author(request.name(), request.bio())));
    }

    @Transactional(readOnly = true)
    public List<AuthorResponse> findAll() {
        return authorRepository.findAll().stream().map(AuthorMapper::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public Author getEntity(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author", id));
    }

    @Transactional(readOnly = true)
    public AuthorResponse findById(Long id) {
        return AuthorMapper.toResponse(getEntity(id));
    }
}
