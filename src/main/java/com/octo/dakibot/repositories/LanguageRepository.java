package com.octo.dakibot.repositories;

import com.octo.dakibot.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
}
