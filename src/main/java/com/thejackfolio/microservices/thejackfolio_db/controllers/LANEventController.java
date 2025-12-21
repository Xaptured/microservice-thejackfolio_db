/*
 * Copyright (c) 2024.
 * Created this for the project called "TheJackFolio"
 * All right reserved by Jack
 */

package com.thejackfolio.microservices.thejackfolio_db.controllers;

import com.thejackfolio.microservices.thejackfolio_db.entities.combinedentities.AudienceTicketEntity;
import com.thejackfolio.microservices.thejackfolio_db.enums.LANEventStatus;
import com.thejackfolio.microservices.thejackfolio_db.enums.LANTeamStatus;
import com.thejackfolio.microservices.thejackfolio_db.enums.UpdateCategory;
import com.thejackfolio.microservices.thejackfolio_db.models.*;
import com.thejackfolio.microservices.thejackfolio_db.services.LANEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Event-LAN", description = "Event LAN management APIs")
@RestController
@RequestMapping("/events-lan")
public class LANEventController {

    @Autowired
    private LANEventService lanEventService;

    @Operation(
            summary = "Save OR Update lan events",
            description = "Save OR Update lan events."
    )
    @PostMapping("/save-or-update-event")
    public ResponseEntity<Void> saveOrUpdateEvent(@RequestBody LANEvent event, @RequestParam boolean isUpdate) {
        lanEventService.saveOrUpdateEvent(event, isUpdate);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Fetch future events with respect to email",
            description = "Fetch future events with respect to email."
    )
    @GetMapping("/future-events/{email}")
    public ResponseEntity<List<LANEvent>> fetchFutureEventsWRTEmail(@PathVariable String email) {
        List<LANEvent> lanEvents = lanEventService.fetchFutureEventsWRTEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(lanEvents);
    }

    @Operation(
            summary = "Fetch past events with respect to email",
            description = "Fetch past events with respect to email."
    )
    @GetMapping("/past-events/{email}")
    public ResponseEntity<List<LANEvent>> fetchPastEventsWRTEmail(@PathVariable String email) {
        List<LANEvent> lanEvents = lanEventService.fetchPastEventsWRTEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(lanEvents);
    }

    @Operation(
            summary = "Fetch live events with respect to email",
            description = "Fetch live events with respect to email."
    )
    @GetMapping("/live-events/{email}")
    public ResponseEntity<List<LANEvent>> fetchLiveEventsWRTEmail(@PathVariable String email) {
        List<LANEvent> lanEvents = lanEventService.fetchLiveEventsWRTEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(lanEvents);
    }

    @Operation(
            summary = "Save team and team mate details",
            description = "Save team and team mate details."
    )
    @PostMapping("/save-teams")
    public ResponseEntity<Void> saveTeams(@RequestBody List<LANTeam> team) {
        lanEventService.saveTeams(team);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Fetch pending teams with respect to email",
            description = "Fetch pending teams with respect to email."
    )
    @GetMapping("/pending-teams/{email}")
    public ResponseEntity<List<LANTeam>> fetchTeamWithTeamMate(@PathVariable String email) {
        List<LANTeam> lanTeams = lanEventService.fetchTeamWithTeamMate(email);
        return ResponseEntity.status(HttpStatus.OK).body(lanTeams);
    }

    @Operation(
            summary = "Update team status",
            description = "Update team status."
    )
    @PostMapping("/update-team-status")
    public ResponseEntity<Void> updateTeamStatus(@RequestParam String email, @RequestParam String eventName, @RequestParam LANTeamStatus status) {
        lanEventService.updateTeamStatus(email, eventName, status);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            summary = "Fetch past events for participant with respect to email",
            description = "Fetch past events for participant with respect to email."
    )
    @GetMapping("/participant-past-events/{email}")
    public ResponseEntity<List<LANEvent>> fetchPastEventsForParticipants(@PathVariable String email) {
        List<LANEvent> lanTeams = lanEventService.fetchPastEventsForParticipants(email);
        return ResponseEntity.status(HttpStatus.OK).body(lanTeams);
    }

    @Operation(
            summary = "Fetch future events for participant with respect to email",
            description = "Fetch future events for participant with respect to email."
    )
    @GetMapping("/participant-future-events/{email}")
    public ResponseEntity<List<LANEvent>> fetchFutureEventsForParticipants(@PathVariable String email) {
        List<LANEvent> lanTeams = lanEventService.fetchFutureEventsForParticipants(email);
        return ResponseEntity.status(HttpStatus.OK).body(lanTeams);
    }

    @Operation(
            summary = "Save audience with payment status",
            description = "Save audience with payment status."
    )
    @PostMapping("/save-or-update-audience")
    public ResponseEntity<Void> saveOrUpdateAudience(@RequestBody Audience audience) {
        lanEventService.saveOrUpdateAudience(audience);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Fetch past events for audience with respect to email",
            description = "Fetch past events for audience with respect to email."
    )
    @GetMapping("/audience-past-events/{email}")
    public ResponseEntity<List<LANEvent>> fetchPastEventsForAudience(@PathVariable String email) {
        List<LANEvent> lanTeams = lanEventService.fetchPastEventsForAudience(email);
        return ResponseEntity.status(HttpStatus.OK).body(lanTeams);
    }

    @Operation(
            summary = "Fetch future events for audience with respect to email",
            description = "Fetch future events for audience with respect to email."
    )
    @GetMapping("/audience-future-events/{email}")
    public ResponseEntity<List<LANEvent>> fetchFutureEventsForAudience(@PathVariable String email) {
        List<LANEvent> lanTeams = lanEventService.fetchFutureEventsForAudience(email);
        return ResponseEntity.status(HttpStatus.OK).body(lanTeams);
    }

    @Operation(
            summary = "Fetch live events for audience with respect to email",
            description = "Fetch live events for audience with respect to email."
    )
    @GetMapping("/audience-live-events/{email}")
    public ResponseEntity<List<LANEvent>> fetchLiveEventsForAudience(@PathVariable String email) {
        List<LANEvent> lanTeams = lanEventService.fetchLiveEventsForAudience(email);
        return ResponseEntity.status(HttpStatus.OK).body(lanTeams);
    }

    @Operation(
            summary = "Fetch unregistered events for audience with respect to email",
            description = "Fetch unregistered events for audience with respect to email."
    )
    @GetMapping("/audience-unregistered-events/{email}")
    public ResponseEntity<List<LANEvent>> findLANEventsNotRegisteredByAudience(@PathVariable String email) {
        List<LANEvent> lanTeams = lanEventService.findLANEventsNotRegisteredByAudience(email);
        return ResponseEntity.status(HttpStatus.OK).body(lanTeams);
    }

    @Operation(
            summary = "Fetch inactive events for admin",
            description = "Fetch inactive events for admin."
    )
    @GetMapping("/admin-inactive-events")
    public ResponseEntity<List<LANEvent>> fetchInactiveEventForAdmin() {
        List<LANEvent> lanTeams = lanEventService.fetchInactiveEventForAdmin();
        return ResponseEntity.status(HttpStatus.OK).body(lanTeams);
    }

    @Operation(
            summary = "Update event status",
            description = "Update event status."
    )
    @PostMapping("/update-event-status")
    public ResponseEntity<Void> updateEventStatus(@RequestParam String eventName, @RequestParam LANEventStatus status) {
        lanEventService.updateEventStatus(eventName, status);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            summary = "Fetch team details",
            description = "Fetch team details."
    )
    @GetMapping("/fetch-team-details/{eventName}")
    public ResponseEntity<List<LANTeam>> fetchParticipatedTeamDetails(@PathVariable String eventName) {
        List<LANTeam> lanTeams = lanEventService.fetchParticipatedTeamDetails(eventName);
        return ResponseEntity.status(HttpStatus.OK).body(lanTeams);
    }

    @Operation(
            summary = "Fetch event details",
            description = "Fetch event details."
    )
    @GetMapping("/fetch-event-details/{eventName}")
    public ResponseEntity<LANEvent> fetchLANEventDetails(@PathVariable String eventName) {
        LANEvent lanEvent = lanEventService.fetchLANEventDetails(eventName);
        return ResponseEntity.status(HttpStatus.OK).body(lanEvent);
    }

    @Operation(
            summary = "Save audience ticket",
            description = "Save audience ticket."
    )
    @PostMapping("/save-audience-ticket")
    public ResponseEntity<Void> saveAudienceTicket(@RequestBody AudienceTicket audienceTicket) {
        lanEventService.saveAudienceTicket(audienceTicket);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Fetch unsent email count",
            description = "Fetch unsent email count."
    )
    @GetMapping("/fetch-unsent-email-count")
    public ResponseEntity<Long> fetchUnsentEmailForAudienceCount() {
        long count = lanEventService.fetchUnsentEmailForAudienceCount();
        return ResponseEntity.status(HttpStatus.OK).body(count);
    }

    @Operation(
            summary = "Fetch unsent emails",
            description = "Fetch unsent emails."
    )
    @GetMapping("/fetch-unsent-emails")
    public ResponseEntity<List<AudienceTicket>> fetchUnsentEmailForAudience() {
        List<AudienceTicket> audienceTickets = lanEventService.fetchUnsentEmailForAudience();
        return ResponseEntity.status(HttpStatus.OK).body(audienceTickets);
    }

    @Operation(
            summary = "Update audience ticket status",
            description = "Update audience ticket status."
    )
    @PostMapping("/update-audience-ticket-status")
    public ResponseEntity<Void> updateAudienceTicketStatus(@RequestBody AudienceTicket audienceTicket) {
        lanEventService.updateEmailStatus(audienceTicket);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            summary = "Update audience ticket check in status",
            description = "Update audience ticket check in status."
    )
    @PostMapping("/update-audience-check-in-status")
    public ResponseEntity<Void> updateCheckedInStatus(@RequestParam String eventName, @RequestParam String email) {
        lanEventService.updateCheckedInStatus(email, eventName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            summary = "Fetch audience ticket details",
            description = "Fetch audience ticket details."
    )
    @GetMapping("/fetch-audience-ticket-details")
    public ResponseEntity<AudienceTicket> fetchAudienceTicketDetails(@RequestParam String eventName, @RequestParam String email) {
        AudienceTicket audienceTicket = lanEventService.fetchAudienceTicketByEventNameAndEmail(email, eventName);
        return ResponseEntity.status(HttpStatus.OK).body(audienceTicket);
    }

    @Operation(
            summary = "Save pending payments",
            description = "Save pending payments."
    )
    @PostMapping("/save-pending-payments")
    public ResponseEntity<Void> savePendingPayments(@RequestBody Audience audience) {
        lanEventService.savePendingPayment(audience);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            summary = "Save failed payments",
            description = "Save failed payments."
    )
    @PostMapping("/save-failed-payments")
    public ResponseEntity<Void> saveFailedPayments(@RequestBody Audience audience) {
        lanEventService.saveFailedPayment(audience);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            summary = "Save initiated payment",
            description = "Save initiated payment."
    )
    @PostMapping("/save-initiated-payment")
    public ResponseEntity<Void> saveInitiatePayment(@RequestBody Audience audience) {
        lanEventService.saveInitiatePayment(audience);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            summary = "Fetch pending payments",
            description = "Fetch pending payments."
    )
    @GetMapping("/fetch-pending-payments")
    public ResponseEntity<List<Audience>> fetchPendingPayments() {
        List<Audience> audiences = lanEventService.fetchAllPendingPayments();
        return ResponseEntity.status(HttpStatus.OK).body(audiences);
    }

    @Operation(
            summary = "Fetch failed payments",
            description = "Fetch failed payments."
    )
    @GetMapping("/fetch-failed-payments")
    public ResponseEntity<List<Audience>> fetchFailedPayments() {
        List<Audience> audiences = lanEventService.fetchAllFailedPayments();
        return ResponseEntity.status(HttpStatus.OK).body(audiences);
    }

    @Operation(
            summary = "Fetch initiate payment",
            description = "Fetch initiate payment."
    )
    @GetMapping("/fetch-initiate-payment")
    public ResponseEntity<Audience> fetchInitiatePayment(@RequestParam String merchantTransactionId) {
        Audience audience = lanEventService.fetchInitiatePayment(merchantTransactionId);
        return ResponseEntity.status(HttpStatus.OK).body(audience);
    }

    @Operation(
            summary = "Delete pending payment",
            description = "Delete pending payment."
    )
    @DeleteMapping("/delete-pending-payment")
    public ResponseEntity<Void> deletePendingPayment(@RequestParam String email, @RequestParam String eventName) {
        lanEventService.deletePendingPaymentByEmailAndEventName(email, eventName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            summary = "Delete failed payment",
            description = "Delete failed payment."
    )
    @DeleteMapping("/delete-failed-payment")
    public ResponseEntity<Void> deleteFailedPayment(@RequestParam String email, @RequestParam String eventName) {
        lanEventService.deleteFailedPaymentByEmailAndEventName(email, eventName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            summary = "Save sub user",
            description = "Save sub user."
    )
    @PostMapping("/save-sub-user")
    public ResponseEntity<Void> saveSubUser(@RequestBody SubUser subUser) {
        lanEventService.saveSubUser(subUser);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            summary = "Update sub user",
            description = "Update sub user."
    )
    @PostMapping("/update-sub-user")
    public ResponseEntity<Void> updateSubUser(@RequestBody SubUser subUser) {
        lanEventService.updateSubUser(subUser);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            summary = "Update active",
            description = "Update sub user."
    )
    @PostMapping("/update-active/{eventName}")
    public ResponseEntity<Void> updateActive(@PathVariable String eventName) {
        lanEventService.updateActive(eventName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            summary = "Fetch unsent email sub users",
            description = "Fetch unsent email sub users."
    )
    @GetMapping("/fetch-unsent-email-sub-users")
    public ResponseEntity<List<SubUser>> fetchUnsentEmailSubUsers() {
        List<SubUser> subUsers = lanEventService.findByIsEmailSent();
        return ResponseEntity.status(HttpStatus.OK).body(subUsers);
    }

    @Operation(
            summary = "Fetch sub user using username",
            description = "Fetch sub user using username."
    )
    @GetMapping("/fetch-sub-user-by-username/{username}")
    public ResponseEntity<SubUser> fetchSubUserByUsername(@PathVariable String username) {
        SubUser subUser = lanEventService.findByUserName(username);
        return ResponseEntity.status(HttpStatus.OK).body(subUser);
    }

    @Operation(
            summary = "Update feedback flag",
            description = "Update feedback flag."
    )
    @PostMapping("/update-feedback")
    public ResponseEntity<Void> updateFeedback(@RequestBody List<Feedback> feedbacks) {
        lanEventService.updateFeedback(feedbacks);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(
            summary = "Fetch feedback flag using email",
            description = "Fetch feedback flag using email."
    )
    @GetMapping("/fetch-feedback-by-email/{email}")
    public ResponseEntity<Feedback> fetchFeedbackByEmail(@PathVariable String email) {
        Feedback feedback = lanEventService.findFeedback(email);
        return ResponseEntity.status(HttpStatus.OK).body(feedback);
    }

    @Operation(
            summary = "Fetch feedback details one month exactly ago",
            description = "Fetch feedback details one month exactly ago."
    )
    @GetMapping("/fetch-feedbacks")
    public ResponseEntity<List<Feedback>> getFeedbackExactlyOneMonthOld() {
        List<Feedback> feedbacks = lanEventService.getFeedbackExactlyOneMonthOld();
        return ResponseEntity.status(HttpStatus.OK).body(feedbacks);
    }

    @Operation(
            summary = "Fetch all active advertisement details",
            description = "Fetch all active advertisement details."
    )
    @GetMapping("/fetch-advertisements")
    public ResponseEntity<List<AdvertisementModel>> getAdvertisementDetails() {
        List<AdvertisementModel> advertisementModels = lanEventService.fetchAllActiveAdvertisements();
        return ResponseEntity.status(HttpStatus.OK).body(advertisementModels);
    }

    @Operation(
            summary = "Save advertisement details",
            description = "Save advertisement details."
    )
    @PostMapping("/save-advertisement")
    public ResponseEntity<Void> saveAdvertisement(@RequestBody AdvertisementModel model) {
        lanEventService.saveAdvertisementDetails(model);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(
            summary = "Save live update details",
            description = "Save live update details."
    )
    @PostMapping("/save-live-update")
    public ResponseEntity<Void> saveUpdateRequest(@RequestBody UpdateRequest updateRequest) {
        lanEventService.saveUpdateRequest(updateRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(
            summary = "Fetch live update details",
            description = "Fetch live update details."
    )
    @GetMapping("/live-updates")
    public ResponseEntity<List<UpdateRequest>> fetchLatestUpdates(@RequestParam(name = "category")UpdateCategory category,
                                                  @RequestParam(name = "limit") int limit, @RequestParam(name = "tournamentName") String tournamentName) {
        List<UpdateRequest> updateRequests = lanEventService.fetchLatestUpdatesByCategory(category, limit, tournamentName);
        return ResponseEntity.status(HttpStatus.OK).body(updateRequests);
    }

    @Operation(
            summary = "Save tournament image details",
            description = "Save tournament image details."
    )
    @PostMapping("/save-tournament-images")
    public ResponseEntity<Void> saveTournamentImages(@RequestBody TournamentImages tournamentImages) {
        lanEventService.saveTournamentImages(tournamentImages);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(
            summary = "Fetch tournament image details",
            description = "Fetch tournament image details."
    )
    @GetMapping("/fetch-tournament-images")
    public ResponseEntity<List<Image>> fetchImagesByTournamentName(@RequestParam String tournamentName) {
        List<Image> images =  lanEventService.fetchImagesByTournamentName(tournamentName);
        return ResponseEntity.status(HttpStatus.OK).body(images);
    }
}
