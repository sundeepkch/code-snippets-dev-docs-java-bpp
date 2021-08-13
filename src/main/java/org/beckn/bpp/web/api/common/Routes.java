package org.beckn.bpp.web.api.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Routes {
    private static final String BASE_URI = "/bpp";
    public static final String SEARCH_API = BASE_URI + "/search";
    public static final String SELECT_API = BASE_URI + "/select";
    public static final String INIT_API = BASE_URI + "/init";
    public static final String CONFIRM_API = BASE_URI + "/confirm";
    public static final String STATUS_API = BASE_URI + "/status";
    public static final String TRACK_API = BASE_URI + "/track";
    public static final String CANCEL_API = BASE_URI + "/cancel";
    public static final String UPDATE_API = BASE_URI + "/update";
    public static final String RATING_API = BASE_URI + "/rating";
    public static final String SUPPORT_API = BASE_URI + "/support";

    public static final String CANCELLATION_REASONS_API = BASE_URI + "/get_cancellation_reasons";
    public static final String RETURN_REASONS_API = BASE_URI + "/get_return_reasons";
    public static final String RATING_CATEGORIES_API = BASE_URI + "/get_rating_categories";
}
