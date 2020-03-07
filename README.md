Webtechnológiák információs rendszerekben - gyakorlati beadandó

Real Estate Management System

Ez a microservice alapú alkalmazás ingatlanok nyilvántartásával, értékbecslésével foglalkozik.

Általános irányelvek:
- Kövessük a már meglévő elnevezési konveciókat!
- Minden microservice buildelését kössük be a fő maven projekt alá.
- A dependenciák verzióit vezessük ki a fő maven pom-ba és ${}-al hivatkozzuk be őket!
- Az issuekat feature branchbe csináljuk és küldjünk egy pull requestet.
- A szemetet ne töltsük fel, tartsuk a maven struktúrát (pl.: IDE fájlok, .mvn mappa ilyesmi), ha kell akkor tegyük be a gitignoreba ezeket.
- Minden microservice a saját kis világa, ezen belül lehet saját döntést hozni a használt adatbázisról, technológiáról (de a SpringBoot erősen javasolt!), nem csatlakozunk rá más db-jére.

Feladatkiosztások:
- Data Store - feladatata az adatok tárolás és az ezeken való CRUD műveletek elvégzése (Vadász Péter)
- User Management - feladata a userek, jogosultságok kezelése, authentikáció megoldása ()
- Recalc Module - feladata különböző számolási algoritmusok lefuttatása (Vecsernyés Márk)
- Document Handling - feladata az ingatlanokhoz kötődő különböző dokumentumok tárolása (Szalai Norbert)
- External API Handling - feladata az alkalmazás külső API-kkal való integráció kezelése ()
