```json

{
  "countries": [
    {
      "countryCode": "IN",
      "countryName": "India",
      "officialName": "Republic of India",
      "isdCode": "+91",
      "continent": "Asia",
      "currencyCode": "INR",
      "currencyName": "Indian Rupee",
      "capital": "New Delhi",
      "population": 1428627663,
      "areaSqKm": 3287263,
      "officialLanguages": "Hindi",
      "timeZones": "UTC+05:30",
      "status": "ACTIVE",
      "states": [
        {
          "stateId": 101,
          "stateCode": "AP",
          "stateName": "Andhra Pradesh",
          "stateType": "State",
          "stateCapital": "Amaravati",
          "statePopulation": 53903393,
          "stateAreaSqKm": 162968,
          "officialLanguages": "Telugu",
          "timezone": "UTC+05:30",
          "gstCode": "37",
          "status": "ACTIVE",
          "cities": [
            {
              "cityId": 1001,
              "cityCode": "TPT",
              "cityName": "Tirupati",
              "cityType": "Municipal Corporation",
              "cityPopulation": 374260,
              "cityAreaSqKm": 27.44,
              "pinCode": 517501,
              "status": "ACTIVE"
            }
          ]
        }
      ]
    }
  ]
}


```

## API Endpoints
### Country Endpoints

* **POST**  : 'http://localhost:8080/api/country/v1/create'
* **GET**   : 'http://localhost:8080/api/country/v1/contries'
* **GET**   : 'http://localhost:8080/api/country/v1/getCountryByCountryCode/{countryCode}'
* **GET**   : 'http://localhost:8080/api/country/v1/getCountryByCountryName/{countryName}'
* **PUT**   : 'http://localhost:8080/api/country/v1/updateCountryByCountryId/{countryId}'
* **DELETE**: 'http://localhost:8080/api/country/v1/deleteCountryByCountryId/{countryId}'

### State Endpoints

* **GET**   : 'http://localhost:8080/api/country/v1/getStates'
* **GET**   : 'http://localhost:8080/api/country/v1/getStatesByCountryId/{countryId}'
* **GET**   : 'http://localhost:8080/api/country/v1/getStateByStateCode/{stateCode}'
* **GET**	: 'http://localhost:8080/api/country/v1/getStateByStateName/{stateName}'	
* **GET**   : 'http://localhost:8080/api/country/v1/getStateByStateCapital/{stateCapital}'
* **PATCH** : 'http://localhost:8080/api/country/v1/updateStateByStateName/{stateId}/{newStateName}'	 
* **PATCH**	: 'http://localhost:8080/api/country/v1/updateStateByCapital/{stateId}/{newCapital}'	
* **DELETE**: 'http://localhost:8080/api/country/v1/deleteState/{stateId}'	

### City Endpoints

* **GET**   : 'http://localhost:8080/api/country/v1/getCities'
* **GET**   : 'http://localhost:8080/api/country/v1/getCitiesByStateId/{stateId}'
* **GET**   : 'http://localhost:8080/api/country/v1/getCitiesByCityCode/{cityCode}'