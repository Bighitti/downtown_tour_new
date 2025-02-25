package it.unicam.ids.dciotti.downtowntour.controller;

import it.unicam.ids.dciotti.downtowntour.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/admin")
public interface AdminController {
    @PostMapping(path = "/create")
    ResponseEntity<Void> createUser(
            @RequestBody UserDTO userDTO);

    @PostMapping
    ResponseEntity<UserDTO> login(
            @RequestBody LoginDTO loginDTO);

    @PatchMapping(path = "/report-solve/{reportId}")
    ResponseEntity<Void> reportSolve(
            @PathVariable Integer reportId,
            @RequestBody LoginDTO loginDTO);

    @PostMapping(path = "/challenge-start")
    ResponseEntity<ChallengeDTO> challengeStart(
            @RequestBody ChallengeDTO challengeDTO);

    @PatchMapping(path = "/promote/admin/{userId}")
    ResponseEntity<Void> promoteAdmin(
            @PathVariable Integer userId,
            @RequestBody LoginDTO loginDTO);

    @PatchMapping(path = "/promote/entertainer/{userId}")
    ResponseEntity<Void> promoteEntertainer(
            @PathVariable Integer userId,
            @RequestBody LoginDTO loginDTO);

    @PatchMapping(path = "/promote/moderator/{userId}")
    ResponseEntity<Void> promoteModerator(
            @PathVariable Integer userId,
            @RequestBody LoginDTO loginDTO);

    @PatchMapping(path = "/promotetocurator/{contributorId}")
    ResponseEntity<Void> promoteFromContributorToCurator(
            @PathVariable Integer contributorId,
            @RequestBody LoginDTO loginDTO);

    @PatchMapping(path = "/demotefromcurator/{contributorId}")
    ResponseEntity<Void> demoteFromCuratorToContributor(
            @PathVariable Integer contributorId,
            @RequestBody LoginDTO loginDTO);

    @PatchMapping(path = "/unauthorize/{contentId}")
    ResponseEntity<Void> unauthorizeContent(
            @PathVariable Integer contentId,
            @RequestBody LoginDTO adminLoginDTO);

    @DeleteMapping
    ResponseEntity<Void> deleteAdmin(
            @RequestBody LoginDTO loginDTO);
}
