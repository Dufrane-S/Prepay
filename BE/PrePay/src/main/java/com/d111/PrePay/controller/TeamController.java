package com.d111.PrePay.controller;

import com.d111.PrePay.dto.request.TeamCreateReq;
import com.d111.PrePay.dto.request.TeamCreateStoreReq;
import com.d111.PrePay.dto.respond.*;

import com.d111.PrePay.dto.request.*;
import com.d111.PrePay.dto.respond.GetUserOfTeamRes;
import com.d111.PrePay.dto.respond.StoresRes;
import com.d111.PrePay.dto.respond.TeamDetailRes;
import com.d111.PrePay.dto.respond.TeamRes;
import com.d111.PrePay.model.Team;
import com.d111.PrePay.model.TeamStore;
import com.d111.PrePay.service.ImageService;
import com.d111.PrePay.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
@Slf4j
public class TeamController {
    private final TeamService teamService;
    private final ImageService imageService;

    // 팀 이미지 수정
    @PostMapping("/image")
    public ResponseEntity<Void> uploadImage(@RequestHeader("userId") Long userId,
                                            @RequestPart("request") TeamIdReq req,
                                            @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            String imgUrl = imageService.uploadImage(image, req.getTeamId());
            teamService.uploadImage(req, imgUrl);
        }

        return ResponseEntity.ok().build();
    }


    @PostMapping("/ban")
    public ResponseEntity<Void> banUser(@RequestHeader("userId") Long userId,
                                        @RequestBody BanUserReq req) {
        teamService.banUser(req);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/exit")
    public ResponseEntity<Void> exitTeam(@RequestHeader("userId") Long userId,
                                         @RequestBody TeamIdReq req) {
        teamService.exitTeam(userId, req);
        return ResponseEntity.ok().build();
    }


    @PostMapping("confirm-privilege")
    public ResponseEntity<Void> confirmPrivilege(@RequestHeader("userId") Long userId,
                                                 @RequestBody PartyConfirmReq req) {
        teamService.confirmPrivilege(req);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/request-privilege")
    public ResponseEntity<Void> privilegeRequest(@RequestHeader("userId") Long userId,
                                                 @RequestBody TeamIdReq req) {
        teamService.privilegeRequest(userId, req);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/charge")
    public ResponseEntity<Void> chargeRequest(@RequestHeader("userId") Long userId,
                                              @RequestBody ChargeReq req) {
        return ResponseEntity.ok().build();
    }


    @PostMapping("/signin")
    public ResponseEntity<Void> signinTeam(@RequestHeader("userId") Long userId,
                                           @RequestBody SignInTeamReq req) {
        teamService.signInTeam(userId, req);
        return ResponseEntity.ok().build();

    }


    @PostMapping("/privilege")
    public ResponseEntity<Void> grantPrivilege(@RequestHeader("userId") Long userId,
                                               @RequestBody GrantPrivilegeReq req) {
        teamService.grantPrivilege(req);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/position")
    public ResponseEntity<Void> grantAdminPosition(@RequestHeader("userId") Long userId,
                                                   @RequestBody GrantAdminPositionReq req) {
        teamService.grantAdminPosition(req);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/limit")
    public ResponseEntity<Team> changeDailyPriceLimit(@RequestHeader("userId") Long userId, @RequestBody ChangeDailyPriceLimitReq req) {
        return ResponseEntity.ok(teamService.changeDailyPriceLimit(req));
    }


    @PostMapping("/code")
    public ResponseEntity<Team> generateInviteCode(@RequestHeader("userId") Long userId, @RequestBody TeamIdReq req) {
        return ResponseEntity.ok(teamService.generateInviteCode(userId, req));
    }


    @PostMapping("/store-id")
    public ResponseEntity<Long> createStore(@RequestHeader("userId") Long userId,
                                            @RequestBody TeamCreateStoreReq req) {
        TeamStore teamStore = teamService.createStore(req);
        return ResponseEntity.ok(teamStore.getId());
    }


    @GetMapping("/{teamId}/user")
    public List<GetUserOfTeamRes> getUserOfTeam(@PathVariable Long teamId,
                                                @RequestHeader("userId") Long userId) {
        return teamService.getUsersOfTeam(teamId, userId);
    }


    @GetMapping("/{teamId}")
    public ResponseEntity<TeamDetailRes> getTeamDetails(@PathVariable Long teamId,
                                                        @RequestHeader("userId") Long userId) {
        return ResponseEntity.ok(teamService.getTeamDetails(teamId, userId));
    }


    @PostMapping(value = "/signup")
    public ResponseEntity<Long> createTeam(@RequestPart("request") TeamCreateReq request,
                                           @RequestPart(value = "image", required = false) MultipartFile image,
                                           @RequestHeader("userId") Long userId) throws IOException {
        Team team = teamService.createTeam(request, userId);

        if (image != null && !image.isEmpty()) {
            String imgUrl = imageService.uploadImage(image, team.getId());
            team.setTeamImgUrl(imgUrl);
            teamService.save(team);
        }

        return ResponseEntity.ok(team.getId());
    }

    @GetMapping("/groups")
    public ResponseEntity<List<TeamRes>> getMyTeams(@RequestHeader("userId") Long userId) {
        return ResponseEntity.ok(teamService.getMyTeams(userId));
    }

    @GetMapping("/{teamId}/stores")
    public ResponseEntity<List<StoresRes>> getMyTeamStores(@RequestHeader("userId") Long userId, @PathVariable Long teamId) {
        return ResponseEntity.ok(teamService.getMyTeamStores(teamId, userId));
    }

    @GetMapping("/coordinate/{teamId}")
    public ResponseEntity<List<StoresCorRes>> getStoresCor(@RequestHeader("userId") Long userId, @PathVariable Long teamId) {
        return ResponseEntity.ok(teamService.getStoresCor(teamId, userId));
    }

    @GetMapping("/public-teams")
    public ResponseEntity<List<PublicTeamsRes>> getPublicTeams() {
        return ResponseEntity.ok(teamService.getPublicTeams());
    }

    @GetMapping("/public-teams/{keyword}")
    public ResponseEntity<List<PublicTeamsRes>> getPublicTeamsByKeyword(@PathVariable String keyword) {
        return ResponseEntity.ok(teamService.getPublicTeamsByKeyword(keyword));
    }
}
