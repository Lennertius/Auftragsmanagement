package com.fhdw.learning.auftragsmanagement.artikel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtikelRepository extends JpaRepository<Artikel, Long> {
}
