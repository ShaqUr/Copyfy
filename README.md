# Copyfy
Zenés alkalmazás (Copyfy): <br/>
-Regisztrációs, beléptető, feltöltő, hangfájllistás, lejátszásilistás, beállítások felület. <br/>
-Felhasználók feltöltik a hangfájlokat, melyek lehetnek privátok, vagy publikusak. <br/>
-Kereső kulcsszavakkal, előadókkal, címekkel. <br/>
-Felhasználók megoszthatják egymással a privát zenéiket. (Jogok. D/S/L ) <br/>
-Admin felhasználó, mindent törölhet, felhasználókat bannolhat. <br/>
-Lejátszási lista. (nem továbbosztható) <br/>

Ha marad idő:<br/>
-Toplista kedvencek alapján, hallgatás alapján.

Adatbázis model:

Users tábla<br/>
ID Username PW Role 

Songs tábla<br/>
ID Title Tags Access

Tags tábla<br/>
Tags

Rights tábla (sok-sok)<br/>
ID uID sID Delete Share Listen 

PlayList song kapcsoló tábla (1-sok)<br/>
ID plID sID

PlayList user kapcsoló tábla <br/>
ID plID uID