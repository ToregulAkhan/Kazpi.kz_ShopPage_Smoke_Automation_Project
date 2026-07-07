package kaspi_shop.constants;

import kaspi_shop.utils.ConfigReader;

public class Urls {

    public static final String BASE_URL = ConfigReader.get("base.url");

    public static final String ALL_CATEGORIES_URL = BASE_URL + "/c/categories/";
    public static final String FASHION_CATEGORIES_URL = BASE_URL + "/c/fashion/";
    public static final String TV_AUDIO_CATEGORIES_URL = BASE_URL + "/c/tv_audio/";
    public static final String FURNITURE_CATEGORIES_URL = BASE_URL + "/c/furniture/";
    public static final String PHARMACY_CATEGORIES_URL = BASE_URL + "/c/pharmacy/";

}
