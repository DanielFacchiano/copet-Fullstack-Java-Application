$(document).ready(function () {
  bindUpdatePetButton();
  fillOlderDate();
});

let adopterIdHolder;

function fillOlderDate() {
  const urlParams = new URLSearchParams(window.location.search);
  const petId = urlParams.get("petId");

  $.ajax({
    type: "GET",
    url: "http://localhost:8080/api/pets/" + petId,
    success: function (pet) {
      console.log(pet);
      $("#petName").val(pet.petName);
      $("#species").val(pet.species);
      $("#description").val(pet.petDescription);
      $("#age").val(pet.age);
      $("#shelterId").val(pet.locationId);
      adopterIdHolder = pet.adopterId;
    },
    error: function () {},
  });
}

function bindUpdatePetButton() {
  console.log("what");
  const urlParams = new URLSearchParams(window.location.search);
  const petId = urlParams.get("petId");

  let signIn = $("#createPetButton");
  signIn.click(function () {
    let petNameText = $("#petName").val();
    let speciesText = $("#species").val();
    let petDescriptionText = $("#description").val();
    let petAgeText = $("#age").val();
    let petShelterId = $("#shelterId").val();
    let breedArr = [];

    $("input:checkbox[name=breedBox]:checked").each(function () {
      breedArr.push($(this).val());
    });
    breedArr = breedArr.map((breed) => {
      return parseInt(breed);
    });
    let newPetJson = {
      id: petId,
      petName: petNameText,
      species: speciesText,
      petDescription: petDescriptionText,
      age: petAgeText,
      locationId: petShelterId,
      breeds: breedArr,
      adopterId: adopterIdHolder,
    };
    if (
      newPetJson.petName == "" ||
      newPetJson.species == "" ||
      newPetJson.petDescription == "" ||
      newPetJson.age == "" ||
      isNaN(newPetJson.age) ||
      newPetJson.locationId == "" ||
      isNaN(newPetJson.locationId) ||
      (newPetJson.species.toLowerCase() != "dog" &&
        newPetJson.species.toLowerCase() != "cat")
    ) {
      document.getElementById("errorMessage").textContent =
        "Invalid Data (fields missing, non-numerics in number inputs, or species not Cat or Dog)";
      return;
    }

    $.ajax({
      data: JSON.stringify(newPetJson),
      contentType: "application/json",
      type: "PUT",
      url: "http://localhost:8080/api/pets/" + petId,
      success: function (pet) {
        window.location.href = "./pets.html";
      },
      error: function () {},
    });
  });
}
