#Testausdokumentti
Projektin pääasiallinen tavoite oli laajennettavuus. Tämän takaamiseksi JUnit-testeihin panostettiin jo ensimmäisistä viikoista.

Yksinomaan näihin ei kuitenkaan turvauduttu, vaan mm. poikkeuksia heittänyttä koodia koetettiin paikantaa NetBeansin Debug-työkaluilla. Toisin sanoen koodia käytiin läpi rivi kerrallaan, kunnes virheen sijainti löydettiin. Vielä yleisempi tapa oli laittaa System.out.println-rivejä koodiin ja nähdä, että etenikö ohjelma ollenkaan kyseiseen kohtaan tai että miten usein siihen päästiin.

Graafista käyttöliittymää testattiin käsin. Ohjelmaa siis ajettiin, ja tarkasteltiin, että paneelit, napit ja muut graafiset elementit ovat oikeissa paikoissa ja että eteneminen näkymästä toiseen onnistui. Erityisesti testausta vaati se, että näkymässä käsiteltiin ajanmukaisia olioita/tietoja. Ongelmia tuotti alussa viittaukset vanhaan/olemattomaan ActiveGame-olioon, jolloin ohjelma kaatui, kun jokin metodi ei pystynyt sitä käsittelemään.

Useasti oli kätevämpää aluksi tarkistaa manuaalisesti tiedon tallentumisen onnistuminen – ts. silmäillä, että data oli tiedostossa oikeanmuotoista. Näitä testaavien JUnit-testien tekeminen oli nimittäin työlästä, ja siis haluttiin varmistaa, että tämä ominaisuus toimi oikein jo ennen varsinaista testaamista.

Olennainen asia, jota ohjelman ajamisella pyrittiin testaamaan, oli luonnollisesti asetusten/muuttujien arvojen mielekkyys: se, että tuntuiko peli pelimäiseltä tai reilulta. Tämä jäi tässä vaiheessa – kuten arvata saattoi – vielä suhteellisen kapealle tarkastelulle. Jos pelin kehittämistä tästä jatketaan (luultavasti jatketaan), tulee tämän testaaminen olemaan ensisijaista, sillä itse rakenne alkaa olla melko hyvällä mallilla.
