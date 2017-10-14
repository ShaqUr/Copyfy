# Copyfy
Zenés alkalmazás (Copyfy):
-Regisztrációs, beléptető, feltöltő, hangfájllistás, lejátszásilistás, beállítások felület.
-Felhasználók feltöltik a hangfájlokat, melyek lehetnek privátok, vagy publikusak.
-Kereső kulcsszavakkal, előadókkal, címekkel.
-Felhasználók megoszthatják egymással a privát zenéiket. (Jogok. D/S/L )
-Admin felhasználó, mindent törölhet, felhasználókat bannolhat.
-Lejátszási lista. (nem továbbosztható)

Ha marad idő:
-Toplista kedvencek alapján, hallgatás alapján.

Adatbázis model:

User tábla
ID Username PW Banned 

Song tábla
ID Name Tags access

Tags tábla
Tags

Rights tábla
uID sID Delete Share Listen (sok-sok)

PlayList song kapcsoló tábla (1-sok)
plID sID

PlayList user kapcsoló tábla
plID uID