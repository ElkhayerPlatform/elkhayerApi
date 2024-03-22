package ma.youcode.elkhayer.controllers;

import ma.youcode.elkhayer.models.dtos.profileDto.ProfileDto;
import ma.youcode.elkhayer.services.ServiceInterfaces.ProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService){
        this.profileService = profileService;
    }

    @GetMapping("/{id}")
    public ProfileDto getProfileById(@PathVariable Long id){

        return profileService.getProfileById(id);
    }

    @GetMapping("/user/{id}")
    public ProfileDto getProfileOfUser(@PathVariable Long id){

        return profileService.getProfileOfUser(id);
    }

    @PostMapping("create")
    public ProfileDto createProfile(@RequestBody ProfileDto profileDto){

        return profileService.createProfile(profileDto);
    }

    @PutMapping("/{id}")
    public ProfileDto updateProfile(@PathVariable Long id, @RequestBody ProfileDto profileDto){

        return profileService.updateProfile(id, profileDto);
    }
}
