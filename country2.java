let searchInputEl = document.getElementById("searchInput");
let spinnerEl = document.getElementById("spinner");
let resultCountriesEl = document.getElementById("resultCountries");
let searchEl = "";
let countryList = [];

function createCon(newCon) {
    let {
        flag,
        name,
        population
    } = newCon;

    let Con1 = document.createElement("div");
    Con1.classList.add("col-10", "col-md-5", "country-card", "d-flex", "flex-row", "ml-3", "mr-3")
    resultCountriesEl.appendChild(Con1);

    let Con2 = document.createElement("div");
    Con1.appendChild(Con2);

    let flag1 = document.createElement("img");
    flag1.classList.add("country-flag");
    flag1.src = flag;
    Con2.appendChild(flag1);

    let Con3 = document.createElement("div");
    Con3.classList.add("d-flex", "flex-column", "ml-5");
    Con1.appendChild(Con3);

    let countryName = document.createElement("p");
    countryName.classList.add("country-name");
    countryName.textContent = name;
    Con3.appendChild(countryName);

    let countryPop = document.createElement("p");
    countryPop.classList.add("country-population");
    countryPop.textContent = population;
    Con3.appendChild(countryPop);
}

function display() {
    resultCountriesEl.textContent = "";
    for (let okay of countryList) {
        let okayName = okay.name;
        if (okayName.includes(searchEl))
            createCon(okay);
    }
}

function startUp() {
    let options = {
        method: "GET",
    };

    spinnerEl.classList.remove("d-none");
    resultCountriesEl.classList.add("d-none");

    fetch("https://apis.ccbp.in/countries-data", options)
        .then(function(response) {
            return response.json();
        })
        .then(function(jsonText) {
            spinnerEl.classList.add("d-none");
            resultCountriesEl.classList.remove("d-none");
            countryList = jsonText;
            display();
        });
}

startUp();

function nowStart(event) {
    searchEl = event.target.value;
    display();
}

searchInputEl.addEventListener("keyup", nowStart);