package pl.sosinski.patryk.letsmeet.web.controller;

public class ControllerConstants {

    public static final String API_URI = "/api";

    public static final String NOTIFICATIONS_URI = API_URI + "/notifications";
    public static final String EVENTS_URI = API_URI + "/events";
    public static final String EVENT_CATEGORY_URI = API_URI + "/event-categories";
    public static final String PARTICIPANTS_URI = API_URI + "/participants";

    public static final String EVENTS_URL = "/events";
    public static final String ADD_EVENT_URL = EVENTS_URL + "/add";
    public static final String SEARCH_EVENT_BY_CATEGORY_URL = EVENTS_URL + "/by-category";
    public static final String LOGIN_URL = "/loginPage";
    public static final String PARTICIPANTS_URL = "/participants";
    public static final String REGISTRATION_URL = "/registration";
    public static final String PARTICIPANTS_REGISTRATION_URL = PARTICIPANTS_URL + REGISTRATION_URL;

    public static final String EVENTS_VIEW = "/events/events";
    public static final String ADD_EVENT_VIEW = "/events/add-event";
    public static final String LOGIN_VIEW = "/security/login";
    public static final String REGISTRATION_VIEW = "/security/registration";

    public static final String EVENTS_ATTRIBUTE = "events";
    public static final String EVENT_ATTRIBUTE = "event";
    public static final String PARTICIPANTS_ATTRIBUTE = "participants";
    public static final String EVENT_CATEGORIES_ATTRIBUTE = "categories";

}
