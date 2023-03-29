$(document).ready(function () {
  bindAddPetButton();
});

function bindAddPetButton() {
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
      petName: petNameText,
      species: speciesText,
      petDescription: petDescriptionText,
      age: petAgeText,
      locationId: petShelterId,
      breeds: breedArr,
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
      type: "POST",
      url: "http://localhost:8080/api/pets",
      success: function (pet) {
        window.location.href = "./pets.html";
      },
      error: function () {},
    });
  });
}
