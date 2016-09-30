/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.sewsiaica.uusiaika.config;

/**
 * This enum class contains the default list of professions. This list will soon
 * be much greater.
 *
 * @author iah1016
 */
public enum DefaultProfessions {
    PROF01("Kauppias"),
    PROF02("Leipuri"),
    PROF03("Opettaja"),
    PROF04("Postinjakaja"),
    PROF05("Lääkäri"),
    PROF06("Radiojuontaja"),
    PROF07("Poliisi"),
    PROF08("Bussikuski"),
    PROF09("Putkimies"),
    PROF10("Poliitikko"),
    PROF11("Tutkija"),
    PROF12("Apteekkari"),
    PROF13("AD"),
    PROF14("Toimitusjohtaja");
    private final String name;

    private DefaultProfessions(String name) {
        this.name = name;
    }

    public String profName() {
        return name;
    }
}
