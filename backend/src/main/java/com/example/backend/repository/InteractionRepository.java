package com.example.backend.repository;

import com.example.backend.model.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Long> {
    List<Interaction> findByHistoryId(int historyId);
    // You can add custom query methods here if needed
}