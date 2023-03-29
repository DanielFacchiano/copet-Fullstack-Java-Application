package com.wolves.copet.service;

import com.wolves.copet.CopetApplication;
import com.wolves.copet.dto.Location;
import com.wolves.copet.dto.Pet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CopetApplication.class)
class CopetServiceImplTest {
    @Autowired
    CopetServiceImpl copetService;
    @Test
    void getAllPets() {
    }

    @Test
    void getPetById() {
    }

    @Test
    void createNewPet() {
        Pet invalidAge = new Pet();
        invalidAge.setAge(-12);

        Pet invalidSpecies = new Pet();
        invalidSpecies.setSpecies("Snake");

        Pet invalidName = new Pet();
        invalidName.setPetName("xXSN4K3_G0DXx");
        invalidName.setSpecies("Dog");

        List<Integer> breeds = new ArrayList<>();
        breeds.add(1);

        try {
            copetService.createNewPet(invalidAge,breeds);
        }catch(EntryDataInfoValidationException e){
            System.out.println("Error: "+e.getMessage());
            invalidAge.setPetDescription("Exception Caught");
        }
        try {
            copetService.createNewPet(invalidSpecies,breeds);
        }catch(EntryDataInfoValidationException e){
            System.out.println("Error: "+e.getMessage());
            invalidSpecies.setPetDescription("Exception Caught");
        }
        try {
            copetService.createNewPet(invalidName,breeds);
        }catch(EntryDataInfoValidationException e){
            System.out.println("Error: "+e.getMessage());
            invalidName.setPetDescription("Exception Caught");
        }


        assertTrue(invalidAge.getPetDescription().equals("Exception Caught"));
        assertTrue(invalidSpecies.getPetDescription().equals("Exception Caught"));
        assertTrue(invalidName.getPetDescription().equals("Exception Caught"));
    }

    @Test
    void updatePet() {
    }

    @Test
    void getAllLocations() {
    }

    @Test
    void getLocationById() {
    }

    @Test
    void updateLocationDetails() {

        Location updateLocation = new Location();
        updateLocation.setId(3);
        updateLocation.setLocationName("AnimalsAndMore");
        updateLocation.setContactInfo("Brenda: (512) 763-9832");
        updateLocation.setLocationDescription("Small animal shelter with limited cats and dogs.");

        try {
            Location finalizedLocation = copetService.updateLocationDetails(updateLocation);
            System.out.println(finalizedLocation);
        }catch (EntryDataInfoValidationException e){
            System.out.println("Error in updating location.\n"+e.getMessage());
        }

        //System.out.println(finalizedLocation);
    }
}