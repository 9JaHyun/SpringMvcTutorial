package com.edu3tospring.domain.bulletin;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BulletinService {
    private final BulletinRepository repository;

    public Integer save(Bulletin bulletin) {
        repository.save(bulletin);
        return bulletin.getId();
    }

    public Bulletin findById(Integer id) {
        return repository.findById(id)
            .orElseThrow(() -> new AssertionError("잘못된 ID 입니다. ID = " + id));
    }

    public List<Bulletin> findByTitle(String title) {
        return repository.findByTitle(title);
    }

    public List<Bulletin> findAll() {
        return repository.findAll();
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

}
