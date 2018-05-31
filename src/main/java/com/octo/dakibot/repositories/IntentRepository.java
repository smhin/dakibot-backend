package com.octo.dakibot.repositories;

import com.octo.dakibot.entities.Intent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IntentRepository extends JpaRepository<Intent, Long> {
}
