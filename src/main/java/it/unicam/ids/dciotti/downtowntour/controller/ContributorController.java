package it.unicam.ids.dciotti.downtowntour.controller;

import it.unicam.ids.dciotti.downtowntour.dto.ContributorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/contributor")
public interface ContributorController {
    // Metodo per creare un record sul DB passandogli un body(Post----->Insert)
    @PostMapping
    ResponseEntity<ContributorDTO> createContributor(
            @RequestBody ContributorDTO contributorDTO);

    // Metodo per ottenere i dati dal DB(Get)
    @GetMapping(path = "/{id}")
    ResponseEntity<ContributorDTO> retrieveContributor(
            @PathVariable Integer id);

    @PutMapping(path = "/{id}")
    ResponseEntity<ContributorDTO> updateContributor(
            @PathVariable Integer id, @RequestBody ContributorDTO contributorDTO);

    // Metodo per recuperare i dati dal dal DB
    @GetMapping(path = "/all")
    ResponseEntity<List<ContributorDTO>> retrieveAllContributors();

    // Metodo per cancellare un record dal DB(Delete)
    @DeleteMapping(path = "/delete/{id}")
    ResponseEntity<Void> deleteContributor(
            @PathVariable Integer id);
}