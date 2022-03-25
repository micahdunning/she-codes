//search location
function search(event) {
  event.preventDefault();
  let cityElement = document.querySelector("#city");
  let cityInput = document.querySelector("#city-input");
  cityElement.innerHTML = cityInput.value;
}
let dateElement = document.querySelector("#time");
let currentTime = new Date();
let searchForm = document.querySelector("#box");
searchForm.addEventListener("submit", search);
dateElement.innerHTML = formatDate(currentTime);

//change city and weather
function formatCity(event) {
  event.preventDefault();
  let searchInput = document.querySelector("#city-search");
  let changeCity = document.querySelector("#change-city");
  changeCity.innerHTML = `${searchInput.value}`;
  let city = `${searchInput.value}`;

  let apiKey = "bf41fab864516655c6d534e86ae6a51d";
  let apiUrl = `https://api.openweathermap.org/data/2.5/weather?q=${city}&units=metric&appid=${apiKey}`;

  function displayWeatherInfo(response) {
    let cityElement = document.querySelector("#change-city");

    cityElement.innerHTML = response.data.name;

    let temperatureElement = document.querySelector("#temp");
    let temperature = Math.round(response.data.main.temp);
    temperatureElement.innerHTML = `${temperature}°`;

    let countryElement = document.querySelector("#country-code");
    let country = response.data.sys.country;
    countryElement.innerHTML = `, ${country}`;

    let weatherDescriptionElement = document.querySelector(
      "#weather-description"
    );
    let weatherDescription = response.data.weather[0].description;
    weatherDescriptionElement.innerHTML = `${weatherDescription}`;
  }

  axios.get(apiUrl).then(displayWeatherInfo);

  let form = document.querySelector("#city-input");
  form.addEventListener("submit", formatCity);
}

//current date and time
function formatDate(date) {
  let hours = date.getHours();
  if (hours < 10) {
    hours = `0${hours}`;
  }
  let minutes = date.getMinutes();
  if (minutes < 10) {
    minutes = `0${minutes}`;
  }
  let dayIndex = date.getDay();
  let days = [
    "Sunday",
    "Monday",
    "Tuesday",
    "Wednesday",
    "Thursday",
    "Friday",
    "Saturday"
  ];
  let day = days[dayIndex];
  return `${day} ${hours}:${minutes}`;
}
