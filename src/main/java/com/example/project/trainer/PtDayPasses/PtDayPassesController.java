package com.example.project.trainer.PtDayPasses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class PtDayPassesController {
    @Autowired
    private PtDayPassesService ptDayPassesService;
    @GetMapping("/trainerPTpage")
    public String PtPage(Model model){
        List<PtDayPassesResponseDTO> ptDayPasses = ptDayPassesService.getAllPtDayPasses();
        System.out.println("ptDayPasses === "+ptDayPasses);
        model.addAttribute("ptDayPasses", ptDayPasses);

        return "mypage/trainerPTPage";
    }
    @GetMapping("/clientPTpage")
    public String ClientPtPage(Model model){
        List<PtDayPassesResponseDTO> ptDayPasses = ptDayPassesService.getAllPtDayPasses();
        System.out.println("ptDayPasses === "+ptDayPasses);
        model.addAttribute("ptDayPasses", ptDayPasses);

        return "mypage/clientPTPage";
    }
    // 예약요청 컨트롤러
    @PostMapping("/ptDayPasses/reserve")
    @ResponseBody
    public ResponseEntity<String> reserve(@RequestBody PtDayPassesRequestDTO requestDTO){
        System.out.println("---------------------------------");
        System.out.println(requestDTO);
        String response = ptDayPassesService.reservePt(requestDTO);
        if (response.equals("failReserve")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }else {
            return ResponseEntity.ok(response);
        }
    }
    // 예약 목록 정보 불러오기
    @GetMapping("/ptDayPasses/getReservations")
    public ResponseEntity<List<PtDayPassesResponseDTO>> getReservations(){
        List<PtDayPassesResponseDTO> reservations = ptDayPassesService.getAllPtDayPasses();
        return ResponseEntity.ok(reservations);
    }
    // 예약 수락
    @PostMapping("/ptDayPasses/accept")
    @ResponseBody
    public ResponseEntity<String> acceptReservation(@RequestBody PtDayPassesRequestDTO requestDTO){
        try{
            ptDayPassesService.acceptPtDayPasses(requestDTO);
            return ResponseEntity.ok("예약 수락");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }
    // 예약 거절
    @PostMapping("/ptDayPasses/reject")
    @ResponseBody
    public ResponseEntity<String> rejectReservation(@RequestBody PtDayPassesRequestDTO requestDTO){
        try{
            ptDayPassesService.rejectPtDayPasses(requestDTO);
            return ResponseEntity.ok("예약 거절");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
        }
    }
    // 현 예약 상태
    @GetMapping("/ptDayPasses/checkStatus")
    public ResponseEntity<?> checkStatus(@RequestParam String trainerName,
                                         @RequestParam LocalDateTime startTime,
                                         @RequestParam LocalDateTime endTime){
        PtDayPassesEntity reservation = ptDayPassesService.reservationCheck(trainerName,startTime,endTime);
        if(reservation == null){
            // 해당 시간에 예약이 없으면 예약 가능
            return ResponseEntity.ok().body("예약가능");
        }else if(reservation.getStatus().equals("accept")){
            // 해당 시간에 예약이 차있는경우 (트레이너가 수락을 누르면 예약상태 accept)
            return ResponseEntity.ok().body("예약불가능");
        }else {
            // 누군가 예약을 했지만 트레이너가 아직 확인전 (보류)
            return ResponseEntity.ok().body("신청 확인중");
        }
    }
}
