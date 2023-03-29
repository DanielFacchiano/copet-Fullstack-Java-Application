let dbPet = {};

$(document).ready(function () {
  loadInfo();
  bindUpdateBtn();
  bindAdoptButton();
  bindDeleteBtn();
  console.log(localStorage.getItem("isAdmin"));
  const isAdmin = localStorage.getItem("isAdmin");
  if (isAdmin === "false") {
    $(".adminButtons").hide();
  }
});

function bindAdoptButton() {
  const adoptButton = $("#adoptButton");
  const urlParams = new URLSearchParams(window.location.search);
  const petId = urlParams.get("petId");
  //console.log(dbPet)
  adoptButton.click(function () {
    const adopterId = localStorage.getItem("userId");
    dbPet.adopterId = parseInt(adopterId);
    console.log(dbPet);
    console.log(adopterId);
    $.ajax({
      type: "PUT",
      contentType: "application/json",
      url: `http://localhost:8080/api/pets/${petId}`,
      data: JSON.stringify(dbPet),
      success: function (good) {
        console.log(dbPet);
        console.log(good);
        if (good) {
          $(".soloButtonsContainer").hide();
        }
        location.reload();
      },
      error: function () {},
    });
  });
}
function bindUpdateBtn() {
  const urlParams = new URLSearchParams(window.location.search);
  const petId = urlParams.get("petId");
  updateBtn = $("#updateBtn");
  updateBtn.attr("href", "./updatePet.html?petId=" + petId);
}

function bindDeleteBtn() {
  const urlParams = new URLSearchParams(window.location.search);
  const petId = urlParams.get("petId");
  const deleteBtn = $("#deleteBtn");
  deleteBtn.click(function () {
    $.ajax({
      type: "DELETE",
      url: `http://localhost:8080/api/pets/${petId}`,
      success: function (pet) {
        window.location.href = "./pets.html";
      },
      error: function () {},
    });
  });
}

function loadInfo() {
  const urlParams = new URLSearchParams(window.location.search);
  const petId = urlParams.get("petId");

  $.ajax({
    type: "GET",
    url: `http://localhost:8080/api/pets/${petId}`,
    success: function (pet) {
      dbPet = pet;
      console.log(pet);
      const petNameTextElem = $(".petNameText");
      const locationTextElem = $(".locationText");
      const speciesTextElem = $(".speciesText");
      const breedElem = $(".breedText");
      const descriptionTextElem = $(".descriptionText");
      const reservedTextElem = $(".reservedText");
      //console.log(pet);

      if (pet.adopterId != 0 && pet.adopterId != null) {
        reservedTextElem.text("Status: reserved");
      } else {
        reservedTextElem.text("Status: available");
      }
      petNameTextElem.text(pet.petName);
      locationTextElem.text("Location: " + pet.location.state);
      speciesTextElem.text("Species: " + pet.species);
      breedElem.text(
        "Breeds:" +
          pet.breedList.map((breed) => {
            return " " + breed.breedName;
          })
      );
      descriptionTextElem.text("Description: " + pet.petDescription);

      const isAdopted = pet.adopterId;

      if (isAdopted !== 0) {
        $(".soloButtonsContainer").hide();
      }
    },
    error: function () {},
  });
}
