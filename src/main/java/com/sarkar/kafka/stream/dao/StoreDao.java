package com.sarkar.kafka.stream.dao;

import com.sarkar.kafka.stream.entity.Store;
import com.sarkar.kafka.stream.repository.StoreRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreDao {
    private final StoreRepo storeRepo;
    public List<Store> findAll(){
        return storeRepo.findAll();
    }
    //@Cacheable(amount="store-cache", key="#eventId", unless = "#result==null")
    public Store getStoreByEventId(String eventId){
        return storeRepo.findByEventId(eventId);
    }
    //@CachePut(amount="store-cache", key="#result.eventId")
    public Store updateTimeStamp(Store store){
        store.setUpdateTime(LocalDateTime.now());
        return storeRepo.save(store);
    }

    @CachePut(value="store-cache", key="#result.eventId")
    public Store  insert(Store store){
        return storeRepo.save(store);
    }
}
