/**
 * Copyright 2024
 * Name: Constant
 */
package com.anhvt.commonservice.utils;

/**
 * Common constant
 *
 * @author trunganhvu
 * @date 9/7/2024
 */
public class Constant {
    // Kafka topic
    public static final String PROFILE_ONBOARDING_TOPIC = "profileOnboarding";
    public static final String PROFILE_ONBOARDED_TOPIC = "profileOnboarded";
    public static final String NOTIFICATION_CREATE_PROFILE_TOPIC = "profileOnboarding";
    public static final String PAYMENT_REQUEST_TOPIC = "paymentRequest";
    public static final String PAYMENT_CREATED_TOPIC = "paymentCreated";
    public static final String PAYMENT_COMPLETED_TOPIC = "paymentCompleted";

    // Profile status
    public static final String STATUS_PROFILE_PENDING = "PENDING";
    public static final String STATUS_PROFILE_ACTIVE = "ACTIVE";
    public static final String STATUS_PROFILE_INACTIVE = "INACTIVE";
    public static final String STATUS_PROFILE_DELETED = "DELETED";
    public static final String STATUS_PROFILE_ACTIVE_DELETED = "ACTIVE_DELETED";

    // Payment status
    public static final String STATUS_PAYMENT_CREATING = "CREATING";
    public static final String STATUS_PAYMENT_REJECTED = "REJECTED";
    public static final String STATUS_PAYMENT_PROCESSING = "PROCESSING";
    public static final String STATUS_PAYMENT_SUCCESSFUL = "SUCCESSFUL";

    public static final String JSON_REQ_CREATE_PROFILE = "validator/createProfile.schema.json";
}
