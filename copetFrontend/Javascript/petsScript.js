$(document).ready(function () {
  loadPets();
  console.log(localStorage.getItem("userName"));
  console.log(localStorage.getItem("isAdmin"));
  const isAdmin = localStorage.getItem("isAdmin");
  if (isAdmin === "false") {
    $(".addPetBtn").hide();
  }
});

let globalPets;

function selectFiler(obj) {
  let petsContainer = document.getElementById("petsContainer");
  while (petsContainer.firstChild) {
    petsContainer.removeChild(petsContainer.firstChild);
  }

  if (obj.value == "Texas") {
    let filteredPets = [...globalPets];
    filteredPets = filteredPets.filter((pet) => {
      if (pet.location.state == "Texas") {
        return true;
      } else return false;
    });
    insertPets(filteredPets);
  } else if (obj.value == "California") {
    let filteredPets = [...globalPets];
    filteredPets = filteredPets.filter((pet) => {
      if (pet.location.state == "California") {
        return true;
      } else return false;
    });
    insertPets(filteredPets);
  } else if (obj.value == "Florida") {
    let filteredPets = [...globalPets];
    filteredPets = filteredPets.filter((pet) => {
      if (pet.location.state == "Florida") {
        return true;
      } else return false;
    });
    insertPets(filteredPets);
  } else if (obj.value == "Dogs") {
    let filteredPets = [...globalPets];
    filteredPets = filteredPets.filter((pet) => {
      if (pet.species == "Dog" || pet.species == "dog") {
        return true;
      } else return false;
    });
    insertPets(filteredPets);
  } else if (obj.value == "Cats") {
    let filteredPets = [...globalPets];
    filteredPets = filteredPets.filter((pet) => {
      if (pet.species == "Cat" || pet.species == "cat") {
        return true;
      } else return false;
    });
    insertPets(filteredPets);
  } else if (obj.value == "Small") {
    let filteredPets = [...globalPets];
    filteredPets = filteredPets.filter((pet) => {
      let petsBreedsSizes = pet.breedList.map((breed) => {
        return breed.size;
      });
      if (
        petsBreedsSizes.includes("Small") ||
        petsBreedsSizes.includes("small")
      ) {
        return true;
      } else return false;
    });
    insertPets(filteredPets);
  } else if (obj.value == "Large") {
    let filteredPets = [...globalPets];
    filteredPets = filteredPets.filter((pet) => {
      let petsBreedsSizes = pet.breedList.map((breed) => {
        return breed.size;
      });
      if (petsBreedsSizes.includes("Large")) {
        return true;
      } else return false;
    });
    insertPets(filteredPets);
  } else if (obj.value == "Medium") {
    let filteredPets = [...globalPets];
    filteredPets = filteredPets.filter((pet) => {
      let petsBreedsSizes = pet.breedList.map((breed) => {
        return breed.size;
      });
      if (petsBreedsSizes.includes("Medium")) {
        return true;
      } else return false;
    });
    insertPets(filteredPets);
  } else {
    let filteredPets = [...globalPets];
    insertPets(filteredPets);
  }
}

function redirectToPet(event) {
  window.location.href = "./soloPet.html?" + "petId=" + event.currentTarget.id;
}

function insertPets(petsArray) {
  $.each(petsArray, function (index, pet) {
    let petCard = document.createElement("div");
    petCard.classList.add("petCardContainer");
    petCard.id = pet.id;
    let image = document.createElement("img");
    image.setAttribute("src", "./images/pet1.jpg");
    image.setAttribute("width", "250px");
    petCard.appendChild(image);
    let name = document.createElement("p");
    let nameNode = document.createTextNode(pet.petName);
    name.appendChild(nameNode);
    petCard.appendChild(name);
    let age = document.createElement("p");
    let ageNode = document.createTextNode("Age: " + pet.age);
    age.appendChild(ageNode);
    petCard.appendChild(age);
    let state = document.createElement("p");
    let stateNode = document.createTextNode("State: " + pet.location.state);
    state.appendChild(stateNode);
    petCard.appendChild(state);
    petCard.addEventListener("click", redirectToPet);
    let container = document.getElementById("petsContainer");
    container.appendChild(petCard);
  });
}

function loadPets() {
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/api/pets",
    success: function (petsArray) {
      let loadingText = document.getElementById("loadingText");
      loadingText.remove();
      globalPets = petsArray;
      insertPets(petsArray);
    },
    error: function () {},
  });
}
