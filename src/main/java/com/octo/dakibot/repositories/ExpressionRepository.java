package com.octo.dakibot.repositories;

import com.octo.dakibot.entities.Expression;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpressionRepository extends JpaRepository<Expression, Long> {
}
