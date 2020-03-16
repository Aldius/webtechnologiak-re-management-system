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
- User Management - feladata a userek, jogosultságok kezelése, authentikáció megoldása (Szoboszlai Norbert)
- Recalc Module - feladata különböző számolási algoritmusok lefuttatása (Vecsernyés Márk)
- Document Handling - feladata az ingatlanokhoz kötődő különböző dokumentumok tárolása (Szalai Norbert)
- External API Handling - feladata az alkalmazás külső API-kkal való integráció kezelése (Szalai Szandra)

Business case (for better understading):

1. A register real estate message is sent to the data store. The data stores assigns a unique id and stores the values. 
2. The data store module sends the real estate to the document handling module and the recalc module. They store the relevant attributes for their own use.
3. A create appraisal message is sent to the data store. It is handled in a similar way like the real estate.
4. A document now can be now uploaded to the appraisal. The document module stores this document.
5. Now, an external service (we don't implement this, but let this be a scheduled API call) calls the recalc module, to recalculate this real estate. It runs all available algorithms (taking dependencies into consideration) to calculate values. For example, it uses the external API module to set the geo coordites of the real estate or apply the current FX rates for the value of real estate.











