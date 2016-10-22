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
  - Asetukset (kieliasetukset)
  - Kunnialista
  - Lopeta

**Uuden pelin aloitus:**
  - Aseta nimet pelaajalle ja liikkeelle

**Pelin toiminnot:**
  - Karttanäkymä
    - Käy temppelissä
    - Käy valmentautumiskeskuksessa
    - Ovelta ovelle -kierrokselle
    - Valitse kaupan piha kojun pystytykselle (ei toteutusta tässä versiossa)
    - Päätä vuoro
    - Tallenna peli
    - Palaa alkuvalikkoon (päättää pelin)
  
  - Liikenäkymä
    - Saarnaa seurakunnalle
    - Tarjoa kaikille limpparia
    - Varaa menolippu paratiisisaarelle
    - Palaa karttanäkymään
    - Päätä vuoro

  - Valmentautumiskeskus-näkymä
    - Käy karismakurssilla (tässä versiossa karisman kasvattaminen onnistuu joka kerta)
    - Käy väittelykurssilla (tässä versiossa argumentointitaitojen kasvattaminen onnistuu joka kerta)
    - Palaa karttanäkymään
    - Päätä vuoro

  - Ovelta ovelle -näkymä
    - Yritä suostuttelua
    - Yritä saarnausta
    - Yritä syyttämistä
    - Seuraava kohde
    - Palaa karttanäkymään
    - Päätä vuoro
  
  - Kojunäkymä (ei toteutusta tässä versiossa)
    - Tyrkytä esitteitä
    - Tarjoa kahvia ja rupattele niitä-näitä (karismasta hyötyä)
    - Palaa karttanäkymään
    - Päätä vuoro
    
##Rakennekuvaus
Ohjelma on jaettu eri paketteihin siten, että toiminnaltaan vastaavat luokat ovat samassa paketissa. Näitä ovat logiikka, käyttöliittymä, konfiguraatio, dialogi, tiedostonhallinta, yleiset työkalut ja "domain"-paketti, joka sisältää käsiteltävien olioiden luokat. Paketit noudattavat hierarkiaa: kaaviossa ylempänä olevat paketit ohjaavat alempana olevia; käyttöliittymä siis ohjaa logiikkaa, logiikka konfiguraatiota ja dialogia jne.

Logiikan keskeisin luokka on GameLogic. Sitä kautta käyttöliittymä ohjaa pelin kulkua. Tähän luokkaan on pysyvästi kiinni ActiveGameChanger-luokka, joka kontrolloi, mikä peli on aktiivisena. Jos pelaaja on käyttöliittymän kautta valinnut uuden pelin, ActiveGameChanger luo uuden ActiveGame-tyyppisen olion käyttäen apunaan kompositioonsa kuuluvia CreateVillagers- ja PlayerAndSectHandler-tyyppisiä olioita. Jos valinta on lataa peli, käyttää ActiveGameChanger kompositioonsa kuuluvaa LoadGameHandleria. Vastaavasti pelin tallennus käyttää ActiveGameChangerin komposition osaa SaveGameHandler. ActiveGame-olioon kuuluu pysyvästi seuraavantyyppiset oliot: ScoreHandler, TurnLogic, Temple, TrainingCentre sekä abstraktin luokan (Conversionin) toteuttavat Persuasion, Sermon ja Accusation.

Käyttöliittymän ytimessä ovat Runnable-rajapinnan toteuttava RunnableGUI ja JFramen aliluokka GameFrame. Jälkimmäinen ohjaa sitä, mikä näkymä ruudulla kulloinkin näkyy. Näkymät ovat abstraktin luokan AbstractViewPanelin (joka itsessään on JPanelin aliluokka) toteuttavien luokkien olioita. Tällaisia luokkia ovat seuraavat: MapViewPanel, DoorToDoorViewPanel, TempleViewPanel, TrainingCentreViewPanel, NewGameViewPanel, OpeningMenuViewPanel, LoadGameViewPanel, SettingsViewPanel, GameOverViewPanel, HallOfFameViewPanel ja LanguageSettingsViewPanel. Kullakin näkymällä on oma tapahtumankuuntelijansa. Neljä ensimmäistä luokkaa sisältää InfoPanel-paneelin, joka näyttää pelin tiedot näkymän yläpalkissa. Tämä ja MapViewPanelissa oleva VillagerListPanel ovat abstraktin AbstractSubPanelin aliluokkia. VillagerListPanelilla on oma ListSelectionListener-kuuntelijansa ja kustomoitu renderöintiluokka CustomCellRendererForVillagerListPanel, joka värjää kyläläislistastasta lahkon jäsenet magentalla värillä. Näkymistä DoorToDoorViewPanel, TempleViewPanel, TrainingCentreViewPanel ja NewGameViewPanel sisältävät DialoguePanel-JPanelin, joka näyttää pelin dialogia yms.

Dialogue-paketissa samanniminen luokka käsittelee pelin dialogin lataamisen eri kielillä tiedostoista. Kielitiedoston oikeellisuuden sen tarkistaa KeysForLangMaps-nimisen enum-luokan avulla. Konfiguraatiopaketissa on logiikalle "näkyvä" luokka Config, jota pelin konfiguraatioiden asettaminen ja hakeminen suoritetaan. Tähän kuuluu kompositiona LoadConfig-luokka, joka lataa konfiguraatiot käyttämällä tiedostonkäsittelijäpakettia, ja muutama Enum-luokka, jotka sisältävät pelin oletusarvoja.

Tiedostonkäsittelijäpaketti sisältää ReadFromInputStream- ja WriteFromOutputStream-luokat. Dataa käsitellään siis tavuina. Yleiset työkalut -paketista muualle "näkyvä" luokka on GeneralTools. Pakettiin kuuluu erilaisia tyypistä tyyppiin -konversioluokkia.

##Luokkakaaviot
**Luokkakaavio – logiikka**
![dokumentaatio/Luokkakaavio - logiikka ja domain - final.png](Luokkakaavio - logiikka ja domain - final.png)
**Luokkakaavio – käyttöliittymä**
![dokumentaatio/Luokkakaavio - kayttoliittyma - final.png](Luokkakaavio - kayttoliittyma - final.png)
**Luokkakaavio – muut paketit**
![dokumentaatio/Luokkakaavio - loput - final.png](Luokkakaavio - loput - final.png)

##Sekvenssikaavioita
![dokumentaatio/Sekvenssikaavio 1.png](Sekvenssikaavio 1.png)
![dokumentaatio/Sekvenssikaavio 2.png](Sekvenssikaavio 2.png)
![dokumentaatio/Sekvenssikaavio 3.png](Sekvenssikaavio 3.png)
![dokumentaatio/Sekvenssikaavio 4.png](Sekvenssikaavio 4.png)
