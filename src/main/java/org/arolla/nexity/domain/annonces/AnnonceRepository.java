package org.arolla.nexity.domain.annonces;

import org.arolla.nexity.infrastructure.PagingInfo;

import java.util.List;

public interface AnnonceRepository {
    void save(Annonce annonce);
    Annonce findById(AnnonceId annonceId);
    List<Annonce> findByVille(String ville);
    List<Annonce> findByVilleAndOffset(String ville, PagingInfo pagingInfo);
}
