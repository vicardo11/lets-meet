package pl.sosinski.patryk.letsmeet.web.controller.rest;

public class ControllerConstants {

    public static final String API_URI = "/api";

    public static final String NOTIFICATIONS_URI = API_URI + "/notifications";
    public static final String EVENTS_URI = API_URI + "/events";
    public static final String EVENT_CATEGORY_URI = API_URI + "/event-categories";
    public static final String PARTICIPANTS_URI = API_URI + "/participants";

    public static final String EVENTS_URL = "/events";
    public static final String ADD_EVENT_URL = EVENTS_URL + "/add";
    public static final String SEARCH_EVENT_BY_CATEGORY_URL = EVENTS_URL + "/by-category";

    public static final String EVENTS_VIEW = "/events/events";
    public static final String ADD_EVENT_VIEW = "/events/add-event";

    public static final String EVENTS_ATTRIBUTE = "events";
    public static final String EVENT_ATTRIBUTE = "event";
    public static final String PARTICIPANTS_ATTRIBUTE = "participants";
    public static final String EVENT_CATEGORIES_ATTRIBUTE = "categories";
}
