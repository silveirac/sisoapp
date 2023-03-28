let dentistName = document.getElementById("dentist-NameInput");
let dentistLastName = document.getElementById("dentist-LastNameInput");
let dentistRegistration = document.getElementById("dentist-RegistrationInput");
let registrationRegex = new RegExp (`^[0-9]{6}$`)
let nameRegex = `^[a-zA-Zà-úÀ-Ú\s]$`;

dentistName.addEventListener("keyup", (e) => {
  if (dentistName.value == nameRegex) {
    console.log("Por favor insira um nome válido");
  } else {
    console.log("Nome Aceito!");
  }
});

dentistLastName.addEventListener("keyup", (e) => {
  if (dentistLastName.value == nameRegex) {
    console.log("Por favor insira um sobrenome válido");
  } else {
    console.log("Nome Aceito!");
  }
});

dentistRegistration.addEventListener("keyup", (e) => {
  if (registrationRegex.test(dentistRegistration.value)) {
    console.log("Matricula Aceita!");
  } else {
    console.log("Por favor insira uma matricula válida!");
  }
});
