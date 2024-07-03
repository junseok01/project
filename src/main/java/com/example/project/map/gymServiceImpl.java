package com.example.project.map;

import com.example.project.dto.Gym;
import com.example.project.dto.GymMapResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class gymServiceImpl implements gymService {
    private final gymDAO dao;
    @Override
    public List<GymMapResponseDTO> gymlist(float x, float y) {
        List<Gym> list = dao.gymlist(x,y);
        List<GymMapResponseDTO> gymlist = list.stream()
                .map(GymMapResponseDTO :: new)
                .collect(Collectors.toList());
        return gymlist;
    }

    @Override
    public List<GymMapResponseDTO> gymselectlist(String keyword, int pageNo) {
        Pattern p = Pattern.compile("^..구");
        Matcher matcher = p.matcher(keyword);
        List<Gym> list = null;
         if (keyword.startsWith("동대문") || keyword.startsWith("영등포") || keyword.startsWith("서대문") || matcher.find()) {
            String[] split = keyword.split("구");
            list = dao.gymselectaddrlist(split[0],pageNo);

        }else{
            list = dao.gymselectlist(keyword,pageNo);
        }
        List<GymMapResponseDTO> gymlist = list.stream()
                .map(GymMapResponseDTO :: new)
                .collect(Collectors.toList());
        return gymlist;
    }
}
