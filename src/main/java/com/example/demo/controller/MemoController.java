package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.entity.Memo;
import com.example.demo.dao.repository.MemoRepository;
import com.example.demo.dto.MemoDto;

@RestController
@RequestMapping("/memo")
public class MemoController {

  private final MemoRepository memoRepository;

  public MemoController(MemoRepository memoRepository) {
    this.memoRepository = memoRepository;
  }

  @GetMapping("/{id}")
  public String getMemo(@PathVariable(value = "id") Long id) {
    Optional<Memo> memoOptional = memoRepository.findById(id);
    if (memoOptional.isPresent()) {
      Memo memo = memoOptional.get();
      return memo.getContent();
    } else {
      return "Not found!";
    }
  }

  @PostMapping("/")
  public String postMemo(@RequestBody MemoDto memoDto) {
    Memo memo = Memo.builder()
        .categoryId(memoDto.getCategoryId())
        .content(memoDto.getContent())
        .build();
    memoRepository.save(memo);
    return "Saved!";
  }

  @GetMapping("/category/{categoryId}")
  public String getMemoByCategory(@PathVariable(value = "categoryId") Integer categoryId) {
    List<Memo> memoList = memoRepository.findByCategoryId(categoryId);
    if (!memoList.isEmpty()) {
      return memoList.get(0).getContent();
    } else {
      return "Not found!";
    }
  }

  @GetMapping("/category/negative-one")
  public String getMemoByCategoryIsNegativeOne() {
    List<Memo> memoList = memoRepository.findByCategoryIdIsNegativeOne();
    if (!memoList.isEmpty()) {
      return memoList.get(0).getContent();
    } else {
      return "Not found!";
    }
  }

}
