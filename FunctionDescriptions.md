# Functions

## Data store
- Function XYZ1: _Please provide a description_
- Function XYZ2: _Please Provide a description_


## Document handling
- Function XYZ1: _Please provide a description_
- Function XYZ2: _Please Provide a description_


## External-api module
- RealEstateController/getForecast: it is a get request for /externalapis/forecast/{lat},{lng}, where lat and lng are path variables. These waits for an expressions like "41.30" or "19", separated by comma (like "41.30,21.1" or "35.6,19"). The requested API is the DarkSky. It returns a JSON format containing momstly (currently and daily) weather and also location informations: summary, dewpoint, windspeed, timezone, lgn and so on. 
- RealEstateController/getLocationWithForecast: it is a get request for /location/{city},{publicSpace},{state}, where city, publicSpace and state are path variables. The correct usage is to take comma between the path variables and if the path variable is more than one word place "+" between them (space also works). This method is combined with the forecast API (DarkSky) so it returns weather and also location informations from Geocoding API by Google. It is also a helper method for getting latitude and longitude values of a city.
- RealEstateController/getCurrencies: it is a get request for /currencies/{currency} where currency is a path variable. It returns a JSON which contains timezone, the base currency, like "HUF" and the conversion rates.


## Recalc module
- Function XYZ1: _Please provide a description_
- Function XYZ2: _Please Provide a description_


## User handling
- Function XYZ1: _Please provide a description_
- Function XYZ2: _Please Provide a description_



