const username = document.getElementById("username");
const firstName = document.getElementById("firstName");
const lastName = document.getElementById("lastName");
const email = document.getElementById("email");

const hiddenValue = document.getElementById("hidden").innerText;


fetch(`/profileInfo/${hiddenValue}`)
    .then(info => info.json())
    .then(infoArr => {
        username.innerText = infoArr[0];
        firstName.innerText = infoArr[1];
        lastName.innerText = infoArr[2];
        email.innerText = infoArr[3];
    });