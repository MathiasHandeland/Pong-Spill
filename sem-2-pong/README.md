# PONG

I semesteroppgave 2 har jeg laget en versjon av spillet PONG, et av de første pc spillene som ble laget. Det kan minne litt om en virtuell versjon av tennis eller bordtennis. 

# Video av spillet

https://youtu.be/ln87hQ-s-_Q

# Spillets arkitektur 

Spillet jeg har laget er basert på design-prinsippet model view controller.  
Koden er delt inn i ulike pakker, en modell, en controller og view.

PongModel klassen består av selve logikken og datastrukturene og representerer et komplett Pong spill.
PongView klassen viser modellen grafisk og her tegnes Pong modellen
PongController klassen sitt ansvarsomrråde er å endre modellen basert på input fra brukeren, samt styre ting som skjer av seg selv


# Grovstruktur / Steg for steg implementasjon av programmet / Beskrivelse av programmet

1. Oppretter 3 ulike pakker, model, view og controller

2. Opprretter en Ball klasse i modellen som kan lage Ball objekter, en Paddle klasse som kan lage Paddle objekter, et enum GameState som inneholder de ulike spill stadiene og PongModel klassen som inneholder all logikken, blant annet de mulige kollisjonsscenarioene, initieringen av ny ball og paddles, dimensjonene til spillet og de ulike objektene og en run metode som kjører dersom man er i ACTIVE GAMEMODE.

3. I controller pakken finnes det et ControllablePongModel grensesnitt som inneholder de metodene fra modellen som skal kunne gjøre det mulig for brukeren å spille spillet basert på input i form av tastetrykk. En PongController klasse som implementerer KeyListener og som overskriver metodene keyPressed og keyReleased for å lytte etter tastetrykk som endrer på modellen.

4. I view pakken finnes det et grensesnitt ColorTheme som inneholder metodesignaturen til de ulike fargene som brukes i PongView klassen. Klassen DefaultColorTheme implementerer grensesnittet ColorTheme og initierer en default implementasjon av de ulike farge metodene. Et ViewablePongModel grensesnitt som inneholder de metodene fra modellen som trengs for at modellen skal kunne tegnes grafisk. PongView klassen tegner modellen og spillelementene og ulike skjermer basert på hvilket GameState man befinner seg i. 

5. Main klassen inneholder en main metode for å kjøre programmet. Her lages det et nytt PongModel objekt, PongView objekt og en PongCongtroller som tar modellen og view som argument. Main metoden lager også et JFrame og legger til view som argument. Dette gjør at visningen dukker opp på lerettet. Main metoden inneholder også en gameloop som oppdaterer modellen og repainter visningen. Det er lagt til en sleep på threaden som kjører slik at spillet kjører på 100 FPS.


# Hvordan spillet fungerer

Spillet PONG er et 2-player spill og spilles på følgende måte:

- Målet med spillet er å score flere poeng enn motstanderen din ved å få ballen til å passere gjennom motstanderens mål. 

- Spillet spilles av to spillere som står på hver sin side av skjermen og bruker tastaturet for å bevege en loddrett rektangulær tykk linje (ofte kalt en "paddle") opp og ned langs kanten av skjermen. Spiller nummer 1 spiller med paddlen på venstre side av skjermen og bruker tastetrrykk "W" for å bevege paddlen oppover og "S" for henholdvis å bevege den nedover". Spiller nummer 2 spiller med paddlen på høyre side av skjermen og bruker tastetrykk "pil opp" for å bevege paddlen oppover og "pil ned" for å bevege den nedover". 

- Ballen starter fra midten og får en tilfeldig start posisjon vertikalt midt på skjemen og i sentrum horisontalt. Ballen får en tilfeldig x og y retning hver gang den initieres og den beveger seg frem og tilbake over skjermen mens spillerne forsøker å treffe ballen med sin paddle og sende den tilbake til motstanderen. Hvis en spiller ikke klarer å treffe ballen og den passerer gjennom deres mål, får motstanderen et poeng.

- Spillet fortsetter til en av spillerne når en poengsum på 7, og den spilleren vinner deretter runden. Deretter kan man returnere til velkomstskjermen for så å starte spillet på nytt.# Pong-Spill
