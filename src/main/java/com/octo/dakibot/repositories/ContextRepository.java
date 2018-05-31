package com.octo.dakibot.repositories;

import com.octo.dakibot.entities.Context;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContextRepository extends JpaRepository<Context, Long> {
}
