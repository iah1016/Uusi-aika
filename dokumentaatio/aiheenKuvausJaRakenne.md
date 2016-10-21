# Uusi aika – aiheen kuvaus ja rakenne

**Aihe:** Uskonliikesimulaatiopeli, jossa pelaaja perustaa uskonnollisen liikkeen ja pyrkii kartuttamaan jäsenten lukumäärää erilaisin menetelmin: ovelta ovelle -käynneillä, infokojun pystyttämisellä, puskaradiolla jne. Pelissä on roolipelimäisiä elementtejä; käännytystyö siis helpottuu muun muassa karisman ja argumentointitaitojen parantuessa.

**Voiton ehdot:** Pelin voi voittaa usealla eri tavalla:
  - Taivaan portille
    - Itsemurhakultti. Johtajalta edellytetään erittäin paljon karismaa. Pisteytyksessä painotetaan erityisesti jäsenten lukumäärää.
  - Jumalallinen oikeutus
    - Johtaja karkaa rahojen kanssa paratiisisaarelle. Pisteytyksessä painotetaan erityisesti liikkeen tilin saldoa pelin lopussa.
    
**Tavat hävitä:** Pelissä voi käydä kalpaten seuraavin tavoin:
  - Kierrosten lukumäärä saavuttaa asetetun maksimimäärän.
  - Kierroksen vaihdettua lahkon tili menee negatiiviseksi.

**Käyttäjät:** 1 pelaaja

**Alkuvalikon toiminnot:**
  - Aloita uusi peli
  - Lataa peli
  - Asetukset
  - Taivaallinen kunnialista
  - Lopeta

**Uuden pelin aloitus:**
  - Aseta nimet pelaajalle ja liikkeelle

**Pelin toiminnot:**
  - Karttanäkymä
    - Siirry liikkeen tiloihin
    - Käy valmentautumiskeskuksessa
    - Ovelta ovelle -kierrokselle
    - Valitse kaupan piha kojun pystytykselle (kaupan kojun totetutus jäänee tulevaisuuteen)
    - Tallenna peli
  
  - Liikenäkymä
    - Saarnaa seurakunnalle
    - Tarjoa kaikille limpparia
    - Varaa menolippu paratiisisaarelle
    - Paluu karttanäkymään

  - Valmentautumiskeskus-näkymä
    - Käy karismakurssilla (tässä versiossa karisman kasvattaminen onnistuu joka kerta)
    - Käy väittelykurssilla (tässä versiossa argumentointitaitojen kasvattaminen onnistuu joka kerta)
    - Paluu karttanäkymään

  - Ovelta ovelle -näkymä
    - Graafisen tekstiseikkailun dialogi
    - Paluu karttanäkymään
  
  - Kojunäkymä (ei luultavasti toteutusta tässä versiossa)
    - Tyrkytä esitteitä
    - Tarjoa kahvia ja rupattele niitä-näitä (karismasta hyötyä)
    - Paluu karttanäkymään
    
##Rakennekuvaus
Ohjelma on jaettu eri paketteihin siten, että toiminnaltaan vastaavat luokat ovat samassa paketissa. Näitä ovat logiikka, käyttöliittymä, konfiguraatio, tiedostonhallinta, yleiset työkalut ja "domain"-paketti, joka sisältää käsiteltävien olioiden luokat.

Logiikan keskeisin luokka on GameLogic. Sitä kautta käyttöliittymä ohjaa pelin kulkua. Tähän luokkaan on pysyvästi kiinni ActiveGameChanger-luokka, joka kontrolloi, mikä peli on aktiivisena. Jos pelaaja on käyttöliittymän kautta valinnut uuden pelin, ActiveGameChanger luo uuden ActiveGame-tyyppisen olion käyttäen apunaan kompositioonsa kuuluvia CreateVillagers- ja PlayerAndSectHandler-tyyppisiä olioita. Jos valinta on lataa peli, käyttää ActiveGameChanger kompositioonsa kuuluvaa LoadGameHandleria. Tallennusominaisuus ei ole tällä hetkellä toiminnassa. ActiveGame-olioon kuuluu pysyvästi seuraavantyyppiset oliot: TurnLogic, Temple, TrainingCentre sekä abstraktin luokan (Conversionin) toteuttavat Persuasion, Sermon ja Accusation.

Käyttöliittymän ytimessä ovat Runnable-rajapinnan toteuttava RunnableGUI ja JFramen aliluokka GameFrame. Jälkimmäinen ohjaa sitä, mikä näkymä ruudulla kulloinkin näkyy. Näkymät ovat abstraktin luokan AbstractViewPanelin (joka itsessään on JPanelin aliluokka) toteuttavien luokkien olioita. Tällaisia luokkia ovat seuraavat: MapViewPanel, DoorToDoorViewPanel, TempleViewPanel, TrainingCentreViewPanel, OpeningMenuViewPanel, NewGameViewPanel, LoadGameViewPanel, SettingsViewPanel ja GameOverViewPanel. Kullakin näkymällä on oma tapahtumankuuntelijansa. Neljä ensimmäistä luokkaa sisältää InfoPanel-paneelin. Tämä ja MapViewPanelissa oleva VillagerListPanel ovat abstraktin AbstractSubPanelin aliluokkia. VillagerListPanelilla on oma ListSelectionListener-kuuntelija.

Tiedostonkäsittelijäpaketissa on tällä hetkellä vain ReadFromTextFile-luokka, joka siis lukee dataa tekstitiedostosta. Konfiguraatiopaketissa on logiikalle "näkyvä" luokka Config, jota pelin konfiguraatioiden asettaminen ja hakeminen suoritetaan. Tähän kuuluu kompositiona tällä hetkellä LoadConfig-luokka ja muutama Enum-luokka, jotka sisältävät pelin oletusarvoja.

Yleiset työkalut -paketista muualle "näkyvä" luokka on GeneralTools. Pakettiin kuuluu erilaisia tyypistä tyyppiin -konversioluokkia.

##Luokkakaaviot
**Luokkakaavio – logiikka**
![dokumentaatio/Luokkakaavio - logiikka.png](Luokkakaavio - logiikka.png)
**Luokkakaavio – käyttöliittymä**
![dokumentaatio/Luokkakaavio - kayttoliittyma.png](Luokkakaavio - kayttoliittyma.png)
**Luokkakaavio – muut paketit (ei Enum-luokkia)**
![dokumentaatio/Luokkakaavio - loput.png](Luokkakaavio - loput.png)

##Sekvenssikaavioita (lisää tulossa lähipäivinä)
![dokumentaatio/Sekvenssikaavio 1.png](Sekvenssikaavio 1.png)
![dokumentaatio/Sekvenssikaavio 2.png](Sekvenssikaavio 2.png)
![dokumentaatio/Sekvenssikaavio 3.png](Sekvenssikaavio 3.png)
