package com.signatic.service;

/**
 * Created by root on 09/11/2016.
 */

public class ConfigurationNotification {
    public static final String TOPIC_GLOBAL = "global";

    // broadcast receiver intent filters
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";

    // id to handle the notification in the notification tray
    public static final int NOTIFICATION_ID = 100;

    public static final String SHARED_PREF = "ah_firebase";
}
