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

USERS tábla<br/>
ID VERSION USERNAME EMAIL PASSWORD ROLE 

SONGS tábla<br/>
ID VERSION TITLE ACCESS

SONGS_TAGS kapcsoló tábla (sok-sok)<br/>
SONG_ID TAGS_ID

TAGS tábla<br/>
ID VERSION TAG

RIGHTS tábla<br/>
ID VERSION UID SID DELETE SHARE LISTEN 

PLAYLISTS<br/>
ID VERSION NAME

PLAYLISTS_SONGS kapcsoló tábla (sok-sok)<br/>
PLAYLIST_ID SONGS_ID

USERS_PLAYLISTS kapcsoló tábla (1-sok)<br/>
USER_ID PLAYLISTS_ID