const btnBeginCensus = document.getElementById("beginCensusBtn");
const resetCensusBtn = document.getElementById("resetCensusBtn");
const summarySection = document.getElementById("profileSummary");
const displayName = document.getElementById("censusName");
const displayPopulation = document.getElementById("censusPopulation");
const displayAddress = document.getElementById("censusAddress");
const infoMessage = document.getElementById("infoMessage");
const thankYouMessage = document.getElementById("thankYouMessage");

let fullName = "";
let householdSize = "";
let address = "";

btnBeginCensus.addEventListener("click", function () {
	fullName = promptForName();
	if (fullName === null) return;

	householdSize = promptForHouseholdSize();
	if (householdSize === null) return;

	address = promptForAddress();
	if (address === null) return;

	let confirmDetails = confirm(
		`Confirm your details:\nName: ${fullName}\nHousehold Population: ${householdSize}\nAddress: ${address}`
	);
	if (confirmDetails) {
		displaySummary();
	} else {
		alert("Census process canceled.");
	}
});

resetCensusBtn.addEventListener("click", function () {
	fullName = "";
	householdSize = "";
	address = "";

	summarySection.classList.add("hidden");
	thankYouMessage.classList.add("hidden");
	infoMessage.classList.remove("hidden");
	btnBeginCensus.classList.remove("hidden");
	resetCensusBtn.classList.add("hidden");

	displayName.innerHTML = "";
	displayPopulation.innerHTML = "";
	displayAddress.innerHTML = "";
});

function promptForName() {
	while (true) {
		let name = prompt("Please enter the Complete Name:");
		if (name === null) return null;
		if (!name.trim() || containsDigits(name)) {
			alert("Complete Name is required and should not contain any numbers.");
		} else {
			return name;
		}
	}
}

function promptForHouseholdSize() {
	while (true) {
		let size = prompt(
			"Please enter the Household Population (must be a positive number):"
		);
		if (size === null) return null;
		if (!size.trim() || isNaN(size) || size <= 0) {
			alert("Household Population must be a valid positive number.");
		} else {
			return size;
		}
	}
}

function promptForAddress() {
	while (true) {
		let addr = prompt("Please enter the Address:");
		if (addr === null) return null;
		if (!addr.trim()) {
			alert("Address is required.");
		} else {
			return addr;
		}
	}
}

function containsDigits(str) {
	for (let char of str) {
		if (char >= "0" && char <= "9") {
			return true;
		}
	}
	return false;
}

function displaySummary() {
	displayName.innerHTML = `<b>Complete Name:</b> ${fullName}`;
	displayPopulation.innerHTML = `<b>Household Population:</b> ${householdSize}`;
	displayAddress.innerHTML = `<b>Address:</b> ${address}`;
	summarySection.classList.remove("hidden");
	thankYouMessage.classList.remove("hidden");
	infoMessage.classList.add("hidden");
	btnBeginCensus.classList.add("hidden");
	resetCensusBtn.classList.remove("hidden");
}
