package org.arolla.nexity.infrastructure.controllers;

import org.arolla.nexity.domain.annonces.*;
import org.arolla.nexity.domain.annonces.adresses.AdresseValidator;
import org.arolla.nexity.domain.annonces.adresses.CodePostalValidator;
import org.arolla.nexity.infrastructure.PagingInfo;
import org.arolla.nexity.infrastructure.models.AnnonceCreationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/annonces")
public class AnnoncesController {

    @Autowired
    public AnnoncesController(AnnonceRepository annonceRepository) {
        this.annonceRepository = annonceRepository;
    }

    private AnnonceRepository annonceRepository;

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> createAnnonce(@RequestBody AnnonceCreationModel annonce) {

        CodePostalValidator codePostalValidator = CodePostalValidator.create()
                .withCodePostal(annonce.getAdresse().getCodePostal());
        if (codePostalValidator.isInvalid()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        AdresseValidator adresseValidator = new AdresseValidator()
                .withCodePostal(annonce.getAdresse().getCodePostal())
                .withLibelle(annonce.getAdresse().getLibelle())
                .withVille(annonce.getAdresse().getVille());
        if (adresseValidator.isInvalid().isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        AnnonceValidator annonceValidator = new AnnonceValidator().withAnnonceId(new AnnonceId(UUID.randomUUID())).withTitre(annonce.getTitre()).withDescriptif(annonce.getTitre()).withAdresse(adresseValidator
                .createAdresse()).withSurface(annonce.getSurface()).withPrix(annonce.getPrix());
        if (annonceValidator.isInvalid()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Annonce newAnnonce = annonceValidator.createAnnonce();
        annonceRepository.save(
                newAnnonce);

        return new ResponseEntity<>(newAnnonce.getAnnonceId().toString(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Annonce getById(@PathVariable("id") String annonceId) {

        return annonceRepository.findById(new AnnonceId(UUID.fromString(annonceId)));
    }

    @GetMapping("/ville/{ville}")
    public List<Annonce> getByVille(@PathVariable("ville") String ville, @RequestParam("offset") Integer offset, @RequestParam("numberOfResults") Integer numberOfResults) {
        if (offset != null) {
            return annonceRepository.findByVilleAndOffset(ville, new PagingInfo(offset, numberOfResults));
        } else {
            return annonceRepository.findByVille(ville);
        }
    }
}
