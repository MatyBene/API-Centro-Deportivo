package com.utn.API_CentroDeportivo.service;

public interface IEnrollmentService {
    void enrollMemberToActivity(String username, Long activityId);
    void unsubscribeMemberFromActivity(String username, Long activityId);
}
