package org.dimas4ek.wrapper.types;

public enum Locale {
    Indonesian("id"),
    Danish("da"),
    German("de"),
    EnglishUK("en-GB"),
    EnglishUS("en-US"),
    Spanish("es-ES"),
    French("fr"),
    Croatian("hr"),
    Italian("it"),
    Lithuanian("lt"),
    Hungarian("hu"),
    Dutch("nl"),
    Norwegian("no"),
    Polish("pl"),
    BrazilianPortuguese("pt-BR"),
    Romanian("ro"),
    Finnish("fi"),
    Swedish("sv-SE"),
    Vietnamese("vi"),
    Turkish("tr"),
    Czech("cs"),
    Greek("el"),
    Bulgarian("bg"),
    Russian("ru"),
    Ukrainian("uk"),
    Hindi("hi"),
    Thai("th"),
    ChineseChina("zh-CN"),
    Japanese("ja"),
    ChineseTaiwan("zh-TW"),
    Korean("ko");

    private final String localeCode;

    Locale(String localeCode) {
        this.localeCode = localeCode;
    }

    public String getLocaleCode() {
        return localeCode;
    }
}
