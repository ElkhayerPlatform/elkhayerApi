package ma.youcode.elkhayer.services.ServiceInterfaces;


import ma.youcode.elkhayer.models.dtos.profileDto.ProfileDto;

public interface ProfileService {
    ProfileDto getProfileById(Long id);

    ProfileDto createProfile(ProfileDto profileDto);

    ProfileDto updateProfile(Long id, ProfileDto profileDto);

    ProfileDto getProfileOfUser(Long id);
}