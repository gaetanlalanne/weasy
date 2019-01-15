package org.arolla.nexity.infrastructure.controllers;

import org.arolla.nexity.domain.annonces.Annonce;
import org.arolla.nexity.infrastructure.models.AdresseModel;
import org.arolla.nexity.infrastructure.models.AnnonceCreationModel;
import org.arolla.nexity.infrastructure.repositories.MapBasedAnnonceRepository;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class AnnoncesControllerTest {

    private final AnnoncesController controller;

    public AnnoncesControllerTest() {
        controller = new AnnoncesController(new MapBasedAnnonceRepository());
    }

    private ResponseEntity<String> createAnnonceWithVille(String ville) {
        return controller.createAnnonce(new AnnonceCreationModel("titre", "appartement de charme", new AdresseModel("my street", "75002", ville), 10D, 50000D));
    }

    private ResponseEntity<String> createAnnonceWithTitre(String titre) {
        return controller.createAnnonce(new AnnonceCreationModel(titre, "appartement de charme", new AdresseModel("my street", "75002", "paris"), 10D, 50000D));
    }

    @Test
    public void successfulAnnonceCreationReturnsHttpOK() {

        ResponseEntity annonce = createAnnonceWithTitre("mon Annonce");

        assertThat(annonce.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(annonce.toString()).isNotBlank();
    }

    @Test
    public void annonceCreationWithInvalidParameterReturnsHttp400() {

        ResponseEntity annonce = createAnnonceWithTitre("");

        assertThat(annonce.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void canGetById() {
        ResponseEntity<String> annonceId = createAnnonceWithTitre("titre");

        Annonce annonce = controller.getById(annonceId.getBody());

        assertThat(annonce).isNotNull();
    }

    @Test
    public void canGetByVille() {
        createAnnonceWithVille("maVille");

        List<Annonce> annonces = controller.getByVille("maVille", null, null);

        assertThat(annonces).hasSize(1);
        assertThat(annonces).extracting("ville").containsOnly("maVille");
    }

    @Test
    public void canAskForMoreResultsThanThereAre() {
        createAnnonceWithVille("maVille");

        List<Annonce> annonces = controller.getByVille("maVille", 0, 12);

        assertThat(annonces).hasSize(1);
        assertThat(annonces).extracting("ville").containsOnly("maVille");
    }
}