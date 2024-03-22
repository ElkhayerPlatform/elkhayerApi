package ma.youcode.elkhayer.services;

import lombok.RequiredArgsConstructor;
import ma.youcode.elkhayer.models.Profile;
import ma.youcode.elkhayer.models.User;
import ma.youcode.elkhayer.models.dtos.profileDto.ProfileDto;
import ma.youcode.elkhayer.repositories.ProfileRepository;
import ma.youcode.elkhayer.repositories.UserRepository;
import ma.youcode.elkhayer.services.ServiceInterfaces.ProfileService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImp implements ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public ProfileDto getProfileById(Long id) {
        Profile profile = profileRepository.findById(id).orElse(null);
        return modelMapper.map(profile, ProfileDto.class);
    }

    @Override
    public ProfileDto createProfile(ProfileDto profileDto) {
        Profile profile = modelMapper.map(profileDto, Profile.class);
        return modelMapper.map(profileRepository.save(profile), ProfileDto.class);
    }

    @Override
    public ProfileDto updateProfile(Long id, ProfileDto profileDto) {

        Profile profile = profileRepository.findById(id).orElse(null);

        if (profile != null) {
            profileRepository.save(modelMapper.map(profileDto, Profile.class));
        }

        return modelMapper.map(profile, ProfileDto.class);
    }

    @Override
    public ProfileDto getProfileOfUser(Long id) {
        if (profileRepository.findByUserId(id) == null) {
            return null;
        }

        User user = userRepository.findById(id).orElse(null);
        Profile profile = profileRepository.findByUserId(id);

        if (profile != null) {
            profile.setUser(user);
        }

        return modelMapper.map(profile, ProfileDto.class);
    }
}
