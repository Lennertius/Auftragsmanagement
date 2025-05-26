package com.fhdw.learning.auftragsmanagement.auftragposition;

import com.fhdw.learning.auftragsmanagement.Kunde.Kunde;
import com.fhdw.learning.auftragsmanagement.artikel.Artikel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuftragPositionRepository extends JpaRepository<AuftragPosition, Long> {
    List<AuftragPosition> findByArtikel(Artikel artikel);
    List<AuftragPosition> findByAuftrag_Kunde(Kunde kunde);
}
