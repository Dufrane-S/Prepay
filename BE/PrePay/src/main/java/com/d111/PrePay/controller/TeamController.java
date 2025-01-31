package com.d111.PrePay.controller;

import com.d111.PrePay.dto.request.InviteCodeReq;
import com.d111.PrePay.dto.request.TeamCreateReq;
<<<<<<< HEAD
=======
import com.d111.PrePay.dto.request.TeamCreateStoreReq;
import com.d111.PrePay.dto.request.TeamDetailReq;
>>>>>>> e822273e26c945781d59f376ec1fdf4c719b5818
import com.d111.PrePay.dto.respond.GetUserOfTeamRes;
import com.d111.PrePay.dto.respond.StoresRes;
import com.d111.PrePay.dto.respond.TeamDetailRes;
import com.d111.PrePay.dto.respond.TeamRes;
import com.d111.PrePay.model.Team;
import com.d111.PrePay.model.TeamStore;
import com.d111.PrePay.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;

//    @PostMapping("/code")
//    public String generateInviteCode(@RequestHeader("userId") Long userId, @RequestBody InviteCodeReq req) {
//
//        return password;
//    }


    @PostMapping("/store-id")
    public Long createStore(@RequestHeader("userId") Long userId,
                            @RequestBody TeamCreateStoreReq req) {
        TeamStore teamStore = teamService.createStore(req);
        return teamStore.getId();
    }


    @GetMapping("/{teamId}/user")
    public List<GetUserOfTeamRes> getUserOfTeam(@PathVariable Long teamId,
                                                @RequestHeader("userId") Long userId) {
        return teamService.getUsersOfTeam(teamId, userId);
    }


    @GetMapping("/{teamId}")
    public TeamDetailRes getTeamDetails(@PathVariable Long teamId,
                                        @RequestHeader("userId") Long userId) {
        return teamService.getTeamDetails(teamId, userId);
    }


    @PostMapping("/signup")
    public ResponseEntity<Long> createTeam(@RequestBody TeamCreateReq request) {
        Team team = teamService.createTeam(request);
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
}
