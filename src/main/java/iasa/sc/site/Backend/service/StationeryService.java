package iasa.sc.site.Backend.service;

import iasa.sc.site.Backend.entity.StationeryItem;

import java.util.List;

public interface StationeryService {

    List<StationeryItem> getAll();

    StationeryItem get(int id);

    StationeryItem add(StationeryItem item);

    void deleteAllItems();

    void deleteById(int itemId);

    StationeryItem editById(int itemId, StationeryItem item);
}
