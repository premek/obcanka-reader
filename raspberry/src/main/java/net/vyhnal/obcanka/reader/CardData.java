package net.vyhnal.obcanka.reader;

import cz.paralelnipolis.obcanka.core.card.Card;
import cz.paralelnipolis.obcanka.core.card.enums.DokState;
import cz.paralelnipolis.obcanka.core.card.enums.IokState;
import cz.paralelnipolis.obcanka.core.certificates.Certificate;
import cz.paralelnipolis.obcanka.core.certificates.IdentificationCertificate;
import cz.paralelnipolis.obcanka.core.communication.CardException;

public class CardData {
    public final String serialNumber;
    public final String cardID;
    public final DokState dokState;
    public final int dokTryLimit;
    public final int dokMaxTryLimit;
    public final IokState iokState;
    public final int iokMaxTryLimit;
    public final int iokTryLimit;
    public final String documentNumber;
    public final String givenName;
    public final String surname;
    public final String marriageStatus;
    public final String sex;
    public final String street;
    public final String city;
    public final String locality;
    public final String countryName;
    public final String countryCode;
    public final String birthNumber;
    public final String birthDate;
    public final String birthCity;
    public final String organization;

    public CardData(Card card) throws CardException {
        IdentificationCertificate cert = (IdentificationCertificate) card.getCertificate(Certificate.CertificateType.IDENTIFICATION);

        cardID = card.getCardNumber();
        dokState = card.getDokState();
        dokTryLimit = card.getDokTryLimit();
        dokMaxTryLimit = card.getDokMaxTryLimit();
        iokState = card.getIokState();
        iokMaxTryLimit = card.getIokMaxTryLimit();
        iokTryLimit = card.getIokTryLimit();
        serialNumber = cert.getSerialNumber();
        documentNumber = cert.getDocumentNumber();
        givenName = cert.getGivenName();
        surname = cert.getSurname();
        marriageStatus = cert.getMarriageStatus();
        sex = cert.getSex();
        street = cert.getStreet();
        city = cert.getCity();
        locality = cert.getLocality();
        countryName = cert.getCountryName();
        countryCode = cert.getCountryCode();
        birthNumber = cert.getBirthNumber();
        birthDate = cert.getBirthDate();
        birthCity = cert.getBirthCity();
        organization = cert.getOrganization();
    }
}
