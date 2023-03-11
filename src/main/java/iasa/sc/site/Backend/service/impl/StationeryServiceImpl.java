package iasa.sc.site.Backend.service.impl;

import iasa.sc.site.Backend.entity.StationeryItem;
import iasa.sc.site.Backend.repository.StationeryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StationeryServiceImpl {
    private final StationeryRepository repository;

    public List<StationeryItem> getAll() {
        return null;
    }

    public void add(StationeryItem item) {

    }

    public void remove(int itemId) {

    }

    public void edit(int itemId) {

    }

}