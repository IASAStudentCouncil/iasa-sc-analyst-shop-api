package iasa.sc.site.Backend.service.impl;

import iasa.sc.site.Backend.entity.StationeryItem;
import iasa.sc.site.Backend.repository.StationeryRepository;
import iasa.sc.site.Backend.service.StationeryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StationeryServiceImpl implements StationeryService {
    private final StationeryRepository repository;

    @Override
    public List<StationeryItem> getAll() {
        return repository.findAll();
    }

    @Override
    public StationeryItem get(int id) {
        return repository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public StationeryItem add(StationeryItem item) {
        return repository.save(item);
    }

    @Override
    public void deleteAllItems() {
        repository.deleteAll();
    }

    @Override
    public void deleteById(int itemId) {
        StationeryItem item = repository.findById(itemId).orElseThrow(NoSuchElementException::new);
        repository.delete(item);
    }

    @Override
    public StationeryItem editById(int itemId, StationeryItem item) {
        StationeryItem itemEntity = repository.findById(itemId).orElseThrow(NoSuchElementException::new);

        itemEntity.setName(item.getName());
        itemEntity.setType(item.getType());
        itemEntity.setPrice(item.getPrice());

        repository.save(itemEntity);

        return itemEntity;
    }
}