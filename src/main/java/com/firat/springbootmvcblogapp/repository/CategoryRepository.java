package com.firat.springbootmvcblogapp.repository;

import com.firat.springbootmvcblogapp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
