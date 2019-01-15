package org.arolla.nexity.infrastructure.repositories;

import org.arolla.nexity.domain.annonces.Annonce;
import org.arolla.nexity.domain.annonces.AnnonceId;
import org.arolla.nexity.domain.annonces.AnnonceRepository;
import org.arolla.nexity.infrastructure.PagingInfo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MapBasedAnnonceRepository implements AnnonceRepository {

    private Map<AnnonceId, Annonce> annonces = new HashMap<>();

    @Override
    public void save(Annonce annonce) {
        annonces.put(annonce.getAnnonceId(), annonce);
    }

    @Override
    public Annonce findById(AnnonceId annonceId) {
        return annonces.get(annonceId);
    }

    @Override
    public List<Annonce> findByVille(String ville) {
        return annonces.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .filter(value -> value.getVille().equals(ville))
                .collect(Collectors.toList());
    }

    @Override
    public List<Annonce> findByVilleAndOffset(String ville, PagingInfo pagingInfo) {
        int toIndex = pagingInfo.getOffset() + pagingInfo.getNumberOfResults();
        List<Annonce> annonces = findByVille(ville);
        if (toIndex > annonces.size()) {
            toIndex = annonces.size();
        }
        return annonces.subList(pagingInfo.getOffset(), toIndex);
    }
}
