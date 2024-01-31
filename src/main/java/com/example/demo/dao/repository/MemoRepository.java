package com.example.demo.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.entity.Memo;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {
  @Query(value = "SELECT m FROM Memo m WHERE m.id = ?1")
  Optional<Memo> findById(Long id);

  @Query(value = "SELECT m FROM Memo m WHERE m.categoryId = ?1")
  List<Memo> findByCategoryId(Integer categoryId);

  @Query(value = "SELECT m FROM Memo m WHERE m.categoryId = -1")
  List<Memo> findByCategoryIdIsNegativeOne();
}
